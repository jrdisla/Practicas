package Clases;

/**
 * Created by ${jrdis} on ${16/6/2017}.
 */
import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.io.Serializable;

@Entity
@Table(name = "article")
@NamedQueries({
        @NamedQuery(
                name = "Article.ByTitle",
                query = "Select arti FROM Clases.Article arti WHERE arti.title = :title"
        ),
        @NamedQuery(
                name = "Article.findArticlesPag",
                query = "SELECT arti FROM Article arti ORDER BY arti.date DESC"
        ),

        @NamedQuery(
                name = "Article.ByTag",
                query = "SELECT arti FROM Article arti JOIN  arti.tags t where t.id = :tagId"
        )
})
public class Article implements Serializable{
        public static String by_title = "Article.ByTitle";
        public static String pag = "Article.findArticlesPag";
        public static String tag = "Article.ByTag";


        @Id
        @GeneratedValue
        @Expose
        private int id;
        @Column(name = "title",unique = true)
        @Expose
        private String title;
        @Expose
        @Column(name = "body",length = 1000)
        private String body;
        @Column(name = "date",nullable = false)
        private Date date;
        @Expose
        @ManyToOne
        @JoinColumn(name = "author",nullable = false)
        private User author;
        @Expose
        @ManyToMany
        @JoinTable(name = "article_tags")
        private Set<Tag> tags = new HashSet<Tag>();
        @OneToMany(mappedBy = "article",cascade = CascadeType.REMOVE)
        private Set<Comment> comments = new HashSet<>();
        @OneToMany(mappedBy = "article",cascade = CascadeType.REMOVE)
        private Set<ArticleLikes> articleLikes=new HashSet<>();

        public Article() {}

        public Article(String title, String body, Date date, User author) {
                this.title = title;
                this.body = body;
                this.date = date;
                this.author = author;
        }

        public int getId() {
                return id;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public String getBody() {
                return body;
        }

        public void setBody(String body) {
                this.body = body;
        }

        public Date getDate() {
                return date;
        }

        public void setDate(Date datePublished) {
                this.date = datePublished;
        }

        public User getAuthor() {
                return author;
        }

        public void setAuthor(User author) {
                this.author = author;
        }

        public String getStringTags(){

                if(getTags() == null){
                        return  "";
                }
                return getTags().stream().map((tag -> tag.getTags())).collect(Collectors.joining(","));
        }

        public Set<Tag> getTags() {
                return tags;
        }

        public void setTags(Set<Tag> tags) {
                this.tags = tags;
        }

        public Set<Comment> getComments() {
                return comments;
        }

        public void setComments(Set<Comment> comments) {
                this.comments = comments;
        }


        public Set<ArticleLikes> getArticlePreferences() {
                return articleLikes;
        }

        public void setArticlePreferences(Set<ArticleLikes> articlePreferences) {
                this.articleLikes = articlePreferences;
        }

}
