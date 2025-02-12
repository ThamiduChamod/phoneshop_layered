package lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom.impl;

import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom.SignInBO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.DAOFactory;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.SigninDAO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.SigninDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Signin;

import java.sql.SQLException;

public class SignInBOImpl implements SignInBO {

    SigninDAO signinDAO = (SigninDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.SIGNIN);

    @Override
    public String getNextUserID () throws SQLException, ClassNotFoundException {
        return signinDAO.getNext();
    }

    @Override
    public boolean saveUser (Signin signinDTO) throws SQLException, ClassNotFoundException {
        return signinDAO.save(signinDTO);
    }

}
