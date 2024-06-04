package br.com.ricardo.model.dao;

import br.com.ricardo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public abstract class GenericsDAO<C, K> {

    private EntityManager con;
    public GenericsDAO(){
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("fitnessz_PU");

        con = emf.createEntityManager();
    }

    public EntityManager getCon(){
        return con;
    }

    public abstract void insert(C obj) throws Exception;
    public abstract void change(C obj) throws Exception;
    public abstract void delete(C obj) throws Exception;
    public abstract C findById(K id) throws Exception;
    public abstract List<C> findAll() throws Exception;


}
