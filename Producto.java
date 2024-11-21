package Supermercado;

public class Producto {
    private String nombre;
    private double precio;
    private long tiempoCobro;

    public Producto(String nombre, double precio, long tiempoCobro) {
        this.nombre = nombre;
        this.precio = precio;
        this.tiempoCobro = tiempoCobro;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public long getTiempoCobro() {
        return tiempoCobro;
    }
}
