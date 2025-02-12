package lk.ijse.gdse71.layeredproject.phoneshoplayered.view.tdm;


import javafx.scene.control.Button;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor


public class ItemTM {

    private String itemId;
    private String categoryId;
    private String itemName;
    private int qty;
    private double price;
    private double totalPrice;
    private Button removeBtn;
}
