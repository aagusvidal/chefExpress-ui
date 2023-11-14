

import core.ChefExpress;
import core.HistoricalRecipesCounter;
import core.RecipesUpdater;
import factories.ChefExpressBuilder;
import factories.HistoricalRecipesCounterFactory;
import factories.RecipesUpdaterFactory;
import vistas.MainView;

public class Main
{
        public static void main(String[] args) throws Exception
        {
                String propertyPath = "conf/chefExpress.properties";
                RecipesUpdaterFactory recipesUpdaterFactory = new RecipesUpdaterFactory();
                RecipesUpdater recipesUpdater = recipesUpdaterFactory.createRecipesUpdater(propertyPath);
                ChefExpressBuilder builder = new ChefExpressBuilder();
                ChefExpress recommender = builder.build(propertyPath, recipesUpdater);
                HistoricalRecipesCounterFactory historicalRecipesCounterFactory = new HistoricalRecipesCounterFactory();
                HistoricalRecipesCounter historicalRecipesCounter = historicalRecipesCounterFactory.createHistoricalRecipesCounter(recommender);

                MainView view = new MainView(recommender, historicalRecipesCounter, recipesUpdater);
                view.start();
        }
}
