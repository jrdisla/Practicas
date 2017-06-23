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
@Table(name = "users")
@NamedQueries({
        @NamedQuery(
                name = "User.findUserByUsername",
                query = "SELECT user FROM User user WHERE user.username = :username"
        ),
        @NamedQuery(
                name = "User.findUserByUsernameAndPassword",
                query = "SELECT user FROM Clases.User user WHERE user.password = :password AND user.username = :username"
        )
})

public class User implements Serializable {
    public static String getUser = "User.findUserByUsername";
    public static String chekUserPassword = "User.findUserByUsernameAndPassword";
    @Id
    @GeneratedValue
    @Expose
    private int id;
    @Column(name = "username",nullable = false,unique = true)
    @Expose
    private String username;
    @Column(name = "name")
    @Expose
    private String name;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "isAdministrator")
    private Boolean administrator;
    @Column(name = "isAuthor")
    private Boolean author;
    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    private Set<Article> articles = new HashSet<Article>();
    @OneToMany(mappedBy = "author")
    private Set<Comment> comments = new HashSet<Comment>();
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Set<ArticleLikes> articleLikes = new HashSet<>();
    @OneToMany(mappedBy = "user")
    private Set<CommentLikes> commentLikes = new HashSet<>();

    public User() {}

    public User(String username, String name, String password, Boolean administrator, Boolean author) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.administrator = administrator;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Boolean administrator) {
        this.administrator = administrator;
    }

    public Boolean getAuthor() {
        return author;
    }

    public void setAuthor(Boolean author) {
        this.author = author;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<ArticleLikes> getArticleLikes() {
        return articleLikes;
    }

    public void setArticleLikes(Set<ArticleLikes> articleLikes) {
        this.articleLikes = articleLikes;
    }

    public Set<CommentLikes> getCommentLikes() {
        return commentLikes;
    }

    public void setCommentLikes(Set<CommentLikes> commentLikes) {
        this.commentLikes = commentLikes;
    }
}
