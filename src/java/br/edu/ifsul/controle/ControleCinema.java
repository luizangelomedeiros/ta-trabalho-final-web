package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CidadeDAO;
import br.edu.ifsul.dao.CinemaDAO;
import br.edu.ifsul.modelo.Cinema;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "controleCinema")
@ViewScoped
public class ControleCinema implements Serializable {

    @EJB
    private CinemaDAO dao;
    private Cinema objeto; 
      
    @EJB
    private CidadeDAO daoCidades;
    
    public ControleCinema() {
    }
    
    public String listar() {
        return "/privado/cinema/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Cinema();        
    }
        
    public void salvar() {
        try {
            if (objeto.getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso");            
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir objeto: " + e.getMessage());            
        }
    }

    public String cancelar() {
        objeto = null;
        return "listar";
    }
    
    public void editar(Integer id) {
        try {
            objeto = dao.getObjectById(id);            
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: "+e.getMessage());            
        }
    }
    
    public void excluir(Integer id){
        try {
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso");
        } catch (Exception e){
            Util.mensagemErro("Erro ao remover objeto:"+Util.getMensagemErro(e));
        }
    }

    public CinemaDAO getDao() {
        return dao;
    }

    public void setDao(CinemaDAO dao) {
        this.dao = dao;
    }

    public Cinema getObjeto() {
        return objeto;
    }

    public void setObjeto(Cinema objeto) {
        this.objeto = objeto;
    }

    public CidadeDAO getDaoCidades() {
        return daoCidades;
    }

    public void setDaoCidades(CidadeDAO daoCidades) {
        this.daoCidades = daoCidades;
    }
    
    
    
}
