/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Kevin
 */
import Entities.Categoria;
import java.util.List;

public interface CategoriaDAO {
    void guardar(Categoria categoria);
    void actualizar(Categoria categoria);
    void eliminarLogico(Long id);
    Categoria buscarPorId(Long id);
    List<Categoria> listarTodas();
}
