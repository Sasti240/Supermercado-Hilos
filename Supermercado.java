package Supermercado;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Supermercado {

    public static void main(String[] args) {
        List<Producto> productosDisponibles = crearProductos();
        List<Cliente> clientes = crearClientes(productosDisponibles);

        final int NUMERO_DE_CAJERAS = 3; 

        ExecutorService servicioCajeras = Executors.newFixedThreadPool(NUMERO_DE_CAJERAS);

        for (int i = 0; i < clientes.size(); i++) {
            Cliente cliente = clientes.get(i);
            servicioCajeras.submit(new Cajera("Cajera " + (i % NUMERO_DE_CAJERAS + 1), cliente));
        }

        servicioCajeras.shutdown();
        try {
            if (!servicioCajeras.awaitTermination(1, TimeUnit.MINUTES)) {
                System.out.println("Algunos hilos no han terminado en el tiempo esperado.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Todos los clientes han sido atendidos.");
    }

    private static List<Producto> crearProductos() {
        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto("Pan", 1500, 1000));
        productos.add(new Producto("Leche", 2300, 1500));
        productos.add(new Producto("Huevos", 3000, 2000));
        productos.add(new Producto("Queso", 4500, 2500));
        productos.add(new Producto("Carne", 10000, 3000));
        productos.add(new Producto("Arroz", 2000, 1200));
        productos.add(new Producto("Frijoles", 1800, 1300));
        productos.add(new Producto("Aceite", 5000, 2200));
        productos.add(new Producto("Café", 3500, 1400));
        productos.add(new Producto("Té", 2500, 1100));
        return productos;
    }

    private static List<Cliente> crearClientes(List<Producto> productos) {
        List<Cliente> clientes = new ArrayList<>();

        clientes.add(crearCliente("Carlos", productos.get(0), productos.get(3), productos.get(7)));
        clientes.add(crearCliente("Ana", productos.get(1), productos.get(2), productos.get(4)));
        clientes.add(crearCliente("Luis", productos.get(5), productos.get(6), productos.get(8)));
        clientes.add(crearCliente("Marta", productos.get(0), productos.get(9), productos.get(2)));
        clientes.add(crearCliente("Juan", productos.get(3), productos.get(4), productos.get(1)));

        return clientes;
    }

    private static Cliente crearCliente(String nombre, Producto... productos) {
        Cliente cliente = new Cliente(nombre);
        for (Producto producto : productos) {
            cliente.agregarProducto(producto);
        }
        return cliente;
    }
}

