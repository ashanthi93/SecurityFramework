/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sse.userinterface.controller;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.DocumentException;
import org.dom4j.Text;
import org.sse.settings.config.source.BugModelConfig;
import org.xml.sax.SAXException;
import org.sse.source.model.BugCategory;
import org.sse.source.BugCategoriesLoader;
import org.sse.source.model.BugControl;
import org.sse.source.BugControlsLoader;
import org.sse.source.BugCategoryToControlMappingHandler;
import org.sse.source.BugCategoryToControlMapping;

public class SettingsController implements Initializable {

    @FXML
    private JFXButton owaspNextBtn;

    @FXML
    private JFXButton proactiveNextBtn;

    @FXML
    private JFXButton btnSave;

    @FXML
    private TextField owaspTop10Version;

    @FXML
    private TextField proactiveVersion;

    @FXML
    private TabPane settingsTabPane;

    //for updated OWASP Top 10 table
    List<BugCategory> updatedOWASP_T10_list;

    //for updated OWASP proactives table
    List<BugControl> updatedProactives_list;

    //for updated OWASP Top 10 mapping table
    List<BugCategoryToControlMapping> updatedOWASP_proactives_mapping;

    //For OWASP Top 10 table
    @FXML
    private TableView<BugCategory> OWASPT10_Table;

    @FXML
    private TableColumn<BugCategory, String> t10_id;
    @FXML
    private TableColumn<BugCategory, String> t10_name;
    @FXML
    private TableColumn<BugCategory, String> t10_description;

    HashMap<Integer, BugCategory> OWASP_T10_list;
    ObservableList<BugCategory> owasp_data;

    //For OWASP Proactives table
    @FXML
    private TableView<BugControl> proactive_table;

    @FXML
    private TableColumn<BugControl, String> proact_id;
    @FXML
    private TableColumn<BugControl, String> proact_name;
    @FXML
    private TableColumn<BugControl, String> proact_description;

    HashMap<Integer, BugControl> proactives_list;
    ObservableList<BugControl> proactive_data;

    //For OWASP_proactives mapping table
    @FXML
    private TableView<BugCategoryToControlMapping> proactMap_table;

    @FXML
    private TableColumn<BugCategoryToControlMapping, String> proact;
    @FXML
    private TableColumn<BugCategoryToControlMapping, CheckBox> owaspProMap_a1;
    @FXML
    private TableColumn<BugCategoryToControlMapping, CheckBox> owaspProMap_a2;
    @FXML
    private TableColumn<BugCategoryToControlMapping, CheckBox> owaspProMap_a3;
    @FXML
    private TableColumn<BugCategoryToControlMapping, CheckBox> owaspProMap_a4;
    @FXML
    private TableColumn<BugCategoryToControlMapping, CheckBox> owaspProMap_a5;
    @FXML
    private TableColumn<BugCategoryToControlMapping, CheckBox> owaspProMap_a6;
    @FXML
    private TableColumn<BugCategoryToControlMapping, CheckBox> owaspProMap_a7;
    @FXML
    private TableColumn<BugCategoryToControlMapping, CheckBox> owaspProMap_a8;
    @FXML
    private TableColumn<BugCategoryToControlMapping, CheckBox> owaspProMap_a9;
    @FXML
    private TableColumn<BugCategoryToControlMapping, CheckBox> owaspProMap_a10;

    HashMap<Integer, BugCategoryToControlMapping> OWASP_proactives_mapping;
    ObservableList<BugCategoryToControlMapping> OWASP_proactive_MappingData;
    BugCategoryToControlMappingHandler owaspMappingModel = new BugCategoryToControlMappingHandler();

