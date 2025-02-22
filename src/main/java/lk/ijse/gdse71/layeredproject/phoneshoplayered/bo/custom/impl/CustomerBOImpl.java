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

    @Override
    public boolean deleteCustomer(String customerId) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(customerId);
    }

    @Override
    public boolean saveCustomer (CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(customerDTO.getCustomerID(),customerDTO.getCustomerName(),customerDTO.getNic(),customerDTO.getCustomerPhone(),customerDTO.getCustomerEmail()));
    }

    @Override
    public String getNextCustomerId() throws SQLException, ClassNotFoundException {
        return customerDAO.getNext();
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> all = customerDAO.getAll();
        ArrayList<CustomerDTO> customerDTOs = new ArrayList<>();
        for (Customer customer : all) {
            CustomerDTO customerDTO = new CustomerDTO(
                    customer.getCustomerID(),
                    customer.getCustomerName(),
                    customer.getNic(),
                    customer.getCustomerPhone(),
                    customer.getCustomerEmail()
            );
            customerDTOs.add(customerDTO);
        }
        return customerDTOs;
    }

    @Override
    public boolean updateCustomer (CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(customerDTO.getCustomerID(),customerDTO.getCustomerName(),customerDTO.getNic(),customerDTO.getCustomerPhone(),customerDTO.getCustomerEmail()));
    }
}
