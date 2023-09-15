package controllers;

import vistas.MainView;

public class MainController
{

    MainView mainView;

    public MainController(MainView view)
    {
        this.mainView = view;
        view.setVisible( true );
    }
}
