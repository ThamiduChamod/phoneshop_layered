package lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom.impl;

import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom.CustomerBO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.DAOFactory;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.CustomerDAO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.CustomerDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CUSTOMER);

    public boolean deleteCustomer(String customerId) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(customerId);
    }

    public boolean saveCustomer (Customer customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.save(customerDTO);
    }

    public String getNextCustomerId() throws SQLException, ClassNotFoundException {
        return customerDAO.getNext();
    }
    public ArrayList<Customer> getAllCustomers() throws SQLException, ClassNotFoundException {
        return customerDAO.getAll();
    }

    public boolean updateCustomer (Customer customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.update(customerDTO);
    }
}
