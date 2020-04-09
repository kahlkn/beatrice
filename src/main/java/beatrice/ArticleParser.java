package beatrice;

import java.io.*;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 文章解析器
 * @author Kahle
 */
public class ArticleParser {
    private static final String NEWLINE = System.getProperty("line.separator");
    private static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    private static final Integer MAX_REFERENCE_COUNT = 30;
    private static final Integer HEADER_NUMBER = 8;
    private static final String POUND_SIGN = "#";
    private static final String COLON = ":";
    private SimpleDateFormat dateFormat;

    private void parseContent(BufferedReader reader, Article article) throws IOException {
        StringBuilder builder = new StringBuilder();
        boolean isStart = false;
        for (String readLine; (readLine = reader.readLine()) != null; ) {
            if (!isStart && readLine.startsWith(POUND_SIGN)) { isStart = true; }
            if (!isStart) { continue; }
            builder.append(readLine).append(NEWLINE);
        }
        article.setContent(builder.toString());
    }

    private void parseHeader(BufferedReader reader, Article article) throws IOException, ParseException {
        for (int i = 0; i < HEADER_NUMBER; i++) {
            String readLine = reader.readLine();
            if (readLine == null) { break; }
            int indexOf = readLine.indexOf(COLON);
            if (indexOf == -1) { continue; }
            String val = readLine.substring(indexOf + 1);
            val = val.trim();
            if (readLine.startsWith("- Title:")) {
                article.setTitle(val);
            }
            else if (readLine.startsWith("- Category:")) {
                article.setCategory(val);
            }
            else if (readLine.startsWith("- Tag:")) {
                article.setTag(val);
            }
            else if (readLine.startsWith("- Author:")) {
                article.setAuthor(val);
            }
            else if (readLine.startsWith("- Creation Time:")) {
                Date parse = this.getDateFormat().parse(val);
                article.setCreateTime(parse);
            }
            else if (readLine.startsWith("- Update Time:")) {
                Date parse = this.getDateFormat().parse(val);
                article.setUpdateTime(parse);
            }
            else if (readLine.startsWith("- Original:")) {
                article.setOriginal(val);
            }
            else if (readLine.startsWith("- Reference:")) {
                Map<String, String> reference =
                        new HashMap<String, String>(MAX_REFERENCE_COUNT);
                for (int j = 0; j < MAX_REFERENCE_COUNT; j++) {
                    readLine = reader.readLine();
                    if (readLine == null) { continue; }
                    if (readLine.startsWith("---")) {
                        break;
                    }
                    if (!readLine.startsWith("    - [")) {
                        continue;
                    }
                    readLine = readLine.trim();
                    indexOf = readLine.indexOf("[");
                    if (indexOf == -1) { continue; }
                    readLine = readLine.substring(indexOf + 1);
                    indexOf = readLine.indexOf("]");
                    if (indexOf == -1) { continue; }
                    String key = readLine.substring(0, indexOf);
                    val = readLine.substring(
                            indexOf + 2, readLine.length() - 1
                    );
                    reference.put(key, val);
                }
                article.setReference(reference);
            }
        }
    }

    public ArticleParser() {

        this.setDateFormat(new SimpleDateFormat(DATE_PATTERN));
    }

    public SimpleDateFormat getDateFormat() {

        return dateFormat;
    }

    public void setDateFormat(SimpleDateFormat dateFormat) {

        this.dateFormat = dateFormat;
    }

    public Article parse(File file) throws IOException, ParseException {

        return this.parse(file, Charset.defaultCharset());
    }

    public Article parse(File file, Charset charset) throws IOException, ParseException {
        if (file == null) { return null; }
        file = file.getAbsoluteFile();
        Article article = new Article();
        article.setLocalPath(file.toString());
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(file), charset)
            );
            this.parseHeader(reader, article);
            this.parseContent(reader, article);
        }
        finally {
            if (reader != null) {
                reader.close();
            }
        }
        return article;
    }

}
