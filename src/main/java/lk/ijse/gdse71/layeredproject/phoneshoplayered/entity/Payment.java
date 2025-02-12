package lk.ijse.gdse71.layeredproject.phoneshoplayered.entity;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString


public class Payment {

    private String paymentID;
    private Date date;
    private double total;


}
