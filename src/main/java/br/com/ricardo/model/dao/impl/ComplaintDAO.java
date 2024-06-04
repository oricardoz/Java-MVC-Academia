package br.com.ricardo.model.dao.impl;

import br.com.ricardo.model.Card;
import br.com.ricardo.model.Complaint;
import br.com.ricardo.model.dao.GenericsDAO;

import java.util.List;

public class ComplaintDAO extends GenericsDAO<Complaint, Integer> {
    @Override
    public void insert(Complaint obj) throws Exception {
        try {
            getCon().getTransaction().begin();
            getCon().persist(obj);
            getCon().getTransaction().commit();
        }catch (Exception e){
            throw new Exception("Error saving complaint :: " + e.getMessage());
        }
    }

    @Override
    public void change(Complaint obj) throws Exception {
        try {
            getCon().getTransaction().begin();
            getCon().merge(obj);
            getCon().getTransaction().commit();
        } catch (Exception e) {
            if (getCon().getTransaction().isActive()) {
                getCon().getTransaction().rollback();
            }
            throw new Exception("Error updating complaint :: " + e.getMessage());
        }
    }

    @Override
    public void delete(Complaint obj) throws Exception {
        try {
            getCon().getTransaction().begin();
            Complaint managedComplaint = getCon().merge(obj);
            getCon().remove(managedComplaint);
            getCon().getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Error deleting complaint :: " + e.getMessage());
        }
    }

    @Override
    public Complaint findById(Integer id) throws Exception {
        try {
            return getCon().find(Complaint.class, id);
        } catch (Exception e) {
            throw new Exception("Error ao querying complaint :: " + e.getMessage());
        }
    }

    @Override
    public List<Complaint> findAll() throws Exception {
        try {
            return getCon().createQuery("select c from Complaint c").getResultList();
        }catch (Exception e){
            throw new Exception("Error querying all complaint:: " + e.getMessage());
        }
    }
}
