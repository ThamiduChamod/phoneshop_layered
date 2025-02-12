package lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.impl;

import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.SQLUtil;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.CategoryDAO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.CategoryDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDAOImpl implements CategoryDAO {


    @Override
    public String getNext() throws SQLException {

        ResultSet rst = SQLUtil.execute("SELECT category_id FROM category ORDER BY category_id desc limit 1;");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("B%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "B001";
    }

    @Override
    public ArrayList<Category> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean save(Category categoryDTO) throws SQLException {
        return SQLUtil.execute("INSERT INTO category VALUES (?,?,?)",
                categoryDTO.getCategory_id(),
                categoryDTO.getName(),
                categoryDTO.getSupplier_id()

        );
    }

    @Override
    public boolean update(Category DTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String Id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM category WHERE supplier_id=?", Id);
    }

    @Override
    public ArrayList<String> getAllBrandNames () throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT name FROM category");
        ArrayList<String> brandNames = new ArrayList<>();
        while (rst.next()) {
            brandNames.add(rst.getString(1));
        }
        return brandNames;
    }

    @Override
    public Category findByName(String name) throws SQLException {

        ResultSet rst = SQLUtil.execute("SELECT * FROM category WHERE name=?", name);

        if (rst.next()) {
            Category category = new Category(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            );
            return category;
        }
        return null;
    }

    @Override
    public String brandIdFindByName (String name) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT category_id FROM category WHERE name=?", name);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

}

