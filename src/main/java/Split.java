import org.kohsuke.args4j.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;

public class Split {
    private BufferedReader reader;
    private BufferedWriter writer;
    private int countFiles;

    @Option(name = "-d", usage = "defines output files names")
    private boolean isNameNum = true;

    @Option(name = "-l", usage = "output files length in strings")
    private int lengthInStrings = -1;

    @Option(name = "-c", usage = "output files length in chars")
    private int lengthInChars = -1;

    @Option(name = "-n", usage = "number of output files")
    private int numOfFiles = -1;

    @Option(name = "-o", usage = "default file name")
    private String defaultName = "x";

    @Argument(required = true, usage = "input file name")
    private String inpFileName;

    public void start() throws IOException {
        reader = new BufferedReader(new FileReader(inpFileName));
        if (defaultName.equals("-")) defaultName = inpFileName;

    }

    public String getName(){
        String result = defaultName;
        if (isNameNum) {
            result += countFiles;
        } else {
            if (countFiles <= 26) {
                result += "a" + (char)((int)'a' + countFiles - 1);
            } else result += (char)((int)'a' + (countFiles - 1) / 26) + + (char)((int)'a' + (countFiles - 1) % 26);
        }
        return result;
    }


}
