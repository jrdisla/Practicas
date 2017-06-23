package Handlers;

import Clases.User;

import javax.persistence.EntityManager;

/**
 * Created by ${jrdis} on ${16/6/2017}.
 */
public class UserHandler extends DbHandler <User>{
    private static UserHandler instance;

    private UserHandler() { super(User.class); }

    public static UserHandler getInstance() {
        if (instance == null) {
            instance = new UserHandler();
        }
        return instance;
    }

    public User getUserBuUP(String username,String password) {
        EntityManager em = getEntityManager();
        try {
            return (User) em.createNamedQuery(User.chekUserPassword)
                    .setParameter("username",username)
                    .setParameter("password",password)
                    .getSingleResult();
        }  finally {
            em.close();
        }
    }

    public User GetUser(String username)  {
        EntityManager em = getEntityManager();
        try {
            return (User) em.createNamedQuery(User.getUser)
                    .setParameter("username", username)
                    .getSingleResult();
        }
        catch (Exception ex) {
            return null;
        }
        finally {
            em.close();
        }
    }
}
