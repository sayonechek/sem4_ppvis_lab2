package sample.model;

import java.util.List;

public class Page {
    private List<Student> pageData;
    private int pageNumber;
    private int pageCount;
    private int totalRecordsCount;

    public Page(int pageNumber, List<Student> pageData, int pageCount, int totalRecordsCount){
        this.pageNumber = pageNumber;
        this.pageData = pageData;
        this.pageCount = pageCount;
        this.totalRecordsCount = totalRecordsCount;
    }

    public int getPageNumber(){ return pageNumber; }

    public int getPageCount(){ return pageCount; }

    public int getTotalRecordsCount(){ return totalRecordsCount; }

    public List<Student> getStudents(){ return  pageData; }

}
