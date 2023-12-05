package com.vadim;

import javafx.fxml.FXML;

import java.awt.*;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class WindowsController {
    @FXML
    public TextField textFieldLogin;
    public TextField textFieldPassword;
    public Button primaryButton;

    public void buttonEnterAction(ActionEvent event) throws IOException {
        String login;
        String pass;
        login = textFieldLogin.getText();
        pass = textFieldPassword.getText();
        //try {
            //RequestController.Authorization(login, pass);
        //} catch (ClassNotFoundException e) {
        //    throw new RuntimeException(e);
        //}
    }

    @FXML
    private void buttonRegisterAction(ActionEvent event) throws IOException {
        Client.setRoot("secondary");
    }

    public static void ThrowAlert(String title, String header, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }


}
