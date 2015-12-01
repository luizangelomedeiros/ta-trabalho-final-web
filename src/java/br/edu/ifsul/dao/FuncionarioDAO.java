
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrder;
import br.edu.ifsul.modelo.Funcionario;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class FuncionarioDAO<T> extends GenericDAO<Funcionario> implements Serializable {
    
    public FuncionarioDAO() {
        super();
        super.setPersistentClass(Funcionario.class);

        super.getListOrder().add(new Order("id", "ID", "="));
        super.getListOrder().add(new Order("nome", "Nome", "like"));
        super.getListOrder().add(new Order("cpf", "CPF", "like"));
        super.getListOrder().add(new Order("data_nascimento", "Data de Nascimento", "like"));
        super.getListOrder().add(new Order("endereco", "Endereço", "like"));
        super.getListOrder().add(new Order("estado_civil", "Estado Civil", "like"));
        super.getListOrder().add(new Order("salario", "Salário", "="));
        super.getListOrder().add(new Order("usuario", "Usuário", "like"));
        super.getListOrder().add(new Order("idcinema", "ID Cinema", "="));

        super.setCurrentOrder((Order) super.getListOrder().get(1));

        super.setFilter("");

        super.setConverterOrder(new ConverterOrder(super.getListOrder()));
    }
   
}
