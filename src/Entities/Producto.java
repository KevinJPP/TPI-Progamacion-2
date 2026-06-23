/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

/**
 *
 * @author Kevin
 */
import java.time.LocalDateTime;

public class Producto extends Base {
    private String nombre;
    private double precio;
    private Categoria categoria;

    public Producto() {
        super();
    }

    public Producto(Long id, boolean eliminado, LocalDateTime createdAt, String nombre, double precio, Categoria categoria) {
        super(id, eliminado, createdAt);
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
}