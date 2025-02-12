package lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.impl;

import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.SQLUtil;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.OrderDAO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.OrderDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.PaymentDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public String getNext() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT order_id FROM orders order by order_id desc limit 1;");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("O%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "O001";    }

    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save  (Order DTO) throws SQLException {
        return SQLUtil.execute("INSERT INTO orders VALUES (?,?,?,?)",
                DTO.getOrderID(),
                DTO.getOrderDate(),
                DTO.getPaymentID(),
                DTO.getCustomerID()

        );
    }

    @Override
    public boolean update(Order DTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String Id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
