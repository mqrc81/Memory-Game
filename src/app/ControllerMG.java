package app;

import gui.ViewMG;
import model.ModelMG;

/**
 * @author: Marc Schmidt
 * @date: 2020-04-09
 * @project: M120
 */

public class ControllerMG {
    public ControllerMG() {
    ModelMG model = new ModelMG();
        new ViewMG(model);
    }

    public static void main(String[] args) {
        new ControllerMG();
    }
}
