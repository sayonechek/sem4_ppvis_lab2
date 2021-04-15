package sample.view;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.xml.sax.SAXException;
import sample.controller.Controller;
import sample.controller.DOMparser;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

public class MainWindow extends Application {

    private Controller controller = new Controller();
    private MainWindowTable mainTable = new MainWindowTable(controller);
    private Stage primaryStage;
    private SAXParser saxParser;
    private DOMparser domParser = new DOMparser();

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane root = new BorderPane();

        MenuBar menuBar = getMenuBar();
        root.setTop(menuBar);

        Group tableBar = mainTable.getGroup();
        root.setRight(tableBar);

        root.setLeft(getToolBar());
        
        this.primaryStage = primaryStage;
        primaryStage.setScene(new Scene(root, 810, 520));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    private MenuBar getMenuBar() {

        MenuItem saveItem = new MenuItem("Save");
        saveItem.setOnAction(actionEvent -> {
            try {
                saveTableData();
            }
            catch (TransformerException | ParserConfigurationException e) {
                e.printStackTrace();
            }
        });

        MenuItem getItem = new MenuItem("Open");
        getItem.setOnAction(actionEvent -> {
            try {
                getTableDataFromFile();
            }
            catch (IOException | ParserConfigurationException | SAXException e) {
                e.printStackTrace();
            }
        });



        Menu fileMenu = new Menu("File");
        fileMenu.getItems().addAll(getItem, saveItem);

        MenuItem addItem = new MenuItem("Add");
        addItem.setOnAction(actionEvent -> new AddStudentWindow(mainTable, primaryStage, controller));

        MenuItem deleteItem = new MenuItem("Delete");
        deleteItem.setOnAction(actionEvent -> new DeleteWindow(controller, mainTable));

        MenuItem searchItem = new MenuItem("Search");
        searchItem.setOnAction(actionEvent -> new SearchWindow(controller));

        Menu patientMenu = new Menu("Student");
        patientMenu.getItems().addAll(addItem, searchItem, deleteItem);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, patientMenu);
        return menuBar;
    }

    private VBox getToolBar(){


        Button saveButton = new Button("Save");
        saveButton.setMinWidth(60);
        saveButton.setOnAction(actionEvent -> {
            try {
                saveTableData();
            }
            catch (TransformerException | ParserConfigurationException e) {
                e.printStackTrace();
            }
        });

        Button openButton = new Button("Open");
        openButton.setMinWidth(60);
        openButton.setOnAction(actionEvent -> {
            try {
                getTableDataFromFile();
            }
            catch (IOException | ParserConfigurationException | SAXException e) {
                e.printStackTrace();
            }
        });
        Button addButton = new Button("Add");
        addButton.setMinWidth(60);
        addButton.setOnAction(actionEvent -> new AddStudentWindow(mainTable, primaryStage, controller));

        Button deleteButton = new Button("Delete");
        deleteButton.setMinWidth(60);
        deleteButton.setOnAction(actionEvent -> new DeleteWindow(controller, mainTable));

        Button searchButton = new Button("Search");
        searchButton.setMinWidth(60);
        searchButton.setOnAction(actionEvent -> new SearchWindow(controller));

        VBox  vBox= new VBox(saveButton, openButton, addButton, deleteButton, searchButton);
        return vBox;
    }


    public void saveTableData() throws TransformerException, ParserConfigurationException {

        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(primaryStage);

        if (file != null) {
            controller.saveTableData(file, domParser);
        }
    }

    public void getTableDataFromFile() throws IOException, SAXException, ParserConfigurationException {

        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(primaryStage);

        if (file != null) {
            controller.getTableDataFromFile(file, saxParser);
            mainTable.updateTable();
            mainTable.setPageNumber(1);
        }
    }
}
