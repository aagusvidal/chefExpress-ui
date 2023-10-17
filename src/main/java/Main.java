
import core.RecommendationLogger;
import factories.RecommendationLoggerFactory;
import vistas.MainView;
import core.ChefExpress;
import factories.ChefExpressFactory;

public class Main
{
        public static void main(String[] args) throws Exception
        {
                ChefExpressFactory factory = new ChefExpressFactory();
                ChefExpress chefExpress = factory.createChefExpress("conf/chefExpress.properties");
                RecommendationLogger logger = new RecommendationLoggerFactory().createRecommendationLogger(chefExpress);

                MainView view = new MainView(chefExpress, factory.getRecipeScorers(), logger);
                view.start();
        }
}
