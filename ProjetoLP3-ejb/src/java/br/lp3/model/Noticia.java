package br.lp3.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author 31409695
 */
public class Noticia implements Serializable {
    
    private long gid;
    private String title;
    private String url;
    private String author;
    private String content;
    //Salva a data da publicação no formato timestamp
    private long timestamp;

    public Noticia(long gid, String title, String url, String author, String content, long timestamp) {
        this.gid = gid;
        this.title = title;
        this.url = url;
        this.author = author;
        this.content = content;
        this.timestamp = timestamp;
    }

    public Noticia() {
    }

    public long getGid() {
        return gid;
    }

    public void setGid(long gid) {
        this.gid = gid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    
    //Retorna o timestamp * 1000 para evitar um bug na conversão
    public Date getDate() {
        return new Date(timestamp * 1000);
    }

    @Override
    public String toString() {
        return "Noticia{" + "gid=" + gid + ", title=" + title + ", url=" + url + ", author=" + author + ", content=" + content + ", timestamp=" + timestamp + '}';
    }
}
