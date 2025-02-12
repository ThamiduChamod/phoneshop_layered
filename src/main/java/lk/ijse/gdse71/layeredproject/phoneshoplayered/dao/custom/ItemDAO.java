package lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom;

import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.CrudDAO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Item;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDAO extends CrudDAO<Item> {
    Item findByName (String itemName) throws SQLException;
    boolean reduceQty(OrderDetails orderDetailsDTO) throws SQLException;
    public ArrayList<String> allItemNameFindByCategoryId (String categoryId) throws SQLException;

}
