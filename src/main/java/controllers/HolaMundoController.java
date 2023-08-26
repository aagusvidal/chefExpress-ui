package controllers;
import vistas.Vista;
import entidades.HolaMundo;

public class HolaMundoController {
	    private HolaMundo model;
	    private Vista view;

	    public HolaMundoController(HolaMundo model, Vista view) {
	        this.model = model;
	        this.view = view;
	    }

	    public void showMessage() {
			String message = model.getHolaMundo();
			view.displayMessage(message);
		}
}
