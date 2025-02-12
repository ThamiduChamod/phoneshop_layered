package lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom.impl;

import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom.ItemBO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.DAOFactory;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.CategoryDAO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.ItemDAO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.PayDetailsDAO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.PaymentDAO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.db.DBConnection;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.ItemDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.PayDetailDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.PaymentDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Category;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Item;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.PayDetail;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Payment;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ITEM);
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.PAYMENT);
    PayDetailsDAO payDetailsDAO = (PayDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.PAYMENT_DETAILS);
    CategoryDAO categoryDAO = (CategoryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CATEGORY);


    @Override
    public boolean insertItem(ArrayList<Item> itemDTOS, Payment paymentDTO, String supplierId) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);
            for (Item itemDTO : itemDTOS) {
                boolean isOrderDetailsSaved = itemDAO.save(itemDTO);

                if (isOrderDetailsSaved) {
                    boolean isSavePayment = paymentDAO.save(paymentDTO);

                    if (isSavePayment) {
                        PayDetail payDetailDTO = new PayDetail(
                                paymentDTO.getPaymentID(),
                                supplierId
                        );
                        boolean isSavePaymentDetails = payDetailsDAO.save(payDetailDTO);

                        if (isSavePaymentDetails) {
                            connection.commit();
                            return true;
                        }
                    }
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            connection.rollback();
            return false;
        }finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public String getNextItemId() throws SQLException, ClassNotFoundException {
        return itemDAO.getNext();
    }

    @Override
    public ArrayList<String> getAllBrandName () throws SQLException {
        return categoryDAO.getAllBrandNames();
    }

    public Category findByName (String name) throws SQLException {
        return categoryDAO.findByName(name);
    }

    public String getNextPaymentID () throws SQLException, ClassNotFoundException {
       return paymentDAO.getNext();
    }


}
