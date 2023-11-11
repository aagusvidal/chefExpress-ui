

import core.ChefExpress;
import core.HistoricalRecipesCounter;
import factories.ChefExpressBuilder;
import factories.HistoricalRecipesCounterFactory;
import vistas.MainView;


import java.util.List;

public class Main
{
        public static void main(String[] args) throws Exception
        {
                ChefExpressBuilder builder = new ChefExpressBuilder();
                ChefExpress recommender = builder.build("conf/chefExpress.properties");

                HistoricalRecipesCounterFactory historicalRecipesCounterFactory = new HistoricalRecipesCounterFactory();
                HistoricalRecipesCounter historicalRecipesCounter = historicalRecipesCounterFactory.createHistoricalRecipesCounter(recommender);

                MainView view = new MainView(recommender, historicalRecipesCounter);
                view.start();
        }
}
