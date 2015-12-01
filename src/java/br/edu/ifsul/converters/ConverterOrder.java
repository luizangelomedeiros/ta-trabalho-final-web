package br.edu.ifsul.converters;

import br.edu.ifsul.dao.Order;
import java.io.Serializable;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class ConverterOrder implements Serializable, Converter{
    private List<Order> listOrder;

    public ConverterOrder(List<Order> listOrder) {
        this.listOrder = listOrder;
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Order retorno = null;
        for (Order o : listOrder) {
            if (o.getAttribute().equals(string)) {
                retorno = o;
            }
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null) {
            return null;
        }
        Order order = (Order) o;
        return order.getAttribute().toString();
    }

    public List<Order> getListOrder() {
        return listOrder;
    }

    public void setListOrder(List<Order> listOrder) {
        this.listOrder = listOrder;
    }
}