package morpheus.controller;

import morpheus.model.MainPlayer;
import morpheus.view.GameState;
import morpheus.view.RandomTile;

import morpheus.view.Tile;

public class Collision {

    private final GameState state;
    private final MainPlayer player;
    public Collision(final GameState state) {
        this.state = state;
        this.player = MainPlayer.getPlayer();
    }
    
    public boolean hasVerticalCollision() {
        // NORMAL TILES
        for (int i = 0; i < state.getTiles().size(); i++) {
                Tile t = state.getTiles().get(i);
                if (player.getBounds().intersects(t.getTop()) && player.velY > 0) {
                        player.canJump = true;
                        player.falling = false;
                        player.velY = 0;
                        return true;
                } else {
                        player.falling = true;
                }

                if (player.getBounds().intersects(t.getBottom()) && player.velY < 0) {
                        player.velY = 0;
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
            if (player.getBounds().intersects(rt1.getTop()) && player.velY >= 0) {
                    
                    player.canJump = true;
                    player.falling = false;
                    player.velY = 0;
                    player.jumpPermission();
                    return true;
            } else {
                    player.falling = true;
            }

            if (player.getBounds().intersects(rt1.getBottom()) && player.isJumping) {
                    player.velY = 0;
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
            // Incremento la varibile dello stesso valore di movimento del
            // giocatore. Viene fatto qui l�incrememento perch� sia il pi�
            // preciso possibile dato che viene incrementata praticamente nello
            // stesso instante della posizione del del giocatore
            

    }
    if (!hasVerticalCollision()) {
            player.verticalCollision = false;
           
    }
}
}
