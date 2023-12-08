package com.vadim.fxml_controllers;

import com.vadim.Car;
import com.vadim.RequestController;
import com.vadim.User;
import com.vadim.Client;
import com.vadim.tables.SuperMMID;

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

import java.io.IOException;
import java.util.ArrayList;

public class MainMenuUser {

    @FXML
    private TableView<Car> allCarsTable;
    @FXML
    private TableView<Tables> OldCarsTable;
    @FXML
    private TableView<Car> NewCarsTable;
    @FXML
    private TableView<SuperMMID> userIncomingRequest;
    @FXML
    private TableView<SuperMMID> userSendedRequest;



    @FXML
    private TableColumn<Car, String> makeColumn;
    @FXML
    private TableColumn<Car, String> modelColumn;
    @FXML
    private TableColumn<Car, String> bodyColumn;
    @FXML
    private TableColumn<Car, Integer> priceColumn;



    @FXML
    private TableColumn<SuperMMID, String> makeColumn_2;
    @FXML
    private TableColumn<SuperMMID, Integer> idColumn_2;
    @FXML
    private TableColumn<SuperMMID, String> mailColumn_2;

    @FXML
    private TableColumn<SuperMMID, String> makeColumn_21;
    @FXML
    private TableColumn<SuperMMID, Integer> idColumn_21;
    @FXML
    private TableColumn<SuperMMID, String> mailColumn_21;



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
    private Button  backButton2;
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
    private ObservableList<SuperMMID> SendedRequests = FXCollections.observableArrayList();
    private ObservableList<SuperMMID> IncomingRequests = FXCollections.observableArrayList();



    private ObservableList<Tables> Data = FXCollections.observableArrayList();
    private ObservableList<Car> NewData = FXCollections.observableArrayList();

    private ObservableList<String> bodies = FXCollections.observableArrayList();

    private static boolean called=false;


    public MainMenuUser() {
        Client.changeStageSize(Client.st, 915, 680);
    }

    @FXML
    public void initialize() throws IOException, ClassNotFoundException {
        header.setText("User menu");
        exitMenu.setText("Exit");
        exitItem.set("Exit from acc");
    }

}
