package main;

import controller.BengkelController;
import view.BengkelView;

public class Main {
    public static void main(String[] args) {
        BengkelController controller = new BengkelController();
        BengkelView view = new BengkelView(controller);
        view.start();
    }
}