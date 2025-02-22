package lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom.impl;

import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom.LoginBO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.DAOFactory;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.LoginDAO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.LoginDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Login;

import java.sql.SQLException;
import java.util.ArrayList;

public class LoginBOImpl implements LoginBO {

    LoginDAO loginDAO = (LoginDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.LOGIN);

    @Override
    public ArrayList<LoginDTO> getAllLogins() throws SQLException, ClassNotFoundException {
        ArrayList<Login> all = loginDAO.getAll();

        ArrayList<LoginDTO> loginDTOs = new ArrayList<>();

        for (Login login : all) {
            LoginDTO loginDTO = new LoginDTO(
                    login.getUserID(),
                    login.getPassword()
            );
            loginDTOs.add(loginDTO);

        }
        return loginDTOs;
    }
}
