/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

/**
 *
 * @author Kevin
 */
import enums.Estado;
import enums.FormaPago;
import java.time.LocalDateTime;
import interfaces.Calculable;
import java.util.ArrayList;
import java.util.List;

public class Pedido extends Base implements Calculable {
    private String codigo;
    private Estado estado;
    private FormaPago formaPago;
    private Usuario cliente;
    private List<DetallePedido> detalles;

    public Pedido() {
        super();
        this.detalles = new ArrayList<>();
    }

    public Pedido(Long id, boolean eliminado, LocalDateTime createdAt, String codigo, Estado estado, FormaPago formaPago, Usuario cliente) {
        super(id, eliminado, createdAt);
        this.codigo = codigo;
        this.estado = estado;
        this.formaPago = formaPago;
        this.cliente = cliente;
        this.detalles = new ArrayList<>();
    }

    @Override
    public double calcularTotal() {
        double total = 0;
        for (DetallePedido detalle : detalles) {
            total += detalle.getSubtotal();
        }
        return total;
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }

    public FormaPago getFormaPago() { return formaPago; }
    public void setFormaPago(FormaPago formaPago) { this.formaPago = formaPago; }

    public Usuario getCliente() { return cliente; }
    public void setCliente(Usuario cliente) { this.cliente = cliente; }

    public List<DetallePedido> getDetalles() { return detalles; }
    public void setDetalles(List<DetallePedido> detalles) { this.detalles = detalles; }
}