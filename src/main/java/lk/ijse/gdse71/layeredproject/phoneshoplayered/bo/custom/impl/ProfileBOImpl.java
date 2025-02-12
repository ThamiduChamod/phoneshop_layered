package lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom.impl;

import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom.ProfileBO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.DAOFactory;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.SigninDAO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Signin;

import java.sql.SQLException;

public class ProfileBOImpl implements ProfileBO {

    SigninDAO signinDAO = (SigninDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.SIGNIN);

    public String getOldPassword(String ID) throws SQLException, ClassNotFoundException {
        return signinDAO.getOldPassWord(ID);
    }

    public boolean signInUpdate (Signin signin) throws SQLException, ClassNotFoundException {
        return signinDAO.update(signin);
    }

    public boolean deleteUser (String ID) throws SQLException, ClassNotFoundException {
        return signinDAO.delete(ID);
    }

    public Signin getAllUsingID(String ID) throws SQLException, ClassNotFoundException {
        return signinDAO.getAllUsingID(ID);
    }
}
