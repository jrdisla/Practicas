package Clases;

import com.google.gson.annotations.Expose;
import Clases.*;
import javax.persistence.*;
import java.io.Serializable;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ${jrdis} on ${16/6/2017}.
 */

@Entity
@Table(name = "tags")
@NamedQueries({
        @NamedQuery(
                name = "getTagByDesc",
                query = "SELECT tag FROM  Tag tag WHERE tag.desc = :desc"
        )
})

public class Tag implements Serializable {

    public static String geTag = "getTagByDesc";
    @Id
    @GeneratedValue
    @Expose
    private int id;
    @Expose
    @Column(name = "desc",nullable = false,unique = true)
    private String desc;

    @ManyToMany(mappedBy = "tags",cascade = CascadeType.ALL)
    private Set<Article> articles = new HashSet<>();

    public Tag() {}

    public Tag(String tags) {
        this.desc = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTags() {
        return desc;
    }

    public void setTags(String tags) {
        this.desc = tags;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }
}
