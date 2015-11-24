
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.PessoaFisica;
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
public class PessoaFisicaDAO implements Serializable {
    
    @PersistenceContext(unitName = "TA-2015-2-6N1-WebPU")
    private EntityManager em;
    private List<PessoaFisica> listarTodos;

    public PessoaFisicaDAO() {
    }
    
    public void persist(PessoaFisica objeto) throws Exception {
        em.persist(objeto);        
    }
    
    public void merge(PessoaFisica objeto) throws Exception {
        em.merge(objeto);
    }
    
    public void remove(PessoaFisica objeto) throws Exception{
        objeto = em.merge(objeto);
        em.remove(objeto);
    }
    
    public PessoaFisica getObjectById(Integer id) throws Exception {
        PessoaFisica obj = em.find(PessoaFisica.class, id);
        // Executar o size das listas para inicializa-las
        obj.getTelefones().size();
        return obj;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<PessoaFisica> getListarTodos() {
        return em.createQuery("from PessoaFisica order by nome").getResultList();
    }

    public void setListarTodos(List<PessoaFisica> listarTodos) {
        this.listarTodos = listarTodos;
    }

}
