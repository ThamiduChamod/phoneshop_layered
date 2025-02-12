package lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom;

import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.CrudDAO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<Customer> {

    ArrayList<String> getAllCustomerNames() throws SQLException;
    String getCustomerIdFindByName (String customerName) throws SQLException;
   // boolean updateCustomer(CustomerDTO customerDTO) throws SQLException;
}
