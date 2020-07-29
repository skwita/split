import org.kohsuke.args4j.*;

public class Split {
    @Option(name = "-d", usage = "defines output files names")
    public boolean isNameNum;

    @Option(name = "-l", metaVar = "Num", usage = "output files length in strings")
    public int lengthInStrings;

    @Option(name = "-c", metaVar = "Num", usage = "output files length in chars")
    public int lengthInChars;

    @Option(name = "-n", metaVar = "Num", usage = "number of output files")
    public int numOfFiles;

    @Option(name = "-o", usage = "default file name")
    public String defaultName;
}
