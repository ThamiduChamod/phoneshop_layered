package lk.ijse.gdse71.layeredproject.phoneshoplayered.view.tdm;

import javafx.scene.control.Button;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString


public class UpdateItemTM {


    private String categoryId;
    private String itemId;
    private String itemName;
    private int quantity;
    private double unitPrice;
    private Double totalPrice;
    private Button removeButton;

}
