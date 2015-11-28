package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Cinema;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CinemaDAO implements Serializable {
    
    @PersistenceContext(unitName = "TA-FINAL-WebPU")
    private EntityManager em;
    private List<Cinema> listarTodos;

    public CinemaDAO() {
    }
    
    public void persist(Cinema objeto) throws Exception {
        em.persist(objeto);        
    }
    
    public void merge(Cinema objeto) throws Exception {
        em.merge(objeto);
    }
    
    public void remove(Cinema objeto) throws Exception{
        objeto = em.merge(objeto);
        em.remove(objeto);
    }
    
    public Cinema getObjectById(Integer id) throws Exception {
        Cinema obj = em.find(Cinema.class, id);
        return obj;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<Cinema> getListarTodos() {
        return em.createQuery("from Cinema order by id").getResultList();
    }

    public void setListarTodos(List<Cinema> listarTodos) {
        this.listarTodos = listarTodos;
    }

}
