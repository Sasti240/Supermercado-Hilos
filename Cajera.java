package Supermercado;

public class Cajera implements Runnable {

    private String nombre;
    private Cliente cliente;

    public Cajera(String nombre, Cliente cliente) {
        this.nombre = nombre;
        this.cliente = cliente;
    }

    @Override
    public void run() {
        synchronized (this) { 
            System.out.println(nombre + " está procesando la compra de " + cliente.getNombre());
            double totalCompra = 0;

            for (Producto producto : cliente.getProductos()) {
                System.out.println(nombre + " - Producto: " + producto.getNombre() + " - Precio: " + producto.getPrecio() + " COP");
                totalCompra += producto.getPrecio();
                try {
                    Thread.sleep(1000); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(nombre + " - El total de la compra de " + cliente.getNombre() + " es: " + totalCompra + " COP");
        }
    }
}