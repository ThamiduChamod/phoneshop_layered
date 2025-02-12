package lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom;

import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.SuperBo;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBo extends SuperBo {
     boolean saveSupplier (Supplier supplierDTO, String brand) throws SQLException;
     boolean deleteSupplier(String supplierId) throws SQLException;
     boolean updateSupplier(Supplier supplierDTO) throws SQLException, ClassNotFoundException;
     String getNextSupplierID () throws SQLException, ClassNotFoundException;
     ArrayList<Supplier> getAllSupplier () throws SQLException, ClassNotFoundException;

}
