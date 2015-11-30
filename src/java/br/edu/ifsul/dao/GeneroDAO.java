
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Genero;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class GeneroDAO implements Serializable {
    
    @PersistenceContext(unitName = "TA-FINAL-WebPU")
    private EntityManager em;
    private List<Genero> listarTodos;

    public GeneroDAO() {
    }
    
    public void persist(Genero objeto) throws Exception {
        em.persist(objeto);        
    }
    
    public void merge(Genero objeto) throws Exception {
        em.merge(objeto);
    }
    
    public void remove(Genero objeto) throws Exception{
        objeto = em.merge(objeto);
        em.remove(objeto);
    }
    
    public Genero getObjectById(Integer id) throws Exception {
        Genero obj = em.find(Genero.class, id);
        return obj;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<Genero> getListarTodos() {
        return em.createQuery("from Genero order by nome").getResultList();
    }

    public void setListarTodos(List<Genero> listarTodos) {
        this.listarTodos = listarTodos;
    }

}
