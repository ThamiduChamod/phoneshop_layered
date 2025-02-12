package lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom.impl;

import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom.LoginBO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.DAOFactory;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.LoginDAO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Login;

import java.sql.SQLException;
import java.util.ArrayList;

public class LoginBOImpl implements LoginBO {

    LoginDAO loginDAO = (LoginDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.LOGIN);

    public ArrayList<Login> getAllLogins() throws SQLException, ClassNotFoundException {
        return loginDAO.getAll();
    }
}
