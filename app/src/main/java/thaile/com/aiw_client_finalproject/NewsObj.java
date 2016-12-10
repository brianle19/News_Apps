package thaile.com.aiw_client_finalproject;

import java.io.Serializable;

/**
 * Created by Thai Le on 11/23/2016.
 */

public class NewsObj implements Serializable{

    private int idNews;
    private String titleNews;
    private String shortIntro;
    private String category;
    private String dateNews;
    private String authorNews;
    private String imgNews;
    private String contentNews;
    private String [] tagNews;

    public NewsObj(int idNews, String titleNews, String shortIntro, String category, String dateNews, String authorNews,
                   String imgNews, String contentNews, String[] tagNews) {
        super();
        this.idNews = idNews;
        this.titleNews = titleNews;
        this.shortIntro = shortIntro;
        this.category = category;
        this.dateNews = dateNews;
        this.authorNews = authorNews;
        this.imgNews = imgNews;
        this.contentNews = contentNews;
        this.tagNews = tagNews;
    }

    public int getIdNews() {
        return idNews;
    }
    public void setIdNews(int idNews) {
        this.idNews = idNews;
    }
    public String getTitleNews() {
        return titleNews;
    }
    public void setTitleNews(String titleNews) {
        this.titleNews = titleNews;
    }
    public String getShortIntro() {
        return shortIntro;
    }
    public void setShortIntro(String shortIntro) {
        this.shortIntro = shortIntro;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getDateNews() {
        return dateNews;
    }
    public void setDateNews(String dateNews) {
        this.dateNews = dateNews;
    }
    public String getAuthorNews() {
        return authorNews;
    }
    public void setAuthorNews(String authorNews) {
        this.authorNews = authorNews;
    }
    public String getImgNews() {
        return imgNews;
    }
    public void setImgNews(String imgNews) {
        this.imgNews = imgNews;
    }
    public String getContentNews() {
        return contentNews;
    }
    public void setContentNews(String contentNews) {
        this.contentNews = contentNews;
    }
    public String[] getTagNews() {
        return tagNews;
    }
    public void setTagNews(String[] tagNews) {
        this.tagNews = tagNews;
    }

}
