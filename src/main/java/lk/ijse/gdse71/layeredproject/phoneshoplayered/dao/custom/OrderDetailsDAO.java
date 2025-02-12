package lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom;

import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.CrudDAO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.OrderDetailsDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailsDAO extends CrudDAO<OrderDetails> {
     boolean saveOrderDetailsList(ArrayList<OrderDetails> orderDetailsDTOS) throws SQLException, ClassNotFoundException;

    }
