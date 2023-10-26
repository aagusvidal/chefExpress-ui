

import core.ChefExpressStatistics;
import core.VideoRecipeRecommendator;
import factories.ChefExpressStatisticsFactory;
import factories.VideoRecipeRecommendatorBuilder;
import interfaces.RecipeScorer;
import vistas.MainView;


import java.util.List;

public class Main
{
        public static void main(String[] args) throws Exception
        {
                VideoRecipeRecommendatorBuilder builder = new VideoRecipeRecommendatorBuilder();
                VideoRecipeRecommendator recommender = builder.build("conf/chefExpress.properties");

                List<RecipeScorer> recipeScorers = builder.getRecipeScorers();

                ChefExpressStatisticsFactory statisticsFactory = new ChefExpressStatisticsFactory();
                ChefExpressStatistics chefExpressStatistics = statisticsFactory.create(recommender, "conf/chefExpress.properties");

                MainView view = new MainView(recommender, recipeScorers, chefExpressStatistics);
                view.start();
        }
}
