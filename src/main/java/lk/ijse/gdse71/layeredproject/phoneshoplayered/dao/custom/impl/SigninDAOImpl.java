package lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.impl;

import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.SQLUtil;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.SigninDAO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.SigninDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Signin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SigninDAOImpl implements SigninDAO {

    @Override
    public Signin getAllUsingID(String userId) throws SQLException, ClassNotFoundException {


        ResultSet rst = SQLUtil.execute("SELECT * FROM user WHERE id = ?", userId);
        Signin signinDTO = new Signin();
        try {
            // Move the cursor to the first row
            if (rst.next()) {
                // Now you can safely access the columns
//                System.out.println("User ID: " + rst.getString(1)); // Column 1
//                System.out.println("Password: " + rst.getString(2)); // Column 2
//                System.out.println("Other Field 1: " + rst.getString(3)); // Column 3
//                System.out.println("Other Field 2: " + rst.getString(4)); // Column 4
//                System.out.println("Other Field 3: " + rst.getString(5)); // Column 5

                signinDTO.setUserID(rst.getString(1));
                signinDTO.setUserName(rst.getString(2));
                signinDTO.setNic(rst.getString(3));
                signinDTO.setPhoneNumber(rst.getString(4));
                signinDTO.setEmail(rst.getString(5));
                signinDTO.setPassword(rst.getString(6));



            } else {
                System.out.println("No user found with the specified ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error retrieving user details: " + e.getMessage());
        }
        return signinDTO;
    }

    @Override
    public String getNext () throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT id FROM user order by id desc limit 1;");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("U%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "U001";
    }

    @Override
    public ArrayList<Signin> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Signin signinDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO user VALUES (?,?,?,?,?,?);",
                signinDTO.getUserID(),
                signinDTO.getUserName(),
                signinDTO.getNic(),
                signinDTO.getPhoneNumber(),
                signinDTO.getEmail(),
                signinDTO.getPassword()
        );
    }

    @Override
    public String getOldPassWord (String oldPassWord) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT password FROM user WHERE id=?;",
                oldPassWord
        );
        if (rst.next()){
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public boolean update(Signin signinDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE user SET password =? WHERE id=(?)",
                signinDTO.getPassword(),
                signinDTO.getUserID()
        );


    }

    @Override
    public boolean delete (String Id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM user WHERE id =?",
                Id
        );
    }

}
