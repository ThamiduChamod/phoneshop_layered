package lk.ijse.gdse71.layeredproject.phoneshoplayered.dto;

import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.OrderDetails;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class OrderDTO {

    private String orderID;
    private Date orderDate;
    private String paymentID;
    private String customerID;


    private ArrayList<OrderDetails> orderDetailsDTOS;
}
