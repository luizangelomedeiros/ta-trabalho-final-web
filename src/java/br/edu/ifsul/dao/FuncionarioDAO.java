
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Funcionario;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class FuncionarioDAO implements Serializable {
    
    @PersistenceContext(unitName = "TA-FINAL-WebPU")
    private EntityManager em;
    private List<Funcionario> listarTodos;

    public FuncionarioDAO() {
    }
    
    public void persist(Funcionario objeto) throws Exception {
        em.persist(objeto);        
    }
    
    public void merge(Funcionario objeto) throws Exception {
        em.merge(objeto);
    }
    
    public void remove(Funcionario objeto) throws Exception{
        objeto = em.merge(objeto);
        em.remove(objeto);
    }
    
    public Funcionario getObjectById(Integer id) throws Exception {
        Funcionario obj = em.find(Funcionario.class, id);
        return obj;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<Funcionario> getListarTodos() {
        return em.createQuery("from Funcionario order by nome").getResultList();
    }

    public void setListarTodos(List<Funcionario> listarTodos) {
        this.listarTodos = listarTodos;
    }

}
