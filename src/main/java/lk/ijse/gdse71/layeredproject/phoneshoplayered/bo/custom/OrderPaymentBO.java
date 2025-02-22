package lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom;

import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.SuperBo;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.ItemDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.OrderDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.PaymentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderPaymentBO extends SuperBo {
     boolean saveOrder(PaymentDTO paymentDTO, OrderDTO order) throws SQLException;
     String getNextOrderID () throws SQLException, ClassNotFoundException;
     String getNextPaymentID () throws SQLException, ClassNotFoundException;
     ArrayList<String> getAllCustomerNames () throws SQLException, ClassNotFoundException;
     String getCustomerIdFindByName (String name) throws SQLException;
     ArrayList<String> getAllBrandNames () throws SQLException;
     String getBrandIdFindByName (String name) throws SQLException;
     ArrayList<String> allItemNameFindByCategoryId (String ID) throws SQLException;
     ItemDTO findByName (String name) throws SQLException;
}
