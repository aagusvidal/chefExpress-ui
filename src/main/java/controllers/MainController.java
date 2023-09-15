package controllers;

import core.ObservableChefExpress;
import entities.Recipe;
import vistas.MainView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class MainController implements PropertyChangeListener
{
    private MainView mainView;
    private ObservableChefExpress chefExpress;

    public MainController(MainView view, ObservableChefExpress chefExpress)
    {
        this.mainView = view;
        this.chefExpress = chefExpress;
        chefExpress.attach(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {}
}
