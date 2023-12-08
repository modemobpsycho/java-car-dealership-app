package com.vadim.fxml_controllers;

import java.io.IOException;
import java.util.function.UnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.ListChangeListener.Change;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import com.vadim.RequestController;
import com.vadim.*;
import org.w3c.dom.Text;

public class RegistrationController {

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
    private Label loginLabel;
    @FXML
    private Label passLabel;
    @FXML
    private Label backButton;
    @FXML
    private Label registrationLabel;

    @FXML
    public void initialize() {
        backButton.setText("Back");
        nameLabel.setText("Surname and name");
        mailLabel.setText("Mail");
        loginLabel.setText("Login");
        passLabel.setText("Pass");
        registrationLabel.setText("Registration");

        fieldName.setPromptText("Surname and name");
        fieldEmail.setPromptText("Email");
        fieldLogin.setPromptText("Login");
        fieldPassword.setPromptText("Pass");
        buttonConfirmRegistration.setText("Register");
        ;
    }

    @FXML
    void RegistrateUser(ActionEvent event) throws IOException, ClassNotFoundException {
        String[] fio;

        String name = fieldName.getText();
        fio = name.split(" ");
        String mail = fieldEmail.getText();
        String login = fieldLogin.getText();
        String pass = fieldPassword.getText();

        if (name.isEmpty() && mail.isEmpty() && pass.isEmpty()) {
            EnterController.ThrowAlert("Error", "Error", "All is empty");
        } else if (!CheckerInput.validateEmail(mail) && !mail.isEmpty())
            EnterController.ThrowAlert("Error", "Error", "Wrong mail format");
        else if (!CheckerInput.validateName(name) && !name.isEmpty())
            EnterController.ThrowAlert("Error", "Error", "Wrong name format");
        else {
            RequestController.Registration(fio[0], fio[1], mail, login, pass);
        }
    }

    @FXML
    void exitInMainWindow(ActionEvent event) throws IOException, ClassNotFoundException {
        Client.setRoot("mainWindow");
    }
}