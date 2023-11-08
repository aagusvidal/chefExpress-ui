package controllers;

import core.ChefExpressStatistics;
import core.VideoRecipeRecommender;
import entities.Recommendation;
import interfaces.RecipeScorer;
import vistas.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainController implements PropertyChangeListener, ActionListener
{
    private MainView mainView;
    private VideoRecipeRecommender recommender;
    private ChefExpressStatistics chefExpressStatistics;
    private List <RecipeScorer> scorers;

    public MainController(MainView view, VideoRecipeRecommender recommender, ChefExpressStatistics chefExpressStatistics)
    {
        this.mainView = view;
        this.recommender = recommender;
        this.scorers = scorers;
        this.chefExpressStatistics = chefExpressStatistics;

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
        java.util.List<Recommendation> recommendRecipes = this.recommender.recommend();
        this.mainView.showRecommendations(recommendRecipes);
    }

    public String[] getScorersArray(){
        List<String> listScorers = new ArrayList<String>();
        for(RecipeScorer scorer : scorers){
            listScorers.add (scorer.getName());
        }
       return listScorers.toArray(new String[0]);
    }

    public void setChefExpressScorer(String scorerName){
        // recommender.setScorer(scorer);
    }

}
