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

public class UserInfo {

    @FXML
    private Button buttonApply;

    @FXML
    private TextField fieldEmail;

    @FXML
    private TextField fieldLogin;

    @FXML
    private TextField fieldName;

    @FXML
    private PasswordField fieldPassword;

    @FXML
    private Label header;
    @FXML
    private Label subtitle;
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

    public UserInfo() {
        Client.changeStageSize(Client.st, 631, 434);
    }

    @FXML
    public void initialize() {
        header.setText("Change user info");
        subtitle.setText("Enter only what you want to change");
        backButton.setText("Back");
        nameLabel.setText("Surname and name");
        mailLabel.setText("Mail");
        loginLabel.setText("Login");
        passLabel.setText("Pass");
        buttonApply.setText("Apply");

        fieldName.setPromptText("Surname and name");
        fieldEmail.setPromptText("Email");
        fieldLogin.setPromptText("Login");
        fieldPassword.setPromptText("Pass");
    }

    @FXML
    void ApplyChanges(ActionEvent event) throws IOException, ClassNotFoundException {
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
            if (!name.isEmpty())
                RequestController.ChangeUserInfo(fio[0], fio[1], mail, login, pass);
            else
                RequestController.ChangeUserInfo("", "", mail, login, pass);
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
}
