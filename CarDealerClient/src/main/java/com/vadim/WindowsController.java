package com.vadim;

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

    @FXML
    public TextField textFieldPassword;

    @FXML
    public Button primaryButton;

    @FXML
    private void buttonEnterAction(ActionEvent event) throws IOException, ClassNotFoundException {
        String login;
        String pass;
        login=textFieldLogin.getText();
        pass=textFieldPassword.getText();
        RequestController.Authorization(login,pass);
    }

    @FXML
    private void buttonRegisterAction(ActionEvent event) throws IOException {
        Client.setRoot("secondary");
    }

    public static void ThrowAlert(String title,String header,String content)
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }
}
