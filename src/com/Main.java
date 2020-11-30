package com;

import com.controller.ControllerJTextFieldByID;
import com.controller.ControllerOkAll;
import com.controller.ControllerOkTitle;
import com.controller.ControllerPrevNext;
import com.modele.ListOfMoviesSearch;
import com.modele.Movie;
import com.view.View;

public class Main {

    public static void main(String[] args) {
        Movie modele = new Movie(1);
        View view = new View(modele);
        ControllerPrevNext controllerNext = new ControllerPrevNext(modele, view);
        view.getViewMovie().suiv.addActionListener(controllerNext);
        view.getViewMovie().prev.addActionListener(controllerNext);
        ControllerJTextFieldByID controllerJTextFieldByID = new ControllerJTextFieldByID(modele, view.getViewMovie());
        view.getViewMovie().jLabelNumFilm.addKeyListener(controllerJTextFieldByID);
        ControllerOkTitle controllerOkTitle = new ControllerOkTitle(new ListOfMoviesSearch(),view.getViewMovie());
        view.getViewMovie().jButtonRechercheT.addActionListener(controllerOkTitle);
        ControllerOkAll controllerOkAll = new ControllerOkAll(new ListOfMoviesSearch(),view.getViewMovie());
        view.getViewMovie().jButtonRechercheN.addActionListener(controllerOkAll);
    }
}
