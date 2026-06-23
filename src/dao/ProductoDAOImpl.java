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
import Entities.Producto;
import config.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOImpl implements ProductoDAO {

    @Override
    public void guardar(Producto producto) {
        String sql = "INSERT INTO producto (nombre, precio, categoria_id, eliminado, created_at) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, producto.getNombre());
            ps.setDouble(2, producto.getPrecio());
            ps.setLong(3, producto.getCategoria().getId());
            ps.setBoolean(4, producto.isEliminado());
            ps.setTimestamp(5, Timestamp.valueOf(producto.getCreatedAt()));
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    producto.setId(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar(Producto producto) {
        String sql = "UPDATE producto SET nombre = ?, precio = ?, categoria_id = ? WHERE id = ? AND eliminado = false";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, producto.getNombre());
            ps.setDouble(2, producto.getPrecio());
            ps.setLong(3, producto.getCategoria().getId());
            ps.setLong(4, producto.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarLogico(Long id) {
        String sql = "UPDATE producto SET eliminado = true WHERE id = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Producto buscarPorId(Long id) {
        String sql = "SELECT p.*, c.nombre as cat_nombre, c.descripcion as cat_desc, c.created_at as cat_created FROM producto p " +
                     "INNER JOIN categoria c ON p.categoria_id = c.id WHERE p.id = ? AND p.eliminado = false";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Categoria cat = new Categoria();
                    cat.setId(rs.getLong("categoria_id"));
                    cat.setNombre(rs.getString("cat_nombre"));
                    cat.setDescripcion(rs.getString("cat_desc"));
                    cat.setEliminado(false);
                    cat.setCreatedAt(rs.getTimestamp("cat_created").toLocalDateTime());

                    Producto prod = new Producto();
                    prod.setId(rs.getLong("id"));
                    prod.setNombre(rs.getString("nombre"));
                    prod.setPrecio(rs.getDouble("precio"));
                    prod.setEliminado(rs.getBoolean("eliminado"));
                    prod.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                    prod.setCategoria(cat);
                    return prod;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Producto> listarTodos() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT p.*, c.nombre as cat_nombre, c.descripcion as cat_desc, c.created_at as cat_created FROM producto p " +
                     "INNER JOIN categoria c ON p.categoria_id = c.id WHERE p.eliminado = false";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Categoria cat = new Categoria();
                cat.setId(rs.getLong("categoria_id"));
                cat.setNombre(rs.getString("cat_nombre"));
                cat.setDescripcion(rs.getString("cat_desc"));
                cat.setEliminado(false);
                cat.setCreatedAt(rs.getTimestamp("cat_created").toLocalDateTime());

                Producto prod = new Producto();
                prod.setId(rs.getLong("id"));
                prod.setNombre(rs.getString("nombre"));
                prod.setPrecio(rs.getDouble("precio"));
                prod.setEliminado(rs.getBoolean("eliminado"));
                prod.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                prod.setCategoria(cat);
                lista.add(prod);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}