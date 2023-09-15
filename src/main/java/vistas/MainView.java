package vistas;

import controllers.MainController;
import core.ObservableChefExpress;
import entities.Recipe;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class MainView extends JFrame implements PropertyChangeListener
{
    private JComboBox<String> comboBox;
    private JPanel panel;
    private JLabel labelTitulo;

    public MainView(ObservableChefExpress chefExpress)
    {
        createViewComponents();
        MainController controller = new MainController(this, chefExpress);
        chefExpress.attach(this);
    }

    private void createViewComponents()
    {
        setTitle("Chef Express");

        panel = new JPanel(new BorderLayout());
        labelTitulo = new JLabel("Chef Express");
        panel.add(labelTitulo, BorderLayout.NORTH);

        String[] opciones = {" ", "Saludables"};
        comboBox = new JComboBox<>(opciones);
        setLayout(new FlowLayout());
        panel.add(comboBox);
        add(panel);
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
     public void propertyChange(PropertyChangeEvent evt)
     {
         java.util.List<Recipe> recommendRecipes = (List<Recipe>) evt.getNewValue();
         System.out.println(recommendRecipes.get(0).getName());
     }

     public void start()
     { setVisible(true); }
 }
