package org.example.dao.daoMySQL;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dao.DaoFactory;
import org.example.dao.IDAO;
import org.example.modele.Client;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDao implements IDAO <Client,Long>{
    MySqlSessionFactory factory;

    @Override
    public Client findById(Long aLong) {
        return null;
    }

    @Override
    public List<Client> findAll() {
        return null;
    }

    @Override
    public Client save(Client client) {
        return null;
    }

    @Override
    public Client update(Client client) {
        return null;
    }

    @Override
    public Boolean delete(Client client) {
        return null;
    }

    @Override
    public Boolean deleteById(Long aLong) {
        return null;
    }
}
