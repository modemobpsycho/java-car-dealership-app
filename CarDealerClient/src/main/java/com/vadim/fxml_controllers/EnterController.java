package com.vadim.fxml_controllers;

import java.io.IOException;

import com.vadim.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

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
    public Button languageButton;

    @FXML
    public Label header;
    @FXML
    public Label passLabel;
    @FXML
    public Label loginLabel;


    @FXML
    public void initialize() {
        if (Client.language.equals("eng")) {
            languageButton.setText("Language: eng");
            header.setText("Carsalon");
            passLabel.setText("Pass");
            loginLabel.setText("Login");
            buttonSignIn.setText("Sign in");
            buttonRegister.setText("Registration");
            textFieldLogin.setPromptText("Login");
            textFieldPassword.setPromptText("Pass");
        } else {
            languageButton.setText("Язык: rus");
            header.setText("Автосалон");
            passLabel.setText("Пароль");
            loginLabel.setText("Логин");
            buttonSignIn.setText("Вход");
            buttonRegister.setText("Регистрация");
            textFieldLogin.setPromptText("Логин");
            textFieldPassword.setPromptText("Пароль");
        }
    }


    @FXML
    private void ChangeLanguage() {
        if (Client.language.equals("eng"))
            Client.language = "rus";
        else
            Client.language = "eng";
        initialize();
    }

    @FXML
    private void buttonEnterAction(ActionEvent event) throws IOException, ClassNotFoundException {
        //System.out.println("oki");
        String login;
        String pass;
        login = textFieldLogin.getText();
        pass = textFieldPassword.getText();
        //Client.setRoot("mainWindow");
        RequestController.Authorization(login, pass);
    }

    @FXML
    private void buttonRegisterAction(ActionEvent event) throws IOException {
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
