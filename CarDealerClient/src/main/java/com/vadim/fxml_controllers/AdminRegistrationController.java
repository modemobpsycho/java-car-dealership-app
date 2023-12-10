package com.vadim.fxml_controllers;

import java.io.IOException;

import com.vadim.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AdminRegistrationController {


    @FXML
    private Button buttonConfirmRegistration;

    @FXML
    private TextField fieldEmail;

    @FXML
    private TextField fieldLogin;

    @FXML
    private TextField fieldName;

    @FXML
    private TextField fieldPassword;


    @FXML
    private Label nameLabel;
    @FXML
    private Label mailLabel;
    @FXML
    private Label loginLabel;
    @FXML
    private Label passLabel;
    @FXML
    private Button backButton;
    @FXML
    private Label header;

    public AdminRegistrationController() {
        Client.changeStageSize(Client.st, 640, 480);
    }

    @FXML
    public void initialize() {
        if (Client.language.equals("eng")) {
            header.setText("Add admin");
            backButton.setText("Back");
            nameLabel.setText("Surname and name");
            mailLabel.setText("Mail");
            loginLabel.setText("Login");
            passLabel.setText("Pass");
            buttonConfirmRegistration.setText("Continue");

            fieldName.setPromptText("Surname and name");
            fieldEmail.setPromptText("Email");
            fieldLogin.setPromptText("Login");
            fieldPassword.setPromptText("Pass");
        }
    }

    @FXML
    void RegistrateUser(ActionEvent event) throws IOException, ClassNotFoundException {
        String[] fio;
        String name = fieldName.getText();
        fio = name.split(" ");
        String mail = fieldEmail.getText();
        String login = fieldLogin.getText();
        String pass = fieldPassword.getText();
        if (name.isEmpty() || mail.isEmpty() || login.isEmpty() || pass.isEmpty()) {
            EnterController.ThrowAlert("Error", "Error", "Something is empty");
        } else if (!CheckerInput.validateEmail(mail))
            EnterController.ThrowAlert("Error", "Error", "Wrong mail format");
        else if (!CheckerInput.validateName(name))
            EnterController.ThrowAlert("Error", "Error", "Wrong name format");
        else {
            RequestController.RegistrationAdmin(fio[0], fio[1], mail, login, pass);
        }
    }

    @FXML
    void exitInMainWindow(ActionEvent event) throws IOException {
        Client.setRoot("AdminMenu");

    }


}