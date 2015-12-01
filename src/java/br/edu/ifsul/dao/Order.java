package br.edu.ifsul.dao;

import java.io.Serializable;

public class Order implements Serializable {
    private String attribute;
    private String label;
    private String operator;

   
    public Order(String attribute, String label, String operator) {
        this.attribute = attribute;
        this.label = label;
        this.operator = operator;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return this.label;
    }
}