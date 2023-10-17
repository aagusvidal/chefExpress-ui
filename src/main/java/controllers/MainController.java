package controllers;

import core.ChefExpress;
import entities.Recipe;
import entities.Recommendation;
import vistas.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class MainController implements PropertyChangeListener, ActionListener
{
    private MainView mainView;
    private ChefExpress chefExpress;

    public MainController(MainView view, ChefExpress chefExpress)
    {
        this.mainView = view;
        this.chefExpress = chefExpress;

        chefExpress.attach(this);

        this.mainView.getBtnRecommend().addActionListener( this );
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {}

    @Override
    public void actionPerformed(ActionEvent event)
    {
        if ( event.getSource() == this.mainView.getBtnRecommend() )
        {
            this.onRecommend();
        }
    }

    private void onRecommend()
    {
        java.util.List<Recommendation> recommendRecipes = this.chefExpress.recommend();
        this.mainView.showRecommendations(recommendRecipes);
    }
}
