
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrder;
import br.edu.ifsul.modelo.Filme;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class FilmeDAO<T> extends GenericDAO<Filme> implements Serializable {

    public FilmeDAO() {
        super();
        super.setPersistentClass(Filme.class);

        super.getListOrder().add(new Order("id", "ID", "="));
        super.getListOrder().add(new Order("nome", "Nome", "like"));
        super.getListOrder().add(new Order("data_lancamento", "Data de Lançamento", "="));
        super.getListOrder().add(new Order("duracao", "Duração", "like"));
        super.getListOrder().add(new Order("idioma", "Idioma", "like"));
        super.getListOrder().add(new Order("num_discos", "Num. Discos", "="));

        super.setCurrentOrder((Order) super.getListOrder().get(0));

        super.setFilter("");

        super.setConverterOrder(new ConverterOrder(super.getListOrder()));
    }
   
}
