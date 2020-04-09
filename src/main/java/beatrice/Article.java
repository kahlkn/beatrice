package beatrice;

import java.util.Date;
import java.util.Map;

/**
 * 文章对象
 * @author Kahle
 */
public class Article {
    private String title;
    private String category;
    private String tag;
    private String author;
    private Date createTime;
    private Date updateTime;
    private String original;
    private Map<String, String> reference;
    private String content;
    private String localPath ;
    private String fileName;
    private String url;

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getCategory() {

        return category;
    }

    public void setCategory(String category) {

        this.category = category;
    }

    public String getTag() {

        return tag;
    }

    public void setTag(String tag) {

        this.tag = tag;
    }

    public String getAuthor() {

        return author;
    }

    public void setAuthor(String author) {

        this.author = author;
    }

    public Date getCreateTime() {

        return createTime;
    }

    public void setCreateTime(Date createTime) {

        this.createTime = createTime;
    }

    public Date getUpdateTime() {

        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {

        this.updateTime = updateTime;
    }

    public String getOriginal() {

        return original;
    }

    public void setOriginal(String original) {

        this.original = original;
    }

    public Map<String, String> getReference() {

        return reference;
    }

    public void setReference(Map<String, String> reference) {

        this.reference = reference;
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content) {

        this.content = content;
    }

    public String getLocalPath() {

        return localPath;
    }

    public void setLocalPath(String localPath) {

        this.localPath = localPath;
    }

    public String getFileName() {

        return fileName;
    }

    public void setFileName(String fileName) {

        this.fileName = fileName;
    }

    public String getUrl() {

        return url;
    }

    public void setUrl(String url) {

        this.url = url;
    }

}
