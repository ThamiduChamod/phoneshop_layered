package lk.ijse.gdse71.layeredproject.phoneshoplayered.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.BOFactory;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom.ItemBO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom.ViewItemBO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom.impl.ItemBOImpl;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom.impl.ViewItemBOImpl;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.ItemDAO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.impl.ItemDAOImpl;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.ItemDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Item;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.view.tdm.ViewItemTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewItemController implements Initializable {
    public TableView tableItem;
    public TableColumn colItemId;
    public TableColumn colCategoryId;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colItemName;
    public AnchorPane itemViewAnchorPane;



    ViewItemBO itemBO = (ViewItemBO) BOFactory.getInstance().getBO(BOFactory.BOType.VIEW_ITEM);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colCategoryId.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        try {
            ArrayList<Item> itemDTOS = itemBO.getAllItems();
            ObservableList<ViewItemTM> itemTMS = FXCollections.observableArrayList();

            for (Item itemDTO : itemDTOS) {
                ViewItemTM viewItemTM = new ViewItemTM(
                        itemDTO.getItem_id(),
                        itemDTO.getCategory_id(),
                        itemDTO.getName(),
                        itemDTO.getQty(),
                        itemDTO.getPrice()
                );
                itemTMS.add(viewItemTM);
            }
            tableItem.setItems(itemTMS);



        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,"Fail to Load Table").show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
