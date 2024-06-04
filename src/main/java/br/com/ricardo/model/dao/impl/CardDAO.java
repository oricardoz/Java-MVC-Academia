package br.com.ricardo.model.dao.impl;

import br.com.ricardo.model.Card;
import br.com.ricardo.model.Exercise;
import br.com.ricardo.model.dao.GenericsDAO;

import java.util.List;

public class CardDAO extends GenericsDAO<Card, Integer> {
    @Override
    public void insert(Card obj) throws Exception {
        try {
            getCon().getTransaction().begin();
            getCon().persist(obj);
            getCon().getTransaction().commit();
        }catch (Exception e){
            throw new Exception("Error saving card :: " + e.getMessage());
        }
    }

    @Override
    public void change(Card obj) throws Exception {
        try {
            getCon().getTransaction().begin();
            getCon().merge(obj);
            getCon().getTransaction().commit();
        }catch (Exception e){
            throw new Exception("Error updating card:: " + e.getMessage());
        }
    }

    @Override
    public void delete(Card obj) throws Exception {
        try {
            getCon().getTransaction().begin();
            getCon().remove(obj);
            getCon().getTransaction().commit();
        }catch (Exception e){
            throw new Exception("Error deleting card :: " + e.getMessage());
        }
    }

    @Override
    public Card findById(Integer id) throws Exception {
        try {
            return getCon().find(Card.class, id);
        } catch (Exception e) {
            throw new Exception("Error ao querying card :: " + e.getMessage());
        }
    }

    @Override
    public List<Card> findAll() throws Exception {
        try {
            return getCon().createQuery("select c from Card c").getResultList();
        }catch (Exception e){
            throw new Exception("Error querying all cards:: " + e.getMessage());
        }
    }

    public List<Card> findCardByUserId(Integer id) throws Exception {
        try {
            return getCon().createQuery("SELECT c FROM Card c WHERE c.user.id = :id")
                    .setParameter("id", id)
                    .getResultList();
        } catch (Exception e) {
            throw new Exception("Error querying cards by user id" + e.getMessage());
        }
    }
}
