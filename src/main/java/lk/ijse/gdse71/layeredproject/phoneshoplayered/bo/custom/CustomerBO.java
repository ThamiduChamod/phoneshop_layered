package lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom;

import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.SuperBo;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.CustomerDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBo {

     boolean deleteCustomer(String customerId) throws SQLException, ClassNotFoundException;
     boolean saveCustomer (CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
     String getNextCustomerId() throws SQLException, ClassNotFoundException;
     ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;
     boolean updateCustomer (CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
}
