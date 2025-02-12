package lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom;

import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.SuperBo;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.CategoryDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.ItemDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.PaymentDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Category;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Item;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBo {
    boolean insertItem(ArrayList<Item> itemDTOS, Payment paymentDTO, String supplierId) throws SQLException;
    String getNextItemId() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllBrandName () throws SQLException;
    Category findByName (String name) throws SQLException;
    String getNextPaymentID () throws SQLException, ClassNotFoundException;
}
