package koral.bungeeauthenticator.listeners;

import koral.bungeeauthenticator.BungeeAuthenticator;
import koral.bungeeauthenticator.database.Statements;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class Authorizer implements Listener {

    @EventHandler
    public void onPreLogin(PreLoginEvent ev){
        ev.getConnection().setOnlineMode(true);
    }
    //TODO: sprawdzic czy kazdy osobny event jest async.
    @EventHandler
    public void loginEv(LoginEvent ev){
                  if(Statements.isPremium(ev.getConnection().getName())){
                    ev.setCancelReason(new TextComponent(BungeeAuthenticator.getConfig().getString("hasPremium")));
                    }
                  else if(Statements.isRegistered(ev.getConnection().getName())){
                    ev.setCancelReason(new TextComponent(BungeeAuthenticator.getConfig().getString("hasAccount")));
                  }
                  else{
                      Statements.updatePremium(ev.getConnection().getName());
                      ev.setCancelReason(new TextComponent(BungeeAuthenticator.getConfig().getString("success")));
                  }
        ev.setCancelled(true);
    }


}
