package sample.view;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.controller.Controller;

public class DeleteWindow {
    public DeleteWindow(Controller controller, MainWindowTable table){
        SearchDeleteGroup deleteGroup = new SearchDeleteGroup(controller, table, "Delete");

        BorderPane layout = new BorderPane();
        layout.setCenter(deleteGroup.getGroup());
        Stage stage = new Stage();
        stage.setTitle("Delete students");
        stage.setScene(new Scene(layout, 400, 250));
        stage.show();
    }
}
