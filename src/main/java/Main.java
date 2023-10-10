
import vistas.MainView;
import core.ChefExpress;
import factories.ChefExpressFactory;

public class Main
{
        public static void main(String[] args) throws Exception
        {
                ChefExpressFactory factory = new ChefExpressFactory("conf/chefExpress.properties");
                ChefExpress chefExpress = factory.createChefExpress();
                MainView view = new MainView(chefExpress);
                view.start();
        }
}
