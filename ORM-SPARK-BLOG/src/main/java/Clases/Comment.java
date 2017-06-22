package Clases;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ${jrdis} on ${16/6/2017}.
 */

@Entity
@Table(name = "comments")
@NamedQueries({
        @NamedQuery(
                name = "Comment.deleteCommentById",
                query = "DELETE FROM Clases.Comment comment WHERE comment.id = :id"
        )
})

public class Comment implements Serializable {
    public static String deleteCommentById = "Comment.deleteCommentById";

    @Id
    @GeneratedValue
    @Expose
    private int id;
    @Column(name = "body",nullable = false)
    @Expose
    private String body;
    @ManyToOne
    @JoinColumn(name = "author",nullable = false)
    @Expose
    private User author;
    @ManyToOne
    @JoinColumn(name = "article",nullable = false)
    @Expose
    private Article article;
    @OneToMany(mappedBy = "comment",cascade = CascadeType.REMOVE)
    private Set<CommentLikes> commentLikes;

    public Comment() {}

    public Comment(String body, User author, Article article) {
        this.body = body;
        this.author = author;
        this.article = article;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String description) {
        this.body = description;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Set<CommentLikes> getCommentLikes() {
        return commentLikes;
    }

    public void setCommentLikes(Set<CommentLikes> commentLikes) {
        this.commentLikes = commentLikes;
    }

}
