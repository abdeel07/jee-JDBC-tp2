package org.example;

import org.example.modele.Client;
import org.example.modele.Credit;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        var credits=new ArrayList<Credit>();
        Connection connection=Singleton.getSession();


       // var statement =connection.createStatement();
       var ps=connection.prepareStatement("SELECT cr.id,cr.capital,cr.nbrMois,cr.taux,cr.demandeur,cr.mensualite,u.nom,u.prenom" +
                " FROM credit cr,client cl,utilisateur u WHERE cr.demandeur = cl.id and cl.id = u.id;");
        var rs=ps.executeQuery();
        while (rs.next()){
            var id=rs.getLong("id");
            var capital =rs.getDouble("capital");
            var nbrMois=rs.getInt("nbrMois");
            var taux=rs.getDouble("taux");
            var nomDemandeur=rs.getString("nom");
            var prenomDemandeur=rs.getString("prenom");
            var mensualité =rs.getDouble("mensualite");

            var client=new Client(); client.setNom(nomDemandeur); client.setPrenom(prenomDemandeur);
            credits.add(new Credit(id,capital,nbrMois,taux,client,mensualité));
        }
        if (credits.isEmpty()) throw new SQLException("Aucun crédit trouvé");
        else credits.forEach(System.out::println);





    }
}