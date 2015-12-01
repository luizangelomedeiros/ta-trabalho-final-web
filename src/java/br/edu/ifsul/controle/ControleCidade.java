package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CidadeDAO;
import br.edu.ifsul.dao.EstadoDAO;
import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.modelo.Estado;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "controleCidade")
@SessionScoped
public class ControleCidade implements Serializable {

    @EJB
    private CidadeDAO<Cidade> dao;
    private Cidade objeto;
    @EJB
    private EstadoDAO<Estado> daoEstado;

    public ControleCidade() {
    }

    public String listar() {
        return "/privado/cidade/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Cidade(); 
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
            Util.mensagemErro("Erro ao recuperar objeto "+Util.getMensagemErro(e));
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
    public Cidade getObjeto() {
        return objeto;
    }

    public void setObjeto(Cidade objeto) {
        this.objeto = objeto;
    }

    public CidadeDAO<Cidade> getDao() {
        return dao;
    }

    public void setDao(CidadeDAO<Cidade> dao) {
        this.dao = dao;
    }

    public EstadoDAO<Estado> getDaoEstado() {
        return daoEstado;
    }

    public void setDaoEstado(EstadoDAO<Estado> daoEstado) {
        this.daoEstado = daoEstado;
    }

    
}
