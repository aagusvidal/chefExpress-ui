import controllers.MainController;
import model.InitCore;
import vistas.MainView;

public class Main {
        public static void main(String[] args) {
            MainView vistaPrincipal = new MainView();
            InitCore init = new InitCore();
            MainController mainController = new MainController(vistaPrincipal, init);


        }
}
