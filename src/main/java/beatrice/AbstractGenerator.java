package beatrice;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * 抽象的索引生成器
 * @author Kahle
 */
public abstract class AbstractGenerator {
    public static final String NEWLINE = System.getProperty("line.separator");
    public static final String README_MD = "README.md";
    public static final String MARKDOWN_EXT = ".md";
    public static final String EMPTY_STRING = "";
    public static final String SLASH = "/";
    private List<String> ignoreDirs = new ArrayList<String>();
    private ArticleParser articleParser;
    private String workDirectory;
    private String fileTitle;
    private String fileDescription;
    private String outputFile;
    private List<Article> articleList;

    private void fillUrl(Article article) {
        String url = article.getLocalPath();
        url = url.replace(workDirectory, EMPTY_STRING);
        url = url.replaceAll("\\\\", SLASH);
        if (url.startsWith(SLASH)) {
            url = url.substring(1);
        }
        article.setUrl(url);
    }

    private void addAllFirst(LinkedList<File> queue, File[] files) {
        if (files == null) { return; }
        for (File file : files) {
            queue.addFirst(file);
        }
    }

    private void initializeQueue(LinkedList<File> queue, File[] files) {
        for (File file : files) {
            if (file.isFile()) { continue; }
            String fileName = file.getName();
            if (ignoreDirs.contains(fileName)) { continue; }
            queue.addFirst(file);
        }
    }

    public void addIgnoreDirs(String... ignoreDirs) {
        if (ignoreDirs.length == 0) { return; }
        this.ignoreDirs.addAll(Arrays.asList(ignoreDirs));
    }

    public List<String> getIgnoreDirs() {

        return Collections.unmodifiableList(ignoreDirs);
    }

    public void setIgnoreDirs(List<String> ignoreDirs) {

        this.ignoreDirs = ignoreDirs;
    }

    public ArticleParser getArticleParser() {

        return articleParser;
    }

    public void setArticleParser(ArticleParser articleParser) {

        this.articleParser = articleParser;
    }

    public String getWorkDirectory() {

        return workDirectory;
    }

    public void setWorkDirectory(String workDirectory) {

        this.workDirectory = workDirectory;
    }

    public String getFileTitle() {

        return fileTitle;
    }

    public void setFileTitle(String fileTitle) {

        this.fileTitle = fileTitle;
    }

    public String getFileDescription() {

        return fileDescription;
    }

    public void setFileDescription(String fileDescription) {

        this.fileDescription = fileDescription;
    }

    public String getOutputFile() {

        return outputFile;
    }

    public void setOutputFile(String outputFile) {

        this.outputFile = outputFile;
    }

    protected List<Article> takeArticleList() {
        try {
            if (articleList != null) { return articleList; }
            articleList = new ArrayList<Article>();
            File targetFile = new File(workDirectory);
            File[] files = targetFile.listFiles();
            if (files == null) { return articleList; }
            LinkedList<File> queue = new LinkedList<File>();
            this.initializeQueue(queue, files);
            while (!queue.isEmpty()) {
                File file = queue.removeFirst();
                if (file == null) { continue; }
                if (file.isDirectory()) {
                    File[] listFiles = file.listFiles();
                    this.addAllFirst(queue, listFiles);
                }
                else {
                    String fileStr = file.toString();
                    if (!fileStr.endsWith(MARKDOWN_EXT)) { continue; }
                    if (fileStr.endsWith(README_MD)) { continue; }
                    Article article = articleParser.parse(file);
                    this.fillUrl(article);
                    articleList.add(article);
                }
            }
            return articleList;
        }
        catch (Exception e) {
            throw e instanceof RuntimeException
                    ? (RuntimeException) e
                    : new RuntimeException(e);
        }
    }

    protected void writeToFile(StringBuilder builder, File file) {
        BufferedWriter writer = null;
        try {
            if (!file.exists() && !file.createNewFile()) {
                throw new IllegalStateException("Create new file failure. ");
            }
            writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(file))
            );
            writer.write(builder.toString());
        }
        catch (Exception e) {
            throw e instanceof RuntimeException
                    ? (RuntimeException) e
                    : new RuntimeException(e);
        }
        finally {
            if (writer != null) {
                try {
                    writer.close();
                }
                catch (Exception e) {
                    writer = null;
                }
            }
        }
    }

    /**
     * 生成
     */
    public abstract void generate();

}
