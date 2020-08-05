import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.io.IOException;

public class Launcher {
    public static void main(String[] args) {
        Split split = new Split();
        CmdLineParser parser = new CmdLineParser(split);
        try {
            parser.parseArgument(args);
            split.start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CmdLineException e) {
            e.printStackTrace();
        }
    }
}
