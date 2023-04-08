package org.example.dao.daoMySQL;

import org.example.dao.DaoFactory;
import org.example.dao.IDAO;
import org.example.modele.Client;
import org.example.modele.Credit;
import org.example.modele.Utulisateur;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySqlSessionFactory extends DaoFactory {

    private static MySqlSessionFactory INSTANCE=null;
    private static Connection session=null;
    private static IDAO<Client,Long> CLIENT_DAO=null;
    private static IDAO<Credit,Long> CREDIT_DAO=null;
    private static IDAO<Utulisateur,Long> USER_DAO=null;
    private MySqlSessionFactory() throws SQLException, IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        ClassLoader cl=Thread.currentThread().getContextClassLoader();
        var config=cl.getResourceAsStream("application.properties");

        if(config==null) throw new IOException("fichier properties introuvable");
        Properties propertiesFile=new Properties();
        propertiesFile.load(config);
        var url=propertiesFile.getProperty("URL");
        var user=propertiesFile.getProperty("USERNAME");
        var pass=propertiesFile.getProperty("PASSWORD");

        session= DriverManager.getConnection(url,user,pass);
        System.out.println("connection établit avec succés");

        var creditDao=propertiesFile.getProperty("CREDITDAO");
        var clientDao=propertiesFile.getProperty("CLIENTDAO");
        var utilisateurDao=propertiesFile.getProperty("USERDAO");

        Class cCreeditDao=Class.forName(creditDao);
        Class cClientDao=Class.forName(clientDao);
        Class cUserDao=Class.forName(utilisateurDao);

        CREDIT_DAO=(IDAO<Credit, Long>) cCreeditDao.getDeclaredConstructor().newInstance();
        CLIENT_DAO=(IDAO<Client, Long>) cClientDao.getDeclaredConstructor().newInstance();
        USER_DAO=(IDAO<Utulisateur, Long>) cUserDao.getDeclaredConstructor().newInstance();

        Method setFactory=cCreeditDao.getMethod("setFactory",MySqlSessionFactory.class);

        setFactory.invoke(CREDIT_DAO,this);


       setFactory=cClientDao.getMethod("setFactory",MySqlSessionFactory.class);

        setFactory.invoke(CLIENT_DAO,this);


        setFactory=cUserDao.getMethod("setFactory",MySqlSessionFactory.class);
        setFactory.invoke(USER_DAO,this);

    }
    public static MySqlSessionFactory getINSTANCE() throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if(INSTANCE==null)
        {
            INSTANCE=new MySqlSessionFactory();
        }
        return INSTANCE;
    }
    public static Connection getSession() throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if(session==null) getINSTANCE();
        return session;
    }


    @Override
    public IDAO<Utulisateur, Long> getUtilisateurDao() throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
     if(USER_DAO==null) getINSTANCE();
     return USER_DAO;
    }

    @Override
    public IDAO<Client, Long> getClientDao() throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if(CLIENT_DAO==null) getINSTANCE();
        return CLIENT_DAO;
    }

    @Override
    public IDAO<Credit, Long> getCreditDao() throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if(CREDIT_DAO==null) getINSTANCE();
        return CREDIT_DAO;
    }
    public static void closeSession(){
        if(session!=null){
            try{session.close(); System.out.println("fermeture de session avec succés");}
            catch (SQLException e){System.err.println("fermuture de session échoué");}
        }
    }

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        getINSTANCE().getClientDao().findById(1L);}


}
