package lk.ijse.gdse71.layeredproject.phoneshoplayered.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class ItemDTO {
    private String item_id;
    private String category_id;
    private String name;
    private int qty;
    private double price;
}
