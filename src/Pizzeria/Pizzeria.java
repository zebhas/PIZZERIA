/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pizzeria;



import java.util.ArrayList;
import Heap.Heap;
import java.util.Comparator;

public class Pizzeria {

    private Heap<Pedido> pedidosRecibidos;
    private ArrayList<Pedido> colaDespachos;

    public Pizzeria() {
        pedidosRecibidos = new Heap<>();
        colaDespachos = new ArrayList<>();
    }

  public Pedido agregarPedido(String nombreAutor, double precio, int cercania) {
    Pedido pedido = new Pedido(nombreAutor, precio, cercania);
    pedidosRecibidos.add(pedido);
    return pedido; 
}

   public Pedido atenderPedido() {
    Pedido pedidoAtendido = pedidosRecibidos.poll();
    if (pedidoAtendido != null) {
        colaDespachos.add(pedidoAtendido);
    }
    return pedidoAtendido;
}

   public Pedido despacharPedido() {
    if (!colaDespachos.isEmpty()) {
        // Ordenar la colaDespachos por cercanía de menor a mayor
        colaDespachos.sort(Comparator.comparingInt(Pedido::getCercania));
        return colaDespachos.remove(0);
    }
    return null;
}

    public ArrayList<Pedido> pedidosRecibidosList() {
        ArrayList<Pedido> listaPedidos = new ArrayList<>();
        Heap<Pedido> copiaPedidosRecibidos = new Heap<>();

        while (!pedidosRecibidos.isEmpty()) {
            Pedido pedido = pedidosRecibidos.poll();
            listaPedidos.add(pedido);
            copiaPedidosRecibidos.add(pedido);
        }

        pedidosRecibidos = copiaPedidosRecibidos; // Restaurar el heap original
        return listaPedidos;
    }

    public ArrayList<Pedido> colaDespachosList() {
        return colaDespachos;
    }
}