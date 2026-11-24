import Controller.SMController;
import Model.SMModel;
import View.SMView;
import processing.core.PApplet;


/**
 * The Main class is the entry point for the Space Misery game.
 * It initializes the model, view, and controller, establishes connections between them.
 * The game follows the Model-View-Controller (MVC) pattern.
 *
 * @author Sobouti
 * @version 1.0
 */
public class Main {

    /**
     * The main method where the Space Misery game starts.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Create instances of the model, view, and controller
        SMModel model = new SMModel();
        SMView view = new SMView();
        SMController controller = new SMController();

        // Set up connections between the model, view, and controller
        view.setController(controller);
        controller.setView(view);
        controller.setModel(model);

        // Run the Processing sketch with the specified view
        PApplet.runSketch(new String[]{"SMView"}, view);
    }
}