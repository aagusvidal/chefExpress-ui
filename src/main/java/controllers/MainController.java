package controllers;

import core.ChefExpress;
import core.HistoricalRecipesCounter;
import core.RecipesUpdater;
import entities.Recipe;
import vistas.MainView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainController implements PropertyChangeListener, ActionListener {
    private MainView mainView;

    private ChefExpress recommender;

    private HistoricalRecipesCounter historicalRecipesCounter;

    private RecipesUpdater recipesUpdater;

    public MainController(MainView view, ChefExpress recommender, HistoricalRecipesCounter historicalRecipesCounter, RecipesUpdater recipesUpdater) {
        this.mainView = view;
        this.recommender = recommender;
        this.recipesUpdater = recipesUpdater;
        this.historicalRecipesCounter = historicalRecipesCounter;
        recommender.attach(this);

        this.mainView.getBtnRecommend().addActionListener(this);
        this.mainView.getComboBox().setModel(new DefaultComboBoxModel<>(this.getScorersArray()));
        this.mainView.getBtnBestRecommendations().addActionListener(this);
        this.mainView.getBtnRecipesUpdater().addActionListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == this.mainView.getBtnRecommend()) {
            this.onRecommend();
        }

        if (event.getSource() == this.mainView.getBtnBestRecommendations()) {
            try {
                this.onBestRecommendations();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        if (event.getSource() == this.mainView.getBtnRecipesUpdater()) {
            try {
                this.updateRecipes();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void onBestRecommendations() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        this.mainView.showBestRecommendationsWindow(this.historicalRecipesCounter.getHistoricRecipes());
    }

    private void updateRecipes() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        this.recipesUpdater.updateRecipes();
        this.onRecommend();
    }

    private void onRecommend() {
        List<Recipe> recommendRecipes = this.recommender.recommend();
        this.mainView.showRecommendations(recommendRecipes);
    }

    public String[] getScorersArray() {
        return recommender.scorersNamesArray();
    }

    public void setChefExpressScorer(String scorerName) {
        recommender.setScorer(scorerName);
    }
}
