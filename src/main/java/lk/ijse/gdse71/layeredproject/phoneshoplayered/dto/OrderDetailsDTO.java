package lk.ijse.gdse71.layeredproject.phoneshoplayered.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString


public class OrderDetailsDTO {
    private String orderId;
    private String itemId;
    private int qty;
    private double price;
}
