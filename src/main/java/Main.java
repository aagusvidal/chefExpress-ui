

import core.ChefExpress;
import core.ChefExpressStatistics;
import core.VideoRecipeRecommender;
import factories.ChefExpressBuilder;
import factories.ChefExpressStatisticsFactory;
import vistas.MainView;


import java.util.List;

public class Main
{
        public static void main(String[] args) throws Exception
        {
                ChefExpressBuilder builder = new ChefExpressBuilder();
                ChefExpress recommender = builder.build("conf/chefExpress.properties");

                ChefExpressStatisticsFactory statisticsFactory = new ChefExpressStatisticsFactory();
                ChefExpressStatistics chefExpressStatistics = statisticsFactory.create(recommender, "conf/chefExpress.properties");

                MainView view = new MainView(recommender, chefExpressStatistics);
                view.start();
        }
}
