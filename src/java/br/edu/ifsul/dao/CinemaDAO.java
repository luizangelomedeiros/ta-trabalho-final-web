package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrder;
import br.edu.ifsul.modelo.Cinema;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CinemaDAO<T> extends GenericDAO<Cinema> implements Serializable {
   
    public CinemaDAO() {
        super();
        super.setPersistentClass(Cinema.class);

        super.getListOrder().add(new Order("id", "ID", "="));
        super.getListOrder().add(new Order("capacidadetotal", "Capacidade", "="));
        super.getListOrder().add(new Order("endereco", "Endereço", "like"));
        super.getListOrder().add(new Order("horariofunc", "Horário", "like"));
        super.getListOrder().add(new Order("qtdfuncionarios", "Qtd. Funcionarios", "="));
        super.getListOrder().add(new Order("qtdsalas", "Qtd. Salas", "="));
        super.getListOrder().add(new Order("cidade", "Cidade", "="));

        super.setCurrentOrder((Order) super.getListOrder().get(0));

        super.setFilter("");

        super.setConverterOrder(new ConverterOrder(super.getListOrder()));
    }

}
