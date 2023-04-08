package org.example.dao.daoMySQL;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dao.DaoFactory;
import org.example.dao.IDAO;
import org.example.modele.Utulisateur;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurDao implements IDAO<Utulisateur,Long> {
    MySqlSessionFactory factory;

    @Override
    public Utulisateur findById(Long aLong) {
        return null;
    }

    @Override
    public List<Utulisateur> findAll() {
        return null;
    }

    @Override
    public Utulisateur save(Utulisateur utulisateur) {
        return null;
    }

    @Override
    public Utulisateur update(Utulisateur utulisateur) {
        return null;
    }

    @Override
    public Boolean delete(Utulisateur utulisateur) {
        return null;
    }

    @Override
    public Boolean deleteById(Long aLong) {
        return null;
    }
}
