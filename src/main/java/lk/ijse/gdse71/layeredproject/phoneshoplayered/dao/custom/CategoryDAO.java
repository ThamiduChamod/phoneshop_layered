package lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom;

import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.CrudDAO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.CategoryDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Category;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CategoryDAO extends CrudDAO<Category> {
    ArrayList<String> getAllBrandNames () throws SQLException;
    Category findByName(String name) throws SQLException;
    String brandIdFindByName (String name) throws SQLException;

}
