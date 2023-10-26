package vistas;

import controllers.MainController;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import core.ChefExpressStatistics;
import core.VideoRecipeRecommendator;
import entities.Recipe;
import entities.Recommendation;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import interfaces.RecipeScorer;

public class MainView extends JFrame implements PropertyChangeListener
{
    private JComboBox<String> comboBox;
    private JPanel panel;
    private JLabel labelTitulo;
    private JButton btnRecommend;
    private JButton btnBestRecommendations;
    private JTextPane lblRecommendations;
    private MainController controller;

    private JTextArea txtBestRecommendations;


    public MainView(VideoRecipeRecommendator recommender, List <RecipeScorer> scorers, ChefExpressStatistics chefExpressStatistics) {
        createViewComponents();
        controller = new MainController(this, recommender, scorers, chefExpressStatistics);
        recommender.attach(this);
    }

    private void createViewComponents() {
        setTitle("Chef Express");

        panel = new JPanel(new FlowLayout());
        labelTitulo = new JLabel("¿Qué tipo de receta buscás?");
        panel.add(labelTitulo);
        comboBox = new JComboBox<>();
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String seleccionado = (String) comboBox.getSelectedItem();
                controller.setChefExpressScorer(seleccionado);
            }
        });
        panel.add(comboBox);

        panel.add(Box.createVerticalStrut(10));

        this.btnRecommend = new JButton();
        this.btnRecommend.setText("Ver recomendaciones");
        this.btnRecommend.setBounds(10, 230, 100, 27);
        this.panel.add(this.btnRecommend);

        addLabelRecommendations();

        addBestRecommendationsBtn();

        add(panel);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addBestRecommendationsBtn() {
        this.btnBestRecommendations = new JButton();
        this.btnBestRecommendations.setText("Ver recomendaciones populares");
        this.btnBestRecommendations.setBounds(10, 350, 100, 27);

        this.panel.add(this.btnBestRecommendations);
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

    public JComboBox<String> getComboBox() {
        return this.comboBox;
    }

    public JButton getBtnBestRecommendations(){return this.btnBestRecommendations;}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        java.util.List<Recommendation> recommendRecipes = (List<Recommendation>) evt.getNewValue();
        showRecommendations(recommendRecipes);
    }

    public void showRecommendations(List<Recommendation> recommendations) {
        Arrays.stream(this.lblRecommendations.getHyperlinkListeners()).forEach(h -> this.lblRecommendations.removeHyperlinkListener(h));

        StringBuilder recommendationsText = new StringBuilder("<html>");

        for (Recommendation recommend : recommendations) {
            recommendationsText.append("<b>Receta:</b> ").append(recommend.getRecipe().getName()).append("<br>");
            recommendationsText.append("<a href=\"").append(recommend.getLink()).append("\"><u>Ver receta en YouTube</u></a><br>");
            recommendationsText.append("<b>Ingredientes:</b><br>");

            for (Map.Entry<String, Float> entry : recommend.getRecipe().getIngredients().entrySet()) {
                String ingredientName = entry.getKey();
                Float ingredientAmount = entry.getValue();
                recommendationsText.append("- ").append(ingredientName).append(": ").append(ingredientAmount).append("<br>");
            }

            recommendationsText.append("<br>");
        }
        this.lblRecommendations.setText(recommendationsText.toString());

        recommendationsText.append("</html>");

        lblRecommendations.setContentType("text/html");
        lblRecommendations.setText(recommendationsText.toString());

        lblRecommendations.addHyperlinkListener(e -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                try {
                    Desktop.getDesktop().browse(new URI(e.getDescription()));
                } catch (IOException | URISyntaxException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void showBestRecommendationsWindow(List<Recipe> recipes) {
        JFrame textAreaFrame = new JFrame("Recetas más populares");
        textAreaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.txtBestRecommendations = new JTextArea(10, 40);

        StringBuilder recipesText = new StringBuilder("\n");

        for (Recipe recipe : recipes) {
            recipesText.append("   • ").append(recipe.getName()).append("\n");

            Map<String, Float> ingredients = recipe.getIngredients();
            for (Map.Entry<String, Float> entry : ingredients.entrySet()) {
                String ingredientName = entry.getKey();
                Float ingredientAmount = entry.getValue();
                recipesText.append("       - ").append(ingredientName).append(": ").append(ingredientAmount).append("\n");
            }
        }

        this.txtBestRecommendations.setText(recipesText.toString());

        JScrollPane scrollPane = new JScrollPane(this.txtBestRecommendations);
        textAreaFrame.add(scrollPane);

        this.txtBestRecommendations.setWrapStyleWord(true);
        this.txtBestRecommendations.setEditable(false);
        textAreaFrame.pack();
        textAreaFrame.setSize(400, 400);

        textAreaFrame.setVisible(true);
    }

    public void start() {
        setVisible(true);
    }
}
