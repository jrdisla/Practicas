package Handlers;

import Clases.Article;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by ${jrdis} on ${16/6/2017}.
 */
public class ArticleHandler extends DbHandler<Article> {

    private static ArticleHandler instance;

    private ArticleHandler() { super(Article.class); }

    public static ArticleHandler getInstance() {
        if (instance == null) {
            instance = new ArticleHandler();
        }
        return instance;
    }

    public Article getByTitle(String title){
        EntityManager em = getEntityManager();
        try {
            return (Article) em.createNamedQuery(Article.by_title)
                    .setParameter("title",title)
                    .getSingleResult();
        } catch (Exception ex) {
            throw ex;
        } finally {
            em.close();
        }
    }


    public List<Article> pagination(int limit,int offset) {
        EntityManager em = getEntityManager();
        try {
            return (List<Article>) em.createNamedQuery(Article.pag)
                    .setFirstResult(offset)
                    .setMaxResults(limit)
                    .getResultList();
        } catch (Exception ex) {
            throw ex;
        } finally {
            em.close();
        }
    }

    public List<Article> getByTag(int tagId) {
        EntityManager em = getEntityManager();
        try {
            return (List<Article>) em.createNamedQuery(Article.tag)
                    .setParameter("tagId",tagId)
                    .getResultList();
        } catch (Exception ex) {
            throw ex;
        } finally {
            em.close();
        }
    }

}
