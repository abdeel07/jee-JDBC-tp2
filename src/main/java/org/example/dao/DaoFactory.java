package org.example.dao;

import org.example.dao.daoMySQL.MySqlSessionFactory;
import org.example.modele.Client;
import org.example.modele.Credit;
import org.example.modele.Utulisateur;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public abstract class DaoFactory {
    public static final int MYSQL_DATA_UNIT=1,FILE_DATA_UNIT=2,InMemory_DATA_UNIT=3;

    public abstract IDAO<Utulisateur,Long> getUtilisateurDao() throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
    public abstract IDAO<Client,Long> getClientDao() throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
    public abstract IDAO<Credit,Long> getCreditDao() throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    //pour instancier la bonne fabrique
    public static final DaoFactory getDaoFactory(int factoryType) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        switch (factoryType)
        {
            case 1:
                MySqlSessionFactory.getINSTANCE();
            default: return null;
        }
    }
}
