package morpheus.model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.awt.event.KeyEvent;

import org.junit.Test;

import morpheus.model.Option;
/**
 * Test for class Statistics.
 * @author jacopo
 *
 */
public class StatisticsTest {

    private static final int NUM_TEST_11 = 11;
    private static final int NUM_TEST_5 = 5;
    private static final int NUM_TEST_16 = 16;
    private static final int NUM_TEST_20 = 20;
    private static final int NUM_TEST_10 = 10;
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
            assertFalse(s.isMainPlayerOpen());
            assertFalse(s.isSidePlayerOpen());
        
            s.setKeyShoot(KeyEvent.VK_0);
            assertEquals(s.getKeyShoot(), KeyEvent.VK_0);
            s.setKeyJump(KeyEvent.VK_1);
            assertEquals(s.getKeyJump(), KeyEvent.VK_1);
            s.close();
        } else {
            s.setKeyJump(KeyEvent.VK_W);
            s.setKeyShoot(KeyEvent.VK_SPACE);
            final int keyJump = s.getKeyJump();
            final int keyShoot = s.getKeyShoot();
            
            assertEquals(keyJump, KeyEvent.VK_W);
            assertEquals(keyShoot, KeyEvent.VK_SPACE);
             System.out.println("Volume:" + s.getVolume());
            s.close();
        }
        
    }

}
