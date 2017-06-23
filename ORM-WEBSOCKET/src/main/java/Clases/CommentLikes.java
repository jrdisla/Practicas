package Clases;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ${jrdis} on ${16/6/2017}.
 */

@Entity
@Table(name = "commentLikes")
@NamedQueries({
        @NamedQuery(
                name = "commentLikeDis.getLikeDisle",
                query = "SELECT commentLikeDis FROM CommentLikes commentLikeDis WHERE commentLikeDis.user.id = :userId " +
                        "AND commentLikeDis.comment.id = :commentId"
        )
})

public class CommentLikes implements Serializable {
    public static String getLikeDisByUserComment= "commentLikeDis.getLikeDisle";
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "status",nullable = false)
    @Expose
    private String Status;
    @ManyToOne
    @JoinColumn(name = "user",nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "comment",nullable = false)
    private Comment comment;

    public CommentLikes() {
    }

    public CommentLikes(String preference, User user, Comment comment) {
        this.setStatus(preference);
        this.user = user;
        this.setComment(comment);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
