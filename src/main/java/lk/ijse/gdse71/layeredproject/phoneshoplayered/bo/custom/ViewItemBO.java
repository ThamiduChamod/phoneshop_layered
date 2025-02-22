package lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom;

import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.SuperBo;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ViewItemBO extends SuperBo {
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException;
}
