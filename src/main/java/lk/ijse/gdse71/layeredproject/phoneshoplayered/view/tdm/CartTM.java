package lk.ijse.gdse71.layeredproject.phoneshoplayered.view.tdm;

import javafx.scene.control.Button;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class CartTM {
    private String brandId;
    private String brandName;
    private String itemId;
    private String itemName;
    private double unitPrice;
    private int  handOnqty;
    private int cartQuantity;
    private double discount;
    private String method;
    private double total;
    private Button removeBtn;
}
