package sample.controller;

import org.xml.sax.SAXException;
import sample.model.Page;
import sample.model.Student;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private List<Student> mainTableData = new ArrayList<>();
    private List<Student> searchTableData = new ArrayList<>();

    public void addStudentToArray(String fullName, Integer group, Integer numberOfAbsencesDueToIllness,
                                  Integer numberOfAbsencesDueToAnotherReason,
                                  Integer numberOfUnjustifiedAbsences, Integer totalOfAbsences){
        Student student = new Student(fullName, group, numberOfAbsencesDueToIllness, numberOfAbsencesDueToAnotherReason,
                numberOfUnjustifiedAbsences, totalOfAbsences);
        mainTableData.add(student);

    }

    public Page updateMainWindowTableView(int pageNumber, int recordsOnPageCount){
        return createPage(pageNumber, recordsOnPageCount, mainTableData);
    }

    public List<Student> getThisPageData(int pageNumber, int recordsOnPageCount, List <Student> tableData){
        List <Student> thisPageData;
        int numberOfLastRecordOnThisPage = pageNumber * recordsOnPageCount;
        if (numberOfLastRecordOnThisPage > tableData.size())
                    numberOfLastRecordOnThisPage = tableData.size();
        thisPageData = tableData.subList((pageNumber-1)*recordsOnPageCount, numberOfLastRecordOnThisPage);
        return thisPageData;
    }

    public Page createPage(int pageNumber, int recordsOnPageCount, List<Student> tableData){
        int pageCount = 1;
        if (tableData.size() > recordsOnPageCount){
            pageCount = tableData.size()/recordsOnPageCount;
            if (tableData.size() % recordsOnPageCount !=0) pageCount++;
        }
        List <Student> pageData = getThisPageData(pageNumber, recordsOnPageCount, tableData);
        Page page = new Page(pageNumber, pageData, pageCount, tableData.size());
        return page;
    }
    public void searchByGroupAndSecondName(int group, String secondName){
        List<Student> searchResult = new ArrayList<>();
        for (Student student : mainTableData){
            if (student.getGroup().equals(group) || student.getSecondName().equals(secondName)){
                searchResult.add(student);
            }
        }
        searchTableData = searchResult;
    }

    public void searchBySecondNameAndAbsencesDueToIllness(String secondName){
        List <Student> searchResult = new ArrayList<>();
        for(Student student : mainTableData){
            if (student.getSecondName().equals(secondName) || student.getNumberOfAbsencesDueToIllness() >0){
                searchResult.add(student);
            }

        }
        searchTableData = searchResult;
    }
    public void searchBySecondNameAndAbsencesDueToAnotherReason(String secondName){
        List <Student> searchResult = new ArrayList<>();
        for(Student student : mainTableData){
            if (student.getSecondName().equals(secondName) || student.getNumberOfAbsencesDueToAnotherReason() >0){
                searchResult.add(student);
            }

        }
        searchTableData = searchResult;
    }
    public void searchBySecondNameAndUnjustifiedAbsences(String secondName){
        List <Student> searchResult = new ArrayList<>();
        for(Student student : mainTableData) {
            if (student.getSecondName().equals(secondName) || student.getNumberOfUnjustifiedAbsences() > 0) {
                searchResult.add(student);
            }
        }
        searchTableData = searchResult;
    }
    public void searchBySecondNameAndTotalAbsences(String secondName) {
        List<Student> searchResult = new ArrayList<>();
        for(Student student : mainTableData){
            if(student.getSecondName().equals(secondName) || student.getTotalOfAbsences() > 0){
                searchResult.add(student);
            }
        }
        searchTableData = searchResult;
    }

    public Page updateSearchWindowTable(int pageNUmber, int recordsOnPageCount){
        return createPage(pageNUmber, recordsOnPageCount, searchTableData);
    }



    public int deleteByGroupAndSecondName(int group, String secondName){
        int deleteStudentsCount = 0;
        for(int i=0; i<mainTableData.size(); i++){
            if((mainTableData.get(i).getGroup().equals(group)) || (mainTableData.get(i).getSecondName().equals(secondName))){
                mainTableData.remove(i);
                i--;
                deleteStudentsCount++;
            }
        }
        return deleteStudentsCount;
    }
    public int deleteBySecondNameAndAbsencesDueToIllness(String secondName){
        int deleteStudentsCount = 0;
        for(int i=0; i<mainTableData.size(); i++){
            if (mainTableData.get(i).getNumberOfAbsencesDueToIllness() >0 || mainTableData.get(i).getSecondName().equals(secondName)){
                mainTableData.remove(i);
                i--;
                deleteStudentsCount++;
            }
        }
        return deleteStudentsCount;
    }
    public int deleteBySecondNameAndAbsencesDueToAnotherReason(String secondName){
        int deleteStudentsCount = 0;
        for(int i=0; i<mainTableData.size(); i++){
            if (mainTableData.get(i).getNumberOfAbsencesDueToAnotherReason() >0 || mainTableData.get(i).getSecondName().equals(secondName)){
                mainTableData.remove(i);
                i--;
                deleteStudentsCount++;
            }
        }
        return deleteStudentsCount;
    }
    public int deleteBySecondNameAndUnjustifiedAbsences(String secondName){
        int deleteStudentsCount = 0;
        for(int i=0; i<mainTableData.size(); i++){
            if (mainTableData.get(i).getNumberOfUnjustifiedAbsences() >0 || mainTableData.get(i).getSecondName().equals(secondName)){
                mainTableData.remove(i);
                i--;
                deleteStudentsCount++;
            }
        }
        return deleteStudentsCount;
    }

    public int deleteBySecondNameAndTotalAbsences(String secondName){
        int deleteStudentCount = 0;
        for(int i=0; i<mainTableData.size(); i++){
            if (mainTableData.get(i).getTotalOfAbsences()>0 || mainTableData.get(i).getSecondName().equals(secondName)){
                mainTableData.remove(i);
                i--;
                deleteStudentCount++;
            }
        }
        return deleteStudentCount;
    }

    public void searchBySecondNameAndNumberOfPassesByType(String secondName, int absencesDueToIllnessMinValue,
              int absencesDueToIllnessMaxValue, int absencesDueToAnotherReasonMinValue, int absencesDueToAnotherReasonMaxValue,
              int unjustifiedAbsencesMinValue, int unjustifiedAbsencesMaxValue, int totalOfAbsencesMinValue,
                                                        int totalOfAbsencesMaxValue){
        List <Student> searchResult = new ArrayList<>();
        for(Student student: mainTableData){
            if (student.getSecondName().equals(secondName)
                    || (student.getNumberOfAbsencesDueToIllness()>=absencesDueToIllnessMinValue &&
                    student.getNumberOfAbsencesDueToIllness()<=absencesDueToIllnessMaxValue &&
                    student.getNumberOfAbsencesDueToAnotherReason()>=absencesDueToAnotherReasonMinValue &&
                    student.getNumberOfAbsencesDueToAnotherReason()<=absencesDueToAnotherReasonMaxValue &&
                    student.getNumberOfUnjustifiedAbsences()>=unjustifiedAbsencesMinValue &&
                    student.getNumberOfUnjustifiedAbsences()<=unjustifiedAbsencesMaxValue &&
                    student.getTotalOfAbsences()>=totalOfAbsencesMinValue &&
                    student.getTotalOfAbsences()<=totalOfAbsencesMaxValue)){
                searchResult.add(student);
            }
        }
        searchTableData = searchResult;
    }

    public int deleteBySecondNameAndNumberOfPassesByType(String secondName, int absencesDueToIllnessMinValue,
              int absencesDueToIllnessMaxValue, int absencesDueToAnotherReasonMinValue, int absencesDueToAnotherReasonMaxValue,
              int unjustifiedAbsencesMinValue, int unjustifiedAbsencesMaxValue, int totalOfAbsencesMinValue,
                                                       int totalOfAbsencesMaxValue){
        int deleteStudentCount = 0;
        for(int i=0; i<mainTableData.size(); i++){
            if (mainTableData.get(i).getSecondName().equals(secondName)
                    || (mainTableData.get(i).getNumberOfAbsencesDueToIllness()>=absencesDueToIllnessMinValue &&
                    mainTableData.get(i).getNumberOfAbsencesDueToIllness()<=absencesDueToIllnessMaxValue &&
                    mainTableData.get(i).getNumberOfAbsencesDueToAnotherReason()>=absencesDueToAnotherReasonMinValue &&
                    mainTableData.get(i).getNumberOfAbsencesDueToAnotherReason()<=absencesDueToAnotherReasonMaxValue &&
                    mainTableData.get(i).getNumberOfUnjustifiedAbsences()>=unjustifiedAbsencesMinValue &&
                    mainTableData.get(i).getNumberOfUnjustifiedAbsences()<=unjustifiedAbsencesMaxValue &&
                    mainTableData.get(i).getTotalOfAbsences()>=totalOfAbsencesMinValue &&
                    mainTableData.get(i).getTotalOfAbsences()<=totalOfAbsencesMaxValue)){
                mainTableData.remove(i);
                i--;
                deleteStudentCount++;
            }
        }
        return deleteStudentCount;
    }

    public void saveTableData(File file, DOMparser parser) throws TransformerException, ParserConfigurationException {
        parser.parse(mainTableData, file);
    }

    public void getTableDataFromFile(File file, SAXParser parser) throws ParserConfigurationException, SAXException,
            IOException {
        SAXparser saxParser = new SAXparser();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        parser = factory.newSAXParser();
        parser.parse(file, saxParser);

        mainTableData = saxParser.getStudents();
    }

}
