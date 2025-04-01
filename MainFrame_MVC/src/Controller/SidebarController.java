package Controller;

import View.MainFrameView;
import View.SidebarView;

public class SidebarController {

    private MainFrameView mainFrame;

    public SidebarController(MainFrameView mainFrameView){
        this.mainFrame = mainFrameView;
        SidebarView view = mainFrameView.getSidebarView();

        // AddcashView anzeigen, wenn der Button gedrückt wird
        view.getAddCashButton().addActionListener(e-> mainFrame.showPanel("AddCash"));

        // Dashboard anzeigen, wenn der Dashboard-Button gedrückt wird
        view.getDashboardButton().addActionListener(e -> mainFrame.showPanel("Dashboard"));


    }
}
