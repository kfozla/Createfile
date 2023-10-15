
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        BorderPane pane =new BorderPane();

        HBox hBox =new HBox();
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(15, 12, 15,12));
        hBox.setAlignment(Pos.CENTER);

        Label lbl =new Label("Do you wish to Create File?");
        lbl.setAlignment(Pos.TOP_CENTER);
        lbl.setPadding(new Insets(20,0,0,20));
        lbl.setFont(new Font("Arial",15));

        Button btn1=new Button("Create File");
        btn1.setPrefSize(80,40);
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Action(stage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Button btn2=new Button("Cancel");
        btn2.setPrefSize(80,40);
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        hBox.getChildren().addAll(btn1,btn2);

        pane.setCenter(hBox);
        pane.setTop(lbl);
        Scene scene =new Scene(pane,300,200);
        stage.setScene(scene);
        stage.setTitle("app");
        stage.show();
        stage.setResizable(false);
    }
    public void Action (Stage stg) throws IOException {
        Label Filename = new Label("Enter File name:");
        Label Content = new Label("Enter Content:");
        TextField tf1 = new TextField();
        TextField tf2 = new TextField();
        Button b = new Button("Save");

        GridPane root =new GridPane();
        root.addRow(0,Filename,tf1);
        root.addRow(1,Content,tf2);
        root.addRow(2,b);
        Scene scene =new Scene(root,300,100);
        stg.setScene(scene);
        stg.setTitle("app");
        stg.show();
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                File file =new File("/Users/yusuf/Desktop/"+tf1.getText());
                if (!file.exists()){
                    System.out.println("file created");
                }
                else {
                    System.out.println("file already exist");
                    Stage stg =new Stage();
                    BorderPane border =new BorderPane();
                    Scene scn =new Scene(border,300,100);
                    Label lbl =new Label("file already exist cannot create file");
                    border.setCenter(lbl);
                    stg.setTitle("app");
                    stg.setScene(scn);
                    stg.show();
                    stg.setResizable(false);
                }
                FileWriter writer = null;
                try {
                    writer = new FileWriter(file);
                } catch (IOException a) {
                    throw new RuntimeException(a);
                }
                try {
                    writer.write(tf2.getText());
                } catch (IOException b) {
                    throw new RuntimeException(b);
                }
                try {
                    writer.close();
                } catch (IOException c) {
                    throw new RuntimeException(c);
                }
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}
