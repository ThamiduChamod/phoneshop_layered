package lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom.impl;

import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom.OrderPaymentBO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.DAOFactory;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.*;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.db.DBConnection;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Item;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Order;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Payment;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderPaymentBOImpl implements OrderPaymentBO {

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ITEM);
    CategoryDAO categoryDAO = (CategoryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CATEGORY);
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CUSTOMER);
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.PAYMENT);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ORDER);
    OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ORDER_DETAILS);

    @Override
    public boolean saveOrder(Payment paymentDTO, Order orderDTO) throws SQLException {
        // @connection: Retrieves the current connection instance for the database
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            // @autoCommit: Disables auto-commit to manually control the transaction
            connection.setAutoCommit(false); // 1

            boolean isSavePayment = paymentDAO.save(paymentDTO);
            if (isSavePayment) {

                System.out.println(orderDTO);

                boolean isSaveOrder = orderDAO.save(orderDTO);
                if (isSaveOrder) {
                    boolean isOrderDetailsSaved = orderDetailsDAO.saveOrderDetailsList(orderDTO.getOrderDetailsDTOS());

                    if (isOrderDetailsSaved) {
                        // @commit: Commits the transaction if both order and details are saved successfully
                        connection.commit(); // 2
                        return true;
                        }
                    }
                }

            // @rollback: Rolls back the transaction if order details saving fails
            connection.rollback(); // 3
            return false;
        } catch (Exception e) {
            // @catch: Rolls back the transaction in case of any exception
            connection.rollback();
            return false;
        } finally {
            // @finally: Resets auto-commit to true after the operation
            connection.setAutoCommit(true); // 4
        }
    }

    @Override
    public String getNextOrderID () throws SQLException, ClassNotFoundException {
        return orderDAO.getNext();
    }

    @Override
    public String getNextPaymentID () throws SQLException, ClassNotFoundException {
        return paymentDAO.getNext();
    }

    @Override
    public ArrayList<String> getAllCustomerNames () throws SQLException, ClassNotFoundException {
        return customerDAO.getAllCustomerNames();
    }

    @Override
    public String getCustomerIdFindByName (String name) throws SQLException {
        return customerDAO.getCustomerIdFindByName(name);
    }

    @Override
    public ArrayList<String> getAllBrandNames () throws SQLException {
        return categoryDAO.getAllBrandNames();
    }

    @Override
    public String getBrandIdFindByName (String name) throws SQLException {
        return categoryDAO.brandIdFindByName(name);
    }

    @Override
    public ArrayList<String> allItemNameFindByCategoryId (String ID) throws SQLException {
        return itemDAO.allItemNameFindByCategoryId(ID);
    }

    @Override
    public Item findByName (String name) throws SQLException {
        return itemDAO.findByName(name);
    }

}
