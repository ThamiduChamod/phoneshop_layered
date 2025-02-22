package lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom;

import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.SuperBo;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBo extends SuperBo {
     boolean saveSupplier (SupplierDTO supplierDTO, String brand) throws SQLException;
     boolean deleteSupplier(String supplierId) throws SQLException;
     boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;
     String getNextSupplierID () throws SQLException, ClassNotFoundException;
     ArrayList<SupplierDTO> getAllSupplier () throws SQLException, ClassNotFoundException;

}
