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

public class MainMenuAdmin {

    @FXML
    private TableView<Car> allCarsTable;
    @FXML
    private TableView<User> allUsersTable;
    @FXML
    private TableView<User> allAdminsTable;
    @FXML
    private TableView<SuperMMID> userIncomingRequest;
    @FXML
    private TableView<SuperMMID> acceptedRequests;


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
    private TableColumn<SuperMMID, Integer> idColumn1;
    @FXML
    private TableColumn<SuperMMID, String> makeColumn2;
    @FXML
    private TableColumn<SuperMMID, String> modelColumn2;
    @FXML
    private TableColumn<SuperMMID, Integer> idColumn2;


    @FXML
    private Label nameLabel;


    @FXML
    private TextField searchTextField;
    @FXML
    private TextField idTextField;
    @FXML
    private TextField idTextField1;
    @FXML
    private TextField idTextField2;
    @FXML
    private TextField idTextField3;


    ObservableList<Car> CarsData = FXCollections.observableArrayList();
    ObservableList<User> Users = FXCollections.observableArrayList();
    ObservableList<User> Admins = FXCollections.observableArrayList();
    ObservableList<SuperMMID> IncomingRequests = FXCollections.observableArrayList();
    ObservableList<SuperMMID> AcceptedRequests = FXCollections.observableArrayList();


    public MainMenuAdmin() {
        Client.changeStageSize(Client.st, 915, 680);
    }

    @FXML
    public void initialize() throws IOException, ClassNotFoundException {
        idLabel.setText(Integer.toString(RequestController.user.getID()));
        nameLabel.setText(RequestController.user.getName() + " " + RequestController.user.getSurname());
        emailLabel.setText(RequestController.user.getMail());
        loginLabel.setText(RequestController.user.getLogin());

        makeColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("make"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("model"));
        bodyColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("body"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Car, Integer>("price"));

        idColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("surname"));
        mailColumn.setCellValueFactory(new PropertyValueFactory<User, String>("mail"));
        loginColumn.setCellValueFactory(new PropertyValueFactory<User, String>("login"));

        idColumn11.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        nameColumn11.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        surnameColumn11.setCellValueFactory(new PropertyValueFactory<User, String>("surname"));
        mailColumn11.setCellValueFactory(new PropertyValueFactory<User, String>("mail"));
        loginColumn11.setCellValueFactory(new PropertyValueFactory<User, String>("login"));

        makeColumn1.setCellValueFactory(new PropertyValueFactory<SuperMMID, String>("make"));
        idColumn1.setCellValueFactory(new PropertyValueFactory<SuperMMID, Integer>("id"));
        mailColumn1.setCellValueFactory(new PropertyValueFactory<SuperMMID, String>("mail"));

        makeColumn2.setCellValueFactory(new PropertyValueFactory<SuperMMID, String>("make"));
        modelColumn2.setCellValueFactory(new PropertyValueFactory<SuperMMID, String>("model"));
        idColumn2.setCellValueFactory(new PropertyValueFactory<SuperMMID, Integer>("id"));

        ArrayList<Car> cars = RequestController.GetCarsTable();
        for (var c : cars) {
            CarsData.add(c);
        }

        ArrayList<User> users = RequestController.GetAllUsers();
        for (var c : users) {
            Users.add(c);
        }
        allUsersTable.setItems(Users);

        ArrayList<User> admins = RequestController.GetAllAdmins();
        for (var c : admins) {
            Admins.add(c);
        }
        allAdminsTable.setItems(Admins);

        ArrayList<SuperMMID> incoming = RequestController.GetIncoming_();
        for (var c : incoming)
            IncomingRequests.add(c);
        userIncomingRequest.setItems(IncomingRequests);

        ArrayList<SuperMMID> accepted = RequestController.GetAccepted();
        for (var c : accepted)
            AcceptedRequests.add(c);
        acceptedRequests.setItems(AcceptedRequests);
    }

    @FXML
    void ExitAction(ActionEvent event) {

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

    @FXML
    void BlockUser(ActionEvent event) throws IOException, ClassNotFoundException {
        String id = idTextField.getText();
        try {
            Integer i = Integer.parseInt(id);
            RequestController.BlockUser(i);
        } catch (NumberFormatException e) {
            EnterController.ThrowAlert("Error", "", "Wrong id");
        }
    }

    @FXML
    void UnblockUser(ActionEvent event) throws IOException, ClassNotFoundException {
        String id = idTextField2.getText();
        try {
            Integer i = Integer.parseInt(id);
            RequestController.UnblockUser(i);
        } catch (NumberFormatException e) {
            EnterController.ThrowAlert("Error", "", "Wrong id");
        }
    }
    @FXML
    void DeleteUser(ActionEvent event) throws IOException, ClassNotFoundException {
        String id = idTextField.getText();
        System.out.println(id);
        try {
            Integer i = Integer.parseInt(id);
            RequestController.DeleteAcc(i);
            Users.clear();
            Admins.clear();
            ArrayList<User> users = RequestController.GetAllUsers();
            for (var c : users) {
                Users.add(c);
            }
            allAdminsTable.setItems(Admins);
        } catch (NumberFormatException e) {
            EnterController.ThrowAlert("Error", "", "Wrong id");
        }
    }

    @FXML
    void AddAdmin(ActionEvent event) throws IOException, ClassNotFoundException {
        Client.setRoot("regAdmin");
    }

    @FXML
    void AcceptRequest(ActionEvent event) throws IOException, ClassNotFoundException {
        String[] make_id;
        String field = idTextField.getText();
        Integer ID;
        make_id = field.split(" ");
        if (field.isEmpty()) {
            EnterController.ThrowAlert("Error", "Error", "Something is empty");
        } else if (!CheckerInput.validateMakeID(field))
            EnterController.ThrowAlert("Error", "Error", "Wrong id");
        else {
            ID = Integer.parseInt(make_id[1]);
            if (ID == RequestController.user.getId())
                EnterController.ThrowAlert("Error", "Error", "Wrong id");
            else {
                RequestController.AcceptRequest(ID, make_id[0]);
                AcceptedRequests.clear();
                var accepted = RequestController.GetAccepted();
                for (var c : accepted) {
                    AcceptedRequests.add(c);
                }
                IncomingRequests.clear();
                var incoming = RequestController.GetIncoming_();
                for (var c : incoming) {
                    IncomingRequests.add(c);
                }
            }
        }

    }
}
