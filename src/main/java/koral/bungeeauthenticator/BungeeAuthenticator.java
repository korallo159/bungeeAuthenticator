package koral.bungeeauthenticator;

import koral.bungeeauthenticator.database.DatabaseConnection;
import koral.bungeeauthenticator.listeners.Authorizer;
import koral.bungeeauthenticator.listeners.MainBungeeListener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public final class BungeeAuthenticator extends Plugin {

    public static BungeeAuthenticator proxy;
    public static Configuration config;


    @Override
    public void onEnable() {
        proxy = this;

        createConfig();
        DatabaseConnection.configureDbConnection();

        if(getConfig().getBoolean("isConfirmServer"))
            getProxy().getPluginManager().registerListener(this, new Authorizer());
        else
            getProxy().getPluginManager().registerListener(this, new MainBungeeListener());
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void createConfig(){
        if (!getDataFolder().exists()) getDataFolder().mkdir();
        File file = new File(getDataFolder(), "config.yml");
        try {
            if (!file.exists())
                Files.copy(getResourceAsStream("config.yml"), file.toPath());

            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static BungeeAuthenticator getInstance() {
        return proxy;
    }

    public static Configuration getConfig() {
        return config;
    }
}
