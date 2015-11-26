package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CidadeDAO;
import br.edu.ifsul.modelo.Filme;
import br.edu.ifsul.modelo.Genero;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "controleFilme")
@ViewScoped
public class ControleFilme implements Serializable {

    @EJB
    private PessoaFisicaDAO dao;
    private Filme objeto;
    
    @EJB
    private CidadeDAO daoCidade;
    private Genero genero; 
    private boolean novoGenero;

    public ControleFilme() {
    }
    
    public String listar() {
        return "/privado/filme/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Filme();        
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
    
    public void novoGenero(){
        genero = new Genero();
        novoGenero = true;        
    }
    
    public void alterarGenero(int index){
        genero = objeto.getGeneros().get(index);
        novoGenero = false;
    }
    
    public void salvarGenero(){
        if(novoGenero){
            objeto.adicionarGenero(genero);
        }        
        Util.mensagemInformacao("Operação realizada com sucesso");
    }
        
    public void removerGenero(int index){
        objeto.removerGenero(index); 
        Util.mensagemInformacao("Operação realizada com sucesso!");
    }
    
    public PessoaFisicaDAO getDao() {
        return dao;
    }

    public void setDao(PessoaFisicaDAO dao) {
        this.dao = dao;
    }

    public Filme getObjeto() {
        return objeto;
    }

    public void setObjeto(Filme objeto) {
        this.objeto = objeto;
    }

    public CidadeDAO getDaoCidade() {
        return daoCidade;
    }

    public void setDaoCidade(CidadeDAO daoCidade) {
        this.daoCidade = daoCidade;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public boolean isNovoTelefone() {
        return novoGenero;
    }

    public void setNovoTelefone(boolean novoGenero) {
        this.novoGenero = novoGenero;
    }
    
    
    
}
