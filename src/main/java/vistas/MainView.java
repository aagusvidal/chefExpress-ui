package vistas;

import controllers.MainController;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javax.swing.*;
import java.awt.*;


public class MainView extends JFrame implements Observable {

    JComboBox<String> comboBox;
    JPanel panel;
    JLabel labelTitulo;
    public MainView() {

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
    public void addListener(InvalidationListener listener) {

    }

    @Override
    public void removeListener(InvalidationListener listener) {

    }
}
