package sample.view;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.controller.Controller;


public class SearchDeleteGroup {
    private Group group = new Group();
    private Controller controller;
    private MainWindowTable table;
    public SearchDeleteGroup(Controller controller, MainWindowTable table, String searchOrDelete){
        this.controller = controller;
        this.table = table;
        Label searchByLabel = new Label(searchOrDelete + " by");
        searchByLabel.setLayoutY(20);
        searchByLabel.setLayoutX(100);

        ObservableList<String> chooseSearchParametersValues = FXCollections.observableArrayList(
          "Group number or Second name", "Second name or type of pass", "Second name or number of passes by type"
        );
        ComboBox<String> chooseSearchParameters = new ComboBox<>(chooseSearchParametersValues);
        chooseSearchParameters.setLayoutX(30);
        chooseSearchParameters.setLayoutY(40);
        group.getChildren().addAll(searchByLabel, chooseSearchParameters);

        chooseSearchParameters.setOnAction(actionEvent -> {
            switch (chooseSearchParameters.getValue()){
                case "Group number or Second name":
                    groupNumberOrSecondNamePanel(searchOrDelete);
                    break;
                case "Second name or type of pass" :
                    secondNameOrTypeOfPassPanel(searchOrDelete);
                    break;
                case "Second name or number of passes by type":
                    secondNameOrNumberOfPassesByTypePanel(searchOrDelete);
                    break;
            }
        });

    }
    private Label getFirstParameterLabel(String parameterName) {

        Label label = new Label(parameterName);
        label.setLayoutX(30);
        label.setLayoutY(70);

        return label;
    }

    private Label getSecondParameterLabel(String parameterName) {

        Label label = new Label(parameterName);
        label.setLayoutX(30);
        label.setLayoutY(100);

        return label;
    }

    public Group getGroup(){
        return this.group;
    }
    private void groupNumberOrSecondNamePanel(String searchOrDelete){
        Button groupNumberOrSecondNameButton  = new Button(searchOrDelete);
        groupNumberOrSecondNameButton.setLayoutX(100);
        groupNumberOrSecondNameButton.setLayoutY(130);

        TextField groupInput = new TextField();
        groupInput.setLayoutX(110);
        groupInput.setLayoutY(70);
        groupInput.setMinWidth(170);

        TextField secondNameInput = new TextField();
        secondNameInput.setLayoutX(110);
        secondNameInput.setLayoutY(100);
        secondNameInput.setMinWidth(170);

        group.getChildren().remove(2, group.getChildren().size());

        group.getChildren().addAll(groupInput, secondNameInput, getFirstParameterLabel("Group"),
                getSecondParameterLabel("Second name"), groupNumberOrSecondNameButton);

        groupNumberOrSecondNameButton.setOnAction(actionEvent1 -> {

            int groupNumber;
            try {
                groupNumber = Integer.parseInt(groupInput.getText());
            } catch (NumberFormatException e) {
                groupNumber = -1;
            }

            if (searchOrDelete.equals("Search")){

                    controller.searchByGroupAndSecondName(groupNumber,
                        secondNameInput.getText());
            } else{

                int deleteNumber = controller.deleteByGroupAndSecondName(groupNumber,
                        secondNameInput.getText());
                showDeleteInformation(deleteNumber);

                Stage stage = (Stage) groupNumberOrSecondNameButton.getScene().getWindow();
                stage.close();
            }
            table.setPageNumber(1);
            table.updateTable();
        });
    }


