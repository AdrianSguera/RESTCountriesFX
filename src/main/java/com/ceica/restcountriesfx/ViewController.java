package com.ceica.restcountriesfx;

import com.ceica.restcountriesfx.models.CountryDTO;
import com.ceica.restcountriesfx.services.FakeRestCountryService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

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

    private ObservableList<CountryDTO> countryDTOS = FXCollections.observableArrayList();

    public void initialize(){
        countriesColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

        tableView.setItems(countryDTOS);
    }
    public void onCancel(){
    }
}