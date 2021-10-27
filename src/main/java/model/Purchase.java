/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.time.LocalDate;

/**
 *
 * @author Laura
 */
public class Purchase {

    private int idPurchase;
    private Customer customer;
    private Item item;
    private LocalDate date;


    private static int aumento;

    public Purchase() {
        //this.idPurchase = aumento++;
    }

    public Purchase(int idPurchase, Customer customer, Item item, LocalDate date) {
        this.idPurchase = idPurchase;
        this.customer = customer;
        this.item = item;
        this.date = date;
    }

    public Purchase(Customer customer, Item item, LocalDate date) {
        //this.idPurchase = aumento++;
        this.customer = customer;
        this.item = item;
        this.date = date;
    }

    public int getIdPurchase() {
        return idPurchase;
    }

    public void setIdPurchase(int idPurchase) {
        this.idPurchase = idPurchase;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ID: " + idPurchase + "  Customer: " + customer.getIdCustomer() + "  Item: " + item.getIdItem() + "  Date: " + date;
    }
    
    public String toStringForClientInfo() {
        return "ID: " + idPurchase + "  Item: " + item.getIdItem()+ "  Date: " + date + "\n";
    }

    public String toStringTexto() {
        return idPurchase + ";" + customer.getIdCustomer() + ";" + item.getIdItem() + ";" + date;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.idPurchase;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Purchase other = (Purchase) obj;
        if (this.idPurchase != other.idPurchase) {
            return false;
        }
        return true;
    }
}
