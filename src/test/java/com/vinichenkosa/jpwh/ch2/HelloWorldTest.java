package com.vinichenkosa.jpwh.ch2;

import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by vinichenkosa on 16.01.16.
 */
public class HelloWorldTest {
    static EntityManagerFactory emf;
    static EntityManager em;
    static EntityTransaction tx;

    @BeforeClass
    public static void setUp() throws Exception {
        emf = Persistence.createEntityManagerFactory("JPWH_PU");
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }

    @Test
    public void testCreate() {

        tx.begin();
        Message message = new Message();
        message.setText("Hello World!");
        em.persist(message);
        tx.commit();

    }

    @Test
    public void testSelect() {
        tx.begin();
        List<Message> messages =
                em.createQuery("select m from Message m", Message.class).getResultList();
        assertEquals(messages.size(), 1);
        assertEquals(messages.get(0).getText(), "Hello World!");
        messages.get(0).setText("Take me to your leader!");
        tx.commit();

    }

    @AfterClass
    public static void tearDown() throws Exception {
        em.close();
        emf.close();
    }
}