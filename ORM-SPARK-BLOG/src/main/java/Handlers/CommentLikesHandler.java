package Handlers;

import Clases.CommentLikes;

import javax.persistence.EntityManager;

/**
 * Created by ${jrdis} on ${16/6/2017}.
 */
public class CommentLikesHandler extends DbHandler<CommentLikes> {

    private static CommentLikesHandler instance;

    private CommentLikesHandler() { super(CommentLikes.class); }

    public static CommentLikesHandler getInstance() {
        if (instance == null) {
            instance = new CommentLikesHandler();
        }
        return instance;
    }

    public CommentLikes getByUserComment(int userId, int commentId){
        EntityManager em = getEntityManager();
        try {
            return (CommentLikes) em.createNamedQuery(CommentLikes.getLikeDisByUserComment)
                    .setParameter("userId",userId)
                    .setParameter("commentId",commentId)
                    .getSingleResult();
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }
}
