package lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.impl;

import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.SQLUtil;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.ItemDAO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.OrderDetailsDAO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.OrderDetailsDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {

    ItemDAO itemDAO = new ItemDAOImpl();

    @Override
    public String getNext() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public ArrayList<OrderDetails> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(OrderDetails DTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO orderdetail VALUES (?,?,?,?)",
                DTO.getOrderId(),
                DTO.getItemId(),
                DTO.getQty(),
                DTO.getPrice()
        );
    }

    @Override
    public boolean update(OrderDetails DTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String Id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean saveOrderDetailsList(ArrayList<OrderDetails> orderDetailsDTOS) throws SQLException, ClassNotFoundException {

        for (OrderDetails orderDetails : orderDetailsDTOS) {
            boolean save = save(orderDetails);
            if (!save) {
                return false;
            }
            boolean isUpdate = itemDAO.reduceQty(orderDetails);
            if (!isUpdate) {
                return false;
            }

        }
        return true;
    }


}
