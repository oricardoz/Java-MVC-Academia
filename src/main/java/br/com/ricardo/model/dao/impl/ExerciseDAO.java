package br.com.ricardo.model.dao.impl;

import br.com.ricardo.model.Exercise;
import br.com.ricardo.model.User;
import br.com.ricardo.model.dao.GenericsDAO;

import java.util.List;

public class ExerciseDAO extends GenericsDAO<Exercise, Integer > {
    @Override
    public void insert(Exercise obj) throws Exception {
        try {
            getCon().getTransaction().begin();
            getCon().persist(obj);
            getCon().getTransaction().commit();
        }catch (Exception e){
            throw new Exception("Error saving exercise:: " + e.getMessage());
        }
    }

    @Override
    public void change(Exercise obj) throws Exception {
        try {
            getCon().getTransaction().begin();
            getCon().merge(obj);
            getCon().getTransaction().commit();
        }catch (Exception e){
            throw new Exception("Error updating exercise :: " + e.getMessage());
        }
    }

    @Override
    public void delete(Exercise obj) throws Exception {
        try {
            getCon().getTransaction().begin();
            getCon().remove(obj);
            getCon().getTransaction().commit();
        }catch (Exception e){
            throw new Exception("Error deleting exercise :: " + e.getMessage());
        }
    }

    @Override
    public Exercise findById(Integer id) throws Exception {
        try {
            Exercise e = new Exercise();
            e.setId(id);
            return getCon().find(Exercise.class,e);
        }catch (Exception e){
            throw new Exception("Error querying exercise :: " + e.getMessage());
        }

    }

    @Override
    public List<Exercise> findAll() throws Exception {
        try {
            return getCon().createQuery("select e from Exercise e").getResultList();
        }catch (Exception e){
            throw new Exception("Error querying all exercises :: " + e.getMessage());
        }
    }
}
