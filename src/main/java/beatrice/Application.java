package beatrice;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static beatrice.AbstractGenerator.SLASH;

/**
 * 程序入口
 * @author Kahle
 */
public class Application {
    private static final String[] IGNORE_DIRS = new String[] {".git", "src", ".idea", "target", "-"};
    private static ArticleParser articleParser = new ArticleParser();

    private static String getRootPath() {
        URL res = Application.class.getResource(SLASH);
        File file = res != null ? new File(res.getFile()) : null;
        file = file != null ? file.getParentFile() : null;
        return file != null ? file.getParent() : null;
    }

    private static void generateEachYear(String rootPath) {
        List<String> ignoreDirs = Arrays.asList(IGNORE_DIRS);
        File rootFile = new File(rootPath);
        File[] files = rootFile.listFiles();
        if (files == null) { return; }
        for (File file : files) {
            if (file.isFile()) { continue; }
            String fileName = file.getName();
            if (ignoreDirs.contains(fileName)) { continue; }
            SingleYearGenerator singleYearGenerator = new SingleYearGenerator();
            singleYearGenerator.setArticleParser(articleParser);
            singleYearGenerator.addIgnoreDirs(IGNORE_DIRS);
            singleYearGenerator.setWorkDirectory(file.toString());
            singleYearGenerator.setFileTitle("In " + fileName);
            singleYearGenerator.setFileDescription("Articles for " + fileName + ". [Home](../README.md). ");
            singleYearGenerator.setOutputFile("./" + fileName + "/README.md");
            singleYearGenerator.generate();
        }
        System.out.println("Generate each year success. ");
    }

    private static void generateLastThreeYears(String rootPath) {
        LastNumberYearGenerator lastNumberYearGenerator = new LastNumberYearGenerator();
        lastNumberYearGenerator.setArticleParser(articleParser);
        lastNumberYearGenerator.addIgnoreDirs(IGNORE_DIRS);
        lastNumberYearGenerator.setWorkDirectory(rootPath);
        lastNumberYearGenerator.setFileTitle("Last Three Years");
        lastNumberYearGenerator.setFileDescription("The last three years of articles. [Home](README.md). ");
        lastNumberYearGenerator.setOutputFile("./last-three-years.md");
        lastNumberYearGenerator.generate();
        System.out.println("Generate last three years success. ");
    }

    public static void main(String[] args) {
        String rootPath = Application.getRootPath();
        Application.generateLastThreeYears(rootPath);
        Application.generateEachYear(rootPath);
    }

}
