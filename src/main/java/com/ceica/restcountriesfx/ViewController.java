package com.ceica.restcountriesfx;

import com.ceica.restcountriesfx.models.CountryDTO;
import com.ceica.restcountriesfx.services.RestCountryService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;

import java.util.Arrays;

public class ViewController {

    @FXML
    private ComboBox<String> comboBoxRegions;

    @FXML
    private TableView<CountryDTO> tableView;

    @FXML
    private TableColumn<CountryDTO,String> countriesColumn;

    @FXML
    private ImageView imageView;

    @FXML
    private Label lblCountry, lblCoin, lblPopulation, lblCapital;

    private final RestCountryService RestCountryService = new RestCountryService();

    public void initialize(){
        countriesColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        comboBoxRegions.getItems().addAll(FXCollections.observableList(Arrays.asList(RestCountryService.getRegions())));

        comboBoxRegions.setOnAction(event -> {
            String selected = comboBoxRegions.getSelectionModel().getSelectedItem();
            tableView.setItems(FXCollections.observableList(RestCountryService.getCountriesByRegion(selected)));
        });

        tableView.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                CountryDTO selectedItem = tableView.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    imageView.setImage(new Image(selectedItem.getFlag()));
                    lblCapital.setText("Capital: " + selectedItem.getCapital());
                    lblCoin.setText("Coin: " + selectedItem.getCoin());
                    lblCountry.setText("Country: " + selectedItem.getName());
                    lblPopulation.setText("Population: " + selectedItem.getPopulation());
                }
            }
        });
    }

    public void onCancel(){
        lblPopulation.setText(null);
        lblCountry.setText(null);
        lblCoin.setText(null);
        lblCapital.setText(null);
        tableView.refresh();
        imageView.setImage(null);
    }
}