    private void secondNameOrTypeOfPassPanel(String searchOrDelete){
        Button secondNameOrTypeOfPassButton  = new Button(searchOrDelete);
        secondNameOrTypeOfPassButton.setLayoutX(100);
        secondNameOrTypeOfPassButton.setLayoutY(160);

        TextField secondNameInput = new TextField();
        secondNameInput.setLayoutX(110);
        secondNameInput.setLayoutY(70);
        secondNameInput.setMinWidth(170);

        ObservableList<String> chooseTypeOfPassValues = FXCollections.observableArrayList(
                "AbsencesDueToIllness", "AbsencesDueToAnotherReason",
                "UnjustifiedAbsences", "TotalOfAbsences");

        ComboBox<String> chooseTypeOfPass = new ComboBox<>(chooseTypeOfPassValues);
        chooseTypeOfPass.setLayoutX(30);
        chooseTypeOfPass.setLayoutY(130);
        chooseTypeOfPass.setMinWidth(250);

        Label chooseTypeOfPassLabel = new Label("Choose type of pass");
        chooseTypeOfPassLabel.setLayoutX(100);
        chooseTypeOfPassLabel.setLayoutY(100);

        group.getChildren().remove(2, group.getChildren().size());

        group.getChildren().addAll(secondNameOrTypeOfPassButton, chooseTypeOfPass, secondNameInput,
                getFirstParameterLabel("Second Name"),
                chooseTypeOfPassLabel);

        secondNameOrTypeOfPassButton.setOnAction(actionEvent1 -> {



            if (searchOrDelete.equals("Search")){

                if (chooseTypeOfPass.getValue() == null){
                    controller.searchByGroupAndSecondName(-1, secondNameInput.getText());
                }else {

                    switch (chooseTypeOfPass.getValue()) {
                        case "AbsencesDueToIllness":
                            controller.searchBySecondNameAndAbsencesDueToIllness(secondNameInput.getText());
                            break;
                        case "AbsencesDueToAnotherReason":
                            controller.searchBySecondNameAndAbsencesDueToAnotherReason(secondNameInput.getText());
                            break;
                        case "UnjustifiedAbsences":
                            controller.searchBySecondNameAndUnjustifiedAbsences(secondNameInput.getText());
                            break;
                        case "TotalOfAbsences":
                            controller.searchBySecondNameAndTotalAbsences(secondNameInput.getText());
                            break;
                    }
                }

            } else{
                int deleteNumber = 0;

                if(chooseTypeOfPass.getValue() == null){
                    deleteNumber = controller.deleteByGroupAndSecondName(-1, secondNameInput.getText());
                } else {
                    switch (chooseTypeOfPass.getValue()) {
                        case "AbsencesDueToIllness":
                            deleteNumber = controller.deleteBySecondNameAndAbsencesDueToIllness(secondNameInput.getText());
                            break;
                        case "AbsencesDueToAnotherReason":
                            deleteNumber = controller.deleteBySecondNameAndAbsencesDueToAnotherReason(secondNameInput.getText());
                            break;
                        case "UnjustifiedAbsences":
                            deleteNumber = controller.deleteBySecondNameAndUnjustifiedAbsences(secondNameInput.getText());
                            break;
                        case "TotalOfAbsences":
                            deleteNumber = controller.deleteBySecondNameAndTotalAbsences(secondNameInput.getText());
                            break;
                    }
                }


                showDeleteInformation(deleteNumber);


                Stage stage = (Stage) secondNameOrTypeOfPassButton.getScene().getWindow();
                stage.close();
            }
            table.setPageNumber(1);
            table.updateTable();
        });
    }

