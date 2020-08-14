import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SplitTest {
    @Test
    public void test() throws IOException {
        File initial = new File("texts/initial");
        Launcher.main("-d -l 4 -o - initial".split(" "));
//        System.out.println(initial.getCanonicalPath());
        assertEquals(new File("initial1"), new File("expected/-l/-1exp"));
        Launcher.main("-c 150 -o First".split(" "));
        Launcher.main("-n 3 First".split(" "));
    }

}