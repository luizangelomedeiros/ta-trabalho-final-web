
package br.edu.ifsul.converters;

import br.edu.ifsul.modelo.Cinema;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@FacesConverter(value = "converterCinema")
public class ConverterCinema implements Converter, Serializable{
    
    @PersistenceContext(unitName = "TA-FINAL-WebPU")
    private EntityManager em;

    // converte da tela para objeto
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null){
            return null;
        }
        return em.find(Cinema.class, Integer.parseInt(string));
    }

    // converter do objeto para a tela
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null){
            return null;
        }
        Cinema obj = (Cinema) o;
        return obj.getId().toString();
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

}
