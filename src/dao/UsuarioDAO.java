/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Kevin
 */
import Entities.Usuario;
import java.util.List;

public interface UsuarioDAO {
    void guardar(Usuario usuario);
    void actualizar(Usuario usuario);
    void eliminarLogico(Long id);
    Usuario buscarPorId(Long id);
    List<Usuario> listarTodos();
}
