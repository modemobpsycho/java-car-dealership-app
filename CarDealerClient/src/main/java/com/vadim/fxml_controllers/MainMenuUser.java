package com.vadim.fxml_controllers;

import com.vadim.Car;
import com.vadim.User;
import com.vadim.CheckerInput;
import com.vadim.RequestController;
import com.vadim.Client;
import com.vadim.tables.SuperMMID;
import com.vadim.tables.Tables;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;

public class MainMenuUser {

    @FXML
    private TableView<Car> allCarsTable;
    @FXML
    private TableView<User> allUserTable;
    @FXML
    private TableView<User> allAdminsTable;
    @FXML
    private TableView<SuperMMID> userIncomingRequest;


    @FXML
    private Label emailLabel;
    @FXML
    private Label idLabel;
    @FXML
    private Label loginLabel;


    @FXML
    private TableColumn<Car, String> makeColumn;
    @FXML
    private TableColumn<Car, String> modelColumn;
    @FXML
    private TableColumn<Car, String> bodyColumn;
    @FXML
    private TableColumn<Car, Integer> priceColumn;


    @FXML
    private TableColumn<User, Integer> idColumn;
    @FXML
    private TableColumn<User, String> nameColumn;
    @FXML
    private TableColumn<User, String> surnameColumn;
    @FXML
    private TableColumn<User, String> loginColumn;
    @FXML
    private TableColumn<User, String> mailColumn;


    @FXML
    private TableColumn<User, Integer> idColumn11;
    @FXML
    private TableColumn<User, String> nameColumn11;
    @FXML
    private TableColumn<User, String> surnameColumn11;
    @FXML
    private TableColumn<User, String> loginColumn11;
    @FXML
    private TableColumn<User, String> mailColumn11;


    @FXML
    private TableColumn<SuperMMID, String> mailColumn1;
    @FXML
    private TableColumn<SuperMMID, String> makeColumn1;
    @FXML
    private TableColumn<SuperMMID, String> idColumn1;
    @FXML
    private TableColumn<SuperMMID, String> makeColumn2;
    @FXML
    private TableColumn<SuperMMID, String> modelColumn2;
    @FXML
    private TableColumn<SuperMMID, Integer> idColumn2;


    @FXML
    private Label nameLabel;


    @FXML
    private TextField SearchTextField;
    @FXML
    private TextField idTextField;
    @FXML
    private TextField idTextField1;
    @FXML
    private TextField idTextField2;
    @FXML
    private TextField idTextField3;


    private ObservableList<Car> CarsData = FXCollections.observableArrayList();
    private ObservableList<User> Users = FXCollections.observableArrayList();
    private ObservableList<User> Admins = FXCollections.observableArrayList();
    private ObservableList<SuperMMID> IncomingRequests = FXCollections.observableArrayList();
    private ObservableList<SuperMMID> AcceptedRequests = FXCollections.observableArrayList();

}
