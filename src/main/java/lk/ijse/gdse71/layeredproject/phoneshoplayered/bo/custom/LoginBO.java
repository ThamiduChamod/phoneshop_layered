package lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom;

import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.SuperBo;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.LoginDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Login;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LoginBO extends SuperBo {


    public ArrayList<Login> getAllLogins() throws SQLException, ClassNotFoundException;
}
