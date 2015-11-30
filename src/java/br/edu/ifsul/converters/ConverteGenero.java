
package br.edu.ifsul.converters;

import br.edu.ifsul.modelo.Genero;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@FacesConverter(value = "converterGenero")
public class ConverteGenero implements Converter, Serializable{
   @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione um registro")) {
            return null;
        }
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TA-FINAL-WebPU");
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Genero.class, Integer.parseInt(string));
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
            emf.close();
        }

    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null){
            return null;
        }
        Genero obj = (Genero) o;
        return obj.getId().toString();
    }

}
