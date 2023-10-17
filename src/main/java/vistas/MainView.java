package vistas;

import controllers.MainController;
import core.ChefExpress;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import entities.Recommendation;

import java.util.List;
import java.util.Map;


public class MainView extends JFrame implements PropertyChangeListener {
    private JComboBox<String> comboBox;
    private JPanel panel;
    private JLabel labelTitulo;
    private JButton btnRecommend;
    private JTextPane lblRecommendations;


    public MainView(ChefExpress chefExpress) {
        createViewComponents();
        MainController controller = new MainController(this, chefExpress);
        chefExpress.attach(this);
    }

    private void createViewComponents() {
        setTitle("Chef Express");

        panel = new JPanel(new FlowLayout());
        labelTitulo = new JLabel("¿Qué tipo de receta buscás?");
        panel.add(labelTitulo);

        String[] opciones = {"Saludables"};
        comboBox = new JComboBox<>(opciones);
        panel.add(comboBox);

        panel.add(Box.createVerticalStrut(10));

        this.btnRecommend = new JButton();
        this.btnRecommend.setText("Ver recomendaciones");
        this.btnRecommend.setBounds(10, 230, 100, 27);
        this.panel.add(this.btnRecommend);

        addLabelRecommendations();

        add(panel);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addLabelRecommendations() {
        this.lblRecommendations = new JTextPane();
        this.lblRecommendations.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(this.lblRecommendations);

        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        scrollPane.setPreferredSize(new Dimension(320, 200));

        panel.add(scrollPane);
    }

    public JButton getBtnRecommend() {
        return this.btnRecommend;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        java.util.List<Recommendation> recommendRecipes = (List<Recommendation>) evt.getNewValue();
        showRecommendations(recommendRecipes);
    }

    public void showRecommendations(List<Recommendation> recommendations) {
        StringBuilder recommendationsText = new StringBuilder("<html>");

        for (Recommendation recommend : recommendations) {
            recommendationsText.append("<b>Receta:</b> ").append(recommend.getRecipe().getName()).append("<br>");
            recommendationsText.append("<a href=\"").append(recommend.getLink() + "").append("\"><u>Ver receta en YouTube</u></a><br>");
            recommendationsText.append("<b>Ingredientes:</b><br>");

            for (Map.Entry<String, Float> entry : recommend.getRecipe().getIngredients().entrySet()) {
                String ingredientName = entry.getKey();
                Float ingredientAmount = entry.getValue();
                recommendationsText.append("- ").append(ingredientName).append(": ").append(ingredientAmount).append("<br>");
            }

            recommendationsText.append("<br>");
        }
        this.lblRecommendations.setText(recommendationsText.toString());
    }

    public void start() {
        setVisible(true);
    }
}
