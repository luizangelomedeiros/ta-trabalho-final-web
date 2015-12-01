package br.edu.ifsul.controle;

import br.edu.ifsul.dao.GeneroDAO;
import br.edu.ifsul.modelo.Genero;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "controleGenero")
@ViewScoped
public class ControleGenero implements Serializable {

    @EJB
    private GeneroDAO<Genero> dao;
    private Genero objeto;    

    public ControleGenero() {
    }
    
    public String listar() {
        return "/privado/genero/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Genero();        
    }

    Calendar diaDeHoje = Calendar.getInstance();
    public void salvar() {
        try {
            objeto.setData(diaDeHoje);
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

    public GeneroDAO<Genero> getDao() {
        return dao;
    }

    public void setDao(GeneroDAO<Genero> dao) {
        this.dao = dao;
    }

    public Genero getObjeto() {
        return objeto;
    }

    public void setObjeto(Genero objeto) {
        this.objeto = objeto;
    }

    public Calendar getDiaDeHoje() {
        return diaDeHoje;
    }

    public void setDiaDeHoje(Calendar diaDeHoje) {
        this.diaDeHoje = diaDeHoje;
    }
    
}
