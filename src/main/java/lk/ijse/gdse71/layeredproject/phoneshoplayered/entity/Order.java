package lk.ijse.gdse71.layeredproject.phoneshoplayered.entity;

import lombok.*;

import java.sql.Date;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Order {

    private String orderID;
    private Date orderDate;
    private String paymentID;
    private String customerID;


    private ArrayList<OrderDetails> orderDetailsDTOS;
}
