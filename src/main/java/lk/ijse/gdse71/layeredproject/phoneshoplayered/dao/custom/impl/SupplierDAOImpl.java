package lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.impl;

import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.SQLUtil;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.SupplierDAO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.SupplierDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public String getNext() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT supplier_id FROM supplier order by supplier_id desc limit 1;");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("S%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "S001";
    }

    @Override
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM supplier;");

        ArrayList<Supplier> supplierList = new ArrayList<>();

        while (rst.next()) {
            Supplier supplierDTO = new Supplier(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)

            );
            supplierList.add(supplierDTO);
        }
        return supplierList;    }

    @Override
    public boolean save(Supplier DTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO supplier VALUES (?,?,?,?,?)",
                DTO.getSupplier_id(),
                DTO.getName(),
                DTO.getNic(),
                DTO.getEmail(),
                DTO.getEmail()

        );
    }

    @Override
    public boolean update(Supplier DTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE supplier SET name =?,nic=?,email=?,contact_number=?WHERE supplier_id=?",
                DTO.getName(),
                DTO.getNic(),
                DTO.getEmail(),
                DTO.getPhone(),
                DTO.getSupplier_id()

        );
    }

    @Override
    public boolean delete(String Id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM supplier WHERE supplier_id=?", Id);
    }
}
