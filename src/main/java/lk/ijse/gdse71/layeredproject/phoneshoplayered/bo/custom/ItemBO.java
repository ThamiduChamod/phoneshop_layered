package lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom;

import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.SuperBo;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.CategoryDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.ItemDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.PaymentDTO;



import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBo {
    boolean insertItem(ArrayList<ItemDTO> itemDTOS, PaymentDTO paymentDTO, String supplierId) throws SQLException;
    String getNextItemId() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllBrandName () throws SQLException;
    CategoryDTO findByName (String name) throws SQLException;
    String getNextPaymentID () throws SQLException, ClassNotFoundException;
}
