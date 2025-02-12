package lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.impl;

import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.SQLUtil;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.LoginDAO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.LoginDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Login;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginDAOImpl implements LoginDAO {

    @Override
    public String getNext() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public ArrayList<Login> getAll() throws SQLException, ClassNotFoundException {


        ResultSet rst = SQLUtil.execute("SELECT id,passWord FROM user");

        ArrayList<Login> loginDTOS = new ArrayList<>();

        while (rst.next()) {
            Login loginDTO = new Login(
                    rst.getString(1),
                    rst.getString(2)
            );


            loginDTOS.add(loginDTO);
        }
        return loginDTOS;
    }

    @Override
    public boolean save(Login DTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Login DTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String Id) throws SQLException, ClassNotFoundException {
        return false;
    }

}
