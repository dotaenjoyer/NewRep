package com.shend.controllers;

import com.shend.entities.Call;
import com.shend.entities.Privilege;
import com.shend.entities.Payment;
import com.shend.db.DBHandler;
import com.shend.entities.Tariff;
import com.shend.entities.Service;
import com.shend.tables.GetCount;
import com.shend.tables.GetDesc;
import com.shend.tables.GetDesc1;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import static com.shend.App.windowHeight;
import static com.shend.App.windowWidth;

public class AbonentWindowController {

    private String[] mainChoices = {"Список тарифів      ",
        "Список пільг      ", "Список послуг       ",
        "Список дзвінків      ", "Список оплат      "};

    private String[] choices2 = {"Знайти тариф",
        "Скільки абонентів обслуговуються кожним тарифом", "Найбільш полпулярний тариф та невикористаний тариф",
        "Найбільш полпулярна послуга та невикористана послуга"};
    @FXML
    private Button backbutton;

    @FXML
    private Label balanceLabel;

    @FXML
    private ChoiceBox<String> choiceBar1;

    @FXML
    private ChoiceBox<String> choos;

    @FXML
    private Button claearButton;

    @FXML
    private Button connectButton;
    @FXML
    private Label avg;

    @FXML
    private Label count;

    @FXML
    private Label errorlabel;

    @FXML
    private Button randombutton;

    @FXML
    private TextField randomtext;

    @FXML
    private StackPane stackpane;

    @FXML
    private StackPane stackpane1;

    private TableView<Tariff> tableView2 = new TableView<>();

    private TableView<Privilege> tableView3 = new TableView<>();

    private TableView<Service> tableView4 = new TableView<>();

    private TableView<Call> tableView55 = new TableView<>();

    private TableView<Payment> tableView6 = new TableView<>();

    private TableView<GetCount> tableView111 = new TableView<>();

    private TableView<GetDesc> tableView222 = new TableView<>();

    private TableView<GetDesc1> tableView444 = new TableView<>();

