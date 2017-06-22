package Clases;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ${jrdis} on ${16/6/2017}.
 */

@Entity
@Table(name = "articleLikes")
@NamedQueries({
        @NamedQuery(
                name = "likeDis.getByArtiUser",
                query = "SELECT artiLikeDis FROM ArticleLikes artiLikeDis WHERE artiLikeDis.user.id = :userId " +
                        "AND artiLikeDis.article.id = :articleId"
        )
})
public class ArticleLikes implements Serializable {
    public static String getLikeDis = "likeDis.getByArtiUser";
    @Id
    @GeneratedValue
    @Expose
    private int id;
    @Column(name = "status")
    @Expose
    private String Status;
    @ManyToOne
    @JoinColumn(name = "user",nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "article",nullable = false)
    @Expose
    private Article article;

    public ArticleLikes() {}

    public ArticleLikes(String Status, User author, Article article) {
        this.setStatus(Status);
        this.user = author;
        this.article = article;
    }

    public int getId() {
        return id;
    }

    public User getAuthor() {
        return user;
    }

    public void setAuthor(User author) {
        this.user = author;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        this.Status = status ;
    }

}
