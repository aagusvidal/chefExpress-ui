import controllers.MainController;
import core.ObservableChefExpress;
import vistas.MainView;
import factories.ChefExpressFactory;

public class Main
{
        public static void main(String[] args) throws Exception
        {
                ChefExpressFactory factory = new ChefExpressFactory("conf/chefExpress.properties");
                ObservableChefExpress chefExpress = factory.createChefExpress();

                MainView view = new MainView();
                MainController controller = new MainController(view);
                chefExpress.attach(controller);

                chefExpress.recommend();
        }
}
