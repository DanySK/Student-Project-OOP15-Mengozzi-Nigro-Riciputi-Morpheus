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
    private static final int DIMENSION39 = 39;
    private static final int DIMENSION40 = 40;
    private static final int DIMENSION64 = 64;
    private static final int DIMENSION80 = 80;

    private final Option option;
    private final Ranking ranking;

    /**
     * .
     */
    public ModelImpl() {
        option = Option.getOption();
        ranking = Ranking.getRankingClass();
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
    public AbstractPill getBluePill(final double x, final double y, final GameState state) {
        return new AbstractPill(x, y, state,
                new Sprite(new SpriteSheet(new Texture("res/pillolaBlu.png"), DIMENSION16, DIMENSION16), 4, 1, 4)
                        .getFramesAsList()) {
            @Override
            public void reaction() {
                if (ModelImpl.this.isMainPlayerOpen()) {
                    MainPlayer.getPlayer().getItem().incHP();
                } else {
                    SidePlayer.getPlayer().getItem().incHP();
                }
            }
        };
    }

    @Override
    public AbstractPill getRedPill(final double x, final double y, final GameState state) {
        return new AbstractPill(x, y, state,
                new Sprite(new SpriteSheet(new Texture("res/pillolaRossa.png"), DIMENSION16, DIMENSION16), 4, 1, 4)
                        .getFramesAsList()) {
            @Override
            public void reaction() {
                if (ModelImpl.this.isMainPlayerOpen()) {
                    MainPlayer.getPlayer().getItem().incHP();
                } else {
                    SidePlayer.getPlayer().getItem().incHP();
                }
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
        if (isMainPlayerOpen()) {
            return new Tree(x, y, state, getMainPlayer(),
                    new Sprite(new SpriteSheet(new Texture("res/Evil_tree.png"), DIMENSION30, DIMENSION64), 3, 1, 3)
                            .getFramesAsList());
        } else {
            return new Tree(x, y, state, getSidePlayer(),
                    new Sprite(new SpriteSheet(new Texture("res/Evil_tree.png"), DIMENSION30, DIMENSION64), 3, 1, 3)
                            .getFramesAsList());
        }
    }

    @Override
    public AbstractMonster getPenguin(final double x, final double y, final GameState state) {
        return new Penguin(x, y, state,
                new Sprite(new SpriteSheet(new Texture("res/tux.png"), DIMENSION40, DIMENSION39), 2, 1, 2)
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
    public SidePlayer getSidePlayer(final double x, final double y, final GameState state) {
        return SidePlayer.getPlayer(x, y, state, option);
    }

    @Override
    public SidePlayer getSidePlayer() {
        return SidePlayer.getPlayer();
    }

    @Override
    public boolean isMainPlayerOpen() {
        return option.isMainPlayerOpen();
    }

    @Override
    public boolean isSidePlayerOpen() {
        return option.isSidePlayerOpen();
    }

    @Override
    public void setMainPlayerOpening(final boolean status) {
        option.setMainPlayerOpening(status);
    }

    @Override
    public void setSidePlayerOpening(final boolean status) {
        option.setSidePlayerOpening(status);
    }

    @Override
    public void saveOption() {
        option.close();
    }

    @Override
    public void setVolume(final double volume) {
        option.setVolume(volume);
    }

    @Override
    public double getVolume() {
        return option.getVolume();
    }

    @Override
    public Ranking getRanking() {
        return ranking;
    }

    @Override
    public Spikes getSpikes(final double x, final double y, final GameState state) {
        return new Spikes(x, y, state,
                new Sprite(new SpriteSheet(new Texture("res/spike.png"), DIMENSION64, DIMENSION80), 1, 1, 1)
                        .getMainFrame());
    }
}
