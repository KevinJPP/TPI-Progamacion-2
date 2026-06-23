/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

/**
 *
 * @author Kevin
 */
import Entities.*;
import Entities.DetallePedido;
import Entities.Pedido;
import Entities.Producto;
import Entities.Usuario;
import dao.PedidoDAO;
import dao.PedidoDAOImpl;
import enums.Estado;
import enums.FormaPago;
import exception.ValidacionException;
import exception.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class PedidoServiceImpl implements PedidoService {

    private PedidoDAO pedidoDAO = new PedidoDAOImpl();

    @Override
    public Pedido crearCarrito(Usuario cliente) throws ValidacionException {
        if (cliente == null || cliente.getId() == null) {
            throw new ValidacionException("El pedido necesita un cliente valido registrado.");
        }
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setEstado(Estado.PENDIENTE);
        pedido.setFormaPago(FormaPago.EFECTIVO);
        pedido.setCodigo("PED-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        return pedido;
    }

    @Override
    public void agregarProducto(Pedido pedido, Producto producto, int cantidad) throws ValidacionException {
        if (pedido == null) {
            throw new ValidacionException("El carrito no existe.");
        }
        if (producto == null || producto.getId() == null) {
            throw new ValidacionException("El producto que intenta agregar no es valido.");
        }
        if (cantidad <= 0) {
            throw new ValidacionException("La cantidad debe ser mayor a cero.");
        }

        DetallePedido detalle = new DetallePedido();
        detalle.setProducto(producto);
        detalle.setCantidad(cantidad);
        detalle.setSubtotal(producto.getPrecio() * cantidad);

        pedido.getDetalles().add(detalle);
    }

    @Override
    public void finalizarPedido(Pedido pedido) throws ValidacionException {
        if (pedido == null) {
            throw new ValidacionException("No se puede finalizar un pedido inexistente.");
        }
        if (pedido.getDetalles().isEmpty()) {
            throw new ValidacionException("El carrito esta vacio. Agregue productos antes de pagar.");
        }
        
        pedidoDAO.guardar(pedido);
    }

    @Override
    public Pedido buscarPedido(Long id) throws EntityNotFoundException {
        Pedido pedido = pedidoDAO.buscarPorId(id);
        if (pedido == null) {
            throw new EntityNotFoundException("No se encontro el pedido con el ID: " + id);
        }
        return pedido;
    }

    @Override
    public List<Pedido> obtenerHistorial() {
        return pedidoDAO.listarTodos();
    }
}
