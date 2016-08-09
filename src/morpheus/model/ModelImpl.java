package morpheus.model;

import morpheus.model.Coin.TypeCoin;
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

    private static final int DIMENSION16 = 16;
    private static final int DIMENSION24 = 24;
    private static final int DIMENSION30 = 30;
    private static final int DIMENSION40 = 40;
    private static final int DIMENSION64 = 64;

    private final Option option;
    
    /**
     * .
     */
    public ModelImpl() {
        option = new Option();
    }

    @Override
    public void setKeyJump(final int key) {
        option.setKeyJump(key);
    }

    @Override
    public int getKeyJump() {
        return option.getKeyJump();
    }

    @Override
    public void setKeyShoot(final int key) {
        option.setKeyShoot(key);
    }

    @Override
    public int getKeyShoot() {
        return option.getKeyShoot();
    }

    @Override
    public Pill getBluePill(final double x, final double y, final GameState state) {
        return new Pill(x, y, state,
                new Sprite(new SpriteSheet(new Texture("res/pillolaBlu.png"), DIMENSION16, DIMENSION16), 4, 1, 4)
                        .getFramesAsList()) {
            @Override
            public void reaction() {
                MainPlayer.getPlayer().getItem().incBullet();
            }
        };
    }

    @Override
    public Pill getRedPill(final double x, final double y, final GameState state) {
        return new Pill(x, y, state,
                new Sprite(new SpriteSheet(new Texture("res/redPill.png"), DIMENSION24, DIMENSION24), 2, 1, 2)
                        .getFramesAsList()) {
            @Override
            public void reaction() {
                MainPlayer.getPlayer().getItem().incHP();
            }
        };
    }

    @Override
    public MainPlayer getMainPlayer(final double x, final double y, final GameState state) {
        return MainPlayer.getPlayer(x, y, state, option);
    }

    @Override
    public MainPlayer getMainPlayer() {
        return MainPlayer.getPlayer();
    }

    @Override
    public AbstractMonster getGhost(final double x, final double y, final GameState state) {
        return new Ghost(x, y, state,
                new Sprite(new SpriteSheet(new Texture("res/ghost.png"), DIMENSION40), 1, 1, 1).getFramesAsList());
    }

    @Override
    public AbstractMonster getTree(final double x, final double y, final GameState state) {
        return new Tree(x, y, state,
                new Sprite(new SpriteSheet(new Texture("res/Evil_tree.png"), DIMENSION30, DIMENSION64), 3, 1, 3)
                        .getFramesAsList());
    }

    @Override
    public AbstractMonster getPenguin(final double x, final double y, final GameState state) {
        return new Penguin(x, y, state,
                new Sprite(new SpriteSheet(new Texture("res/tux.png"), DIMENSION30, DIMENSION64), 3, 1, 3)
                        .getFramesAsList());
    }

    @Override
    public Coin getNormalCoin(final double x, final double y, final GameState state) {
        return new Coin(x, y, state);
    }

    @Override
    public Coin getDoubleCoin(final double x, final double y, final GameState state) {
        return new Coin(x, y, TypeCoin.X2, state,
                new Sprite(new SpriteSheet(new Texture("res/coin.png"), DIMENSION24, DIMENSION24), 10, 1, 10)
                        .getFramesAsList());
    }

    @Override
    public Coin getSpecialCoin(final double x, final double y, final GameState state) {
        return new Coin(x, y, TypeCoin.SPECIAL, state,
                new Sprite(new SpriteSheet(new Texture("res/coin_silver.png"), DIMENSION24, DIMENSION24), 8, 1, 8)
                        .getFramesAsList());
    }

    @Override
    public Option getOption() {
        return option;
    }
}
