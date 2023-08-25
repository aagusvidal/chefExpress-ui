package controllers;
import vistas.Vista;

public class HolaMundoController {
	    private HolaMundo model;
	    private Vista view;

	    public Controller(Model model, View view) {
	        this.model = model;
	        this.view = view;
	    }

	    public void showMessage() {
	        String message = model.getMessage();
	        view.displayMessage(message)
}
