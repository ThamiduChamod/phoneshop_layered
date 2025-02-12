package lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.impl;

import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.SQLUtil;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.PaymentDAO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.PaymentDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO  {

    @Override
    public String getNext () throws SQLException {

        ResultSet rst = SQLUtil.execute("SELECT payment_id FROM payment ORDER BY payment_id desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("P%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "P001";
    }

    @Override
    public ArrayList<Payment> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Payment DTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO payment VALUES (?,?,?)",
                DTO.getPaymentID(),
                DTO.getDate(),
                DTO.getTotal()
        );
    }

    @Override
    public boolean update(Payment DTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String Id) throws SQLException, ClassNotFoundException {
        return false;
    }


}

