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
    private PasswordField fieldPassword;

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
            RequestController.RegistrationAdmin(fio[0], fio[1], mail, login, pass);
        }
    }

    @FXML
    void exitUserMenu(ActionEvent event) throws IOException {
        if (RequestController.user.getStatus().equals("admin")) {
            Client.setRoot("AdminMenu");
        } else {
            Client.setRoot("UserMenu");
        }
    }

    @FXML
    void exitInMainWindow(ActionEvent event) throws IOException {
        Client.setRoot("AdminMenu");
    }
}
