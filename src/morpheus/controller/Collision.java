package morpheus.controller;

import java.util.ArrayList;

import morpheus.model.AbstractDrawable;
import morpheus.model.AbstractPill;
import morpheus.model.Bullet;
import morpheus.model.Coin;
import morpheus.model.Player;
import morpheus.model.Spikes;
import morpheus.model.monster.AbstractMonster;
import morpheus.model.monster.Tree.TreeBullet;
import morpheus.view.RandomTile;
import morpheus.view.Tile;
import morpheus.view.state.GameState;

public class Collision {

    private final GameState state;
    private final Player player;
    private ArrayList<AbstractDrawable> playerBullets;

    public Collision(final GameState state, final Player p) {
        this.state = state;
        this.player = p;
        this.playerBullets = new ArrayList<>();
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

        // SPIKES VERTICAL

        for (int i = 0; i < state.getEntities().size(); i++) {
            if (state.getEntities().get(i) instanceof Spikes) {
                Spikes ad = (Spikes) state.getEntities().get(i);
                if (ad.getTop().intersects(player.getBottom()) && player.getVelY() >= 0) {
                    player.groundCollission();
                    if (player.isKnocking() == false) {
                        ad.reaction(player);
                    }
                    return true;
                } else {
                    player.falling();
                }

                if (player.getBounds().intersects(ad.getBottom()) && player.isInJump()) {
                    player.stopJumping();
                    return true;

                }
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
            if (player.getBounds().intersects(rt1.getRight()) && player.getVelRun() <= 0) {
                return true;
            }
            if (player.getBounds().intersects(rt1.getLeft()) && player.getVelRun() >= 0) {
                return true;
            }
        }

        // SPIKES HORIZONTAL
        if (player.isKnocking() == false) {
            for (int i = 0; i < state.getEntities().size(); i++) {
                if (state.getEntities().get(i) instanceof Spikes) {
                    Spikes ad = (Spikes) state.getEntities().get(i);
                    if (player.getBounds().intersects(ad.getRight()) && player.getVelRun() <= 0) {
                        return true;
                    }
                    if (player.getBounds().intersects(ad.getLeft()) && player.getVelRun() >= 0) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean hasSpikeVerticalCollsion() {
        // SPIKES VERTICAL
        if (player.isKnocking() == false) {
            for (int i = 0; i < state.getEntities().size(); i++) {
                if (state.getEntities().get(i) instanceof Spikes) {
                    AbstractDrawable ad = state.getEntities().get(i);
                    if (player.getBottom().intersects(ad.getTop()) && player.getVelY() >= 0) {
                        player.hit();
                        player.groundCollission();
                        return true;
                    } else {
                        player.falling();
                    }

                    if (player.getBounds().intersects(ad.getBottom()) && player.isInJump()) {
                        player.stopJumping();
                        return true;

                    }
                }
            }
        }
        return false;
    }

    public boolean hasSpikeHorizontalCollision() {
        // SPIKES HORIZONTAL
        if (player.isKnocking() == false) {
            for (int i = 0; i < state.getEntities().size(); i++) {
                if (state.getEntities().get(i) instanceof Spikes) {
                    AbstractDrawable ad = state.getEntities().get(i);
                    if (player.getBounds().intersects(ad.getRight()) && player.getVelRun() <= 0) {
                        return true;
                    }
                    if (player.getBounds().intersects(ad.getLeft()) && player.getVelRun() >= 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void hittedEnemy() {
        // HIT
        for (int i = 0; i < state.getEntities().size(); i++) {
            if (state.getEntities().get(i) instanceof Bullet) {
                playerBullets.add(state.getEntities().get(i));
            }
        }
        for (int i = 0; i < player.getBullets().size(); i++) {
            for (int j = 0; j < state.getEntities().size(); j++) {
                if (state.getEntities().get(j) instanceof AbstractMonster) {
                    if (player.getBullets().get(i).getBounds().intersects(state.getEntities().get(j).getBounds())) {
                        state.getEntities().get(j).setRemove();
                    }
                }
            }
        }

    }

    public void getEnemyCollision() {
        // ENEMIES
        if (player.isKnocking() == false) {
            for (int i = 0; i < state.getEntities().size(); i++) {
                if (state.getEntities().get(i) instanceof AbstractMonster) {
                    AbstractDrawable ad = state.getEntities().get(i);
                    if (player.getBounds().intersects(ad.getBounds())) {
                        player.hit();
                    }
                }
            }
        }
    }

    public void getCoinCollision() {
        // COINS
        for (int i = 0; i < state.getEntities().size(); i++) {
            if (state.getEntities().get(i) instanceof Coin) {
                AbstractDrawable ad = state.getEntities().get(i);
                if (player.getBounds().intersects(ad.getBounds())) {
                    state.getEntities().get(i).setRemove();
                }
            }
        }
    }

    public void getPillCollision() {
        // PILLS
        for (int i = 0; i < state.getEntities().size(); i++) {
            if (state.getEntities().get(i) instanceof AbstractPill) {
                AbstractDrawable ad = state.getEntities().get(i);
                if (player.getBounds().intersects(ad.getBounds())) {
                    ((AbstractPill) state.getEntities().get(i)).reaction();
                    state.getEntities().get(i).setRemove();
                }
            }
        }
    }

    public void getBulletCollision() {
        // BULLETS
        if (player.isKnocking() == false) {
            for (int i = 0; i < state.getEntities().size(); i++) {
                if (state.getEntities().get(i) instanceof TreeBullet) {
                    AbstractDrawable ad = state.getEntities().get(i);
                    if (player.getBounds().intersects(ad.getBounds())) {
                        player.hit();
                    }
                }
            }
        }
    }

    public void tick() {

        if (!hasHorizontalCollision()) {
            player.startRun();
        }

        if (!hasVerticalCollision()) {
            player.setVerticalCollision(false);
        }

        hittedEnemy();

        getCoinCollision();

        getPillCollision();

        getEnemyCollision();

        getBulletCollision();
    }
}
