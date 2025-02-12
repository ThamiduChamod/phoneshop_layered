package lk.ijse.gdse71.layeredproject.phoneshoplayered.bo;

import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom.impl.*;

public class BOFactory {

    public static BOFactory boFactory;

    private BOFactory() {}

    public static BOFactory getInstance() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOType {
        CUSTOMER,ITEM,LOGIN,ORDER_PAYMENT,PROFILE,SIGNIN,SUPPLIER,UPDATE_ITEM,VIEW_ITEM
    }

    public SuperBo getBO(BOType type) {
        switch (type) {
            case CUSTOMER:
                return new CustomerBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case LOGIN:
                return new LoginBOImpl();
            case ORDER_PAYMENT:
                return new OrderPaymentBOImpl();
            case PROFILE:
                return new ProfileBOImpl();
            case SIGNIN:
                return new SignInBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case UPDATE_ITEM:
                return new UpdateItemBOImpl();
            case VIEW_ITEM:
                return new ViewItemBOImpl();
            default:
                return null;
        }
    }
}
