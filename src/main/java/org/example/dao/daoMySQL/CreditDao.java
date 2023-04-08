package org.example.dao.daoMySQL;

import com.mysql.cj.Session;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dao.DaoFactory;
import org.example.dao.IDAO;
import org.example.modele.Client;
import org.example.modele.Credit;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.List;

@Component("daoCredit")
@Data @AllArgsConstructor @NoArgsConstructor
public class CreditDao implements IDAO<Credit,Long> {


    MySqlSessionFactory factory;
    @Override
    public Credit findById(Long idcredit) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        Credit credit=null;

        Connection session=factory.getSession();

        PreparedStatement ps=null;
        ResultSet rs=null;
        String SQL="SELECT * FROM credit WHERE id=?";

        ps=Utilitaire.initPS(session,SQL,false,idcredit);

        rs=ps.executeQuery();
        if(rs.next()) credit=map(rs);
        System.out.println("[SQL] : "+SQL);

        return credit;
    }


    @Override
    public List<Credit> findAll() {
        return null;
    }

    @Override
    public Credit save(Credit credit) {
        return null;
    }

    @Override
    public Credit update(Credit credit) {
        return null;
    }

    @Override
    public Boolean delete(Credit credit) {
        return null;
    }

    @Override
    public Boolean deleteById(Long aLong) {
        return null;
    }
    public Credit map(ResultSet rs) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        long id =rs.getLong("id");
        double capital=rs.getDouble("capital");
        int nbrMois =rs.getInt("nbrMois");
        double taux=rs.getDouble("taux");
        long idClient=rs.getLong("demandeur");
        double mensualite=rs.getDouble("mensualite");
      factory.getClientDao().findById(idClient);

        return new Credit(id,capital,nbrMois,taux,null,mensualite);
    }
}
