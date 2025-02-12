package lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom;

import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.SuperBo;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.ItemDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ViewItemBO extends SuperBo {
    public ArrayList<Item> getAllItems() throws SQLException, ClassNotFoundException;
}
