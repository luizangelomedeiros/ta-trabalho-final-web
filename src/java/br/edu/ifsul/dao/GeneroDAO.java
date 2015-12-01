
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrder;
import br.edu.ifsul.modelo.Genero;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class GeneroDAO<T> extends GenericDAO<Genero> implements Serializable {

    public GeneroDAO() {
        super();
        super.setPersistentClass(Genero.class);

        super.getListOrder().add(
                new Order("id", "ID", "="));
        super.getListOrder().add(
                new Order("nome", "Nome", "like"));
        super.getListOrder().add(
                new Order("data", "Data", "="));

        super.setCurrentOrder((Order) super.getListOrder().get(1));

        super.setFilter("");

        super.setConverterOrder(new ConverterOrder(super.getListOrder()));
    }
}
