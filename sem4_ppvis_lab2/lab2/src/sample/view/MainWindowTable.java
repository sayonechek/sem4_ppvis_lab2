package sample.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.controller.Controller;
import sample.model.Page;
import sample.model.Student;

public class MainWindowTable {
    private Group group = new Group();
    private ObservableList<Student> students = FXCollections.observableArrayList();
    private Controller controller;
    private Label pageCountLabel = new Label();
    private Label totalRecordsCountLabel = new Label();
    private Label pageNumberLabel = new Label();
    private int pageCount = 1;
    private int totalRecordsCount = 0;
    private int pageNumber = 1;
    private int recordsOnPageCount = 10;
    public Page currentPage;
    public MainWindowTable(Controller controller){
        this.controller = controller;

        Label chooseRecordsOnPageCountLabel = new Label("Choose record count");
        chooseRecordsOnPageCountLabel.setLayoutY(385);

        ObservableList<Integer> recordsCountValues = FXCollections.observableArrayList(5, 10, 15);
        ComboBox <Integer> chooseRecordsOnPageCountValueComboBox = new ComboBox<>(recordsCountValues);
        chooseRecordsOnPageCountValueComboBox.setValue(10);
        chooseRecordsOnPageCountValueComboBox.setLayoutY(385);
        chooseRecordsOnPageCountValueComboBox.setLayoutX(120);

        chooseRecordsOnPageCountValueComboBox.setOnAction(actionEvent -> {
            recordsOnPageCount = chooseRecordsOnPageCountValueComboBox.getValue();
            pageNumber = 1;
            updateTable();
        });

        TableView<Student> table = new TableView<>(students);
        TableColumn<Student, String> fullNameColumn = new TableColumn<>("full name");
        fullNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("fullName"));
        fullNameColumn.setPrefWidth(150);
        table.getColumns().add(fullNameColumn);

        TableColumn<Student, Integer> groupColumn = new TableColumn<>("group");
        groupColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("group"));
        groupColumn.setPrefWidth(80);
        table.getColumns().add(groupColumn);

        TableColumn<Student, Integer> numberOfAbsencesDueToIllnessColumn = new TableColumn<>("due to illness");
        numberOfAbsencesDueToIllnessColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("numberOfAbsencesDueToIllness"));
        numberOfAbsencesDueToIllnessColumn.setPrefWidth(130);

        TableColumn<Student, Integer> numberOfAbsencesDueToAnotherReasonColumn = new TableColumn<>("another reason");
        numberOfAbsencesDueToAnotherReasonColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("numberOfAbsencesDueToAnotherReason"));
        numberOfAbsencesDueToAnotherReasonColumn.setPrefWidth(130);

        TableColumn<Student, Integer> numberOfUnjustifiedAbsencesColumn = new TableColumn<>("without good reason");
        numberOfUnjustifiedAbsencesColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("numberOfUnjustifiedAbsences"));
        numberOfUnjustifiedAbsencesColumn.setPrefWidth(130);

        TableColumn<Student, Integer> totalOfAbsences = new TableColumn<>("total");
        totalOfAbsences.setCellValueFactory(new PropertyValueFactory<Student, Integer>("totalOfAbsences"));
        totalOfAbsences.setPrefWidth(130);

        TableColumn<Student, Integer> numberOfPassesPerYearColumn = new TableColumn<>("number of passes per year");


        numberOfPassesPerYearColumn.getColumns().addAll(numberOfAbsencesDueToIllnessColumn, numberOfAbsencesDueToAnotherReasonColumn,
                numberOfUnjustifiedAbsencesColumn,totalOfAbsences);
        table.getColumns().add(numberOfPassesPerYearColumn);
        table.setPrefWidth(750);
        table.setPrefHeight(385);

        pageCountLabel.setText("Page count: " + pageCount);
        pageCountLabel.setLayoutY(420);

        pageNumberLabel.setText("Page number: " + pageNumber);
        pageNumberLabel.setLayoutY(460);
        pageNumberLabel.setLayoutX(330);

        totalRecordsCountLabel.setText("Total records count: " + totalRecordsCount);
        totalRecordsCountLabel.setLayoutY(450);

        Button firstPageButton = new Button("First page");
        firstPageButton.setOnAction(actionEvent -> {
            pageNumber = 1;
            updateTable();
        });
        firstPageButton.setLayoutY(420);
        firstPageButton.setLayoutX(250);

        Button nextPageButton = new Button("=>");
        nextPageButton.setOnAction(actionEvent -> {
            if(pageNumber!=pageCount){
                pageNumber++;
                updateTable();
            }
        });
        nextPageButton.setLayoutY(420);
        nextPageButton.setLayoutX(380);

        Button previousPageButton = new Button("<=");
        previousPageButton.setOnAction(actionEvent -> {
            if (pageNumber!=1){
                pageNumber--;
                updateTable();
            }
        });
        previousPageButton.setLayoutY(420);
        previousPageButton.setLayoutX(330);

        Button lastPageButton = new Button("Last page");
        lastPageButton.setOnAction(actionEvent -> {
            pageNumber = pageCount;
            updateTable();
        });
        lastPageButton.setLayoutY(420);
        lastPageButton.setLayoutX(425);

        group.getChildren().addAll(table, chooseRecordsOnPageCountLabel, chooseRecordsOnPageCountValueComboBox,
                pageCountLabel, pageNumberLabel, totalRecordsCountLabel, firstPageButton, nextPageButton,
                previousPageButton, lastPageButton);
    }

    public Group getGroup() {return group;}

    public void updateTable(){


        updateCurrentPage();

        students.setAll(currentPage.getStudents());
        pageCount = currentPage.getPageCount();
        pageCountLabel.setText("Page Count: " + getPageCount());
        totalRecordsCount = currentPage.getTotalRecordsCount();
        totalRecordsCountLabel.setText("Total records count: " + getTotalRecordsCount());
        pageNumberLabel.setText("Page number: " + currentPage.getPageNumber());
    }

    public void updateCurrentPage(){
        setCurrentPage(controller.updateMainWindowTableView(pageNumber, recordsOnPageCount));
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setCurrentPage(Page  currentPage) {this.currentPage = currentPage;}

    public int getPageCount(){
        return this.pageCount;
    }

    public int getRecordsOnPageCount() {
        return recordsOnPageCount;
    }

    public int getTotalRecordsCount(){
        return this.totalRecordsCount;
    }
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}
