/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pizzeria;

public class Pedido implements Comparable<Pedido> {

    private double precio;
    private String autorPedido;
    private int cercania;

    public Pedido(String autorPedido, double precio, int cercania) {
        this.autorPedido = autorPedido;
        this.precio = precio;
        this.cercania = cercania;
    }

    public double getPrecio() {
        return precio;
    }

    public String getAutorPedido() {
        return autorPedido;
    }

    public int getCercania() {
        return cercania;
    }

    @Override
    public int compareTo(Pedido otroPedido) {
        // Comparar por precio de mayor a menor
        return Double.compare(otroPedido.getPrecio(), this.getPrecio());
    }
}
