/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Kevin
 */
import Entities.DetallePedido;
import Entities.Pedido;
import Entities.Producto;
import Entities.Usuario;
import config.ConexionDB;
import enums.Estado;
import enums.FormaPago;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAOImpl implements PedidoDAO {

    @Override
    public void guardar(Pedido pedido) {
        String sqlPedido = "INSERT INTO pedido (codigo, estado, forma_pago, usuario_id, eliminado, created_at) VALUES (?, ?, ?, ?, ?, ?)";
        String sqlDetalle = "INSERT INTO detalle_pedido (cantidad, subtotal, producto_id, pedido_id, eliminado, created_at) VALUES (?, ?, ?, ?, ?, ?)";
        
        Connection conn = null;
        try {
            conn = ConexionDB.getConnection();
            conn.setAutoCommit(false);

            try (PreparedStatement psPed = conn.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS)) {
                psPed.setString(1, pedido.getCodigo());
                psPed.setString(2, pedido.getEstado().name());
                psPed.setString(3, pedido.getFormaPago().name());
                psPed.setLong(4, pedido.getCliente().getId());
                psPed.setBoolean(5, pedido.isEliminado());
                psPed.setTimestamp(6, Timestamp.valueOf(pedido.getCreatedAt()));
                psPed.executeUpdate();

                try (ResultSet rs = psPed.getGeneratedKeys()) {
                    if (rs.next()) {
                        pedido.setId(rs.getLong(1));
                    }
                }
            }

            try (PreparedStatement psDet = conn.prepareStatement(sqlDetalle)) {
                for (DetallePedido detalle : pedido.getDetalles()) {
                    psDet.setInt(1, detalle.getCantidad());
                    psDet.setDouble(2, detalle.getSubtotal());
                    psDet.setLong(3, detalle.getProducto().getId());
                    psDet.setLong(4, pedido.getId());
                    psDet.setBoolean(5, detalle.isEliminado());
                    psDet.setTimestamp(6, Timestamp.valueOf(detalle.getCreatedAt()));
                    psDet.addBatch();
                }
                psDet.executeBatch();
            }

            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void actualizarEstado(Long id, Estado estado) {
        String sql = "UPDATE pedido SET estado = ? WHERE id = ? AND eliminado = false";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, estado.name());
            ps.setLong(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarLogico(Long id) {
        String sql = "UPDATE pedido SET eliminado = true WHERE id = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Pedido buscarPorId(Long id) {
        String sqlPedido = "SELECT p.*, u.nombre as user_nombre, u.email as user_email, u.rol as user_rol, u.created_at as user_created " +
                           "FROM pedido p INNER JOIN usuario u ON p.usuario_id = u.id WHERE p.id = ? AND p.eliminado = false";
        String sqlDetalles = "SELECT d.*, prod.nombre as prod_nombre, prod.precio as prod_precio, prod.created_at as prod_created " +
                            "FROM detalle_pedido d INNER JOIN producto prod ON d.producto_id = prod.id WHERE d.pedido_id = ? AND d.eliminado = false";
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement psPed = conn.prepareStatement(sqlPedido)) {
            psPed.setLong(1, id);
            try (ResultSet rsPed = psPed.executeQuery()) {
                if (rsPed.next()) {
                    Usuario u = new Usuario();
                    u.setId(rsPed.getLong("usuario_id"));
                    u.setNombre(rsPed.getString("user_nombre"));
                    u.setEmail(rsPed.getString("user_email"));
                    u.setRol(enums.Rol.valueOf(rsPed.getString("user_rol")));
                    u.setEliminado(false);
                    u.setCreatedAt(rsPed.getTimestamp("user_created").toLocalDateTime());

                    Pedido p = new Pedido();
                    p.setId(rsPed.getLong("id"));
                    p.setCodigo(rsPed.getString("codigo"));
                    p.setEstado(Estado.valueOf(rsPed.getString("estado")));
                    p.setFormaPago(FormaPago.valueOf(rsPed.getString("forma_pago")));
                    p.setEliminado(false);
                    p.setCreatedAt(rsPed.getTimestamp("created_at").toLocalDateTime());
                    p.setCliente(u);

                    try (PreparedStatement psDet = conn.prepareStatement(sqlDetalles)) {
                        psDet.setLong(1, id);
                        try (ResultSet rsDet = psDet.executeQuery()) {
                            while (rsDet.next()) {
                                Producto prod = new Producto();
                                prod.setId(rsDet.getLong("producto_id"));
                                prod.setNombre(rsDet.getString("prod_nombre"));
                                prod.setPrecio(rsDet.getDouble("prod_precio"));
                                prod.setEliminado(false);
                                prod.setCreatedAt(rsDet.getTimestamp("prod_created").toLocalDateTime());

                                DetallePedido d = new DetallePedido();
                                d.setId(rsDet.getLong("id"));
                                d.setCantidad(rsDet.getInt("cantidad"));
                                d.setSubtotal(rsDet.getDouble("subtotal"));
                                d.setEliminado(false);
                                d.setCreatedAt(rsDet.getTimestamp("created_at").toLocalDateTime());
                                d.setProducto(prod);

                                p.getDetalles().add(d);
                            }
                        }
                    }
                    return p;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Pedido> listarTodos() {
        List<Pedido> lista = new ArrayList<>();
        String sql = "SELECT p.*, u.nombre as user_nombre, u.email as user_email, u.rol as user_rol, u.created_at as user_created " +
                     "FROM pedido p INNER JOIN usuario u ON p.usuario_id = u.id WHERE p.eliminado = false";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getLong("usuario_id"));
                u.setNombre(rs.getString("user_nombre"));
                u.setEmail(rs.getString("user_email"));
                u.setRol(enums.Rol.valueOf(rs.getString("user_rol")));
                u.setEliminado(false);
                u.setCreatedAt(rs.getTimestamp("user_created").toLocalDateTime());

                Pedido p = new Pedido();
                p.setId(rs.getLong("id"));
                p.setCodigo(rs.getString("codigo"));
                p.setEstado(Estado.valueOf(rs.getString("estado")));
                p.setFormaPago(FormaPago.valueOf(rs.getString("forma_pago")));
                p.setEliminado(false);
                p.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                p.setCliente(u);
                
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