    public SettingsController() throws DocumentException, ParserConfigurationException, SAXException, IOException {

        //For OWASP Top 10 table
        OWASP_T10_list = BugCategoriesLoader.getBugCategoryWithDescriptionHashMap();
        TreeMap<Integer, BugCategory> owaspTreeMap = new TreeMap<Integer, BugCategory>(OWASP_T10_list);
        owasp_data = FXCollections.observableArrayList(owaspTreeMap.values());

        //for OWASP proactives table
        proactives_list = BugControlsLoader.getBugControlsWithDescription();
        TreeMap<Integer, BugControl> proactivesTreeMap = new TreeMap<Integer, BugControl>(proactives_list);
        proactive_data = FXCollections.observableArrayList(proactivesTreeMap.values());

        //for OWASP_proactives mapping table 
        OWASP_proactives_mapping = owaspMappingModel.getMapping();
        TreeMap<Integer, BugCategoryToControlMapping> proactiveMappingTreeMap = new TreeMap<Integer, BugCategoryToControlMapping>(OWASP_proactives_mapping);
        OWASP_proactive_MappingData = FXCollections.observableArrayList(proactiveMappingTreeMap.values());
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    public void initialize(URL url, ResourceBundle rb) {

        try {
            //OWASP Top 10 Table org.sse.knowedgemodel.settings
            setOWASPT10TableProperties();

            owaspTop10Version.setText(BugCategoriesLoader.getVersionName());

            //OWASP Proactives Table org.sse.knowedgemodel.settings
            setOWASPProactivesTableProperties();

            proactiveVersion.setText(BugControlsLoader.getVersionName());

            //OWASP proatcives mapping table properties
            setOWASP_ProactivesMappingTableProperties();

        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private void setOWASPT10TableProperties() {
        t10_id.setCellValueFactory(new PropertyValueFactory<BugCategory, String>("id"));
        t10_id.prefWidthProperty().bind(OWASPT10_Table.widthProperty().divide(9));


        t10_name.setCellValueFactory(new PropertyValueFactory<BugCategory, String>("name"));
        t10_name.setCellFactory(TextFieldTableCell.<BugCategory>forTableColumn());
        t10_name.prefWidthProperty().bind(OWASPT10_Table.widthProperty().divide(5));
        t10_name.setOnEditCommit(event -> {
            BugCategory row = event.getRowValue();
            row.setName(event.getNewValue());
        });

        t10_description.setCellValueFactory(new PropertyValueFactory<BugCategory, String>("description"));
        t10_description.setCellFactory(TextFieldTableCell.<BugCategory>forTableColumn());
        t10_description.prefWidthProperty().bind(OWASPT10_Table.widthProperty().divide(1.5));
        t10_description.setOnEditCommit(event -> {
            BugCategory row = event.getRowValue();
            row.setDescription(event.getNewValue());
        });

        OWASPT10_Table.setItems(owasp_data);
    }

    private void setOWASPProactivesTableProperties() {
        proact_id.setCellValueFactory(new PropertyValueFactory<BugControl, String>("id"));
        proact_id.prefWidthProperty().bind(OWASPT10_Table.widthProperty().divide(9));
        proact_id.setOnEditCommit(event -> {
            BugControl row = event.getRowValue();
            row.setId(event.getNewValue());
        });

        proact_name.setCellValueFactory(new PropertyValueFactory<BugControl, String>("name"));
        proact_name.setCellFactory(TextFieldTableCell.<BugControl>forTableColumn());
        proact_name.prefWidthProperty().bind(OWASPT10_Table.widthProperty().divide(5));
        proact_name.setOnEditCommit(event -> {
            BugControl row = event.getRowValue();
            row.setName(event.getNewValue());
        });

        proact_description.setCellValueFactory(new PropertyValueFactory<BugControl, String>("description"));
        proact_description.setCellFactory(TextFieldTableCell.<BugControl>forTableColumn());
        proact_description.prefWidthProperty().bind(OWASPT10_Table.widthProperty().divide(1.5));
        proact_description.setOnEditCommit(event -> {
            BugControl row = event.getRowValue();
            row.setDescription(event.getNewValue());
        });

        proactive_table.setItems(proactive_data);
    }

    private void setOWASP_ProactivesMappingTableProperties() {

        proact.setCellValueFactory(new PropertyValueFactory<BugCategoryToControlMapping, String>("control"));
        proact.prefWidthProperty().bind(proactMap_table.widthProperty().divide(11));
        proact.setOnEditCommit(event -> {
            BugCategoryToControlMapping row = event.getRowValue();
            row.setControl(event.getNewValue());
        });

        owaspProMap_a1.setCellValueFactory(new Callback<CellDataFeatures<BugCategoryToControlMapping, CheckBox>, ObservableValue<CheckBox>>() {

            public ObservableValue<CheckBox> call(CellDataFeatures<BugCategoryToControlMapping, CheckBox> p) {
                BugCategoryToControlMapping obj = p.getValue();
                CheckBox box = new CheckBox();
                box.setAlignment(Pos.CENTER);

                if (obj.getA1()) {
                    box.selectedProperty().setValue(Boolean.TRUE);
                }
                return new SimpleObjectProperty<CheckBox>(box);
            }
        });
        owaspProMap_a1.prefWidthProperty().bind(proactMap_table.widthProperty().divide(11));
        owaspProMap_a1.setOnEditCommit(event -> {
            BugCategoryToControlMapping row = event.getRowValue();
            if (event.getNewValue().selectedProperty().get()) {
                row.setA1(true);
            } else {
                row.setA1(false);
            }
        });

        owaspProMap_a2.setCellValueFactory(new Callback<CellDataFeatures<BugCategoryToControlMapping, CheckBox>, ObservableValue<CheckBox>>() {

            public ObservableValue<CheckBox> call(CellDataFeatures<BugCategoryToControlMapping, CheckBox> p) {
                BugCategoryToControlMapping obj = p.getValue();
                CheckBox box = new CheckBox();
                box.setAlignment(Pos.CENTER);

                if (obj.getA2()) {
                    box.selectedProperty().setValue(Boolean.TRUE);
                }
                return new SimpleObjectProperty<CheckBox>(box);
            }
        });
        owaspProMap_a2.prefWidthProperty().bind(proactMap_table.widthProperty().divide(11));
        owaspProMap_a2.setOnEditCommit(event -> {
            BugCategoryToControlMapping row = event.getRowValue();
            if (event.getNewValue().selectedProperty().get()) {
                row.setA2(true);
            } else {
                row.setA2(false);
            }
        });

        owaspProMap_a3.setCellValueFactory(new Callback<CellDataFeatures<BugCategoryToControlMapping, CheckBox>, ObservableValue<CheckBox>>() {

            public ObservableValue<CheckBox> call(CellDataFeatures<BugCategoryToControlMapping, CheckBox> p) {
                BugCategoryToControlMapping obj = p.getValue();
                CheckBox box = new CheckBox();
                box.setAlignment(Pos.CENTER);

                if (obj.getA3()) {
                    box.selectedProperty().setValue(Boolean.TRUE);
                }
                return new SimpleObjectProperty<CheckBox>(box);
            }
        });
        owaspProMap_a3.prefWidthProperty().bind(proactMap_table.widthProperty().divide(11));
        owaspProMap_a3.setOnEditCommit(event -> {
            BugCategoryToControlMapping row = event.getRowValue();
            row.setA3(event.getNewValue().isSelected());
        });

        owaspProMap_a4.setCellValueFactory(new Callback<CellDataFeatures<BugCategoryToControlMapping, CheckBox>, ObservableValue<CheckBox>>() {

            public ObservableValue<CheckBox> call(CellDataFeatures<BugCategoryToControlMapping, CheckBox> p) {
                BugCategoryToControlMapping obj = p.getValue();
                CheckBox box = new CheckBox();
                box.setAlignment(Pos.CENTER);

                if (obj.getA4()) {
                    box.selectedProperty().setValue(Boolean.TRUE);
                }
                return new SimpleObjectProperty<CheckBox>(box);
            }
        });
        owaspProMap_a4.prefWidthProperty().bind(proactMap_table.widthProperty().divide(11));
        owaspProMap_a4.setOnEditCommit(event -> {
            BugCategoryToControlMapping row = event.getRowValue();
            row.setA4(event.getNewValue().isSelected());
        });

        owaspProMap_a5.setCellValueFactory(new Callback<CellDataFeatures<BugCategoryToControlMapping, CheckBox>, ObservableValue<CheckBox>>() {

            public ObservableValue<CheckBox> call(CellDataFeatures<BugCategoryToControlMapping, CheckBox> p) {
                BugCategoryToControlMapping obj = p.getValue();
                CheckBox box = new CheckBox();
                box.setAlignment(Pos.CENTER);

                if (obj.getA5()) {
                    box.selectedProperty().setValue(Boolean.TRUE);
                }
                return new SimpleObjectProperty<CheckBox>(box);
            }
        });
        owaspProMap_a5.prefWidthProperty().bind(proactMap_table.widthProperty().divide(11));
        owaspProMap_a5.setOnEditCommit(event -> {
            BugCategoryToControlMapping row = event.getRowValue();
            row.setA5(event.getNewValue().isSelected());
        });

        owaspProMap_a6.setCellValueFactory(new Callback<CellDataFeatures<BugCategoryToControlMapping, CheckBox>, ObservableValue<CheckBox>>() {

            public ObservableValue<CheckBox> call(CellDataFeatures<BugCategoryToControlMapping, CheckBox> p) {
                BugCategoryToControlMapping obj = p.getValue();
                CheckBox box = new CheckBox();
                box.setAlignment(Pos.CENTER);

                if (obj.getA6()) {
                    box.selectedProperty().setValue(Boolean.TRUE);
                }
                return new SimpleObjectProperty<CheckBox>(box);
            }
        });
        owaspProMap_a6.prefWidthProperty().bind(proactMap_table.widthProperty().divide(11));
        owaspProMap_a6.setOnEditCommit(event -> {
            BugCategoryToControlMapping row = event.getRowValue();
            row.setA6(event.getNewValue().isSelected());
        });

        owaspProMap_a7.setCellValueFactory(new Callback<CellDataFeatures<BugCategoryToControlMapping, CheckBox>, ObservableValue<CheckBox>>() {

            public ObservableValue<CheckBox> call(CellDataFeatures<BugCategoryToControlMapping, CheckBox> p) {
                BugCategoryToControlMapping obj = p.getValue();
                CheckBox box = new CheckBox();
                box.setAlignment(Pos.CENTER);

                if (obj.getA7()) {
                    box.selectedProperty().setValue(Boolean.TRUE);
                }
                return new SimpleObjectProperty<CheckBox>(box);
            }
        });
        owaspProMap_a7.prefWidthProperty().bind(proactMap_table.widthProperty().divide(11));
        owaspProMap_a7.setOnEditCommit(event -> {
            BugCategoryToControlMapping row = event.getRowValue();
            row.setA7(event.getNewValue().isSelected());
        });

        owaspProMap_a8.setCellValueFactory(new Callback<CellDataFeatures<BugCategoryToControlMapping, CheckBox>, ObservableValue<CheckBox>>() {

            public ObservableValue<CheckBox> call(CellDataFeatures<BugCategoryToControlMapping, CheckBox> p) {
                BugCategoryToControlMapping obj = p.getValue();
                CheckBox box = new CheckBox();
                box.setAlignment(Pos.CENTER);

                if (obj.getA8()) {
                    box.selectedProperty().setValue(Boolean.TRUE);
                }
                return new SimpleObjectProperty<CheckBox>(box);
            }
        });
        owaspProMap_a8.prefWidthProperty().bind(proactMap_table.widthProperty().divide(11));
        owaspProMap_a8.setOnEditCommit(event -> {
            BugCategoryToControlMapping row = event.getRowValue();
            row.setA8(event.getNewValue().isSelected());
        });

        owaspProMap_a9.setCellValueFactory(new Callback<CellDataFeatures<BugCategoryToControlMapping, CheckBox>, ObservableValue<CheckBox>>() {

            public ObservableValue<CheckBox> call(CellDataFeatures<BugCategoryToControlMapping, CheckBox> p) {
                BugCategoryToControlMapping obj = p.getValue();
                CheckBox box = new CheckBox();
                box.setAlignment(Pos.CENTER);

                if (obj.getA9()) {
                    box.selectedProperty().setValue(Boolean.TRUE);
                }
                return new SimpleObjectProperty<CheckBox>(box);
            }
        });
        owaspProMap_a9.prefWidthProperty().bind(proactMap_table.widthProperty().divide(11));
        owaspProMap_a9.setOnEditCommit(event -> {
            BugCategoryToControlMapping row = event.getRowValue();
            row.setA9(event.getNewValue().isSelected());
        });

        owaspProMap_a10.setCellValueFactory(new Callback<CellDataFeatures<BugCategoryToControlMapping, CheckBox>, ObservableValue<CheckBox>>() {

            public ObservableValue<CheckBox> call(CellDataFeatures<BugCategoryToControlMapping, CheckBox> p) {
                BugCategoryToControlMapping obj = p.getValue();
                CheckBox box = new CheckBox();
                box.setAlignment(Pos.CENTER);

                if (obj.getA10()) {
                    box.selectedProperty().setValue(Boolean.TRUE);
                }
                return new SimpleObjectProperty<CheckBox>(box);
            }
        });
        owaspProMap_a10.prefWidthProperty().bind(proactMap_table.widthProperty().divide(11));
        owaspProMap_a10.setOnEditCommit(event -> {
            BugCategoryToControlMapping row = event.getRowValue();
            row.setA10(event.getNewValue().isSelected());
        });

        proactMap_table.setItems(OWASP_proactive_MappingData);
    }

    @FXML
    private void owaspNextBtnAction(ActionEvent event) throws Exception {
        updatedOWASP_T10_list = new ArrayList<BugCategory>();

        ObservableList<BugCategory> updatedOWASP_T10 = OWASPT10_Table.getItems();

        updatedOWASP_T10_list = updatedOWASP_T10;

        SingleSelectionModel<Tab> selectionModel = settingsTabPane.getSelectionModel();
        selectionModel.select(1);
    }

    @FXML
    private void proactiveNextBtnAction(ActionEvent event) throws Exception {
        updatedProactives_list = new ArrayList<BugControl>();

        ObservableList<BugControl> updatedProactives = proactive_table.getItems();

        updatedProactives_list = updatedProactives;

        SingleSelectionModel<Tab> selectionModel = settingsTabPane.getSelectionModel();
        selectionModel.select(2);
    }

    @FXML
    private void btnSaveAction(ActionEvent event) throws Exception {
        updatedOWASP_proactives_mapping = new ArrayList<BugCategoryToControlMapping>();

        ObservableList<BugCategoryToControlMapping> updatedMapping = proactMap_table.getItems();

        updatedOWASP_proactives_mapping = updatedMapping;

        for (BugCategoryToControlMapping bug : updatedOWASP_proactives_mapping) {
            System.out.println(bug.getControl() + ", " + bug.getA1() + ", " + bug.getA2() + ", " + bug.getA3() + ", " + bug.getA4() + ", " + bug.getA5() + ", " + bug.getA6() + ", " + bug.getA7() + ", " + bug.getA8() + ", " + bug.getA9() + ", " + bug.getA10());
        }

        updateOWASPT10();
        updateProactives();
        updateOWASP_proactives_mapping();
    }

    private void updateOWASPT10() {


    }

    private void updateProactives() {

    }

    private void updateOWASP_proactives_mapping() {

    }

}
