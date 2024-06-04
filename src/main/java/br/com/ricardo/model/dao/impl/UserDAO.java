package br.com.ricardo.model.dao.impl;

import br.com.ricardo.model.Card;
import br.com.ricardo.model.Complaint;
import br.com.ricardo.model.User;
import br.com.ricardo.model.dao.GenericsDAO;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import java.util.List;

public class UserDAO extends GenericsDAO<User, Integer> {
    @Override
    public void insert(User user) throws Exception {
        try {
            getCon().getTransaction().begin();
            getCon().persist(user);
            getCon().getTransaction().commit();
        }catch (Exception e){
            throw new Exception("Error saving user :: " + e.getMessage());
        }
    }

    @Override
    public void change(User user) throws Exception {
        try {
            getCon().getTransaction().begin();
            getCon().merge(user);
            getCon().getTransaction().commit();
        } catch (Exception e) {
            if (getCon().getTransaction().isActive()) {
                getCon().getTransaction().rollback();
            }
            throw new Exception("Error updating user :: " + e.getMessage());
        }
    }

    @Override
    public void delete(User user) throws Exception {
        try {
            getCon().getTransaction().begin();


            List<Complaint> complaints = user.getComplaints();
            for (Complaint complaint : complaints) {
                getCon().remove(complaint);
            }

            getCon().remove(user);

            getCon().getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Error deleting user ::" + e.getMessage());
        }
    }


    @Override
    public User findById(Integer id) throws Exception {
        try {
            return getCon().find(User.class, id);
        } catch (Exception e) {
            throw new Exception("Error querying user :: " + e.getMessage());
        }
    }

    @Override
    public List<User> findAll() throws Exception {
        try {
            return getCon().createQuery("select u from User u").getResultList();
        }catch (Exception e){
            throw new Exception("Error querying all users :: " + e.getMessage());
        }

    }
    public User findByLoginAndPassword(String login, String password) throws Exception {
        try {
            Query query =  getCon().createQuery("select u from User u where " +
                    "u.login = :log and u.password = :sen");

            query.setParameter("log", login);
            query.setParameter("sen", password);

            return (User) query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }catch (NonUniqueResultException e){
            throw new Exception("More them one user find :: " + e.getMessage());
        }catch (Exception e){
            throw new Exception("Error finding user :: " + e.getMessage());
        }
    }

}
