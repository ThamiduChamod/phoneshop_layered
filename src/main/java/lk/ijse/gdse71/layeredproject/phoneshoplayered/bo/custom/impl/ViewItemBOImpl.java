package lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom.impl;

import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom.ViewItemBO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.DAOFactory;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.ItemDAO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.ItemDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;



public class ViewItemBOImpl implements ViewItemBO {

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ITEM);

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<Item> allItem = itemDAO.getAll();

        ArrayList<ItemDTO> itemDTOs = new ArrayList<>();
        for (Item item : allItem) {
            ItemDTO itemDTO = new ItemDTO(
                    item.getItem_id(),
                    item.getCategory_id(),
                    item.getName(),
                    item.getQty(),
                    item.getPrice()
            );
            itemDTOs.add(itemDTO);
        }
        return itemDTOs;
    }
}
