package lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom.impl;

import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom.UpdateItemBO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.DAOFactory;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.CategoryDAO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.ItemDAO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.PayDetailsDAO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.PaymentDAO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.db.DBConnection;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.CategoryDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.ItemDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.PaymentDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Category;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Item;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.PayDetail;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Payment;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class UpdateItemBOImpl implements UpdateItemBO {

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ITEM);
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.PAYMENT);
    PayDetailsDAO payDetailsDAO = (PayDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.PAYMENT_DETAILS);
    CategoryDAO categoryDAO = (CategoryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CATEGORY);

    @Override
    public boolean updateItem(ArrayList<ItemDTO> itemDTOS, PaymentDTO paymentDTO, String supplierId) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);
            boolean isUpdate = false;
            for (ItemDTO itemDTO : itemDTOS) {
                // @isOrderDetailsSaved: Saves the individual order detail
                isUpdate = itemDAO.update(new Item(itemDTO.getItem_id(),itemDTO.getCategory_id(),itemDTO.getName(),itemDTO.getQty(),itemDTO.getPrice()));

            }
            if (isUpdate) {
                System.out.println("update item");
                // Return false if saving any order detail fails
                boolean isSavePayment = paymentDAO.save(new Payment(paymentDTO.getPaymentID(),paymentDTO.getDate(),paymentDTO.getTotal()));

                if (isSavePayment) {
                    System.out.println("save Payment");


//                    boolean isSavePaymentDetails = savePayDetails(paymentDTO.getPaymentID(),supplierId);
                    PayDetail payDetailDTO = new PayDetail(
                            paymentDTO.getPaymentID(),
                            supplierId
                    );

                    boolean isSavePaymentDetails = payDetailsDAO.save(payDetailDTO);
                    if (isSavePaymentDetails) {
                        System.out.println("save payment Details");
                        connection.commit();
                        return true;
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
    public String getNextPaymentID () throws SQLException, ClassNotFoundException {
        return paymentDAO.getNext();
    }

    @Override
    public CategoryDTO findCategoryByName (String name) throws SQLException {
        Category byName = categoryDAO.findByName(name);
        return new CategoryDTO(byName.getCategory_id(),byName.getName(),byName.getSupplier_id());
    }

    @Override
    public ArrayList<String> getAllBrandNames () throws SQLException {
        return categoryDAO.getAllBrandNames();
    }

    @Override
    public ArrayList<String> allItemNameFindByCategoryId (String ID) throws SQLException {
        return itemDAO.allItemNameFindByCategoryId(ID);
    }

    @Override
    public ItemDTO findByName (String name) throws SQLException {
        Item item = itemDAO.findByName(name);

        return new ItemDTO(item.getItem_id(),item.getCategory_id(),item.getName(),item.getQty(),item.getPrice());
    }
}
