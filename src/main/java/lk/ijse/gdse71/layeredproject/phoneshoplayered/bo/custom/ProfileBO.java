package lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom;

import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.SuperBo;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Signin;

import java.sql.SQLException;

public interface ProfileBO extends SuperBo {

     String getOldPassword(String ID) throws SQLException, ClassNotFoundException;
     boolean signInUpdate (Signin signin) throws SQLException, ClassNotFoundException;
     boolean deleteUser (String ID) throws SQLException, ClassNotFoundException;
     Signin getAllUsingID(String ID) throws SQLException, ClassNotFoundException;
}
