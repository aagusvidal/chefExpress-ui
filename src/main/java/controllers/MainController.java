package controllers;

import core.ObservableChefExpress;
import vistas.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainController implements PropertyChangeListener, ActionListener
{
    private MainView mainView;
    private ObservableChefExpress chefExpress;

    public MainController(MainView view, ObservableChefExpress chefExpress)
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
        this.chefExpress.recommend();
    }
}
