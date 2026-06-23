/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Kevin
 */
import Entities.Pedido;
import java.util.List;

public interface PedidoDAO {
    void guardar(Pedido pedido);
    void actualizarEstado(Long id, enums.Estado estado);
    void eliminarLogico(Long id);
    Pedido buscarPorId(Long id);
    List<Pedido> listarTodos();
}
