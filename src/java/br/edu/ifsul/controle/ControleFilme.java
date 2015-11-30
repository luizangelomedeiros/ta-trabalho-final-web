package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CinemaDAO;
import br.edu.ifsul.dao.FilmeDAO;
import br.edu.ifsul.dao.GeneroDAO;
import br.edu.ifsul.modelo.Filme;
import br.edu.ifsul.modelo.Genero;
import br.edu.ifsul.modelo.Sessao;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "controleFilme")
@ViewScoped
public class ControleFilme implements Serializable {

    @EJB
    private FilmeDAO dao;
    private Filme objeto;    
    @EJB
    private CinemaDAO cinemaDao;    
    private Sessao sessao; 
    private boolean novaSessao;
    @EJB
    private GeneroDAO generoDao;    
    private Genero genero;

    public ControleFilme() {
    }
    
    public String listar() {
        return "/privado/filme/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Filme();        
    }
    
    public void adicionarGeneros(){
        if (genero != null){
            if (!objeto.getFilme_genero().contains(genero)){
                objeto.getFilme_genero().add(genero);
                Util.mensagemInformacao("Gênero adicionado com sucesso");
            } else {
                Util.mensagemErro("Gênero já existente na lista");
            }
        }
    }
    
    public void removerGeneros(int idx){
        Genero p = objeto.getFilme_genero().get(idx);
        objeto.getFilme_genero().remove(p);
        Util.mensagemInformacao("Gênero removido");
              
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
    
    public void novaSessao(){
        sessao = new Sessao();
        novaSessao = true;        
    }
    
    public void alterarSessao(int index){
        sessao = objeto.getSessoes().get(index);
        novaSessao = false;
    }
        
    public void salvarSessao(){
        if(novaSessao){
            objeto.adicionarSessao(sessao);
        }        
        Util.mensagemInformacao("Operação realizada com sucesso");
    }
        
    public void removerSessao(int index){
        objeto.removerSessao(index); 
        Util.mensagemInformacao("Operação realizada com sucesso!");
    }
    
    public FilmeDAO getDao() {
        return dao;
    }

    public void setDao(FilmeDAO dao) {
        this.dao = dao;
    }

    public Filme getObjeto() {
        return objeto;
    }

    public void setObjeto(Filme objeto) {
        this.objeto = objeto;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public boolean isNovaSessao() {
        return novaSessao;
    }

    public void setNovaSessao(boolean novaSessao) {
        this.novaSessao = novaSessao;
    }

    public CinemaDAO getCinemaDao() {
        return cinemaDao;
    }

    public void setCinemaDao(CinemaDAO cinemaDao) {
        this.cinemaDao = cinemaDao;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public GeneroDAO getGeneroDao() {
        return generoDao;
    }

    public void setGeneroDao(GeneroDAO generoDao) {
        this.generoDao = generoDao;
    }
    
}
