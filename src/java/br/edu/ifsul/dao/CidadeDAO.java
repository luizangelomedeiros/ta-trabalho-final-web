package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrder;
import br.edu.ifsul.modelo.Cidade;
import java.io.Serializable;
import javax.ejb.Stateless;

@Stateless
public class CidadeDAO<T> extends GenericDAO<Cidade> implements Serializable {
    
    public CidadeDAO() {
        super();
        super.setPersistentClass(Cidade.class);

        super.getListOrder().add(
                new Order("id", "ID", "="));
        super.getListOrder().add(
                new Order("nome", "Nome", "like"));
        super.getListOrder().add(
                new Order("estado", "Estado", "="));

        super.setCurrentOrder((Order) super.getListOrder().get(1));

        super.setFilter("");

        super.setConverterOrder(new ConverterOrder(super.getListOrder()));
    }
 }
