package lk.ijse.gdse71.layeredproject.phoneshoplayered.dao;

import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.CategoryDAO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {}

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }
    public enum DAOType {
        CATEGORY,CUSTOMER,ITEM,LOGIN,ORDER,ORDER_DETAILS,PAYMENT_DETAILS,PAYMENT,SIGNIN,SUPPLIER

    }
    public SuperDAO getDAO(DAOType type) {
        switch (type) {
            case CATEGORY:
                return new CategoryDAOImpl();
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case LOGIN:
                return new LoginDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDER_DETAILS:
                return new OrderDetailsDAOImpl();
            case PAYMENT_DETAILS:
                return new PayDetailsDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case SIGNIN:
                return new SigninDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            default:
                return null;
        }
    }
}
