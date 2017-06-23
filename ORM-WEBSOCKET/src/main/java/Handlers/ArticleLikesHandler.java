package Handlers;

import Clases.ArticleLikes;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 * Created by ${jrdis} on ${16/6/2017}.
 */
public class ArticleLikesHandler extends DbHandler <ArticleLikes> {
    private static ArticleLikesHandler instance;

    private ArticleLikesHandler() { super(ArticleLikes.class); }

    public static ArticleLikesHandler getInstance() {
        if (instance == null) {
            instance = new ArticleLikesHandler();
        }
        return instance;
    }

    public ArticleLikes getItByArtiUser(int userId, int articleId){
        EntityManager em = getEntityManager();
        try {
            return (ArticleLikes) em.createNamedQuery(ArticleLikes.getLikeDis)
                    .setParameter("userId",userId)
                    .setParameter("articleId",articleId)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }
}
