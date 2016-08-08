package morpheus.model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;

import morpheus.model.monster.AbstractMonster;
import morpheus.model.monster.Ghost;
import morpheus.model.monster.Penguin;
import morpheus.model.monster.Tree;
import morpheus.view.Texture;
import morpheus.view.state.GameState;

/**
 * 
 * @author jacopo
 *
 */
public class ModelImpl implements Model {

    private final Map<String, BufferedImage> textureMap;

    /**
     * 
     * @param map
     *            una mappa con tutte le path delle texture da caricare
     */
    public ModelImpl() {
        this.textureMap = new HashMap<>();
    }

    @Override
    public BufferedImage loader(final String file) {

        try {
            return ImageIO.read(new File(file));
        } catch (IOException e) {
            return null;
        }

    }

    @Override
    public void setKeyJump(final String key) {

    }

    @Override
    public String getKeyJump() {
        return null;
    }

    @Override
    public void addTexture(final Pair<String, String> p) {
        if (textureMap.containsKey(p.getKey())) {
            throw new IllegalArgumentException();
        }
        textureMap.put(p.getKey(), this.loader(p.getValue()));
    }

    @Override
    public BufferedImage getTexture(final String name) {
        return textureMap.get(name);
    }

    @Override
    public Map<String, BufferedImage> associate(final Map<String, String> map) {
        for (final Entry<String, String> e : map.entrySet()) {
            textureMap.put(e.getKey(), this.loader(e.getValue()));
        }
        return textureMap;
    }

    @Override
    public void setKeyShoot(final String key) {

    }

    @Override
    public String getKeyShoot() {
        return null;
    }

    @Override
    public Obstacle getBluePill(double x, double y, GameState state) {
        return new Obstacle(x, y, state,
                new Sprite(new SpriteSheet(new Texture("res/bluePill.png"), 24, 24), 2, 1, 2).getFramesAsList()) {
            @Override
            public void reaction() {
                MainPlayer.getPlayer().getItem().incBullet();
            }
        };
    }

    @Override
    public Obstacle getRedPill(double x, double y, GameState state) {
        return new Obstacle(x, y, state,
                new Sprite(new SpriteSheet(new Texture("res/redPill.png"), 24, 24), 2, 1, 2).getFramesAsList()) {
            @Override
            public void reaction() {
                MainPlayer.getPlayer().getItem().incHP();
            }
        };
    }

    @Override
    public MainPlayer getMainPlayer(double x, double y, GameState state) {
        return MainPlayer.getPlayer(x, y, state);
    }

    @Override
    public MainPlayer getMainPlayer() {
        return MainPlayer.getPlayer();
    }

    @Override
    public AbstractMonster getGhost(double x, double y, GameState state) {
        return new Ghost(x, y, state,
                new Sprite(new SpriteSheet(new Texture("res/ghost.png"), 40), 1, 1, 1).getFramesAsList());
    }

    @Override
    public AbstractMonster getTree(double x, double y, GameState state) {
        return new Tree(x, y, state,
                new Sprite(new SpriteSheet(new Texture("res/Evil_tree.png"), 30, 64), 3, 1, 3).getFramesAsList());
    }

    @Override
    public AbstractMonster getPenguin(double x, double y, GameState state) {
        return new Penguin(x, y, state,
                new Sprite(new SpriteSheet(new Texture("res/tux.png"), 30, 64), 3, 1, 3).getFramesAsList());
    }
}
