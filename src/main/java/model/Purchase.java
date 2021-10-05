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
    private int idCustomer;
    private int Iditem;
    private LocalDate date;

    private static int aumento;
    public Purchase() {
        this.idPurchase = aumento++;
    }

    public Purchase( int idCustomer, int item, LocalDate date) {
        this.idPurchase = aumento++;
        this.idCustomer = idCustomer;
        this.Iditem = item;
        this.date = date;
    }

    public Purchase(String s){
        String [] array = s.split(";");
        this.idPurchase = Integer.parseInt(array[0]);
        this.idCustomer = Integer.parseInt(array[1]);
        this.Iditem = Integer.parseInt(array[2]);
        this.date = LocalDate.parse(array[3]);

    }

    public int getIdPurchase() {
        return idPurchase;
    }

    public void setIdPurchase(int idPurchase) {
        this.idPurchase = idPurchase;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getIditem() {
        return Iditem;
    }

    public void setIditem(int iditem) {
        this.Iditem = iditem;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ID: " + idPurchase + "  Customer: " + idCustomer + "  Item: " + Iditem + "  Date: " + date;
    }
    
    public String toStringForClientInfo() {
        return "ID: " + idPurchase + "  Item: " + Iditem + "  Date: " + date + "\n";
    }

    public String toStringTexto() {
        return idPurchase + ";" + idCustomer + ";" + Iditem + ";" + date;
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