    private void secondNameOrNumberOfPassesByTypePanel(String searchOrDelete){
        Button secondNameOrTypeOfPassButton  = new Button(searchOrDelete);
        secondNameOrTypeOfPassButton.setLayoutX(100);
        secondNameOrTypeOfPassButton.setLayoutY(220);

        TextField secondNameInput = new TextField();
        secondNameInput.setLayoutX(110);
        secondNameInput.setLayoutY(70);
        secondNameInput.setMinWidth(170);

        TextField absencesDueToIllnessMin = new TextField();
        absencesDueToIllnessMin.setLayoutY(100);
        absencesDueToIllnessMin.setLayoutX(250);
        absencesDueToIllnessMin.setMaxWidth(40);
        TextField absencesDueToIllnessMax = new TextField();
        absencesDueToIllnessMax.setLayoutY(100);
        absencesDueToIllnessMax.setLayoutX(340);
        absencesDueToIllnessMax.setMaxWidth(40);

        TextField absencesDueToAnotherReasonMin = new TextField();
        absencesDueToAnotherReasonMin.setLayoutY(130);
        absencesDueToAnotherReasonMin.setLayoutX(250);
        absencesDueToAnotherReasonMin.setMaxWidth(40);
        TextField absencesDueToAnotherReasonMax = new TextField();
        absencesDueToAnotherReasonMax.setLayoutY(130);
        absencesDueToAnotherReasonMax.setLayoutX(340);
        absencesDueToAnotherReasonMax.setMaxWidth(40);

        TextField unjustifiedAbsencesMin = new TextField();
        unjustifiedAbsencesMin.setLayoutY(160);
        unjustifiedAbsencesMin.setLayoutX(250);
        unjustifiedAbsencesMin.setMaxWidth(40);
        TextField unjustifiedAbsencesMax = new TextField();
        unjustifiedAbsencesMax.setLayoutY(160);
        unjustifiedAbsencesMax.setLayoutX(340);
        unjustifiedAbsencesMax.setMaxWidth(40);

        TextField totalOfAbsencesMin = new TextField();
        totalOfAbsencesMin.setLayoutY(190);
        totalOfAbsencesMin.setLayoutX(250);
        totalOfAbsencesMin.setMaxWidth(40);
        TextField totalOfAbsencesMax = new TextField();
        totalOfAbsencesMax.setLayoutY(190);
        totalOfAbsencesMax.setLayoutX(340);
        totalOfAbsencesMax.setMaxWidth(40);



        group.getChildren().remove(2, group.getChildren().size());

        group.getChildren().addAll(secondNameOrTypeOfPassButton, secondNameInput, getFirstParameterLabel("Second Name"),
                absencesDueToIllnessMin, absencesDueToAnotherReasonMin, unjustifiedAbsencesMin, totalOfAbsencesMin,
                absencesDueToIllnessMax, absencesDueToAnotherReasonMax, unjustifiedAbsencesMax, totalOfAbsencesMax,
                getAbsencesDueToIllnessLabels(), getAbsencesDueToAnotherReasonLabels(), getUnjustifiedAbsencesLabels(),
                getTotalOfAbsencesLabels());

        secondNameOrTypeOfPassButton.setOnAction(actionEvent -> {


            if (absencesDueToIllnessMin.getText().isEmpty() || absencesDueToIllnessMax.getText().isEmpty() ||
                    absencesDueToAnotherReasonMin.getText().isEmpty() || absencesDueToAnotherReasonMax.getText().isEmpty() ||
                    unjustifiedAbsencesMin.getText().isEmpty() || unjustifiedAbsencesMax.getText().isEmpty() ||
                    totalOfAbsencesMin.getText().isEmpty() || totalOfAbsencesMax.getText().isEmpty()){
                Alert warning = new Alert(Alert.AlertType.WARNING);
                warning.setTitle("Warning");
                warning.setContentText("Some fields are empty. Only surname was used!");
                warning.show();

                if (searchOrDelete.equals("Search")){
                    controller.searchByGroupAndSecondName(-1, secondNameInput.getText());
                } else {
                    int deleteNumber = controller.deleteByGroupAndSecondName(-1, secondNameInput.getText());
                    showDeleteInformation(deleteNumber);

                    Stage stage = (Stage) secondNameOrTypeOfPassButton.getScene().getWindow();
                    stage.close();
                }

            } else {

                int absencesDueToIllnessMinValue, absencesDueToIllnessMaxValue, absencesDueToAnotherReasonMinValue,
                        absencesDueToAnotherReasonMaxValue, unjustifiedAbsencesMinValue, unjustifiedAbsencesMaxValue,
                        totalOfAbsencesMinValue, totalOfAbsencesMaxValue;
                try {
                    absencesDueToIllnessMinValue = Integer.parseInt(absencesDueToIllnessMin.getText());
                    absencesDueToIllnessMaxValue = Integer.parseInt(absencesDueToIllnessMax.getText());
                    absencesDueToAnotherReasonMinValue = Integer.parseInt(absencesDueToIllnessMin.getText());
                    absencesDueToAnotherReasonMaxValue = Integer.parseInt(absencesDueToIllnessMax.getText());
                    unjustifiedAbsencesMinValue = Integer.parseInt(unjustifiedAbsencesMin.getText());
                    unjustifiedAbsencesMaxValue = Integer.parseInt(unjustifiedAbsencesMax.getText());
                    totalOfAbsencesMinValue = Integer.parseInt(totalOfAbsencesMin.getText());
                    totalOfAbsencesMaxValue = Integer.parseInt(totalOfAbsencesMax.getText());
                } catch (NumberFormatException e) {
                    Alert warning = new Alert(Alert.AlertType.WARNING);
                    warning.setTitle("Warning");
                    warning.setContentText("Incorrect data!!!");
                    warning.show();
                    return;
                }

                if (searchOrDelete.equals("Search")) {
                    controller.searchBySecondNameAndNumberOfPassesByType(secondNameInput.getText(),
                            absencesDueToIllnessMinValue, absencesDueToIllnessMaxValue,
                            absencesDueToAnotherReasonMinValue, absencesDueToAnotherReasonMaxValue,
                            unjustifiedAbsencesMinValue, unjustifiedAbsencesMaxValue,
                            totalOfAbsencesMinValue, totalOfAbsencesMaxValue);

                } else {
                    int deleteNumber = controller.deleteBySecondNameAndNumberOfPassesByType(secondNameInput.getText(),
                            absencesDueToIllnessMinValue, absencesDueToIllnessMaxValue,
                            absencesDueToAnotherReasonMinValue, absencesDueToAnotherReasonMaxValue,
                            unjustifiedAbsencesMinValue, unjustifiedAbsencesMaxValue,
                            totalOfAbsencesMinValue, totalOfAbsencesMaxValue);
                    showDeleteInformation(deleteNumber);

                    Stage stage = (Stage) secondNameOrTypeOfPassButton.getScene().getWindow();
                    stage.close();
                }
            }
            table.setPageNumber(1);
            table.updateTable();
        });

    }



