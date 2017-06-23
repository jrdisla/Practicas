package Handlers;

import Clases.Comment;

import javax.persistence.EntityManager;

/**
 * Created by ${jrdis} on ${16/6/2017}.
 */
public class CommentHandler extends DbHandler<Comment>{

    private static CommentHandler instance;

    private CommentHandler() { super(Comment.class); }

    public static CommentHandler getInstance() {
        if (instance == null) {
            instance = new CommentHandler();
        }
        return instance;
    }

    public int deleteCommentById(int id) {
        int success = 0;
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            success = em.createNamedQuery(Comment.deleteCommentById)
                    .setParameter("id", id)
                    .executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            success = 0;
            e.printStackTrace();
        } finally {
            em.close();
            return success;
        }
    }
}
