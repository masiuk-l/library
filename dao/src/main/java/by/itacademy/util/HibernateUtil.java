package by.itacademy.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
    private static EntityManagerFactory emf;
    private static final ThreadLocal<EntityManager> threadLocal;

    static {
        //emf = Persistence.createEntityManagerFactory("by.itacademy");
        threadLocal = new ThreadLocal<>();
    }

    public static EntityManager getEntityManager() {
        //emf = Persistence.createEntityManagerFactory("by.itacademy");
        EntityManager em = threadLocal.get();

        if (em == null) {
            emf = Persistence.createEntityManagerFactory("by.itacademy");
            em = emf.createEntityManager();
            threadLocal.set(em);
        }
        return em;
    }

    public static EntityManager getEntityManager(String unit) {
        emf = Persistence.createEntityManagerFactory(unit);
        EntityManager em = threadLocal.get();

        if (em == null) {
            em = emf.createEntityManager();
            threadLocal.set(em);
        }
        return em;
    }

    public static void closeEntityManager() {
        EntityManager em = threadLocal.get();
        if (em != null) {
            em.close();
            threadLocal.set(null);
        }
    }

    public static void closeEntityManagerFactory() {
        emf.close();
    }

    public static void beginTransaction() {
        getEntityManager().getTransaction().begin();
    }

    public static void rollback() {
        getEntityManager().getTransaction().rollback();
    }

    public static void commit() {
        getEntityManager().getTransaction().commit();
    }
}

