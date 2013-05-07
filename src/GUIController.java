/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hp
 */
public class GUIController extends Controller {

    private NewJFrame gui;

    public GUIController(ControllerDataInterface globalData, NewJFrame gui) {
        super(globalData);

        this.gui = gui;
        gui.setController(this);
//        this.autoAccepts.clear();
    }

    @Override
    public void control() {
        while (true) {
            try {
                Task task = this.transmissions.take();
                if (this.autoAccepts.contains(task.type())) {
                    task.run();
                } else {
                    this.gui.addRequest(task);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
