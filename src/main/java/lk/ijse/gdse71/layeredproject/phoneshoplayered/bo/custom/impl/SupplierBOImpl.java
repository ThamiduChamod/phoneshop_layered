package lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom.SupplierBo;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.DAOFactory;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.SQLUtil;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.CategoryDAO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.SupplierDAO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.db.DBConnection;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.CategoryDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Category;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Supplier;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBo {

    SupplierDAO supplierDAO =(SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.SUPPLIER);
    CategoryDAO categoryDAO = (CategoryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CATEGORY);


    public boolean saveSupplier (Supplier supplierDTO, String brand) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);

            boolean isSupplierSave = supplierDAO.save(supplierDTO);
            if (isSupplierSave) {
                try {
                    String nextCategoryId = categoryDAO.getNext();

                    System.out.println(nextCategoryId);

                    Category categoryDTO = new Category(
                            nextCategoryId,
                            brand,
                            supplierDTO.getSupplier_id()
                    );
                    boolean isCategorySave = categoryDAO.save(categoryDTO);
                    if (isCategorySave) {
                        connection.commit();
                        return true;

                    }
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR,"Add Category Failed");
                    alert.showAndWait();
                }


            }
            connection.rollback();
            return false;


        } catch (Exception e) {
            connection.rollback();
            return false;
        }finally {
            connection.setAutoCommit(true);
        }
    }

    public boolean deleteSupplier(String supplierId) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            boolean isDelete =  categoryDAO.delete(supplierId);

            if (isDelete) {
                boolean isDeleteSupplier = SQLUtil.execute("DELETE FROM supplier WHERE supplier_id=?",supplierId);
                if (isDeleteSupplier) {
                    connection.commit();
                    return true;
                }
            }
            connection.rollback();
            return false;


        } catch (Exception e) {
            connection.rollback();
            return false;
        }finally {
            connection.setAutoCommit(true);
        }
    }

    public boolean updateSupplier(Supplier supplierDTO) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(supplierDTO);
    }

    public String getNextSupplierID () throws SQLException, ClassNotFoundException {
        return supplierDAO.getNext();
    }

    public ArrayList<Supplier> getAllSupplier () throws SQLException, ClassNotFoundException {
        return supplierDAO.getAll();
    }


}
