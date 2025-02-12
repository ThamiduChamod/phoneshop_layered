package lk.ijse.gdse71.layeredproject.phoneshoplayered.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString


public class OrderDetails {
    private String orderId;
    private String itemId;
    private int qty;
    private double price;
}