    private void showDeleteInformation(int deleteNumber){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Delete information");
        if (deleteNumber == 0){
            alert.setContentText("No records found!");
        } else{
            alert.setContentText(deleteNumber + "record(s) have been deleted");
        }
        alert.show();
    }

    private Group getAbsencesDueToIllnessLabels(){
        Group group = new Group();
        Label absencesDueToIllnessLabel = new Label("AbsencesDueToIllness");
        absencesDueToIllnessLabel.setLayoutY(100);
        absencesDueToIllnessLabel.setLayoutX(30);
        Label min1Label = new Label("Min");
        min1Label.setLayoutY(100);
        min1Label.setLayoutX(220);
        Label max1Label = new Label("Max");
        max1Label.setLayoutY(100);
        max1Label.setLayoutX(300);
        group.getChildren().addAll(absencesDueToIllnessLabel, min1Label, max1Label);
        return group;
    }

    private Group getAbsencesDueToAnotherReasonLabels(){
        Group group = new Group();
        Label absencesDueToAnotherReasonLabel = new Label("AbsencesDueToAnotherReason");
        absencesDueToAnotherReasonLabel.setLayoutY(130);
        absencesDueToAnotherReasonLabel.setLayoutX(30);
        Label min2Label = new Label("Min");
        min2Label.setLayoutY(130);
        min2Label.setLayoutX(220);
        Label max2Label = new Label("Max");
        max2Label.setLayoutY(130);
        max2Label.setLayoutX(300);
        group.getChildren().addAll(absencesDueToAnotherReasonLabel, min2Label, max2Label);
        return group;
    }

    private Group getUnjustifiedAbsencesLabels(){
        Group group = new Group();
        Label unjustifiedAbsencesLabel = new Label("UnjustifiedAbsences");
        unjustifiedAbsencesLabel.setLayoutY(160);
        unjustifiedAbsencesLabel.setLayoutX(30);
        Label min3Label = new Label("Min");
        min3Label.setLayoutY(160);
        min3Label.setLayoutX(220);
        Label max3Label = new Label("Max");
        max3Label.setLayoutY(160);
        max3Label.setLayoutX(300);
        group.getChildren().addAll(unjustifiedAbsencesLabel, min3Label, max3Label);
        return group;
    }
    private Group getTotalOfAbsencesLabels(){
        Group group = new Group();
        Label totalOfAbsencesLabel = new Label("TotalOfAbsences");
        totalOfAbsencesLabel.setLayoutY(190);
        totalOfAbsencesLabel.setLayoutX(30);
        Label min4Label = new Label("Min");
        min4Label.setLayoutY(190);
        min4Label.setLayoutX(220);
        Label max4Label = new Label("Max");
        max4Label.setLayoutY(190);
        max4Label.setLayoutX(300);
        group.getChildren().addAll(totalOfAbsencesLabel, min4Label, max4Label);
        return group;
    }


}

