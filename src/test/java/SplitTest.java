import org.junit.*;

import java.io.File;
import java.io.IOException;

import static org.apache.commons.io.FileUtils.contentEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SplitTest {
    @Test
    public void test() throws IOException {
        Launcher.main("-d -l 4 -o - initial".split(" "));
        assertTrue(contentEquals(
                new File("initial1.txt"),
                new File("expected\\-l\\t1.txt")));
        assertTrue(contentEquals(
                new File("initial2.txt"),
                new File("expected\\-l\\t2.txt")));
        assertTrue(contentEquals(
                new File("initial3.txt"),
                new File("expected\\-l\\t3.txt")));

        Launcher.main("-d -c 150 initial".split(" "));
        assertTrue(contentEquals(
                new File("x1.txt"),
                new File("expected\\-c\\t1.txt")));
        assertTrue(contentEquals(
                new File("x2.txt"),
                new File("expected\\-c\\t2.txt")));
        assertTrue(contentEquals(
                new File("x3.txt"),
                new File("expected\\-c\\t3.txt")));
        assertTrue(contentEquals(
                new File("x4.txt"),
                new File("expected\\-c\\t4.txt")));
        assertTrue(contentEquals(
                new File("x5.txt"),
                new File("expected\\-c\\t5.txt")));
       assertTrue(contentEquals(
                new File("x6.txt"),
                new File("expected\\-c\\t6.txt")));

        Launcher.main("-n 3 initial".split(" "));
        assertTrue(contentEquals(
                new File("xaa.txt"),
                new File("expected\\-n\\t1.txt")));
        assertTrue(contentEquals(
                new File("xab.txt"),
                new File("expected\\-n\\t2.txt")));
        assertTrue(contentEquals(
                new File("xac.txt"),
                new File("expected\\-n\\t3.txt")));
    }

}