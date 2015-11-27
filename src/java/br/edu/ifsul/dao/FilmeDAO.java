
package br.edu.ifsul.dao;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import br.edu.ifsul.modelo.Filme;



@Stateless
public class FilmeDAO implements Serializable {
    
    @PersistenceContext(unitName = "TA-FINAL-WebPU")
    private EntityManager em;
    private List<Filme> listarTodos;

    public FilmeDAO() {
    }
    
    public void persist(Filme objeto) throws Exception {
        em.persist(objeto);        
    }
    
    public void merge(Filme objeto) throws Exception {
        em.merge(objeto);
    }
    
    public void remove(Filme objeto) throws Exception{
        objeto = em.merge(objeto);
        em.remove(objeto);
    }
    
    public Filme getObjectById(Integer id) throws Exception {
        Filme obj = em.find(Filme.class, id);
        obj.getGeneros().size();
        return obj;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<Filme> getListarTodos() {
        return em.createQuery("from PessoaFisica order by nome").getResultList();
    }

    public void setListarTodos(List<Filme> listarTodos) {
        this.listarTodos = listarTodos;
    }

}
