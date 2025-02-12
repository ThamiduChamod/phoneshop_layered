package lk.ijse.gdse71.layeredproject.phoneshoplayered.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class CustomerDTO {


    private String customerID;
    private String customerName;
    private String nic;
    private String customerPhone;
    private String customerEmail;
}