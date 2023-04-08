package org.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Singleton {
    private static Singleton INSTANCE=null;
    private static Connection session=null;

    private Singleton(){
        try {
            ClassLoader cl=Thread.currentThread().getContextClassLoader();
            var config=cl.getResourceAsStream("application.properties");

            if(config==null) throw new IOException("fichier properties introuvable");
            Properties properties=new Properties();
            properties.load(config);
            var url=properties.getProperty("URL");
            var user=properties.getProperty("USERNAME");
            var pass=properties.getProperty("PASSWORD");

            session= DriverManager.getConnection(url,user,pass);
            System.out.println("connection établit avec succés");
        }catch (IOException e){e.printStackTrace();}
        catch (SQLException e){System.err.println("connexion échoué");}
    }
    public static Singleton getINSTANCE()
    {
        if(INSTANCE==null) INSTANCE=new Singleton();
        return INSTANCE;
    }

    public static Connection getSession(){
        if(session==null) getINSTANCE();
        return session;
    }

    public static void closeSession(){
        if(session!=null){
            try {
                session.close();
                System.out.println("fermeture de session avec succés");
            } catch (SQLException e) {
                System.err.println("fermeture de session échoué");
            }
        }
    }
}
