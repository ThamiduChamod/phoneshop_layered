package lk.ijse.gdse71.layeredproject.phoneshoplayered.dto;


import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SigninDTO {

    private String userID;
    private String userName;
    private String nic;
    private String phoneNumber;
    private String email;
    private String password;

}

