package lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom;

import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.SuperBo;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Category;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Item;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UpdateItemBO extends SuperBo {

    boolean updateItem(ArrayList<Item> itemDTOS, Payment paymentDTO, String supplierId) throws SQLException;
    String getNextPaymentID () throws SQLException, ClassNotFoundException;
    Category findCategoryByName (String name) throws SQLException;
    ArrayList<String> getAllBrandNames () throws SQLException;
    ArrayList<String> allItemNameFindByCategoryId (String ID) throws SQLException;
    Item findByName (String name) throws SQLException;

}
