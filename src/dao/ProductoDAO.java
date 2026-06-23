/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Kevin
 */
import Entities.Producto;
import java.util.List;

public interface ProductoDAO {
    void guardar(Producto producto);
    void actualizar(Producto producto);
    void eliminarLogico(Long id);
    Producto buscarPorId(Long id);
    List<Producto> listarTodos();
}
