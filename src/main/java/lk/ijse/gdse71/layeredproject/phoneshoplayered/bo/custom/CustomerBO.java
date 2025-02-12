package lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom;

import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.SuperBo;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBo {

     boolean deleteCustomer(String customerId) throws SQLException, ClassNotFoundException;
     boolean saveCustomer (Customer customerDTO) throws SQLException, ClassNotFoundException;
     String getNextCustomerId() throws SQLException, ClassNotFoundException;
     ArrayList<Customer> getAllCustomers() throws SQLException, ClassNotFoundException;
     boolean updateCustomer (Customer customerDTO) throws SQLException, ClassNotFoundException;
}
