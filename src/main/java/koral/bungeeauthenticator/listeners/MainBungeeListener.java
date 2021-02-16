package koral.bungeeauthenticator.listeners;

import koral.bungeeauthenticator.database.Statements;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class MainBungeeListener implements Listener {

    @EventHandler
    public void onPreLogin(PreLoginEvent e){
        if(Statements.isPremium(e.getConnection().getName())) {
            e.getConnection().setOnlineMode(true);
        }
    }

}
