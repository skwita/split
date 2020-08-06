import org.kohsuke.args4j.*;

import java.io.*;

public class Split {
    private BufferedReader reader;
    private BufferedWriter writer;
    private int countFiles;

    @Option(name = "-d", usage = "defines output files names")
    private boolean isNameNum = true;

    @Option(name = "-l", usage = "output files length in strings", forbids = {"-n","-c"})
    private int lengthInStrings = -1;

    @Option(name = "-c", usage = "output files length in chars", forbids = {"-n","-l"})
    private int lengthInChars = -1;

    @Option(name = "-n", usage = "number of output files", forbids = {"-l","-c"})
    private int numOfFiles = -1;

    @Option(name = "-o", usage = "default file name")
    private String defaultName = "x";

    @Argument(required = true, usage = "input file name")
    private String inpFileName;

    public void start() throws IOException {
        if (defaultName.equals("-")) defaultName = inpFileName;
        boolean isNewFile;
        isNewFile = false;
        if (numOfFiles != -1) {
            reader = new BufferedReader(new FileReader(inpFileName));
            int lineNumber = 0;
            LineNumberReader lineNumberReader = new LineNumberReader(reader);
            while (lineNumberReader.readLine() != null) lineNumber++; //counting number of lines in the inp file
            lineNumberReader.close();
            int linesInFile = (int)Math.ceil((double)lineNumber / numOfFiles); //counting number of lines in each out file
            for (countFiles = 0; countFiles < numOfFiles; countFiles++){
                File file = new File (getName() + ".txt");
                writer = new BufferedWriter(new FileWriter(file));
                int count = 0;
                while (count < linesInFile) {
                    String temp = reader.readLine();
                    if (temp != null) {
                        writer.write(temp);
                    } else {
                        writer.close();
                        reader.close();
                        break;
                    }
                    count++;
                }
            }
        } else if (lengthInChars != -1) {
            FileInputStream reader = new FileInputStream(inpFileName);
            while(!isNewFile){
                File file = new File (getName() + ".txt");
                FileOutputStream writer = new FileOutputStream(file);
                for (int i = 0; i < lengthInChars; i++){
                    int temp = reader.read();
                    if (temp != -1) {
                        writer.write(temp);
                    } else {
                        countFiles++;
                        isNewFile = true;
                        break;
                    }
                }
                writer.close();
                reader.close();
            }
        } else if (lengthInStrings != -1){
            while(!isNewFile){
                File file = new File (getName() + ".txt");
                writer = new BufferedWriter(new FileWriter(file));
                for (int i = 0; i < lengthInStrings; i++){
                    String temp = reader.readLine();
                    if (temp != null) {
                        writer.write(temp);
                    } else {
                        countFiles++;
                        isNewFile = true;
                        break;
                    }
                }
                writer.close();
                reader.close();
            }
        } else {
            while(!isNewFile){
                File file = new File (getName() + ".txt");
                writer = new BufferedWriter(new FileWriter(file));
                for (int i = 0; i < 100; i++){
                    String temp = reader.readLine();
                    if (temp != null) {
                        writer.write(temp);
                    } else {
                        countFiles++;
                        isNewFile = true;
                        break;
                    }
                }
                writer.close();
                reader.close();
            }
        }
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
