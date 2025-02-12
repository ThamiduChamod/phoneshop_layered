package lk.ijse.gdse71.layeredproject.phoneshoplayered.entity;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Item {
    private String item_id;
    private String category_id;
    private String name;
    private int qty;
    private double price;
}
