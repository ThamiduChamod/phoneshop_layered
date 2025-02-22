package lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom.SupplierBo;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.DAOFactory;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.SQLUtil;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.CategoryDAO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.SupplierDAO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.db.DBConnection;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.CategoryDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.SupplierDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Category;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Supplier;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBo {

    SupplierDAO supplierDAO =(SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.SUPPLIER);
    CategoryDAO categoryDAO = (CategoryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CATEGORY);


    @Override
    public boolean saveSupplier (SupplierDTO supplierDTO, String brand) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);

            boolean isSupplierSave = supplierDAO.save(new Supplier(supplierDTO.getSupplier_id(),supplierDTO.getName(),supplierDTO.getNic(),supplierDTO.getEmail(),supplierDTO.getPhone()));
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

    @Override
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

    @Override
    public boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(supplierDTO.getSupplier_id(),supplierDTO.getName(),supplierDTO.getNic(),supplierDTO.getEmail(),supplierDTO.getPhone()));
    }

    @Override
    public String getNextSupplierID () throws SQLException, ClassNotFoundException {
        return supplierDAO.getNext();
    }

    @Override
    public ArrayList<SupplierDTO> getAllSupplier () throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> all = supplierDAO.getAll();

        ArrayList<SupplierDTO> dtos = new ArrayList<>();
        for (Supplier allSuppliers : all) {
            SupplierDTO dto = new SupplierDTO(
                    allSuppliers.getSupplier_id(),
                    allSuppliers.getName(),
                    allSuppliers.getNic(),
                    allSuppliers.getEmail(),
                    allSuppliers.getPhone()

            );
            dtos.add(dto);
        }
        return dtos;

    }


}
