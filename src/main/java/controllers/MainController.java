package controllers;

import entities.Recipe;
import vistas.MainView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class MainController implements PropertyChangeListener
{
    MainView mainView;

    public MainController(MainView view)
    {
        this.mainView = view;
        view.setVisible( true );
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        List<Recipe> recommendRecipes = (List<Recipe>) evt.getNewValue();
        System.out.println(recommendRecipes.get(0).getName());
    }
}
