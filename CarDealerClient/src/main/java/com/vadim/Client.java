package com.vadim;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.io.*;
import java.net.*;

public class Client {

    private static Scene scene;
    public static Socket server;
    public static ObjectOutputStream ostream;
    public static ObjectInputStream istream;
    public static Stage st;

    //@Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("mainWindow"), 600, 405);
        stage.setScene(scene);
        stage.setTitle("Автосалончик");
        stage.setResizable(false);
        st = stage;
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void changeStageSize(Window stage, int width, int height) {
        stage.setWidth(width);
        stage.setHeight(height);
    }

    public static void main(String[] args) throws IOException {
        ConnnectToServer();
    }

    public static void ConnnectToServer() {
        try {
            System.out.println("----Connection to server----");
            server = new Socket("127.0.0.1", 8080);
            System.out.println("----Success----");
            ostream = new ObjectOutputStream(Client.server.getOutputStream());
            istream = new ObjectInputStream(Client.server.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}