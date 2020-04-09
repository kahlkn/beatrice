package beatrice;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 单年的文章索引的生成器
 * @author Kahle
 */
public class SingleYearGenerator extends AbstractGenerator {

    @Override
    public void generate() {
        Calendar calendar = Calendar.getInstance();
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
                .append(NEWLINE)
                .append(NEWLINE)
                .append(NEWLINE);
        Integer runtimeMonth = null;
        for (Article article : articleList) {
            Date createTime = article.getCreateTime();
            calendar.setTime(createTime);
            int month = calendar.get(Calendar.MONTH) + 1;
            if (runtimeMonth == null || runtimeMonth != month) {
                runtimeMonth = month;
                builder.append("- ### ")
                        .append(runtimeMonth)
                        .append(NEWLINE);
            }
            builder.append("    - [")
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
