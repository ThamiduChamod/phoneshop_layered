package lk.ijse.gdse71.layeredproject.phoneshoplayered.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO {
    String getNext () throws SQLException, ClassNotFoundException;
    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
     boolean save(T DTO) throws SQLException, ClassNotFoundException;
     boolean update(T DTO) throws SQLException, ClassNotFoundException;
     boolean delete(String Id) throws SQLException, ClassNotFoundException;
}
