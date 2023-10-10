/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pizzeria;

import java.util.ArrayList;
import Heap.Heap;
import java.util.Comparator;

import Heap.MinHeap;
import java.util.Collections;

public class Pizzeria {
    
    private Heap<Pedido> pedidosRecibidos; // Heap para organizar por precio
    private MinHeap<Pedido> pedidosAtendidos; // MinHeap para organizar por precio
    private MinHeap<Pedido> colaDespachos; // MinHeap para organizar por cercanía

    public Pizzeria() {
        pedidosRecibidos = new Heap<>();
        pedidosAtendidos = new MinHeap<>(Comparator.comparingInt(Pedido::getCercania)); // Usar un MinHeap para ordenar por precio
        colaDespachos = new MinHeap<>(Comparator.comparingInt(Pedido::getCercania)); // Usar un MinHeap para ordenar por cercanía
    }

    public Pedido agregarPedido(String nombreAutor, double precio, int cercania) {
        Pedido pedido = new Pedido(nombreAutor, precio, cercania);
        pedidosRecibidos.add(pedido);
        return pedido;
    }

    public Pedido atenderPedido() {
        Pedido pedidoAtendido = pedidosRecibidos.poll();
        if (pedidoAtendido != null) {
            pedidosAtendidos.add(pedidoAtendido);
            colaDespachos.add(pedidoAtendido);
        }
        return pedidoAtendido;
    }

    public Pedido despacharPedido() {
    return colaDespachos.poll();
}

    public ArrayList<Pedido> pedidosRecibidosList() {
        ArrayList<Pedido> listaPedidos = new ArrayList<>();
        Heap<Pedido> copiaPedidosRecibidos = new Heap<>();

        while (!pedidosRecibidos.isEmpty()) {
            Pedido pedido = pedidosRecibidos.poll();
            listaPedidos.add(pedido);
            copiaPedidosRecibidos.add(pedido);
        }

        pedidosRecibidos = copiaPedidosRecibidos;
        return listaPedidos;
    }

    public ArrayList<Pedido> pedidosAtendidosList() {
        ArrayList<Pedido> listaPedidosAtendidos = new ArrayList<>(pedidosAtendidos.size());

        while (!pedidosAtendidos.isEmpty()) {
            listaPedidosAtendidos.add(pedidosAtendidos.poll());
        }

        for (Pedido pedido : listaPedidosAtendidos) {
            pedidosAtendidos.add(pedido);
        }

        return listaPedidosAtendidos;
    }

    public ArrayList<Pedido> colaDespachosList() {
    ArrayList<Pedido> listaColaDespachos = new ArrayList<>(colaDespachos.size());

    while (!colaDespachos.isEmpty()) {
        listaColaDespachos.add(colaDespachos.poll());
    }

    // Restaurar los pedidos a la cola de despachos original
    for (Pedido pedido : listaColaDespachos) {
        colaDespachos.add(pedido);
    }

    return listaColaDespachos;
}
}