/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Pizzeria.Pedido;
import Pizzeria.Pizzeria;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    static BufferedReader br;
    static String ln;
    static Pizzeria pizzeria;

    static final int INTERFAZ_USUARIO = 1;
    static final int INGRESO_MANUAL = 2;
    static final int ARCHIVO_DE_PRUEBAS = 3;
    static final int SALIR = 4;

    private static final String ARCHIVO_PRUEBAS = "./data/tests.txt";

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        boolean end = false;

        while (!end) {
            imprimirArchivo("./data/header.txt");
            Integer option = Integer.parseInt(br.readLine());
            if (option == INTERFAZ_USUARIO) {
                interfazUsuario();
            } else if (option == INGRESO_MANUAL) {
                imprimirArchivo("./data/ingresoManual.txt");
                ingresoManual();
            } else if (option == ARCHIVO_DE_PRUEBAS) {
                ingresoArchivoPruebas();
            } else if (option == SALIR) {
                System.out.println("SALIENDO");
                end = true;
            }
        }
    }

    static void interfazUsuario() throws Exception {
        pizzeria = new Pizzeria();
        boolean end = false;
        while (!end) {
            imprimirArchivo("./data/interfazUsuario.txt");
            System.out.println("Seleccione una opción:");
            int option = Integer.parseInt(br.readLine());
            if (option == 1) {
                System.out.println("Escriba el primer nombre del autor del pedido:");
                String nombre = br.readLine();
                System.out.println("Escriba el precio del pedido:");
                double precio = Double.parseDouble(br.readLine());
                System.out.println("Escriba la cercanía del pedido (1-5):");
                int cercania = Integer.parseInt(br.readLine());
                pizzeria.agregarPedido(nombre, precio, cercania);
                System.out.println("Pedido agregado.");
            } else if (option == 2) {
                Pedido p = pizzeria.atenderPedido();
                if (p == null) {
                    System.out.println("Cola vacía");
                } else {
                    System.out.println("Pedido atendido: " + p.getAutorPedido() + " - " + p.getPrecio() + " - " + p.getCercania());
                }
            } else if (option == 3) {
                Pedido p = pizzeria.despacharPedido();
                if (p == null) {
                    System.out.println("Cola vacía");
                } else {
                    System.out.println("Pedido despachado: " + p.getAutorPedido() + " - " + p.getPrecio() + " - " + p.getCercania());
                }
            } else if (option == 4) {
                imprimirColas(pizzeria);
            } else if (option == 5) {
                end = true;
            }
        }
    }

    static void ingresoManual() throws Exception {
        ingresoComandos(br);
    }

    static void ingresoComandos(BufferedReader br) throws Exception {
        pizzeria = new Pizzeria();

        while ((ln = br.readLine()) != null && !ln.equalsIgnoreCase("SALIR")) {
            String comando = ln.split(" ")[0];
            if (comando.equalsIgnoreCase("RECIBIR")) {  // Cambio aquí
                String nombre = ln.split(" ")[1];
                Double precio = Double.parseDouble(ln.split(" ")[2]);
                Integer cercania = Integer.parseInt(ln.split(" ")[3]);
                pizzeria.agregarPedido(nombre, precio, cercania);
            } else if (comando.equalsIgnoreCase("ATENDER")) {  // Cambio aquí
                pizzeria.atenderPedido();
            } else if (comando.equalsIgnoreCase("DESPACHAR")) {  // Cambio aquí
                pizzeria.despacharPedido();
            }
            imprimirColas(pizzeria);
        }
    }

    static void ingresoArchivoPruebas() throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_PRUEBAS))) {
            ingresoComandos(br);
        }
    }

    static void imprimirColas(Pizzeria pizzeria) {
        imprimirColaPedidosRecibidos(pizzeria);
        imprimirColaPedidosAtendidos(pizzeria);
        imprimirColaDespachos(pizzeria);
        System.out.println();
        System.out.println();
    }

    private static void imprimirColaPedidosRecibidos(Pizzeria pizzeria) {
        System.out.println("    PEDIDOS RECIBIDOS    ");
        int index = 1;

        try {
            for (Pedido p : pizzeria.pedidosRecibidosList()) {
                System.out.printf("%d. %s ($%.2f)%n", index++, p.getAutorPedido(), p.getPrecio());
            }
        } catch (java.lang.NullPointerException e) {
            System.out.println("Cola pizzeria vacía");
        }
        System.out.println();
    }

    private static void imprimirColaPedidosAtendidos(Pizzeria pizzeria) {
        System.out.println("    PEDIDOS ATENDIDOS    ");
        int index = 1;

        try {
            for (Pedido p : pizzeria.pedidosAtendidosList()) {
                System.out.printf("%d. %s (%.2f)%n", index++, p.getAutorPedido(), p.getPrecio());
            }
        } catch (java.lang.NullPointerException e) {
            System.out.println("Cola pizzeria vacía");
        }
        System.out.println();
    }

    private static void imprimirColaDespachos(Pizzeria pizzeria) {
        System.out.println("    COLA DE DESPACHOS    ");
        int index = 1;

        try {
            ArrayList<Pedido> colaDespachos = new ArrayList<>(pizzeria.colaDespachosList());
            for (Pedido p : colaDespachos) {
                System.out.printf("%d. %s (%d mts)%n", index++, p.getAutorPedido(), p.getCercania());
            }
        } catch (java.lang.NullPointerException e) {
            System.out.println("Cola de despachos vacía");
        }
        System.out.println();
    }

    static void imprimirArchivo(String filename) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}
