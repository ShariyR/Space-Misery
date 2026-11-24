package Model;

import Controller.SMController;

/**
 * Represents the model component in the Space-themed Game MVC architecture.
 */
public class SMModel {

    private SMController controller;

    /**
     * Constructs a new Space-themed Game model.
     */
    public SMModel() {
    }

    /**
     * Sets the controller associated with the model.
     *
     * @param controller The controller to be associated with the model.
     */
    public void setController(SMController controller) {this.controller = controller;}
}
