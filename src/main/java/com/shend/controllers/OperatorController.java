package com.shend.controllers;

import com.shend.links.AbonentPrivilege;
import com.shend.links.AbonentTariff;
import com.shend.entities.Call;
import com.shend.entities.Privilege;
import com.shend.entities.Payment;
import com.shend.db.DBHandler;
import com.shend.entities.Tariff;
import com.shend.entities.Service;
import com.shend.entities.Abonent;
import com.shend.services.AbonentService;
import com.shend.tables.GetCount;
import com.shend.tables.GetCount1;
import com.shend.tables.GetDesc;
import com.shend.tables.GetDesc1;
import com.shend.entities.JournalLog;
import com.shend.tables.MaxCallDuration;
import com.shend.tables.Queries20;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class OperatorController {

    @FXML
    private ChoiceBox<String> choiceBar1;
    @FXML
    private ChoiceBox<String> choos;

    private String[] mainChoices = {"Список абонентів     ", "Список тарифів      ",
        "Список пільг      ", "Список послуг       ",
        "Список дзвінків      ", "Список оплат      ", "Підключення до тарифів     ", "Підключення до пільг  "};
    private String[] choices1 = {"Знайти абонента по прізвищу", "Використав найбільшу кількість тарифів",
        "Зробив більше всього дзвінків", "Абоненти, що мали більше 2 тарифів", "Має найбільшу суму тривалості дзвінків",
        "Для кожного абонента максимальну оплату за послугу", "Для кожного абонента максимальну вартість дзвінка",
        "Абоненти, що не оплачували послуги за останній місяць", "Абоненти, що не дзвонили за останній місяць"};
    private String[] choices2 = {"Пошук абонентів, що мають тариф Х", "Знайти тариф",
        "Скільки абонентів обслуговуються кожним тарифом", "Найбільш полпулярний тариф та невикористаний тариф"};
    private String[] choices3 = {"Скільки абонентів мають кожну пільгу"};
    private String[] choices4 = {"Найбільш популярна послуга та невикористана послуга"};
    private String[] choices5 = {"Дзвінки, що були здійснені абонентом Х"};
    @FXML
    private Button backbutton;

    @FXML
    private Button disconnect;

    @FXML
    private Button deletebutton;

    @FXML
    private Button claearButton;

    @FXML
    private Button editBar;

    @FXML
    private Label errorlabel;

    @FXML
    private Button journal;

    @FXML
    private Button newbar;

    @FXML
    private ComboBox<Tariff> combo1;

    @FXML
    private ComboBox<Abonent> combo2;

    @FXML
    private Button printButton;

    @FXML
    private Button randombutton;

    @FXML
    private Button connectButton;

    @FXML
    private TextField randomtext;

    public static Service service = new Service(0, null, null, null);
    public static Abonent abonent = new Abonent(null, null, null, null, null, null);
    public static Tariff tariff = new Tariff(0, null, null, null, 0);
    public static Privilege privilege = new Privilege(0, null, null);
    public static Payment payment = new Payment(0, null, null, null);
    public static Call call = new Call(0, null, null, null, null, 0, null);
    public static AbonentPrivilege abonentPrivilege = new AbonentPrivilege(0, null, null, null, null);
    public static AbonentTariff abonentTariff = new AbonentTariff(0, null, null, null, null);
    public static int outerkeyAbonent;
    public static int outerkeyTariff;
    public static int outerkeyService;
    public static int outerkeyPrivilege;
    public static int outerkeyAbonentPrivilege;
    public static int outerkeyAbonentTariff;

    @FXML
    private StackPane stackpane;

    private TableView<Abonent> tableView1 = new TableView<>();

    private TableView<Tariff> tableView2 = new TableView<>();

    private TableView<Privilege> tableView3 = new TableView<>();

    private TableView<Service> tableView4 = new TableView<>();

    private TableView<Call> tableView5 = new TableView<>();

    private TableView<Payment> tableView6 = new TableView<>();

    private TableView<AbonentTariff> tableView7 = new TableView<>();

    private TableView<AbonentPrivilege> tableView8 = new TableView<>();

    private TableView<JournalLog> tableViewJournal = new TableView<>();

    private TableView<Queries20> tableView33 = new TableView<>();

    private TableView<Queries20> tableView44 = new TableView<>();

    private TableView<Queries20> tableView55 = new TableView<>();

    private TableView<MaxCallDuration> tableView66 = new TableView<>();

    private TableView<Queries20> tableView77 = new TableView<>();

    private TableView<Queries20> tableView88 = new TableView<>();

    private TableView<Queries20> tableView99 = new TableView<>();

    private TableView<GetCount> tableView111 = new TableView<>();

    private TableView<GetDesc> tableView222 = new TableView<>();

    private TableView<GetCount1> tableView333 = new TableView<>();

    private TableView<GetDesc1> tableView444 = new TableView<>();

    private TableView<Queries20> tableView555 = new TableView<>();

    ObservableList<Queries20> list7 = null;
    ObservableList<MaxCallDuration> list8 = null;
    ObservableList<GetCount> list12 = null;
    ObservableList<GetDesc> list13 = null;
    ObservableList<GetCount1> list14 = null;
    ObservableList<GetDesc1> list15 = null;

    @FXML
    void print(MouseEvent event) {
        PDF pdf = new PDF();
        switch (choiceBar1.getValue()) {
            case "Для кожного абонента максимальну оплату за послугу":
                pdf.genPDF("query13", null, null, list7, null, null, null);
                break;
            case "Для кожного абонента максимальну вартість дзвінка":
                pdf.genPDF("query14", null, null, null, list8, null, null);
                break;
            case "Скільки абонентів обслуговуються кожним тарифом":
                pdf.genPDF("query9", list12, null, null, null, null, null);
                break;
            case "Скільки абонентів мають кожну пільгу":
                pdf.genPDF("query10", null, list14, null, null, null, null);
                break;
            case "Найбільш полпулярний тариф та невикористаний тариф":
                pdf.genPDF("query17", null, null, null, null, list13, null);
                break;
            case "Найбільш популярна послуга та невикористана послуга":
                System.out.println(list15);
                pdf.genPDF("query18", null, null, null, null, null, list15);
                break;
        }
    }

    @FXML
    void initialize() {
        claearButton.setVisible(false);
        printButton.setVisible(false);
        DBHandler dbHandler = new DBHandler();
        AbonentService abonentService = new AbonentService();
        randomtext.setVisible(false);
        choos.setStyle("-fx-font-size:20");
        choiceBar1.setStyle("-fx-font-size:20");
        tableView1.setStyle("-fx-font-size:16");
        tableView2.setStyle("-fx-font-size:16");
        tableView3.setStyle("-fx-font-size:16");
        tableView4.setStyle("-fx-font-size:16");
        tableView5.setStyle("-fx-font-size:16");
        tableView6.setStyle("-fx-font-size:16");
        tableView7.setStyle("-fx-font-size:16");
        tableView8.setStyle("-fx-font-size:16");
        choos.getItems().addAll(mainChoices);
        choos.setOnAction((ActionEvent actionEvent) -> {
            claearButton.setVisible(false);
            printButton.setVisible(false);
            combo1.setVisible(false);
            combo2.setVisible(false);

            switch (choos.getSelectionModel().getSelectedIndex()) {
                case 0:
                    disconnect.setVisible(false);
                    choiceBar1.getItems().clear();
                    choiceBar1.getItems().addAll(choices1);
                    connectButton.setVisible(true);
                    errorlabel.setText("");
                    stackpane.getChildren().clear();
                    stackpane.getChildren().add(tableView1);
                    TableColumn<Abonent, String> tel = new TableColumn("Телефон");
                    tel.setCellValueFactory(new PropertyValueFactory<>("telephone"));
                    TableColumn<Abonent, String> name = new TableColumn("Ім'я");
                    name.setCellValueFactory(new PropertyValueFactory<>("name"));
                    TableColumn<Abonent, String> surname = new TableColumn("Прізвище");
                    surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
                    TableColumn<Abonent, String> patronymic = new TableColumn("По батькові");
                    patronymic.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
                    TableColumn<Abonent, String> balance = new TableColumn("Баланс");
                    balance.setCellValueFactory(new PropertyValueFactory<>("balance"));
                    TableColumn<Abonent, String> add_info = new TableColumn("Додатково");
                    add_info.setCellValueFactory(new PropertyValueFactory<>("addInfo"));
                    ObservableList<Abonent> list1 = FXCollections.observableArrayList();
                     {
                         list1 = abonentService.getAll();
                     }
                    tableView1.getColumns().setAll(tel, name, surname, patronymic, balance, add_info);
                    tableView1.getItems().setAll(list1);
                    tableView1.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                    break;
                case 1:
                    disconnect.setVisible(false);
                    choiceBar1.getItems().clear();
                    choiceBar1.getItems().addAll(choices2);
                    connectButton.setVisible(false);
                    errorlabel.setText("");
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
                case 2:
                    disconnect.setVisible(false);
                    choiceBar1.getItems().clear();
                    choiceBar1.getItems().addAll(choices3);
                    connectButton.setVisible(false);
                    errorlabel.setText("");
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
                case 3:
                    disconnect.setVisible(false);
                    choiceBar1.getItems().clear();
                    choiceBar1.getItems().addAll(choices4);
                    connectButton.setVisible(false);
                    errorlabel.setText("");
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
                case 4:
                    connectButton.setVisible(false);
                    disconnect.setVisible(false);
                    choiceBar1.getItems().clear();
                    choiceBar1.getItems().addAll(choices5);
                    errorlabel.setText("");
                    stackpane.getChildren().clear();
                    stackpane.getChildren().add(tableView5);
                    TableColumn<Call, String> number = new TableColumn("Номер");
                    number.setCellValueFactory(new PropertyValueFactory<>("tel"));
                    TableColumn<Call, String> receiverNumber = new TableColumn("Отримуючий номер");
                    receiverNumber.setCellValueFactory(new PropertyValueFactory<>("receiverNumber"));
                    TableColumn<Call, LocalDate> date = new TableColumn("Дата");
                    date.setCellValueFactory(new PropertyValueFactory<>("date"));
                    TableColumn<Call, LocalTime> time = new TableColumn("Час");
                    time.setCellValueFactory(new PropertyValueFactory<>("time"));
                    TableColumn<Call, Integer> duration = new TableColumn("Тривалість");
                    duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
                    TableColumn<Call, Double> price3 = new TableColumn("Ціна");
                    price3.setCellValueFactory(new PropertyValueFactory<>("price"));
                    ObservableList<Call> list5 = FXCollections.observableArrayList();
                     {
                        try {
                            list5 = dbHandler.getCallList();
                        } catch (SQLException ex) {
                            Logger.getLogger(OperatorController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    tableView5.getColumns().setAll(number, receiverNumber, date, time, duration, price3);
                    tableView5.getItems().setAll(list5);
                    tableView5.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                    break;
                case 5:
                    connectButton.setVisible(false);
                    disconnect.setVisible(false);
                    choiceBar1.getItems().clear();
                    errorlabel.setText("");
                    stackpane.getChildren().clear();
                    stackpane.getChildren().add(tableView6);
                    TableColumn<Payment, String> tel1 = new TableColumn("Номер");
                    tel1.setCellValueFactory(new PropertyValueFactory<>("tel"));
                    TableColumn<Payment, String> service1 = new TableColumn("Послуга");
                    service1.setCellValueFactory(new PropertyValueFactory<>("service"));
                    TableColumn<Payment, LocalDateTime> date1 = new TableColumn("Дата");
                    date1.setCellValueFactory(new PropertyValueFactory<>("date"));
                    ObservableList<Payment> list6 = FXCollections.observableArrayList();
                     {
                        try {
                            list6 = dbHandler.getPaymentList();
                        } catch (SQLException | ClassNotFoundException ex) {
                            Logger.getLogger(OperatorController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    tableView6.getColumns().setAll(tel1, service1, date1);
                    tableView6.getItems().setAll(list6);
                    tableView6.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                    break;
                case 6:
                    connectButton.setVisible(false);
                    stackpane.getChildren().clear();
                    choiceBar1.getItems().clear();
                    stackpane.getChildren().add(tableView7);
                    disconnect.setVisible(true);
                    TableColumn<AbonentTariff, Abonent> abonent = new TableColumn("Абонент");
                    abonent.setCellValueFactory(new PropertyValueFactory<>("abonent"));
                    TableColumn<AbonentTariff, Tariff> tariff = new TableColumn("Тариф");
                    tariff.setCellValueFactory(new PropertyValueFactory<>("tariff"));
                    TableColumn<AbonentTariff, LocalDateTime> date3 = new TableColumn("Дата підключення");
                    date3.setCellValueFactory(new PropertyValueFactory<>("connectionDate"));
                    TableColumn<AbonentTariff, LocalDateTime> date4 = new TableColumn("Дата відключення");
                    date4.setCellValueFactory(new PropertyValueFactory<>("disconnectionDate"));
                    ObservableList<AbonentTariff> list7 = FXCollections.observableArrayList();
                     {
                        try {
                            list7 = dbHandler.getAbonentTariffList();
                        } catch (SQLException | ClassNotFoundException ex) {
                            Logger.getLogger(OperatorController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    tableView7.getColumns().setAll(abonent, tariff, date3, date4);
                    tableView7.getItems().setAll(list7);
                    tableView7.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                    break;
                case 7:
                    connectButton.setVisible(false);
                    disconnect.setVisible(true);
                    choiceBar1.getItems().clear();
                    errorlabel.setText("");
                    stackpane.getChildren().clear();
                    stackpane.getChildren().add(tableView8);
                    TableColumn<AbonentPrivilege, Abonent> abonent1 = new TableColumn("Абонент");
                    abonent1.setCellValueFactory(new PropertyValueFactory<>("abonent"));
                    TableColumn<AbonentPrivilege, Privilege> privilege1 = new TableColumn("Пільга");
                    privilege1.setCellValueFactory(new PropertyValueFactory<>("privilege"));
                    TableColumn<AbonentPrivilege, LocalDateTime> date5 = new TableColumn("Дата підключення");
                    date5.setCellValueFactory(new PropertyValueFactory<>("connectionDate"));
                    TableColumn<AbonentPrivilege, LocalDateTime> date6 = new TableColumn("Дата відключення");
                    date6.setCellValueFactory(new PropertyValueFactory<>("disconnectionDate"));
                    ObservableList<AbonentPrivilege> list8 = FXCollections.observableArrayList();
                     {
                        try {
                            list8 = dbHandler.getAbonentPrivilegeList();
                        } catch (Exception ex) {
                            Logger.getLogger(OperatorController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    tableView8.getColumns().setAll(abonent1, privilege1, date5, date6);
                    tableView8.getItems().setAll(list8);
                    tableView8.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                    break;
            }
        });

        choiceBar1.setOnAction((ActionEvent actionEvent) -> {
            switch (choiceBar1.getValue()) {
                case "Знайти абонента по прізвищу":
                    combo1.setVisible(false);
                    combo2.setVisible(false);
                    randomtext.setVisible(true);
                    break;
                case "Використав найбільшу кількість тарифів":
                    combo1.setVisible(false);
                    combo2.setVisible(false);
                    randomtext.setVisible(false);
                    break;
                case "Зробив більше всього дзвінків":
                    combo1.setVisible(false);
                    combo2.setVisible(false);
                    randomtext.setVisible(false);
                    break;
                case "Абоненти, що мали більше 2 тарифів":
                    combo1.setVisible(false);
                    combo2.setVisible(false);
                    randomtext.setVisible(false);
                    break;
                case "Має найбільшу суму тривалості дзвінків":
                    combo1.setVisible(false);
                    combo2.setVisible(false);
                    randomtext.setVisible(false);
                    break;
                case "Для кожного абонента максимальну оплату за послугу":
                    combo1.setVisible(false);
                    combo2.setVisible(false);
                    randomtext.setVisible(false);
                    break;
                case "Для кожного абонента максимальну вартість дзвінка":
                    combo1.setVisible(false);
                    combo2.setVisible(false);
                    randomtext.setVisible(false);
                    break;
                case "Абоненти, що не оплачували послуги за останній місяць":
                    combo1.setVisible(false);
                    combo2.setVisible(false);
                    randomtext.setVisible(false);
                    break;
                case "Абоненти, що не дзвонили за останній місяць":
                    combo1.setVisible(false);
                    combo2.setVisible(false);
                    randomtext.setVisible(false);
                    break;
                case "Пошук абонентів, що мають тариф Х":
                    combo1.setVisible(true);
                     {
                        try {
                            combo1.getItems().addAll(dbHandler.getTariffList());
                        } catch (SQLException ex) {
                            Logger.getLogger(OperatorController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    combo2.setVisible(false);
                    randomtext.setVisible(false);
                    break;

                case "Знайти тариф":
                    combo1.setVisible(false);
                    combo2.setVisible(false);
                    randomtext.setVisible(true);
                    break;
                case "Скільки абонентів обслуговуються кожним тарифом":
                    combo1.setVisible(false);
                    combo2.setVisible(false);
                    randomtext.setVisible(false);
                    break;
                case "Найбільш полпулярний тариф та невикористаний тариф":
                    combo1.setVisible(false);
                    combo2.setVisible(false);
                    randomtext.setVisible(false);
                    break;
                case "Скільки абонентів мають кожну пільгу":
                    combo1.setVisible(false);
                    combo2.setVisible(false);
                    randomtext.setVisible(false);
                    break;
                case "Найбільш популярна послуга та невикористана послуга":
                    combo1.setVisible(false);
                    combo2.setVisible(false);
                    randomtext.setVisible(false);
                    break;
                case "Дзвінки, що були здійснені абонентом Х":
                    combo1.setVisible(false);
                    combo2.setVisible(true);
                     {
                         combo2.getItems().addAll(abonentService.getAll());
                     }
                    randomtext.setVisible(false);
                    break;

            }
        });

        randombutton.setOnAction((ActionEvent actionEvent) -> {
            int key = 0;
            if (choos.getValue() == null) {
                errorlabel.setText("Оберіть список!");
                key = 1;
            }
            if (key == 0) {
                switch (choiceBar1.getValue()) {
                    case "Знайти абонента по прізвищу":
                        stackpane.getChildren().clear();
                        stackpane.getChildren().add(tableView1);
                        String a = randomtext.getText().trim();
                        TableColumn<Abonent, String> tel = new TableColumn("Телефон");
                        tel.setCellValueFactory(new PropertyValueFactory<>("telephone"));
                        TableColumn<Abonent, String> name = new TableColumn("Ім'я");
                        name.setCellValueFactory(new PropertyValueFactory<>("name"));
                        TableColumn<Abonent, String> surname = new TableColumn("Прізвище");
                        surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
                        TableColumn<Abonent, String> patronymic = new TableColumn("По батькові");
                        patronymic.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
                        TableColumn<Abonent, String> balance = new TableColumn("Баланс");
                        balance.setCellValueFactory(new PropertyValueFactory<>("balance"));
                        TableColumn<Abonent, String> add_info = new TableColumn("Додатково");
                        add_info.setCellValueFactory(new PropertyValueFactory<>("addInfo"));
                        ObservableList<Abonent> list1 = FXCollections.observableArrayList();
                         {
                             list1 = abonentService.getAbonentbySurname(a);
                         }
                        tableView1.getColumns().setAll(tel, name, surname, patronymic, balance, add_info);
                        tableView1.getItems().setAll(list1);
                        tableView1.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                        break;

                    case "Знайти тариф":
                        printButton.setVisible(false);
                        stackpane.getChildren().clear();
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
                        break;

                    case "Використав найбільшу кількість тарифів":
                        printButton.setVisible(false);
                        combo1.setVisible(false);
                        combo2.setVisible(false);
                        randomtext.setVisible(false);
                        stackpane.getChildren().clear();
                        stackpane.getChildren().add(tableView1);
                        TableColumn<Abonent, String> tel2 = new TableColumn("Телефон");
                        tel2.setCellValueFactory(new PropertyValueFactory<>("telephone"));
                        TableColumn<Abonent, String> name2 = new TableColumn("Ім'я");
                        name2.setCellValueFactory(new PropertyValueFactory<>("name"));
                        TableColumn<Abonent, String> surname2 = new TableColumn("Прізвище");
                        surname2.setCellValueFactory(new PropertyValueFactory<>("surname"));
                        TableColumn<Abonent, String> patronymic2 = new TableColumn("По батькові");
                        patronymic2.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
                        TableColumn<Abonent, String> balance2 = new TableColumn("Баланс");
                        balance2.setCellValueFactory(new PropertyValueFactory<>("balance"));
                        TableColumn<Abonent, String> add_info2 = new TableColumn("Додатково");
                        add_info2.setCellValueFactory(new PropertyValueFactory<>("addInfo"));
                        ObservableList<Abonent> list3 = FXCollections.observableArrayList();
                         {
                            dbHandler.updateAbonent1();
                             list3 = abonentService.getAll();
                         }
                        tableView1.getColumns().setAll(tel2, name2, surname2, patronymic2, balance2, add_info2);
                        tableView1.getItems().setAll(list3);
                        tableView1.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                        break;

                    case "Зробив більше всього дзвінків":
                        printButton.setVisible(false);
                        combo1.setVisible(false);
                        combo2.setVisible(false);
                        randomtext.setVisible(false);
                        stackpane.getChildren().clear();
                        stackpane.getChildren().add(tableView1);
                        TableColumn<Abonent, String> tel3 = new TableColumn("Телефон");
                        tel3.setCellValueFactory(new PropertyValueFactory<>("telephone"));
                        TableColumn<Abonent, String> name3 = new TableColumn("Ім'я");
                        name3.setCellValueFactory(new PropertyValueFactory<>("name"));
                        TableColumn<Abonent, String> surname3 = new TableColumn("Прізвище");
                        surname3.setCellValueFactory(new PropertyValueFactory<>("surname"));
                        TableColumn<Abonent, String> patronymic3 = new TableColumn("По батькові");
                        patronymic3.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
                        TableColumn<Abonent, String> balance3 = new TableColumn("Баланс");
                        balance3.setCellValueFactory(new PropertyValueFactory<>("balance"));
                        TableColumn<Abonent, String> add_info3 = new TableColumn("Додатково");
                        add_info3.setCellValueFactory(new PropertyValueFactory<>("addInfo"));
                        ObservableList<Abonent> list4 = FXCollections.observableArrayList();
                        dbHandler.updateAbonent2();
                        list4 = abonentService.getAll();
                        tableView1.getColumns().setAll(tel3, name3, surname3, patronymic3, balance3, add_info3);
                        tableView1.getItems().setAll(list4);
                        tableView1.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                        break;

                    case "Абоненти, що мали більше 2 тарифів":
                        printButton.setVisible(false);
                        combo1.setVisible(false);
                        combo2.setVisible(false);
                        randomtext.setVisible(false);
                        stackpane.getChildren().clear();
                        stackpane.getChildren().add(tableView33);
                        TableColumn<Queries20, String> tel33 = new TableColumn("Телефон");
                        tel33.setCellValueFactory(new PropertyValueFactory<>("tel_number"));
                        TableColumn<Queries20, String> name4 = new TableColumn("Ім'я");
                        name4.setCellValueFactory(new PropertyValueFactory<>("name"));
                        TableColumn<Queries20, String> surname4 = new TableColumn("Прізвище");
                        surname4.setCellValueFactory(new PropertyValueFactory<>("surname"));
                        TableColumn<Queries20, String> patronymic4 = new TableColumn("По батькові");
                        patronymic4.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
                        TableColumn<Queries20, Integer> count = new TableColumn("Кількість");
                        count.setCellValueFactory(new PropertyValueFactory<>("count"));
                        ObservableList<Queries20> list5 = FXCollections.observableArrayList();
                        try {
                            list5 = dbHandler.getAbonent3();
                        } catch (SQLException ex) {
                            Logger.getLogger(OperatorController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        tableView33.getColumns().setAll(tel33, name4, surname4, patronymic4, count);
                        tableView33.getItems().setAll(list5);
                        tableView33.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                        break;

                    case "Має найбільшу суму тривалості дзвінків":
                        printButton.setVisible(false);
                        combo1.setVisible(false);
                        combo2.setVisible(false);
                        randomtext.setVisible(false);
                        stackpane.getChildren().clear();
                        stackpane.getChildren().add(tableView44);
                        TableColumn<Queries20, String> name5 = new TableColumn("Ім'я");
                        name5.setCellValueFactory(new PropertyValueFactory<>("name"));
                        TableColumn<Queries20, String> surname5 = new TableColumn("Прізвище");
                        surname5.setCellValueFactory(new PropertyValueFactory<>("surname"));
                        TableColumn<Queries20, String> patronymic5 = new TableColumn("По батькові");
                        patronymic5.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
                        TableColumn<Queries20, Integer> sum = new TableColumn("Сума");
                        sum.setCellValueFactory(new PropertyValueFactory<>("sum"));
                        ObservableList<Queries20> list6 = FXCollections.observableArrayList();
                         {
                            try {
                                list6 = dbHandler.getCallSum();
                            } catch (SQLException ex) {
                                Logger.getLogger(OperatorController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        tableView44.getColumns().setAll(name5, surname5, patronymic5, sum);
                        tableView44.getItems().setAll(list6);
                        tableView44.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                        break;

                    case "Для кожного абонента максимальну оплату за послугу":
                        printButton.setVisible(true);
                        combo1.setVisible(false);
                        combo2.setVisible(false);
                        randomtext.setVisible(false);
                        stackpane.getChildren().clear();
                        stackpane.getChildren().add(tableView55);
                        TableColumn<Queries20, String> tel55 = new TableColumn("Телефон");
                        tel55.setCellValueFactory(new PropertyValueFactory<>("tel_number"));
                        TableColumn<Queries20, String> name6 = new TableColumn("Ім'я");
                        name6.setCellValueFactory(new PropertyValueFactory<>("name"));
                        TableColumn<Queries20, String> surname6 = new TableColumn("Прізвище");
                        surname6.setCellValueFactory(new PropertyValueFactory<>("surname"));
                        TableColumn<Queries20, String> patronymic6 = new TableColumn("По батькові");
                        patronymic6.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
                        TableColumn<Queries20, Double> price1 = new TableColumn("Ціна");
                        price1.setCellValueFactory(new PropertyValueFactory<>("price"));
                        list7 = FXCollections.observableArrayList();
                         {
                            try {
                                list7 = dbHandler.getMaxSum();
                                System.out.println(list7);
                            } catch (SQLException ex) {
                                Logger.getLogger(OperatorController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        System.out.println(list7);
                        tableView55.getColumns().setAll(tel55, name6, surname6, patronymic6, price1);
                        tableView55.getItems().setAll(list7);
                        tableView55.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                        break;

                    case "Для кожного абонента максимальну вартість дзвінка":
                        printButton.setVisible(true);
                        combo1.setVisible(false);
                        combo2.setVisible(false);
                        randomtext.setVisible(false);
                        stackpane.getChildren().clear();
                        stackpane.getChildren().add(tableView66);
                        TableColumn<MaxCallDuration, String> tel66 = new TableColumn("Телефон");
                        tel66.setCellValueFactory(new PropertyValueFactory<>("tel_number"));
                        TableColumn<MaxCallDuration, String> name66 = new TableColumn("Ім'я");
                        name66.setCellValueFactory(new PropertyValueFactory<>("name"));
                        TableColumn<MaxCallDuration, String> surname66 = new TableColumn("Прізвище");
                        surname66.setCellValueFactory(new PropertyValueFactory<>("surname"));
                        TableColumn<MaxCallDuration, String> patronymic66 = new TableColumn("По батькові");
                        patronymic66.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
                        TableColumn<MaxCallDuration, Double> max66 = new TableColumn("Максимальна вартість");
                        max66.setCellValueFactory(new PropertyValueFactory<>("max"));
                        list8 = FXCollections.observableArrayList();
                         {
                            try {
                                list8 = dbHandler.getMaxDur();
                            } catch (SQLException ex) {
                                Logger.getLogger(OperatorController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        System.out.println(list8);
                        tableView66.getColumns().setAll(tel66, name66, surname66, patronymic66, max66);
                        tableView66.getItems().setAll(list8);
                        tableView66.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                        break;

                    case "Абоненти, що не оплачували послуги за останній місяць":
                        printButton.setVisible(false);
                        combo1.setVisible(false);
                        combo2.setVisible(false);
                        randomtext.setVisible(false);
                        stackpane.getChildren().clear();
                        stackpane.getChildren().add(tableView77);
                        TableColumn<Queries20, String> tel77 = new TableColumn("Телефон");
                        tel77.setCellValueFactory(new PropertyValueFactory<>("tel_number"));
                        TableColumn<Queries20, String> name77 = new TableColumn("Ім'я");
                        name77.setCellValueFactory(new PropertyValueFactory<>("name"));
                        TableColumn<Queries20, String> surname77 = new TableColumn("Прізвище");
                        surname77.setCellValueFactory(new PropertyValueFactory<>("surname"));
                        TableColumn<Queries20, String> patronymic77 = new TableColumn("По батькові");
                        patronymic77.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
                        ObservableList<Queries20> list9 = FXCollections.observableArrayList();
                         {
                            try {
                                list9 = dbHandler.getNoPayers();
                            } catch (SQLException ex) {
                                Logger.getLogger(OperatorController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        tableView77.getColumns().setAll(tel77, name77, surname77, patronymic77);
                        tableView77.getItems().setAll(list9);
                        tableView77.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                        break;

                    case "Абоненти, що не дзвонили за останній місяць":
                        printButton.setVisible(false);
                        combo1.setVisible(false);
                        combo2.setVisible(false);
                        randomtext.setVisible(false);
                        stackpane.getChildren().clear();
                        stackpane.getChildren().add(tableView88);
                        TableColumn<Queries20, String> tel88 = new TableColumn("Телефон");
                        tel88.setCellValueFactory(new PropertyValueFactory<>("tel_number"));
                        TableColumn<Queries20, String> name88 = new TableColumn("Ім'я");
                        name88.setCellValueFactory(new PropertyValueFactory<>("name"));
                        TableColumn<Queries20, String> surname88 = new TableColumn("Прізвище");
                        surname88.setCellValueFactory(new PropertyValueFactory<>("surname"));
                        TableColumn<Queries20, String> patronymic88 = new TableColumn("По батькові");
                        patronymic88.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
                        ObservableList<Queries20> list10 = FXCollections.observableArrayList();
                         {
                            try {
                                list10 = dbHandler.getNoCallers();
                            } catch (SQLException ex) {
                                Logger.getLogger(OperatorController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        System.out.println(list10);
                        tableView88.getColumns().setAll(tel88, name88, surname88, patronymic88);
                        tableView88.getItems().setAll(list10);
                        tableView88.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                        break;
                    case "Пошук абонентів, що мають тариф Х":
                        printButton.setVisible(false);
                        combo1.setVisible(true);
                        combo2.setVisible(false);
                        randomtext.setVisible(false);
                        stackpane.getChildren().clear();
                        stackpane.getChildren().add(tableView99);
                        TableColumn<Queries20, String> tel99 = new TableColumn("Телефон");
                        tel99.setCellValueFactory(new PropertyValueFactory<>("tel_number"));
                        TableColumn<Queries20, String> name99 = new TableColumn("Ім'я");
                        name99.setCellValueFactory(new PropertyValueFactory<>("name"));
                        TableColumn<Queries20, String> surname99 = new TableColumn("Прізвище");
                        surname99.setCellValueFactory(new PropertyValueFactory<>("surname"));
                        TableColumn<Queries20, String> patronymic99 = new TableColumn("По батькові");
                        patronymic99.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
                        ObservableList<Queries20> list11 = FXCollections.observableArrayList();
                         {
                            try {
                                list11 = dbHandler.getAbonentListOnTariff(combo1.getSelectionModel().getSelectedItem());
                            } catch (SQLException ex) {
                                Logger.getLogger(OperatorController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        System.out.println(combo1.getSelectionModel().getSelectedItem());
                        tableView99.getColumns().setAll(tel99, name99, surname99, patronymic99);
                        tableView99.getItems().setAll(list11);
                        tableView99.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                        break;

                    case "Скільки абонентів обслуговуються кожним тарифом":
                        printButton.setVisible(true);
                        combo1.setVisible(false);
                        combo2.setVisible(false);
                        randomtext.setVisible(false);
                        stackpane.getChildren().clear();
                        stackpane.getChildren().add(tableView111);
                        TableColumn<GetCount, String> tar = new TableColumn("Тариф");
                        tar.setCellValueFactory(new PropertyValueFactory<>("name"));
                        TableColumn<GetCount, Integer> count2 = new TableColumn("Кількість");
                        count2.setCellValueFactory(new PropertyValueFactory<>("count"));
                        list12 = FXCollections.observableArrayList();
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
                    case "Найбільш полпулярний тариф та невикористаний тариф":
                        printButton.setVisible(true);
                        combo1.setVisible(false);
                        combo2.setVisible(false);
                        randomtext.setVisible(false);
                        printButton.setVisible(true);
                        stackpane.getChildren().clear();
                        stackpane.getChildren().add(tableView222);
                        TableColumn<GetDesc, String> tar2 = new TableColumn("Тариф");
                        tar2.setCellValueFactory(new PropertyValueFactory<>("tariff"));
                        TableColumn<GetDesc, String> desc = new TableColumn("Опис");
                        desc.setCellValueFactory(new PropertyValueFactory<>("description"));
                        list13 = FXCollections.observableArrayList();
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

                    case "Скільки абонентів мають кожну пільгу":
                        printButton.setVisible(true);
                        combo1.setVisible(false);
                        combo2.setVisible(false);
                        randomtext.setVisible(false);
                        stackpane.getChildren().clear();
                        stackpane.getChildren().add(tableView333);
                        TableColumn<GetCount1, String> pl = new TableColumn("Пільга");
                        pl.setCellValueFactory(new PropertyValueFactory<>("pl"));
                        TableColumn<GetCount1, String> desc1 = new TableColumn("Опис");
                        desc1.setCellValueFactory(new PropertyValueFactory<>("count"));
                        list14 = FXCollections.observableArrayList();
                         {
                            try {
                                list14 = dbHandler.getCount1();
                            } catch (SQLException ex) {
                                Logger.getLogger(OperatorController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                        System.out.println(list14);
                        tableView333.getColumns().setAll(pl, desc1);
                        tableView333.getItems().setAll(list14);
                        tableView333.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                        break;

                    case "Найбільш популярна послуга та невикористана послуга":
                        printButton.setVisible(true);
                        combo1.setVisible(false);
                        combo2.setVisible(false);
                        randomtext.setVisible(false);

                        printButton.setVisible(true);
                        stackpane.getChildren().clear();
                        stackpane.getChildren().add(tableView444);
                        TableColumn<GetDesc1, String> ser = new TableColumn("Послуга");
                        ser.setCellValueFactory(new PropertyValueFactory<>("service"));
                        TableColumn<GetDesc1, String> desc2 = new TableColumn("Опис");
                        desc2.setCellValueFactory(new PropertyValueFactory<>("description"));
                        list15 = FXCollections.observableArrayList();
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
                    case "Дзвінки, що були здійснені абонентом Х":
                        printButton.setVisible(false);
                        combo1.setVisible(false);
                        combo2.setVisible(true);
                        randomtext.setVisible(false);
                        stackpane.getChildren().clear();
                        stackpane.getChildren().add(tableView555);
                        TableColumn<Queries20, String> tel555 = new TableColumn("Кому");
                        tel555.setCellValueFactory(new PropertyValueFactory<>("receivingnumber"));
                        TableColumn<Queries20, String> dur555 = new TableColumn("Тривалість");
                        dur555.setCellValueFactory(new PropertyValueFactory<>("duration"));
                        TableColumn<Queries20, Double> price555 = new TableColumn("Ціна");
                        price555.setCellValueFactory(new PropertyValueFactory<>("price"));
                        TableColumn<Queries20, LocalDate> date555 = new TableColumn("Дата");
                        date555.setCellValueFactory(new PropertyValueFactory<>("date"));
                        TableColumn<Queries20, LocalTime> time555 = new TableColumn("Час");
                        time555.setCellValueFactory(new PropertyValueFactory<>("time"));
                        ObservableList<Queries20> list16 = FXCollections.observableArrayList();
                         {
                            try {
                                list16 = dbHandler.getCallsOnAbonent(combo2.getSelectionModel().getSelectedItem());
                            } catch (SQLException ex) {
                                Logger.getLogger(OperatorController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        System.out.println(combo2.getSelectionModel().getSelectedItem());
                        tableView555.getColumns().setAll(tel555, dur555, price555, date555, time555);
                        tableView555.getItems().setAll(list16);
                        tableView555.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                        break;
                }
            }

        });
        connectButton.setOnAction(actionEvent -> {
            int key3 = 0;
            if (tableView1.getSelectionModel().getSelectedItem() == null) {
                errorlabel.setText("Оберіть рядок!");
                key3 = 1;
            }
            if (key3 == 0) {
                outerkeyAbonentTariff = 0;
                abonent = tableView1.getSelectionModel().getSelectedItem();
                connectButton.getScene().getWindow().hide();
                String src = "/com/shend/graphicFiles/newConnection.fxml";
                LoadHelper helper = LoadHelper.getInstance();
                helper.loadWindow(src);
            }

        });
        newbar.setOnAction(actionEvent -> {
            if (choos.getValue() == null) {
                errorlabel.setText(" Оберіть список!");
            }
            if (choos.getSelectionModel().getSelectedIndex() == 0) {
                outerkeyAbonent = 0;
                newbar.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                String src = "/com/shend/graphicFiles/newAbonent.fxml";
                LoadHelper helper = LoadHelper.getInstance();
                helper.loadWindow(src);
            }
            if (choos.getSelectionModel().getSelectedIndex() == 1) {
                outerkeyTariff = 0;
                newbar.getScene().getWindow().hide();
                String src = "/com/shend/graphicFiles/newTariff.fxml";
                LoadHelper helper = LoadHelper.getInstance();
                helper.loadWindow(src);
            }
            if (choos.getSelectionModel().getSelectedIndex() == 2) {
                outerkeyPrivilege = 0;
                newbar.getScene().getWindow().hide();
                String src = "/com/shend/graphicFiles/newPrivilege.fxml";
                LoadHelper helper = LoadHelper.getInstance();
                helper.loadWindow(src);
            }
            if (choos.getSelectionModel().getSelectedIndex() == 3) {
                outerkeyService = 0;
                newbar.getScene().getWindow().hide();
                String src = "/com/shend/graphicFiles/newService.fxml";
                LoadHelper helper = LoadHelper.getInstance();
                helper.loadWindow(src);
            }
        });

        claearButton.setOnAction(actionEvent -> {
            try {
                dbHandler.clearJournalLog();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(OperatorController.class.getName()).log(Level.SEVERE, null, ex);
            }
            tableViewJournal.getItems().clear();
        });
        journal.setOnAction(actionEvent -> {
            claearButton.setVisible(true);
            errorlabel.setText("");
            stackpane.getChildren().clear();
            stackpane.getChildren().add(tableViewJournal);
            TableColumn<JournalLog, String> login = new TableColumn("Логін");
            login.setCellValueFactory(new PropertyValueFactory<>("login"));
            TableColumn<JournalLog, LocalDateTime> time = new TableColumn("Час");
            time.setCellValueFactory(new PropertyValueFactory<>("time"));
            TableColumn<JournalLog, String> description = new TableColumn("Опис");
            description.setCellValueFactory(new PropertyValueFactory<>("description"));
            ObservableList<JournalLog> list = FXCollections.observableArrayList();
            {
                try {
                    list = dbHandler.getJournalList();
                } catch (SQLException ex) {
                    Logger.getLogger(OperatorController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            tableViewJournal.getColumns().setAll(login, time, description);
            tableViewJournal.getItems().setAll(list);
            tableViewJournal.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        });
        editBar.setOnAction(actionEvent -> {
            int key = 0;
            if (choos.getValue() == null) {
                errorlabel.setText(" Оберіть список!");
                key = 1;
            }
            if (key == 0) {
                switch (choos.getSelectionModel().getSelectedIndex()) {
                    case 0:
                        if (tableView1.getSelectionModel().getSelectedItem() == null) {
                            errorlabel.setText(" Оберіть рядок!");
                            key = 1;
                        }
                        if (key == 0) {
                            errorlabel.setText("");
                            outerkeyAbonent = 1;
                            abonent = tableView1.getSelectionModel().getSelectedItem();
                            editBar.getScene().getWindow().hide();
                            String src = "/com/shend/graphicFiles/newAbonent.fxml";
                            LoadHelper helper = LoadHelper.getInstance();
                            helper.loadWindow(src);
                        }
                        break;
                    case 1:
                        if (tableView2.getSelectionModel().getSelectedItem() == null) {
                            errorlabel.setText(" Оберіть рядок!");
                            key = 1;
                        }
                        if (key == 0) {
                            errorlabel.setText("");
                            outerkeyTariff = 1;
                            tariff = tableView2.getSelectionModel().getSelectedItem();
                            editBar.getScene().getWindow().hide();
                            String src = "/com/shend/graphicFiles/newTariff.fxml";
                            LoadHelper helper = LoadHelper.getInstance();
                            helper.loadWindow(src);
                        }
                        break;
                    case 2:
                        if (tableView3.getSelectionModel().getSelectedItem() == null) {
                            errorlabel.setText(" Оберіть рядок!");
                            key = 1;
                        }
                        if (key == 0) {
                            errorlabel.setText("");
                            outerkeyPrivilege = 1;
                            privilege = tableView3.getSelectionModel().getSelectedItem();
                            editBar.getScene().getWindow().hide();
                            String src = "/com/shend/graphicFiles/newPrivilege.fxml";
                            LoadHelper helper = LoadHelper.getInstance();
                            helper.loadWindow(src);
                        }
                        break;
                    case 3:
                        if (tableView4.getSelectionModel().getSelectedItem() == null) {
                            errorlabel.setText(" Оберіть рядок!");
                            key = 1;
                        }
                        if (key == 0) {
                            errorlabel.setText("");
                            outerkeyService = 1;
                            service = tableView4.getSelectionModel().getSelectedItem();
                            editBar.getScene().getWindow().hide();
                            String src = "/com/shend/graphicFiles/newService.fxml";
                            LoadHelper helper = LoadHelper.getInstance();
                            helper.loadWindow(src);
                        }
                        break;
                    case 6:
                        if (tableView7.getSelectionModel().getSelectedItem() == null) {
                            errorlabel.setText(" Оберіть рядок!");
                            key = 1;
                        }
                        if (key == 0) {
                            errorlabel.setText("");
                            outerkeyAbonentTariff = 1;
                            abonentTariff = tableView7.getSelectionModel().getSelectedItem();
                            editBar.getScene().getWindow().hide();
                            String src = "/com/shend/graphicFiles/newConnection.fxml";
                            LoadHelper helper = LoadHelper.getInstance();
                            helper.loadWindow(src);
                        }
                        break;
                    case 7:
                        if (tableView8.getSelectionModel().getSelectedItem() == null) {
                            errorlabel.setText(" Оберіть рядок!");
                            key = 1;
                        }
                        if (key == 0) {
                            errorlabel.setText("");
                            outerkeyAbonentPrivilege = 1;
                            abonentPrivilege = tableView8.getSelectionModel().getSelectedItem();
                            editBar.getScene().getWindow().hide();
                            String src = "/com/shend/graphicFiles/newConnection.fxml";
                            LoadHelper helper = LoadHelper.getInstance();
                            helper.loadWindow(src);
                        }
                        break;
                }
            }
        });

        deletebutton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int key = 0;
                if (choos.getValue() == null) {
                    errorlabel.setText("Оберіть список!");
                    key = 1;
                }
                if (key == 0) {
                    switch (choos.getSelectionModel().getSelectedIndex()) {
                        case 0:
                            key = 0;
                            if (tableView1.getSelectionModel().getSelectedItem() == null) {
                                errorlabel.setText(" Оберіть рядок!");
                                key = 1;
                            }
                            if (key == 0) {
                                errorlabel.setText("");
                                try {
                                    abonentService.delete(tableView1.getSelectionModel().getSelectedItem());
                                } catch (SQLException | ClassNotFoundException ex) {
                                    Logger.getLogger(OperatorController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                Abonent selectedItem = tableView1.getSelectionModel().getSelectedItem();
                                tableView1.getItems().remove(selectedItem);
                                errorlabel.setText("");
                            }
                        case 1:
                            key = 0;
                            if (tableView2.getSelectionModel().getSelectedItem() == null) {
                                errorlabel.setText(" Оберіть рядок!");
                                key = 1;
                            }
                            if (key == 0) {
                                errorlabel.setText("");
                                try {
                                    dbHandler.deleteTariff(tableView2.getSelectionModel().getSelectedItem());
                                } catch (SQLException | ClassNotFoundException ex) {
                                    Logger.getLogger(OperatorController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                Tariff selectedItem = tableView2.getSelectionModel().getSelectedItem();
                                tableView2.getItems().remove(selectedItem);
                                errorlabel.setText("");
                            }
                            break;
                        case 2:
                            key = 0;
                            if (tableView3.getSelectionModel().getSelectedItem() == null) {
                                errorlabel.setText(" Оберіть рядок!");
                                key = 1;
                            }
                            if (key == 0) {
                                errorlabel.setText("");
                                try {
                                    dbHandler.deletePrivilege(tableView3.getSelectionModel().getSelectedItem());
                                } catch (SQLException | ClassNotFoundException ex) {
                                    Logger.getLogger(OperatorController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                Privilege selectedItem = tableView3.getSelectionModel().getSelectedItem();
                                tableView3.getItems().remove(selectedItem);
                                errorlabel.setText("");
                            }
                            break;
                        case 3:
                            key = 0;
                            errorlabel.setText("");
                            if (tableView4.getSelectionModel().getSelectedItem() == null) {
                                errorlabel.setText(" Оберіть рядок!");
                                key = 1;
                            }
                            if (key == 0) {
                                errorlabel.setText("");
                                try {
                                    dbHandler.deleteService(tableView4.getSelectionModel().getSelectedItem());
                                } catch (SQLException | ClassNotFoundException ex) {
                                    Logger.getLogger(OperatorController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                Service selectedItem = tableView4.getSelectionModel().getSelectedItem();
                                tableView4.getItems().remove(selectedItem);
                                errorlabel.setText("");
                            }
                            break;
                        case 4:
                            errorlabel.setText("");
                            key = 0;
                            if (tableView5.getSelectionModel().getSelectedItem() == null) {
                                errorlabel.setText(" Оберіть рядок!");
                                key = 1;
                            }
                            if (key == 0) {
                                errorlabel.setText("");
                                try {
                                    dbHandler.deleteCall(tableView5.getSelectionModel().getSelectedItem());
                                } catch (SQLException | ClassNotFoundException ex) {
                                    Logger.getLogger(OperatorController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                Call selectedItem = tableView5.getSelectionModel().getSelectedItem();
                                tableView5.getItems().remove(selectedItem);
                                errorlabel.setText("");
                            }
                            break;
                        case 5:
                            key = 0;
                            errorlabel.setText("");
                            if (tableView6.getSelectionModel().getSelectedItem() == null) {
                                errorlabel.setText(" Оберіть рядок!");
                                key = 1;
                            }
                            if (key == 0) {
                                errorlabel.setText("");
                                try {
                                    dbHandler.deletePayment(tableView6.getSelectionModel().getSelectedItem());
                                } catch (SQLException | ClassNotFoundException ex) {
                                    Logger.getLogger(OperatorController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                Payment selectedItem = tableView6.getSelectionModel().getSelectedItem();
                                tableView6.getItems().remove(selectedItem);
                                errorlabel.setText("");
                            }
                            break;
                        case 6:
                            key = 0;
                            errorlabel.setText("");
                            if (tableView7.getSelectionModel().getSelectedItem() == null) {
                                errorlabel.setText(" Оберіть рядок!");
                                key = 1;
                            }
                            if (key == 0) {
                                errorlabel.setText("");
                                try {
                                    dbHandler.deleteAbonentTariff(tableView7.getSelectionModel().getSelectedItem());
                                } catch (SQLException | ClassNotFoundException ex) {
                                    Logger.getLogger(OperatorController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                AbonentTariff selectedItem = tableView7.getSelectionModel().getSelectedItem();
                                tableView7.getItems().remove(selectedItem);
                                errorlabel.setText("");
                            }
                            break;
                        case 7:
                            key = 0;
                            errorlabel.setText("");
                            if (tableView8.getSelectionModel().getSelectedItem() == null) {
                                errorlabel.setText(" Оберіть рядок!");
                                key = 1;
                            }
                            if (key == 0) {
                                errorlabel.setText("");
                                try {
                                    dbHandler.deleteAbonentPrivilege(tableView8.getSelectionModel().getSelectedItem());
                                } catch (SQLException | ClassNotFoundException ex) {
                                    Logger.getLogger(OperatorController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                AbonentPrivilege selectedItem = tableView8.getSelectionModel().getSelectedItem();
                                tableView8.getItems().remove(selectedItem);
                                errorlabel.setText("");
                            }
                            break;
                    }
                }
            }
        });

        backbutton.setOnAction(actionEvent
                -> {
            backbutton.getScene().getWindow().hide();
            String src = "/com/shend/graphicFiles/simple.fxml";
            LoadHelper helper = LoadHelper.getInstance();
            helper.loadWindow(src);
        });

    }
}
