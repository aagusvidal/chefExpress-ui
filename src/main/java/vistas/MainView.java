package vistas;

import controllers.MainController;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javax.swing.*;
import java.awt.*;


public class MainView extends JFrame implements Observable {

    JComboBox<String> comboBox;
    public MainView() {
        setTitle("Chef Express");

        String[] opciones = {"Saludables"};
        comboBox = new JComboBox<>(opciones);
        setLayout(new FlowLayout());
        add(comboBox);

        setSize(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void addListener(InvalidationListener listener) {

    }

    @Override
    public void removeListener(InvalidationListener listener) {

    }
}
