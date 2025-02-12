package lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.impl;

import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.SQLUtil;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.PayDetailsDAO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.PayDetailDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.PayDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public class PayDetailsDAOImpl implements PayDetailsDAO {

    @Override
    public String getNext() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public ArrayList<PayDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(PayDetail DTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO paydetails VALUES (?,?)",
                DTO.getP_ID(),
                DTO.getS_ID()

        );
    }

    @Override
    public boolean update(PayDetail DTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String Id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
