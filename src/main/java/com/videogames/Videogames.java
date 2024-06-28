package com.videogames;

import com.videogames.controller.GameController;
import com.videogames.model.GameDao;
import com.videogames.view.GameView;

public class Videogames {
    public static void main(String[] args) {
        GameView view = new GameView();
        GameDao dao = new GameDao();
        clearConsole();
        GameController controller = new GameController(view, dao);
        controller.start();
    }
    public static void clearConsole() {
        // Secuencia de escape ANSI para limpiar la consola
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}