import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Parser implements Serializable {

    private volatile InputStream is;
    public static List<String> lines;
    private Long linesCount;

    {
        lines = new LinkedList<>();
    }

    public Parser(File file) {
        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public InputStream getIs() {
        return is;
    }

    public void setIs(InputStream is) {
        this.is = is;
    }

/*    public List<String> getLines() {
        return lines;
    }*/

    public Long getLinesCount() {
        return linesCount;
    }

    public synchronized String read() throws IOException {
        return readFromInputStream(is);
    }

    private String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuffer resultStringBuilder = new StringBuffer();
        linesCount = 0L;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
                lines.addAll(Collections.singleton(line));
                linesCount++;
            }
        }
        return resultStringBuilder.toString();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        Parser parser = (Parser) o;
        return Objects.equals(is, parser.is) &&
                Objects.equals(lines, parser.lines) &&
                Objects.equals(linesCount, parser.linesCount);
    }

}