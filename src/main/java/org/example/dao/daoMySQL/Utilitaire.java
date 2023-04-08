package org.example.dao.daoMySQL;

import java.sql.*;

public class Utilitaire {
    public static PreparedStatement initPS(Connection CNX,String SQL,boolean generateKey,Object... Columns) throws SQLException {
        PreparedStatement ps=null;
        ps=CNX.prepareStatement(SQL,generateKey ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
        for(int i=0 ;i<Columns.length;i++)
        {
            ps.setObject(i+1,Columns[i]);
        }
        return ps;
    }
    public static void close(PreparedStatement ps)
    {
        if(ps!=null)
        {
            try{ps.close();
                System.out.println("fermeture de l'objet statement avec succés");}
            catch (SQLException e){System.err.println("fermeture de statement échoué");}
        }
    }
    public static void close(ResultSet rs)
    {
        if(rs!=null)
        {
            try{rs.close();
                System.out.println("fermeture de l'objet resultset avec succés");}
            catch (SQLException e){System.err.println("fermeture de resultset échoué");}
        }
    }
    public static void close(PreparedStatement ps,ResultSet rs){close(ps);close(rs);}
}
