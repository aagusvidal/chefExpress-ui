package controllers;

import core.ChefExpress;
import core.ChefExpressStatistics;
import entities.Recipe;
import entities.Recommendation;
import vistas.MainView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainController implements PropertyChangeListener, ActionListener
{
    private MainView mainView;
    private ChefExpress recommender;

    private ChefExpressStatistics chefExpressStatistics;

    public MainController(MainView view, ChefExpress recommender, ChefExpressStatistics chefExpressStatistics)
    {
        this.mainView = view;
        this.recommender = recommender;

        recommender.attach(this);

        this.mainView.getBtnRecommend().addActionListener( this );
        this.mainView.getComboBox().setModel(new DefaultComboBoxModel<>(this.getScorersArray()));
        this.mainView.getBtnBestRecommendations().addActionListener(this);
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

        if ( event.getSource() == this.mainView.getBtnBestRecommendations() )
        {
            try
            {
                this.onBestRecommendations();
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    private void onBestRecommendations() throws InterruptedException
    {
        TimeUnit.SECONDS.sleep(3);
        this.mainView.showBestRecommendationsWindow(this.chefExpressStatistics.getTopRecipes());
    }

    private void onRecommend()
    {
        List<Recipe> recommendRecipes = this.recommender.recommend();
        this.mainView.showRecommendations(recommendRecipes);
    }

    public String[] getScorersArray(){
       return recommender.scorersNamesArray();
    }

    public void setChefExpressScorer(String scorerName){
        recommender.setScorer(scorerName);
    }
}