    @FXML
    void initialize() {
        choos.getItems().addAll(mainChoices);
        choiceBar1.getItems().addAll(choices2);
        DBHandler dbHandler = new DBHandler();
        String formattedString = String.format("%.02f", dbHandler.getAvg());
        count.setText("Середня тривалість дзвінків:" + formattedString + " хвилин");
        avg.setText("Кількість дзвінків:" + dbHandler.getCallCount());
        choos.setStyle("-fx-font-size:20");
        choiceBar1.setStyle("-fx-font-size:20");
        tableView2.setStyle("-fx-font-size:16");
        tableView3.setStyle("-fx-font-size:16");
        tableView4.setStyle("-fx-font-size:16");
        tableView111.setStyle("-fx-font-size:16");
        tableView222.setStyle("-fx-font-size:16");
        tableView444.setStyle("-fx-font-size:16");

        choos.setOnAction(actionEvent -> {
            switch (choos.getSelectionModel().getSelectedIndex()) {
                case 0:
                    stackpane.getChildren().clear();
                    stackpane.getChildren().add(tableView2);
                    TableColumn<Tariff, String> name1 = new TableColumn("Тариф");
                    name1.setCellValueFactory(new PropertyValueFactory<>("name"));
                    TableColumn<Tariff, Double> price = new TableColumn("Ціна");
                    price.setCellValueFactory(new PropertyValueFactory<>("price"));
                    TableColumn<Tariff, String> description = new TableColumn("Опис");
                    description.setCellValueFactory(new PropertyValueFactory<>("description"));
                    TableColumn<Tariff, Integer> term = new TableColumn("Строк");
                    term.setCellValueFactory(new PropertyValueFactory<>("term"));
                    ObservableList<Tariff> list2 = FXCollections.observableArrayList();
                     {
                        try {
                            list2 = dbHandler.getTariffList();
                        } catch (SQLException ex) {
                            Logger.getLogger(OperatorController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    tableView2.getColumns().setAll(name1, price, description, term);
                    tableView2.getItems().setAll(list2);
                    tableView2.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                    break;
                case 1:
                    stackpane.getChildren().clear();
                    stackpane.getChildren().add(tableView3);
                    TableColumn<Privilege, String> name2 = new TableColumn("Пільга");
                    name2.setCellValueFactory(new PropertyValueFactory<>("name"));
                    TableColumn<Privilege, String> description1 = new TableColumn("Опис");
                    description1.setCellValueFactory(new PropertyValueFactory<>("description"));
                    ObservableList<Privilege> list3 = FXCollections.observableArrayList();
                     {
                        try {
                            list3 = dbHandler.getPrivilegeList();
                        } catch (SQLException ex) {
                            Logger.getLogger(OperatorController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    tableView3.getColumns().setAll(name2, description1);
                    tableView3.getItems().setAll(list3);
                    tableView3.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                    break;
                case 2:

                    stackpane.getChildren().clear();
                    stackpane.getChildren().add(tableView4);
                    TableColumn<Service, String> name3 = new TableColumn("Послуга");
                    name3.setCellValueFactory(new PropertyValueFactory<>("name"));
                    TableColumn<Service, String> description2 = new TableColumn("Опис");
                    description2.setCellValueFactory(new PropertyValueFactory<>("description"));
                    TableColumn<Service, Double> price2 = new TableColumn("Ціна");
                    price2.setCellValueFactory(new PropertyValueFactory<>("price"));
                    ObservableList<Service> list4 = FXCollections.observableArrayList();
                     {
                        try {
                            list4 = dbHandler.getServiceList();
                        } catch (SQLException ex) {
                            Logger.getLogger(OperatorController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    tableView4.getColumns().setAll(name3, description2, price2);
                    tableView4.getItems().setAll(list4);
                    tableView4.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                    break;
                case 3:
                    stackpane.getChildren().clear();
                    stackpane.getChildren().add(tableView55);
                    TableColumn<Call, String> name4 = new TableColumn("Приймаючий номер");
                    name4.setCellValueFactory(new PropertyValueFactory<>("receiverNumber"));
                    TableColumn<Call, LocalDate> date = new TableColumn("Дата");
                    date.setCellValueFactory(new PropertyValueFactory<>("date"));
                    TableColumn<Call, LocalTime> time = new TableColumn("Час");
                    time.setCellValueFactory(new PropertyValueFactory<>("time"));
                    TableColumn<Call, Integer> dur = new TableColumn("Тривалість");
                    dur.setCellValueFactory(new PropertyValueFactory<>("duration"));
                    TableColumn<Call, Double> price1 = new TableColumn("Ціна");
                    price1.setCellValueFactory(new PropertyValueFactory<>("price"));
                    ObservableList<Call> list5 = FXCollections.observableArrayList();
                     {
                        try {
                            list5 = dbHandler.getCallListForAbonent();
                        } catch (SQLException ex) {
                            Logger.getLogger(OperatorController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    tableView55.getColumns().setAll(name4, date, time, dur, price1);
                    tableView55.getItems().setAll(list5);
                    tableView55.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                    break;
                case 4:
                    stackpane.getChildren().clear();
                    stackpane.getChildren().add(tableView6);
                    TableColumn<Payment, String> name5 = new TableColumn("Послуга");
                    name5.setCellValueFactory(new PropertyValueFactory<>("service"));
                    TableColumn<Payment, String> description3 = new TableColumn("Опис");
                    description3.setCellValueFactory(new PropertyValueFactory<>("description"));
                    TableColumn<Payment, Double> price3 = new TableColumn("Ціна");
                    price3.setCellValueFactory(new PropertyValueFactory<>("price"));
                    TableColumn<Payment, LocalDateTime> date2 = new TableColumn("Дата");
                    date2.setCellValueFactory(new PropertyValueFactory<>("date"));
                    ObservableList<Payment> list6 = FXCollections.observableArrayList();
                     {
                        try {
                            list6 = dbHandler.getPaymentListForAbonent();
                        } catch (SQLException ex) {
                            Logger.getLogger(OperatorController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(AbonentWindowController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    tableView6.getColumns().setAll(name5, description3, price3, date2);
                    tableView6.getItems().setAll(list6);
                    tableView6.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                    break;
            }

        });
        choiceBar1.setOnAction(actionEvent -> {
            switch (choiceBar1.getSelectionModel().getSelectedIndex()) {
                case 0:
                    stackpane.getChildren().add(tableView2);
                    TableColumn<Tariff, String> name1 = new TableColumn("Тариф");
                    name1.setCellValueFactory(new PropertyValueFactory<>("name"));
                    TableColumn<Tariff, Double> price = new TableColumn("Ціна");
                    price.setCellValueFactory(new PropertyValueFactory<>("price"));
                    TableColumn<Tariff, String> description = new TableColumn("Опис");
                    description.setCellValueFactory(new PropertyValueFactory<>("description"));
                    TableColumn<Tariff, Integer> term = new TableColumn("Строк");
                    term.setCellValueFactory(new PropertyValueFactory<>("term"));
                    ObservableList<Tariff> list2 = FXCollections.observableArrayList();
                     {
                        try {
                            list2 = dbHandler.getTariffList();
                        } catch (SQLException ex) {
                            Logger.getLogger(OperatorController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    tableView2.getColumns().setAll(name1, price, description, term);
                    tableView2.getItems().setAll(list2);
                    tableView2.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                    break;
                case 1:
                    stackpane.getChildren().clear();
                    stackpane.getChildren().add(tableView111);
                    TableColumn<GetCount, String> tar = new TableColumn("Тариф");
                    tar.setCellValueFactory(new PropertyValueFactory<>("name"));
                    TableColumn<GetCount, Integer> count2 = new TableColumn("Максимальна тривалість");
                    count2.setCellValueFactory(new PropertyValueFactory<>("count"));
                    ObservableList<GetCount> list12 = FXCollections.observableArrayList();
                     {
                        try {
                            list12 = dbHandler.getCount();
                        } catch (SQLException ex) {
                            Logger.getLogger(OperatorController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                    tableView111.getColumns().setAll(tar, count2);
                    tableView111.getItems().setAll(list12);
                    tableView111.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                    break;
                case 2:
                    stackpane.getChildren().clear();
                    stackpane.getChildren().add(tableView222);
                    TableColumn<GetDesc, String> tar2 = new TableColumn("Тариф");
                    tar2.setCellValueFactory(new PropertyValueFactory<>("tariff"));
                    TableColumn<GetDesc, String> desc = new TableColumn("Опис");
                    desc.setCellValueFactory(new PropertyValueFactory<>("description"));
                    ObservableList<GetDesc> list13 = FXCollections.observableArrayList();
                     {
                        try {
                            list13 = dbHandler.getDesc();
                        } catch (SQLException ex) {
                            Logger.getLogger(OperatorController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                    System.out.println(list13);
                    tableView222.getColumns().setAll(tar2, desc);
                    tableView222.getItems().setAll(list13);
                    tableView222.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                    break;

                case 3:
                    stackpane.getChildren().clear();
                    stackpane.getChildren().add(tableView444);
                    TableColumn<GetDesc1, String> ser = new TableColumn("Послуга");
                    ser.setCellValueFactory(new PropertyValueFactory<>("service"));
                    TableColumn<GetDesc1, String> desc2 = new TableColumn("Опис");
                    desc2.setCellValueFactory(new PropertyValueFactory<>("description"));
                    ObservableList<GetDesc1> list15 = FXCollections.observableArrayList();
                     {
                        try {
                            list15 = dbHandler.getDesc1();
                        } catch (SQLException ex) {
                            Logger.getLogger(OperatorController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    System.out.println(list15);
                    tableView444.getColumns().setAll(ser, desc2);
                    tableView444.getItems().setAll(list15);
                    tableView444.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                    break;
            }
        });
        randombutton.setOnAction(actionEvent -> {
            stackpane.getChildren().add(tableView2);
            String a1 = randomtext.getText().trim();
            TableColumn<Tariff, String> name1 = new TableColumn("Тариф");
            name1.setCellValueFactory(new PropertyValueFactory<>("name"));
            TableColumn<Tariff, Double> price = new TableColumn("Ціна");
            price.setCellValueFactory(new PropertyValueFactory<>("price"));
            TableColumn<Tariff, String> description = new TableColumn("Опис");
            description.setCellValueFactory(new PropertyValueFactory<>("description"));
            TableColumn<Tariff, Integer> term = new TableColumn("Строк");
            term.setCellValueFactory(new PropertyValueFactory<>("term"));
            ObservableList<Tariff> list2 = FXCollections.observableArrayList();
            {
                try {
                    list2 = dbHandler.getByTariff(a1);
                } catch (SQLException ex) {
                    Logger.getLogger(OperatorController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            tableView2.getColumns().setAll(name1, price, description, term);
            tableView2.getItems().setAll(list2);
            tableView2.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        });

        backbutton.setOnAction(actionEvent -> {
            backbutton.getScene().getWindow().hide();
            String src = "/com/shend/graphicFiles/simple.fxml";
            LoadHelper helper = LoadHelper.getInstance();
            helper.loadWindow(src);
        });
    }
}
