package morpheus.model.test;

import static org.junit.Assert.*;

import java.awt.event.KeyEvent;

import org.junit.Test;

import morpheus.model.Option;

/**
 * Test for class Option.
 * 
 * @author jacopo
 *
 */
public class OptionTest {


    /**
     * .
     */
    @Test
    public void test() {
        final Option s = Option.getOption();

        if (s.isFirstOpen()) {
            assertEquals(s.getFileName(), "res/Option.dat");
            assertEquals(s.getKeyShoot(), KeyEvent.VK_SPACE);
            assertEquals(s.getKeyJump(), KeyEvent.VK_W);

            s.setKeyShoot(KeyEvent.VK_0);
            assertEquals(s.getKeyShoot(), KeyEvent.VK_0);
            s.setKeyJump(KeyEvent.VK_1);
            assertEquals(s.getKeyJump(), KeyEvent.VK_1);
            reset();
            s.close();
        } else {
            s.setKeyJump(KeyEvent.VK_0);
            s.setKeyShoot(KeyEvent.VK_1);
            s.setVolume(0);
            final int keyJump = s.getKeyJump();
            final int keyShoot = s.getKeyShoot();
            boolean volume = false;
            if (s.getVolume() == 0) {
                volume = true;
            }
            
            assertEquals(keyJump, KeyEvent.VK_0);
            assertEquals(keyShoot, KeyEvent.VK_1);
            assertTrue(volume);
            reset();
            s.close();
        }

    }

    private void reset() {
        final Option o = Option.getOption();

        o.setKeyJump(Option.DEFAULT_JUMP_KEY);
        o.setKeyShoot(Option.DEFAULT_SHOOT_KEY);
        o.setVolume(Option.DEFAULT_VOLUME);
    }

}
