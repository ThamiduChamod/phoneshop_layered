package lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.impl;

import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.SQLUtil;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.ItemDAO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.ItemDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Item;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.OrderDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {

    public String getNext() throws SQLException {

        ResultSet rst = SQLUtil.execute("SELECT item_id FROM item order by item_id desc limit 1;");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("I%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "I001";
    }


    public ArrayList<Item> getAll () throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM item");

        ArrayList<Item> itemDTOS = new ArrayList<>();

        while (rst.next()) {
            Item itemDTO = new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4),
                    rst.getDouble(5)
            );

            itemDTOS.add(itemDTO);
        }
        return itemDTOS;
    }

    @Override
    public boolean save(Item DTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Item DTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE item SET qty = qty + ? WHERE item_id = ?",
                DTO.getQty(),
                DTO.getItem_id()
        );
    }

    @Override
    public boolean delete(String Id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<String> allItemNameFindByCategoryId (String categoryId) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT name from item WHERE category_id = ?", categoryId);

        ArrayList<String> allItemName = new ArrayList<>();

        while (rst.next()) {
            allItemName.add(rst.getString(1));
        }
        return allItemName;

    }

    @Override
    public Item findByName (String itemName) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM item WHERE name=?", itemName);

        while (rst.next()) {
            Item itemDTO = new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4),
                    rst.getDouble(5)
            );
            return itemDTO;
        }
        return null;
    }

//    public boolean updateItem (ArrayList<ItemDTO> itemDTOS,PaymentDTO paymentDTO,String supplierId) throws SQLException {
//        Connection connection = DBConnection.getInstance().getConnection();
//
//        try {
//            connection.setAutoCommit(false);
//            boolean isUpdate = false;
//            for (ItemDTO itemDTO : itemDTOS) {
//                // @isOrderDetailsSaved: Saves the individual order detail
//                isUpdate = CrudUtil.execute("UPDATE item SET qty = qty + ? WHERE item_id = ?",
//                        itemDTO.getQty(),
//                        itemDTO.getItem_id()
//                );
//            }
//            if (isUpdate) {
//                System.out.println("update item");
//                // Return false if saving any order detail fails
//                boolean isSavePayment = savePayment(paymentDTO);
//
//                if (isSavePayment) {
//                    System.out.println("save Payment");
//                    boolean isSavePaymentDetails = savePayDetails(paymentDTO.getPaymentID(),supplierId);
//                    if (isSavePaymentDetails) {
//                        System.out.println("save payment Details");
//                        connection.commit();
//                        return true;
//                    }
//                }
//            }
//
//            connection.rollback();
//            return false;
//        } catch (Exception e) {
//            connection.rollback();
//            return false;
//        }finally {
//            connection.setAutoCommit(true);
//        }
////        return CrudUtil.execute("UPDATE item SET qty = qty + ? WHERE item_id = ?",qty,itemId);
//    }
    @Override
    public boolean reduceQty(OrderDetails orderDetailsDTO) throws SQLException {
        return SQLUtil.execute("UPDATE item SET qty = qty - ? WHERE item_id = ?",
                orderDetailsDTO.getQty(),
                orderDetailsDTO.getItemId()

        );
    }

//    public boolean savePayment(PaymentDTO paymentDTO) throws SQLException {
//        return SQLUtil.execute("INSERT INTO payment VALUES (?,?,?)",
//                paymentDTO.getPaymentID(),
//                paymentDTO.getDate(),
//                paymentDTO.getTotal()
//        );
//    }

//    public boolean savePayDetails (String p_ID, String s_ID) throws SQLException {
//        return SQLUtil.execute("INSERT INTO paydetails VALUES (?,?)",
//                p_ID,
//                s_ID
//
//        );
//    }

}

