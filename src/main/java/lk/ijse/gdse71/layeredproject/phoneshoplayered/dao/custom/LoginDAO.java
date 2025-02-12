package lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom;

import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.CrudDAO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.LoginDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Login;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LoginDAO extends CrudDAO<Login>  {
    ArrayList<Login> getAll() throws SQLException, ClassNotFoundException;
}
