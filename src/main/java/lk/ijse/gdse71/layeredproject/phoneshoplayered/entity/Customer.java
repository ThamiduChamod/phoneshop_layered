package lk.ijse.gdse71.layeredproject.phoneshoplayered.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Customer {


    private String customerID;
    private String customerName;
    private String nic;
    private String customerPhone;
    private String customerEmail;
}