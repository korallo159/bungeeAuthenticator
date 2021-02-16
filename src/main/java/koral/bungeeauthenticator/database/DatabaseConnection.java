package koral.bungeeauthenticator.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import koral.bungeeauthenticator.BungeeAuthenticator;

public class DatabaseConnection {

    public static HikariDataSource hikari;

    public static void configureDbConnection() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(BungeeAuthenticator.getInstance().getConfig().getString("jdbcurl"));
        hikariConfig.setMaxLifetime(900000); // zeby uniknac wiekszy lifetime hikari niz mysql
        hikariConfig.addDataSourceProperty("user", BungeeAuthenticator.getInstance().getConfig().getString("username"));
        hikariConfig.addDataSourceProperty("password",BungeeAuthenticator.getInstance().getConfig().getString("password"));
        hikariConfig.addDataSourceProperty( "cachePrepStmts" , "true" ); //pozwala lepiej wspolpracowac z prepared statements
        hikariConfig.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        hikariConfig.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        hikari = new HikariDataSource(hikariConfig);
    }
}
