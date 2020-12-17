package app;

import controllers.Controller;

public class MusicApp {

    private static void run() {
        final Controller controller = new Controller();
        controller.run();
    }

    public static void main(final String[] args) {
        MusicApp.run();
    }
}
