package vistas;

import controllers.MainController;
import core.ChefExpress;
import entities.Recipe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Map;
import interfaces.RecipeScorer;
public class MainView extends JFrame implements PropertyChangeListener
{
    private JComboBox<String> comboBox;
    private JPanel panel;
    private JLabel labelTitulo;
    private JButton btnRecommend;
    private JTextArea lblRecommendations;
    private MainController controller;
    public MainView(ChefExpress chefExpress,
                    List <RecipeScorer> scorers) {
        createViewComponents();
        controller = new MainController(this, chefExpress, scorers);
        chefExpress.attach(this);
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

        add(panel);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addLabelRecommendations() {
        this.lblRecommendations = new JTextArea();
        this.lblRecommendations.setEditable(false);
        this.lblRecommendations.setPreferredSize(new Dimension(300, 200));
        panel.add(new JScrollPane(this.lblRecommendations));
    }

    public JButton getBtnRecommend() {
        return this.btnRecommend;
    }

    public JComboBox<String> getComboBox() {
        return this.comboBox;
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        java.util.List<Recipe> recommendRecipes = (List<Recipe>) evt.getNewValue();
        showRecipes(recommendRecipes);
    }

    public void showRecipes(List<Recipe> recipes) {
        StringBuilder recommendationsText = new StringBuilder();

        for (Recipe recipe : recipes) {
            recommendationsText.append("Receta: ").append(recipe.getName()).append("\n");

            recommendationsText.append("Ingredientes:\n");
            for (Map.Entry<String, Float> entry : recipe.getIngredients().entrySet()) {
                String ingredientName = entry.getKey();
                Float ingredientAmount = entry.getValue();
                recommendationsText.append("- ").append(ingredientName).append(": ").append(ingredientAmount).append("\n");
            }

            recommendationsText.append("\n");
        }
        this.lblRecommendations.setText(recommendationsText.toString());
    }

    public void start() {
        setVisible(true);
    }
}
