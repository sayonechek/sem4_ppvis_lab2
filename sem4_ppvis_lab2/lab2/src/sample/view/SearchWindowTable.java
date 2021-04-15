package sample.view;

import sample.controller.Controller;


public class SearchWindowTable extends MainWindowTable {
    private Controller controller;

    public SearchWindowTable(Controller controller) {
        super(controller);
        this.controller = controller;
    }
    @Override
    public void updateCurrentPage(){
        setCurrentPage(controller.updateSearchWindowTable(getPageNumber(), getRecordsOnPageCount()));
    }
}
