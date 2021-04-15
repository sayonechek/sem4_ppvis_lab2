package sample.controller;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import sample.model.Student;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class DOMparser {

    public void parse(List<Student> tableData, File file) throws ParserConfigurationException, TransformerException {


        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document document = docBuilder.newDocument();
        Element docRootElement = document.createElement("tableData");

        for (int index = 0; index < tableData.size(); index++) {
            docRootElement.appendChild(addStudentToDocument(index, tableData.get(index), document));
        }

        document.appendChild(docRootElement);
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(file);
        saveDataInFile(source, result);
    }

    private Element addStudentToDocument(int index, Student student, Document document) {

        Element studentItem = document.createElement("student");
        studentItem.setAttribute("id", Integer.toString(index));
        document.appendChild(studentItem);

        Element fullName = document.createElement("fullName");
        fullName.appendChild(document.createTextNode(student.getFullName()));
        studentItem.appendChild(fullName);

        Element address = document.createElement("group");
        address.appendChild(document.createTextNode(student.getGroup().toString()));
        studentItem.appendChild(address);

        Element birthDate = document.createElement("numberOfAbsencesDueToIllness");
        birthDate.appendChild(document.createTextNode(student.getNumberOfAbsencesDueToIllness().toString()));
        studentItem.appendChild(birthDate);

        Element receiptDate = document.createElement("numberOfAbsencesDueToAnotherReason");
        receiptDate.appendChild(document.createTextNode(student.getNumberOfAbsencesDueToAnotherReason().toString()));
        studentItem.appendChild(receiptDate);

        Element doctorFullName = document.createElement("numberOfUnjustifiedAbsences");
        doctorFullName.appendChild(document.createTextNode(student.getNumberOfUnjustifiedAbsences().toString()));
        studentItem.appendChild(doctorFullName);

        Element conclusion = document.createElement("totalOfAbsences");
        conclusion.appendChild(document.createTextNode(student.getTotalOfAbsences().toString()));
        studentItem.appendChild(conclusion);

        return studentItem;
    }

    private void saveDataInFile(DOMSource source, StreamResult result) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(source, result);
    }
}
