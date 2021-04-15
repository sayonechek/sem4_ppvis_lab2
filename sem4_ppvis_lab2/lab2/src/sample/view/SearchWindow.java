package sample.view;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.controller.Controller;

public class SearchWindow {
    public SearchWindow( Controller controller){
        SearchWindowTable table = new SearchWindowTable(controller);
        SearchDeleteGroup searchGroup = new SearchDeleteGroup(controller, table, "Search");

        BorderPane layout = new BorderPane();

        layout.setLeft(searchGroup.getGroup());
        layout.setRight(table.getGroup());

        Stage stage = new Stage();
        stage.setScene(new Scene(layout, 1100, 520));
        stage.setTitle("Search Students");
        stage.show();

    }

}
