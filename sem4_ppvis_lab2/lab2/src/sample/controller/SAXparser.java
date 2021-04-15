package sample.controller;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import sample.model.Student;

import java.util.ArrayList;
import java.util.List;


public class SAXparser extends DefaultHandler {
    private List<Student> students = new ArrayList<>();
    private String thisElement = "";
    private String fullName = "";
    private int group;
    private int numberOfAbsencesDueToIllness;
    private int numberOfAbsencesDueToAnotherReason;
    private int numberOfUnjustifiedAbsences;
    private int totalOfAbsences;
    private int readCounter = 0;

    @Override
    public  void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
        thisElement = qName;
    }

    @Override
    public void characters(char [] ch, int start, int length) throws SAXException{
        switch (thisElement){
            case "fullName" :
                fullName = new String(ch, start, length);
                break;
            case "group" :
                group = Integer.parseInt(new String(ch, start, length));
                break;
            case "numberOfAbsencesDueToIllness":
                numberOfAbsencesDueToIllness = Integer.parseInt(new String(ch, start, length));
                break;
            case "numberOfAbsencesDueToAnotherReason":
                numberOfAbsencesDueToAnotherReason = Integer.parseInt(new String(ch, start, length));
                break;
            case "numberOfUnjustifiedAbsences":
                numberOfUnjustifiedAbsences = Integer.parseInt(new String(ch, start, length));
                break;
            case "totalOfAbsences":
                totalOfAbsences = Integer.parseInt(new String(ch, start, length));
                break;
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        readCounter++;
        if(readCounter == 7){
            students.add(new Student(fullName, group, numberOfAbsencesDueToIllness, numberOfAbsencesDueToAnotherReason,
                    numberOfUnjustifiedAbsences, totalOfAbsences));
            readCounter = 0;
        }
    }

    public List<Student> getStudents(){return students;}
}
