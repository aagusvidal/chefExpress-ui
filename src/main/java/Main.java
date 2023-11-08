

import core.ChefExpressStatistics;
import core.VideoRecipeRecommender;
import factories.ChefExpressStatisticsFactory;
import factories.VideoRecipeRecommendatorBuilder;
import vistas.MainView;


import java.util.List;

public class Main
{
        public static void main(String[] args) throws Exception
        {
                VideoRecipeRecommendatorBuilder builder = new VideoRecipeRecommendatorBuilder();
                VideoRecipeRecommender recommender = builder.build("conf/chefExpress.properties");

                ChefExpressStatisticsFactory statisticsFactory = new ChefExpressStatisticsFactory();
                ChefExpressStatistics chefExpressStatistics = statisticsFactory.create(recommender, "conf/chefExpress.properties");

                MainView view = new MainView(recommender, chefExpressStatistics);
                view.start();
        }
}
