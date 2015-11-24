
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Cidade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 */
@Stateless
public class CidadeDAO implements Serializable {
    
    @PersistenceContext(unitName = "TA-2015-2-6N1-WebPU")
    private EntityManager em;
    private List<Cidade> listarTodos;

    public CidadeDAO() {
    }
    
    public void persist(Cidade objeto) throws Exception {
        em.persist(objeto);        
    }
    
    public void merge(Cidade objeto) throws Exception {
        em.merge(objeto);
    }
    
    public void remove(Cidade objeto) throws Exception{
        objeto = em.merge(objeto);
        em.remove(objeto);
    }
    
    public Cidade getObjectById(Integer id) throws Exception {
        return em.find(Cidade.class, id);
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<Cidade> getListarTodos() {
        return em.createQuery("from Cidade order by nome").getResultList();
    }

    public void setListarTodos(List<Cidade> listarTodos) {
        this.listarTodos = listarTodos;
    }

}
