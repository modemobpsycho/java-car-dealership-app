package com.vadim.fxml_controllers;

import java.io.IOException;
import java.util.function.UnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.ListChangeListener.Change;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import com.vadim.RequestController;
import com.vadim.*;
import org.w3c.dom.Text;

public class EnterController {
    @FXML
    public TextField textFieldLogin;
    @FXML
    public TextField textFieldPassword;
    @FXML
    public Button buttonSignIn;
    @FXML
    public Button buttonRegister;
    @FXML
    public Label header;
    @FXML
    public Label passLabel;
    @FXML
    public Label loginLabel;

    @FXML
    public void initialize() {
        header.setText("Car salon");
        passLabel.setText("Pass");
        loginLabel.setText("Login");
        buttonSignIn.setText("Sign in");
        buttonRegister.setText("Registration");
        textFieldLogin.setPromptText("Login");
        textFieldPassword.setPromptText("Pass");
    }

    @FXML
    private void buttonEnterAction(ActionEvent event) throws IOException, ClassNotFoundException {
        String login;
        String pass;
        login = textFieldLogin.getText();
        pass = textFieldPassword.getText();
        RequestController.Authorization(login, pass);
    }

    @FXML
    private void buttonRegisterAction(ActionEvent event) throws IOException, ClassNotFoundException {
        Client.setRoot("registration");
    }

    public static void ThrowAlert(String title, String header, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }

    public static void ThrowMessage(String title, String header, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }
}
