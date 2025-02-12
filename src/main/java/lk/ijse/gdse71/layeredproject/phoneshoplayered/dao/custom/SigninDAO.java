package lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom;

import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.CrudDAO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Signin;

import java.sql.SQLException;

public interface SigninDAO extends CrudDAO<Signin> {

    Signin getAllUsingID(String userId) throws SQLException, ClassNotFoundException;
    String getOldPassWord (String oldPassWord) throws SQLException, ClassNotFoundException;



}
