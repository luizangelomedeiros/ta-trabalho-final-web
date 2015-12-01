
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrder;
import br.edu.ifsul.modelo.Estado;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class EstadoDAO<T> extends GenericDAO<Estado> implements Serializable {

    public EstadoDAO() {
        super();
        super.setPersistentClass(Estado.class);

        super.getListOrder().add(
                new Order("id", "ID", "="));
        super.getListOrder().add(
                new Order("nome", "Nome", "like"));
        super.getListOrder().add(
                new Order("uf", "UF", "like"));

        super.setCurrentOrder((Order) super.getListOrder().get(1));

        super.setFilter("");

        super.setConverterOrder(new ConverterOrder(super.getListOrder()));
    }
}
