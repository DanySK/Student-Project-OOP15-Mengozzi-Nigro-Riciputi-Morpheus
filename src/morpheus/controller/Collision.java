package morpheus.controller;

import morpheus.model.Player;
import morpheus.view.RandomTile;
import morpheus.view.Tile;
import morpheus.view.state.GameState;

public class Collision {

    private final GameState state;
    private final Player player;

    public Collision(final GameState state, final Player p) {
        this.state = state;
        this.player = p;
    }

    public boolean hasVerticalCollision() {
        // NORMAL TILES
        for (int i = 0; i < state.getTiles().size(); i++) {
            Tile t = state.getTiles().get(i);
            if (player.getBottom().intersects(t.getTop()) && player.getVelY() > 0) {
                player.groundCollission();
                return true;
            } else {
                player.falling();
            }

            if (player.getBounds().intersects(t.getBottom()) && player.getVelY() < 0) {
                player.setVelY(0);
                return true;
            }
        }
        // Aggiunto il calcolo delle collisioni sia verticali sia orizzontali
        // (vedi metodo sotto) delle RandomTile prendendole dal metodo
        // getAllRandomTiles() del GameState in cui � contenuto un Array con
        // tutte le RandomTile calcolate dalle BitMap

        // RANDOM TILES
        for (int i = 0; i < state.getAllRandomTiles().size(); i++) {
            RandomTile rt1 = state.getAllRandomTiles().get(i);
            if (player.getBottom().intersects(rt1.getTop()) && player.getVelY() >= 0) {

                player.groundCollission();
                return true;
            } else {
                player.falling();
            }

            if (player.getBounds().intersects(rt1.getBottom()) && player.isInJump()) {
                player.stopJumping();
                return true;
            }
        }
        return false;

    }

    public boolean hasHorizontalCollision() {
        // NORMAL TILES
        for (int i = 0; i < state.getTiles().size(); i++) {
            Tile t = state.getTiles().get(i);
            if (player.getBounds().intersects(t.getRight()) && player.getVelRun() < 0) {
                player.setVelRun(0);
                return true;
            }
            if (player.getBounds().intersects(t.getLeft()) && player.getVelRun() > 0) {
                player.setVelRun(0);
                return true;
            }
        }

        // RANDOM TILES

        for (int i = 0; i < state.getAllRandomTiles().size(); i++) {
            RandomTile rt1 = state.getAllRandomTiles().get(i);
            player.getBounds();
            if (player.getBounds().intersects(rt1.getRight()) && player.getVelRun() <= 0) {
                return true;
            }
            if (player.getBounds().intersects(rt1.getLeft()) && player.getVelRun() >= 0) {
                return true;
            }
        }
        return false;
    }

    public void tick() {
        if (!hasHorizontalCollision()) {
            player.startRun();
        }
        if (!hasVerticalCollision()) {
            player.setVerticalCollision(false);
        }
    }
}
