package beatrice;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 最近几年的文章索引的生成器
 * @author Kahle
 */
public class LastNumberYearGenerator extends AbstractGenerator {
    private Integer numberYear = 3;

    public Integer getNumberYear() {

        return numberYear;
    }

    public void setNumberYear(Integer numberYear) {

        this.numberYear = numberYear;
    }

    @Override
    public void generate() {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int leastYear = currentYear - numberYear;
        List<Article> articleList = this.takeArticleList();
        if (articleList.size() == 0) { return; }
        SimpleDateFormat dateFormat =
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        StringBuilder builder = new StringBuilder();
        builder.append("# ")
                .append(this.getFileTitle())
                .append(NEWLINE)
                .append(NEWLINE)
                .append(this.getFileDescription())
                .append("(Generated in ")
                .append(dateFormat.format(new Date()))
                .append(")")
                .append(NEWLINE);
        Integer runtimeYear = null, runtimeMonth = null;
        for (Article article : articleList) {
            Date createTime = article.getCreateTime();
            calendar.setTime(createTime);
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            if (year == leastYear) { break; }
            if (runtimeYear == null || runtimeYear != year) {
                runtimeYear = year;
                builder.append(NEWLINE)
                        .append(NEWLINE)
                        .append("- ### ")
                        .append(runtimeYear)
                        .append(NEWLINE);
            }
            if (runtimeMonth == null || runtimeMonth != month) {
                runtimeMonth = month;
                builder.append("    - #### ")
                        .append(runtimeMonth)
                        .append(NEWLINE);
            }
            builder.append("        - [")
                    .append(article.getTitle())
                    .append("](")
                    .append(article.getUrl())
                    .append(")")
                    .append(NEWLINE);
        }
        File outputFile = new File(this.getOutputFile());
        this.writeToFile(builder, outputFile);
    }

}
