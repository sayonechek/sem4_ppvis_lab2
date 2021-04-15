package sample.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.controller.Controller;


public class AddStudentWindow {
    private Controller controller;
    private MainWindowTable table;

    public AddStudentWindow(MainWindowTable table, Stage primaryStage, Controller controller){
        this.controller = controller;
        this.table = table;
        VBox layout = getWindowLayout();


        Stage newWindow = new Stage();
        newWindow.setTitle("Information");
        newWindow.setScene(new Scene(layout));

        newWindow.show();
    }
    private VBox getWindowLayout(){
        VBox adding = new VBox();
        adding.setPadding(new Insets(10, 20, 20, 20));
        //adding.setSpacing(10);

        Label fullName = new Label("Full name");
        TextField fullNameInput = new TextField();
        fullNameInput.setMinWidth(300);
        adding.getChildren().addAll(fullName, fullNameInput);

        Label group = new Label("Group");
        TextField groupInput = new TextField();
        groupInput.setMinWidth(300);
        adding.getChildren().addAll(group, groupInput);

        Label numberOfAbsencesDueToIllness = new Label("Due to illness");
        TextField numberOfAbsencesDueToIllnessInput = new TextField();
        numberOfAbsencesDueToIllnessInput.setMinWidth(300);
        adding.getChildren().addAll(numberOfAbsencesDueToIllness, numberOfAbsencesDueToIllnessInput);

        Label numberOfAbsencesDueToAnotherReason = new Label("Due to another reason");
        TextField numberOfAbsencesDueToAnotherReasonInput = new TextField();
        numberOfAbsencesDueToAnotherReasonInput.setMinWidth(300);
        adding.getChildren().addAll(numberOfAbsencesDueToAnotherReason, numberOfAbsencesDueToAnotherReasonInput);

        Label numberOfUnjustifiedAbsences = new Label("Without good reason");
        TextField numberOfUnjustifiedAbsencesInput = new TextField();
        numberOfUnjustifiedAbsencesInput.setMinWidth(300);
        adding.getChildren().addAll(numberOfUnjustifiedAbsences, numberOfUnjustifiedAbsencesInput);

        Button addInfo = new Button("Enter");
        adding.getChildren().add(addInfo);
        addInfo.setOnAction(actionEvent -> {
            if (fullNameInput.getText().isEmpty() || groupInput.getText().isEmpty() ||
            numberOfAbsencesDueToIllnessInput.getText().isEmpty() || numberOfAbsencesDueToAnotherReasonInput.getText().isEmpty()
            || numberOfUnjustifiedAbsencesInput.getText().isEmpty()){
                Alert warning = new Alert(Alert.AlertType.WARNING);
                warning.setTitle("Warning");
                warning.setContentText("Some fields are empty.");
                warning.show();
                return;

            }

            int totalAbsences=0;
            try {
                totalAbsences = Integer.parseInt(numberOfAbsencesDueToIllnessInput.getText()) +
                        Integer.parseInt(numberOfAbsencesDueToAnotherReasonInput.getText()) +
                        Integer.parseInt(numberOfUnjustifiedAbsencesInput.getText());
                int temp_group = Integer.parseInt(groupInput.getText());
            } catch (NumberFormatException e) {
                Alert warning = new Alert(Alert.AlertType.WARNING);
                warning.setTitle("Warning");
                warning.setContentText("Incorrect data!!!");
                warning.show();
                return;
            }
            controller.addStudentToArray(fullNameInput.getText(), Integer.parseInt(groupInput.getText()),
                    Integer.parseInt(numberOfAbsencesDueToIllnessInput.getText()), Integer.parseInt(numberOfAbsencesDueToAnotherReasonInput.getText()),
                    Integer.parseInt(numberOfUnjustifiedAbsencesInput.getText()), totalAbsences);

            table.updateTable();


            Stage stage = (Stage) adding.getScene().getWindow();
            stage.close();
        });



        return adding;
    }
}
