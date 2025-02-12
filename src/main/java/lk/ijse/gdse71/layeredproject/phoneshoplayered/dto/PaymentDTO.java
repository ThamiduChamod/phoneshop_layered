package lk.ijse.gdse71.layeredproject.phoneshoplayered.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString


public class PaymentDTO {

    private String paymentID;
    private Date date;
    private double total;


}
