package lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom;

import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.SuperBo;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.CategoryDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.ItemDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.PaymentDTO;


import java.sql.SQLException;
import java.util.ArrayList;

public interface UpdateItemBO extends SuperBo {

    boolean updateItem(ArrayList<ItemDTO> itemDTOS, PaymentDTO paymentDTO, String supplierId) throws SQLException;
    String getNextPaymentID () throws SQLException, ClassNotFoundException;
    CategoryDTO findCategoryByName (String name) throws SQLException;
    ArrayList<String> getAllBrandNames () throws SQLException;
    ArrayList<String> allItemNameFindByCategoryId (String ID) throws SQLException;
    ItemDTO findByName (String name) throws SQLException;

}
