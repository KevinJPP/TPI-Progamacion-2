/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

/**
 *
 * @author Kevin
 */
import Entities.Pedido;
import Entities.Producto;
import Entities.Usuario;
import exception.ValidacionException;
import exception.EntityNotFoundException;
import java.util.List;

public interface PedidoService {
    Pedido crearCarrito(Usuario cliente) throws ValidacionException;
    void agregarProducto(Pedido pedido, Producto producto, int cantidad) throws ValidacionException;
    void finalizarPedido(Pedido pedido) throws ValidacionException;
    Pedido buscarPedido(Long id) throws EntityNotFoundException;
    List<Pedido> obtenerHistorial();
}