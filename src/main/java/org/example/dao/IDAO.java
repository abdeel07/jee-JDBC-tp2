package org.example.dao;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface IDAO <T,ID>{
    T findById (ID id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
    List<T> findAll();
    T save(T t);
    T update(T t);
    Boolean delete(T t);
    Boolean deleteById(ID id);

}
