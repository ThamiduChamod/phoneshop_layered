package lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom;

import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.SuperBo;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.SigninDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Signin;

import java.sql.SQLException;

public interface SignInBO extends SuperBo {

    public String getNextUserID() throws SQLException, ClassNotFoundException;
    public boolean saveUser (Signin signinDTO) throws SQLException, ClassNotFoundException;

}
