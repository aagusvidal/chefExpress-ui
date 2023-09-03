package controllers;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import model.InitCore;
import vistas.MainView;

public class MainController implements Observable {

    MainView mainView;
    InitCore core;

    @SuppressWarnings("deprecation")
    public MainController(MainView view, InitCore initCore) {
        this.mainView = view;
        this.core = initCore;
        view.setVisible( true );
    }

    @Override
    public void addListener(InvalidationListener listener) {

    }

    @Override
    public void removeListener(InvalidationListener listener) {

    }
}
