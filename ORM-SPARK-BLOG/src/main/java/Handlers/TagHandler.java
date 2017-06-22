package Handlers;

import Clases.Tag;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 * Created by ${jrdis} on ${16/6/2017}.
 */
public class TagHandler extends DbHandler<Tag>{
    private static TagHandler instance;

    private TagHandler() { super(Tag.class); }

    public static TagHandler getInstance() {
        if (instance == null) {
            instance = new TagHandler();
        }
        return instance;
    }

    public Tag getTags(String desc){
        try {
            EntityManager em = getEntityManager();
            return (Tag) em.createNamedQuery(Tag.geTag)
                    .setParameter("desc", desc)
                    .getSingleResult();
        } catch(NoResultException e) {
            return null;
        }
    }
}
