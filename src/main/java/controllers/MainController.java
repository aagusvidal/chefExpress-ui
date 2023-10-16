package controllers;

import core.ChefExpress;
import entities.Recipe;
import interfaces.RecipeScorer;
import vistas.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class MainController implements PropertyChangeListener, ActionListener
{
    private MainView mainView;
    private ChefExpress chefExpress;

    private List <RecipeScorer> scorers;
    public MainController(MainView view, ChefExpress chefExpress, List <RecipeScorer> scorers)
    {
        this.mainView = view;
        this.chefExpress = chefExpress;
        this.scorers = scorers;

        chefExpress.attach(this);

        this.mainView.getBtnRecommend().addActionListener( this );
        this.mainView.getComboBox().setModel(new DefaultComboBoxModel<>(this.getScorersArray()));
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
        java.util.List<Recipe> recommendRecipes = this.chefExpress.recommend();
        this.mainView.showRecipes(recommendRecipes);
    }

    public String[] getScorersArray(){
        List<String> listScorers = new ArrayList<String>();
        for(RecipeScorer scorer : scorers){
            listScorers.add (scorer.getName());
        }
       return listScorers.toArray(new String[0]);
    }

    public void setChefExpressScorer(String scorerName){
        RecipeScorer scorer = this.findScorerByName(scorerName);
        chefExpress.setScorer(scorer);
    }

    private RecipeScorer findScorerByName(String scorerName){
        for (RecipeScorer scorer : scorers) {
            if (scorer.getName().equals(scorerName)) {
                return scorer;
            }
        }
        return null;
    }

}
