package com.ceica.restcountriesfx;

import com.ceica.restcountriesfx.models.CountryDTO;
import com.ceica.restcountriesfx.services.RestCountryService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

import java.util.Arrays;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

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

    @FXML
    private TextField txtSearch;

    private final RestCountryService restCountryService = new RestCountryService();
    private ObservableList<CountryDTO> countriesDTO = FXCollections.observableArrayList();

    public void initialize(){
        countriesColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        comboBoxRegions.getItems().addAll(FXCollections.observableList(Arrays.asList(restCountryService.getRegions())));

        comboBoxRegions.setOnAction(event -> {
            String selected = comboBoxRegions.getSelectionModel().getSelectedItem();
            countriesDTO = FXCollections.observableList(restCountryService.getCountriesByRegion(selected));

            // Ordenar la lista por orden alfabético
            SortedSet<CountryDTO> sortedCountries = new TreeSet<>(Comparator.comparing(CountryDTO::getName));
            sortedCountries.addAll(countriesDTO);

            // Actualizar la tabla con la lista ordenada
            tableView.setItems(FXCollections.observableArrayList(sortedCountries));
        });

        tableView.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 1) {
                showCountry();
            }
        });
        tableView.setOnKeyPressed(event -> {
            showCountry();
        });
        txtSearch.setOnKeyPressed(event ->{
            if (event.getCode() == KeyCode.ENTER){
                tableView.setItems(FXCollections.observableArrayList(restCountryService.getCountryByName(txtSearch.getText())));
            }
        });
    }

    private void showCountry() {
        String cca3 = tableView.getSelectionModel().getSelectedItem().getCca3();
        CountryDTO countryDTO = restCountryService.getCountryByCca3(cca3);
        if (countryDTO != null) {
            imageView.setImage(new Image(countryDTO.getFlag()));
            try {
                lblCapital.setText("Capital: " + String.join("", countryDTO.getCapital()));
            } catch (NullPointerException e) {
                lblCapital.setText("Capital: none");
            }
            lblCoin.setText("Coin: " + countryDTO.getCoin());
            lblCountry.setText("Country: " + countryDTO.getName());
            lblPopulation.setText("Population: " + countryDTO.getPopulation());
        }
    }

    public void onCancel(){
        lblPopulation.setText(null);
        lblCountry.setText(null);
        lblCoin.setText(null);
        lblCapital.setText(null);
        tableView.refresh();
        imageView.setImage(null);
        countriesDTO.clear();
        //comboBoxRegions.getSelectionModel().clearSelection();
    }
}