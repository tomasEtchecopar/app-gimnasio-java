package com.gimnasio.appgimnasio;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.awt.*;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage)  {
        //escena
        Button btn = new Button("Ingresar");
        btn.setOnAction(e -> System.out.println("¡Bienvenido!"));

        StackPane root = new StackPane(btn);
        Scene scene = new Scene(root, 400, 300);

        stage.setTitle("¡Bienvenido a nuestra app de entrenamieno!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}