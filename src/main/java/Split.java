import org.kohsuke.args4j.*;

import java.io.*;

public class Split {
    private int countFiles = 0;

    @Option(name = "-d", usage = "defines output files names")
    private boolean isNameNum = true;

    @Option(name = "-l", usage = "output files length in strings", forbids = {"-n", "-c"})
    private int lengthInStrings = -1;

    @Option(name = "-c", usage = "output files length in chars", forbids = {"-n", "-l"})
    private int lengthInChars = -1;

    @Option(name = "-n", usage = "number of output files", forbids = {"-l", "-c"})
    private int numOfFiles = -1;

    @Option(name = "-o", usage = "default file name")
    private String defaultName = "x";

    @Argument(required = true, usage = "input file name")
    String inpFileName;

    public void start() throws IOException {
        if (defaultName.equals("-")) defaultName = inpFileName;
        if (numOfFiles != -1) {
            BufferedReader reader = new BufferedReader(new FileReader(inpFileName));
            LineNumberReader lineNumberReader = new LineNumberReader(reader);
            int lineNumber = 0;
            while (lineNumberReader.readLine() != null) lineNumber++;
            lineNumberReader.close();
            int linesInFile = (int) Math.ceil((double) lineNumber / numOfFiles);
            preProcess(false, linesInFile);
        } else if (lengthInChars != -1) {
            preProcess(true, lengthInChars);
        } else if (lengthInStrings != -1) {
            preProcess(false, lengthInStrings);
        } else {
            preProcess(false, 100);
        }
    }

    public String getName() {
        String result = defaultName;
        if (isNameNum) {
            result += countFiles;
        } else {
            result += (char) ((int) 'a' + (countFiles - 1) / 26) + +(char) ((int) 'a' + (countFiles - 1) % 26);
        }
        return result;
    }

    public void preProcess(boolean isChar, int length) throws IOException {
        boolean isEnd = false;
        FileInputStream fileInputStream = new FileInputStream(inpFileName);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(inpFileName));
        while (!isEnd) {
            countFiles++;
            File file = new File(getName() + ".txt");
            if (isChar) {
                FileOutputStream writer = new FileOutputStream(file);
                isEnd = process(length, fileInputStream, writer);
            } else {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                isEnd = process(length, bufferedReader, writer);
            }
        }
    }

    public boolean process(int length, BufferedReader reader, BufferedWriter writer) throws IOException {
        String temp;
        for (int i = 0; i < length; i++) {
            temp = reader.readLine();
            if (temp != null) {
                writer.write(temp);
            } else {
                writer.close();
                reader.close();
                return true;
            }
        }
        return false;
    }

    public boolean process(int length, FileInputStream reader, FileOutputStream writer) throws IOException {
        int temp;
        for (int i = 0; i < length; i++) {
            temp = reader.read();
            if (temp != -1) {
                writer.write(temp);
            } else {
                writer.close();
                reader.close();
                return true;
            }
        }
        return false;
    }
}