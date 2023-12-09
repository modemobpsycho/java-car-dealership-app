package com.vadim.fxml_controllers;

import com.vadim.Car;
import com.vadim.RequestController;
import com.vadim.User;
import com.vadim.Client;
import com.vadim.tables.IdMailMake;
import com.vadim.tables.SuperMMID;
import com.vadim.CheckerInput;
import com.vadim.tables.Tables;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainMenuUser {

    @FXML
    private TableView<Car> allCarsTable;
    @FXML
    private TableView<Tables> OldCarsTable;
    @FXML
    private TableView<Car> NewCarsTable;
    @FXML
    private TableView<IdMailMake> userIncomingRequest;
    @FXML
    private TableView<IdMailMake> userSendedRequest;


    @FXML
    private TableColumn<Car, String> makeColumn;
    @FXML
    private TableColumn<Car, String> modelColumn;
    @FXML
    private TableColumn<Car, String> bodyColumn;
    @FXML
    private TableColumn<Car, Integer> priceColumn;


    @FXML
    private TableColumn<IdMailMake, String> makeColumn_2;
    @FXML
    private TableColumn<IdMailMake, Integer> idColumn_2;
    @FXML
    private TableColumn<IdMailMake, String> mailColumn_2;

    @FXML
    private TableColumn<IdMailMake, String> makeColumn_21;
    @FXML
    private TableColumn<IdMailMake, Integer> idColumn_21;
    @FXML
    private TableColumn<IdMailMake, String> mailColumn_21;


    @FXML
    private TableColumn<Tables, Integer> idColumn1;
    @FXML
    private TableColumn<Tables, String> makeColumn1;
    @FXML
    private TableColumn<Tables, String> modelColumn1;
    @FXML
    private TableColumn<Tables, String> bodyColumn1;
    @FXML
    private TableColumn<Tables, String> mailColumn1;
    @FXML
    private TableColumn<Tables, Integer> priceColumn1;


    @FXML
    private TableColumn<Car, String> makeColumn11;
    @FXML
    private TableColumn<Car, String> modelColumn11;
    @FXML
    private TableColumn<Car, String> bodyColumn11;
    @FXML
    private TableColumn<Car, Integer> priceColumn11;

    @FXML
    private ComboBox<String> bodyBox;
    @FXML
    private TextField priceField;
    @FXML
    private TextField makeField;
    @FXML
    private TextField modelField;


    @FXML
    private TextField searchTextField;
    @FXML
    private TextField searchTextField1;
    @FXML
    private TextField makeIDTextField;


    @FXML
    private TextField searchTextField11;
    @FXML
    private TextField makeModelTextField;


    @FXML
    private AnchorPane OldCarsPane;
    @FXML
    private AnchorPane NewCarsPane;


    @FXML
    private Label emailLabel;
    @FXML
    private Label idLabel;
    @FXML
    private Label loginLabel;
    @FXML
    private Label nameLabel;

    @FXML
    private Label mailLabel1;
    @FXML
    private Label loginLabel1;
    @FXML
    private Label nameLabel1;

    @FXML
    private TitledPane sendedTitled;
    @FXML
    private Button soldButton;
    @FXML
    private TitledPane soldTitled;
    @FXML
    private Label priceLabel;
    @FXML
    private Button searchButton;
    @FXML
    private Button searchButton1;
    @FXML
    private Button searchButton2;
    @FXML
    private Button sendButton;
    @FXML
    private Button sendButton1;
    @FXML
    private Button backButton1;
    @FXML
    private Button backButton2;
    @FXML
    private Label modelLabel;
    @FXML
    private Label makeidLabel;
    @FXML
    private Label makeidLabel1;
    @FXML
    private Button newButton;
    @FXML
    private Button oldButton;
    @FXML
    private Label makeLabel;
    @FXML
    private TitledPane incomingTitled;
    @FXML
    private Tab infoTab;
    @FXML
    private TitledPane infoTitled;
    @FXML
    private TitledPane allTitled;
    @FXML
    private Label bodyLabel;
    @FXML
    private MenuItem changeItem;
    @FXML
    private Menu changeMenu;
    @FXML
    private MenuItem deleteItem;
    @FXML
    private MenuItem exitItem;
    @FXML
    private Menu exitMenu;
    @FXML
    private Label header;


    private ObservableList<Car> CarsData = FXCollections.observableArrayList();
    private ObservableList<IdMailMake> SendedRequests = FXCollections.observableArrayList();
    private ObservableList<IdMailMake> IncomingRequests = FXCollections.observableArrayList();


    private ObservableList<Tables> Data = FXCollections.observableArrayList();
    private ObservableList<Car> NewData = FXCollections.observableArrayList();

    private ObservableList<String> bodies = FXCollections.observableArrayList();

    private static boolean called = false;


    public MainMenuUser() {
        Client.changeStageSize(Client.st, 915, 680);
    }

    @FXML
    public void initialize() throws IOException, ClassNotFoundException {
        //ЗДЕСЬ МОЖНО УЗНАТЬ ЮЗ КАСИКИ
        header.setText("User menu");
        exitMenu.setText("Exit");
        exitItem.setText("Exit from acc");
        changeMenu.setText("Change");
        changeItem.setText("Change acc");
        deleteItem.setText("Delete acc");

        infoTitled.setText("Personal info");
        infoTab.setText("Main info");
        nameLabel1.setText("Surname and name");
        mailLabel1.setText("Mail");
        loginLabel1.setText("Login");

        newButton.setText("Buy new car");
        oldButton.setText("Buy old car");
        soldTitled.setText("Sold car");
        makeLabel.setText("Make");
        modelLabel.setText("Model");
        bodyLabel.setText("Body");
        priceLabel.setText("Price");
        makeField.setPromptText("Make");
        modelField.setPromptText("Model");
        bodyBox.setPromptText("Body");
        priceField.setPromptText("Price");
        soldButton.setText("Sold");

        allTitled.setText("Show all cars");
        searchTextField.setPromptText("Find by make");
        searchTextField1.setPromptText("Find by make");
        searchTextField11.setPromptText("Find by make");

        searchButton.setText("Search");
        makeColumn1.setText("Make");
        makeColumn_2.setText("Make");
        makeColumn11.setText("Make");
        makeColumn_21.setText("Make");
        makeColumn.setText("Make");

        modelColumn1.setText("Model");
        modelColumn11.setText("Model");
        modelColumn.setText("Model");

        bodyColumn1.setText("Body");
        bodyColumn11.setText("Body");
        bodyColumn.setText("Body");

        priceColumn.setText("Price");
        priceColumn1.setText("Price");
        priceColumn11.setText("Price");

        idColumn1.setText("User ID");
        idColumn_2.setText("User ID");
        idColumn_21.setText("User ID");

        mailColumn1.setText("Mail");
        mailColumn_2.setText("Mail");
        mailColumn_21.setText("Mail");

        incomingTitled.setText("Incoming requests");
        sendedTitled.setText("Sended requests");

        makeIDTextField.setPromptText("Make and ID");
        makeModelTextField.setPromptText("Make and Model");
        searchButton1.setText("Search");
        searchButton2.setText("Search");
        sendButton.setText("Send");
        sendButton1.setText("Send");
        backButton1.setText("Back");
        backButton2.setText("Back");

        makeidLabel.setText("Enter make and user ID");
        makeidLabel1.setText("Enter make and model");


        idLabel.setText(Integer.toString(RequestController.user.getId()));
        nameLabel.setText(RequestController.user.getName() + " " + RequestController.user.getSurname());
        emailLabel.setText(RequestController.user.getMail());
        loginLabel.setText(RequestController.user.getLogin());

        ArrayList<Car> cars = RequestController.GetCarsTable();
        ArrayList<IdMailMake> sended = RequestController.GetSended();
        ArrayList<IdMailMake> incoming = RequestController.GetIncoming();

        bodies.setAll("Хэтчбэк", "Купе", "Седан", "Универсал", "Минивен", "Родстер", "Внедорожник", "Кроссовер", "Пикап", "Кабриолет", "Лимузин");
        bodyBox.setItems(bodies);

        for (var c : cars) {
            CarsData.add(c);
        }

        for (var c : sended) {
            SendedRequests.add(c);
        }

        for (var c : incoming) {
            IncomingRequests.add(c);
        }

        makeColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("make"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("model"));
        bodyColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("body"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Car, Integer>("price"));

        makeColumn1.setCellValueFactory(new PropertyValueFactory<Tables, String>("make"));
        modelColumn1.setCellValueFactory(new PropertyValueFactory<Tables, String>("model"));
        bodyColumn1.setCellValueFactory(new PropertyValueFactory<Tables, String>("body"));
        priceColumn1.setCellValueFactory(new PropertyValueFactory<Tables, Integer>("price"));
        mailColumn1.setCellValueFactory(new PropertyValueFactory<Tables, String>("mail"));
        idColumn1.setCellValueFactory(new PropertyValueFactory<Tables, Integer>("userID"));

        makeColumn11.setCellValueFactory(new PropertyValueFactory<Car, String>("make"));
        modelColumn11.setCellValueFactory(new PropertyValueFactory<Car, String>("model"));
        bodyColumn11.setCellValueFactory(new PropertyValueFactory<Car, String>("body"));
        priceColumn11.setCellValueFactory(new PropertyValueFactory<Car, Integer>("price"));

        makeColumn_21.setCellValueFactory(new PropertyValueFactory<IdMailMake, String>("make"));
        idColumn_21.setCellValueFactory(new PropertyValueFactory<IdMailMake, Integer>("id"));
        mailColumn_21.setCellValueFactory(new PropertyValueFactory<IdMailMake, String>("mail"));

        makeColumn_2.setCellValueFactory(new PropertyValueFactory<IdMailMake, String>("make"));
        idColumn_2.setCellValueFactory(new PropertyValueFactory<IdMailMake, Integer>("id"));
        mailColumn_2.setCellValueFactory(new PropertyValueFactory<IdMailMake, String>("mail"));


        allCarsTable.setItems(CarsData);
        userSendedRequest.setItems(SendedRequests);
        userIncomingRequest.setItems(IncomingRequests);

    }


    @FXML
    void ComeBackFromOldTable(ActionEvent event) {
        OldCarsPane.setVisible(false);
    }

    @FXML
    void ComeBackFromNewTable(ActionEvent event) {
        NewCarsPane.setVisible(false);
    }

    void BuyOldCar(ActionEvent event) throws IOException, ClassNotFoundException {
        ArrayList<Tables> cars = RequestController.GetOldCars();
        Data.clear();
        for (var c : cars) {
            Data.add(c);
        }
        OldCarsTable.setItems(Data);
        OldCarsPane.setVisible(true);
    }

    @FXML
    void ExitAction(ActionEvent event) {

    }

    @FXML
    void BuyNewCar(ActionEvent event) throws IOException, ClassNotFoundException {
        ArrayList<Car> cars = RequestController.GetNewCars();
        NewData.clear();
        for (var c : cars) {
            NewData.add(c);
        }
        NewCarsTable.setItems(NewData);
        NewCarsPane.setVisible(true);
    }

    @FXML
    void SendRequestOld(ActionEvent event) throws IOException, ClassNotFoundException {
        String[] fio;
        String name = makeIDTextField.getText();
        Integer ID;
        fio = name.split(" ");
        if (name.isEmpty()) {
            EnterController.ThrowAlert("Error", "Error", "Something is empty");
        } else if (!CheckerInput.validateMakeID(name))
            EnterController.ThrowAlert("Error", "Error", "Wrong format");
        else {
            ID = Integer.parseInt(fio[1]);
            if (ID == RequestController.user.getId())
                EnterController.ThrowAlert("Error", "Error", "Wrong id");
            else {
                System.out.println(RequestController.user.getId() + fio[0] + ID);
                RequestController.AddRequest(RequestController.user.getId(), fio[0], ID);
                ArrayList<IdMailMake> sended = RequestController.GetSended();
                SendedRequests.clear();
                for (var c : sended) {
                    SendedRequests.add(c);
                }

            }
        }
    }

    @FXML
    void SendRequestNew(ActionEvent event) throws IOException, ClassNotFoundException {
        String[] fio;
        String name = makeModelTextField.getText();
        fio = name.split(" ");
        if (name.isEmpty()) {
            EnterController.ThrowAlert("Error", "Error", "Something is empty");
        } else if (!CheckerInput.validateMakeModel(name))
            EnterController.ThrowAlert("Error", "Error", "Wrong format");
        else {
            RequestController.AddRequestNewCar(RequestController.user.getId(), fio[0], fio[1]);
            ArrayList<IdMailMake> sended = RequestController.GetSended();
            SendedRequests.clear();
            for (var c : sended)
                SendedRequests.add(c);
        }
    }

    @FXML
    void SearchButton(ActionEvent event) {
        String make = searchTextField.getText();
        if (!make.isEmpty()) {
            ObservableList<Car> arr = FXCollections.observableArrayList();
            for (var c : CarsData) {
                if (c.getMake().equals(make)) {
                    arr.add(c);
                }
            }
            allCarsTable.setItems(arr);
        } else {
            allCarsTable.setItems(CarsData);
        }
    }

    @FXML
    void SearchButtonInOld(ActionEvent event) {
        String make = searchTextField1.getText();
        if (!make.isEmpty()) {
            ObservableList<Tables> arr = FXCollections.observableArrayList();
            for (var c : Data) {
                if (c.getMake().equals(make)) {
                    arr.add(c);
                }
            }
            OldCarsTable.setItems(arr);
        } else {
            OldCarsTable.setItems(Data);
        }
    }

    @FXML
    void SearchButtonInNew(ActionEvent event) {
        String make = searchTextField11.getText();
        if (!make.isEmpty()) {
            ObservableList<Car> arr = FXCollections.observableArrayList();
            for (var c : NewData) {
                if (c.getMake().equals(make)) {
                    arr.add(c);
                }
            }
            NewCarsTable.setItems(arr);
        } else {
            NewCarsTable.setItems(NewData);
        }
    }

    @FXML
    void SoldCar(ActionEvent event) throws IOException, ClassNotFoundException {
        Integer pr;
        try {
            pr = Integer.parseInt(priceField.getText());
            String body = bodyBox.getValue();
            if (makeField.getText().equals("") || modelField.getText().equals("") || body.isEmpty() || pr <= 0) {
                EnterController.ThrowAlert("Error", "Error", "Something is empty");
            } else {
                Car car = new Car(makeField.getText(), modelField.getText(),
                        bodyBox.getValue(), pr, Integer.parseInt(idLabel.getText()));

                if (RequestController.SoldCar(car)) {
                    CarsData.add(car);
                    allCarsTable.setItems(CarsData);
                }
            }
        } catch (NumberFormatException | NullPointerException e) {
            EnterController.ThrowAlert("Error", "Error", "Something is wrong");
        }
    }

    @FXML
    void changeUserInfo(ActionEvent event) throws IOException, ClassNotFoundException {
        Client.setRoot("changeUserInfo");
    }

    @FXML
    void comeBack(ActionEvent event) throws IOException {
        Client.changeStageSize(Client.st, 640, 480);
        Client.setRoot("mainWindow");
    }

    @FXML
    void deleteAcc(ActionEvent event) throws IOException, ClassNotFoundException {
        RequestController.DeleteAcc(RequestController.user.getId());
    }


}
