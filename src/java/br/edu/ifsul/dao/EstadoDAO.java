
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Estado;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class EstadoDAO implements Serializable {
    
    @PersistenceContext(unitName = "TA-FINAL-WebPU")
    private EntityManager em;
    private List<Estado> listarTodos;

    public EstadoDAO() {
    }
    
    public void persist(Estado objeto) throws Exception {
        em.persist(objeto);        
    }
    
    public void merge(Estado objeto) throws Exception {
        em.merge(objeto);
    }
    
    public void remove(Estado objeto) throws Exception{
        objeto = em.merge(objeto);
        em.remove(objeto);
    }
    
    public Estado getObjectById(Integer id) throws Exception {
        return em.find(Estado.class, id);
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<Estado> getListarTodos() {
        return em.createQuery("from Estado order by nome").getResultList();
    }

    public void setListarTodos(List<Estado> listarTodos) {
        this.listarTodos = listarTodos;
    }

}
