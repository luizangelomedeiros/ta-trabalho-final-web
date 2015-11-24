
package br.edu.ifsul.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 */
public class Util {
    
    public static void mensagemInformacao(String texto){
        FacesMessage mensagem = 
                new FacesMessage(FacesMessage.SEVERITY_INFO, texto, "");
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
    }
    
    public static void mensagemErro(String texto){
        FacesMessage mensagem = 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, texto, "");
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
    }    
    
    public static String getMensagemErro(Exception e){
        while (e.getCause() != null){
            e = (Exception) e.getCause();
        }
        String erro = e.getMessage();
        if (erro.contains("viola restrição de chave estrangeira")){
            erro = "Objeto não pode ser removido pois outro registro o referencia";
        }
        return erro;
    }
}
