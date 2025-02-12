package lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.impl;

import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.SQLUtil;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.CustomerDAO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.CustomerDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public String getNext () throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT cust_id FROM customer order by cust_id desc limit 1;");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("C%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "C001";
    }

    @Override
    public boolean save(Customer customerDTO) throws SQLException {

        return SQLUtil.execute("INSERT into customer VALUES (?,?,?,?,?)",
                customerDTO.getCustomerID(),
                customerDTO.getCustomerName(),
                customerDTO.getNic(),
                customerDTO.getCustomerEmail(),
                customerDTO.getCustomerPhone()
        );

    }

    @Override
    public ArrayList<Customer> getAll() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM customer");

        ArrayList<Customer> customers = new ArrayList<>();

        while (rst.next()) {
            Customer customer = new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );

            customers.add(customer);
        }
        return customers;
    }

    @Override
    public boolean delete(String Id) throws SQLException {
        return SQLUtil.execute("delete from customer where cust_id=?", Id);
    }

    @Override
    public ArrayList<String> getAllCustomerNames() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT name FROM customer");

        ArrayList<String> customerNames = new ArrayList<>();
        while (rst.next()) {

            customerNames.add(rst.getString(1));

        }
        return customerNames;
    }

    @Override
    public String getCustomerIdFindByName (String customerName) throws SQLException  {
        ResultSet rst = SQLUtil.execute("SELECT cust_id FROM customer WHERE name=?", customerName);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public boolean update(Customer customerDTO) throws SQLException {
        return SQLUtil.execute("UPDATE customer SET name =?,nic=?,email=?,contact_number=?WHERE cust_id=?",
                customerDTO.getCustomerName(),
                customerDTO.getNic(),
                customerDTO.getCustomerEmail(),
                customerDTO.getCustomerPhone(),
                customerDTO.getCustomerID()
        );

    }
}
