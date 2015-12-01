package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CinemaDAO;
import br.edu.ifsul.dao.FuncionarioDAO;
import br.edu.ifsul.modelo.Cinema;
import br.edu.ifsul.modelo.Funcionario;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "controleFuncionario")
@SessionScoped
public class ControleFuncionario implements Serializable {

    @EJB
    private FuncionarioDAO<Funcionario> dao;
    private Funcionario objeto;
    private Funcionario usuarioLogado;

    @EJB
    private CinemaDAO<Cinema> daoCinema;
    
    private String usuario;
    private String senha;
    
    public ControleFuncionario() {
    }

    public String listar() {
        return "/privado/funcionario/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Funcionario();        
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

     public String paginaLogin() {
        return "/login?faces-redirect=true";
    }
    
    public String efetuarLogin(){
        if (dao.login(usuario, senha)) {
            usuarioLogado = dao.localizaPorNomeUsuario(usuario);
            try{
                dao.merge(usuarioLogado);
            }catch(Exception e){
                Util.mensagemErro("Erro ao persistir Usuário" + e.getMessage());
            }
            Util.mensagemInformacao("Login efetuado com sucesso");
            return "/index?faces-redirect=true";
        } else {
            Util.mensagemInformacao("Usuário ou senha inválidos!");
            return "/login?faces-redirect=true";
        }
    }
    
    public String efetuarLogout() {
        usuarioLogado = null;
        Util.mensagemInformacao("Logout efetuado com sucesso");
        return "/login?faces-redirect=true";
    }

    
    public FuncionarioDAO<Funcionario> getDao() {
        return dao;
    }

    public void setDao(FuncionarioDAO<Funcionario> dao) {
        this.dao = dao;
    }

    public Funcionario getObjeto() {
        return objeto;
    }

    public void setObjeto(Funcionario objeto) {
        this.objeto = objeto;
    }

    public CinemaDAO<Cinema> getDaoCinema() {
        return daoCinema;
    }

    public void setDaoCinema(CinemaDAO<Cinema> daoCinema) {
        this.daoCinema = daoCinema;
    }

    public Funcionario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Funcionario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    
    
}
