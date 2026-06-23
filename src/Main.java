/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Kevin
 */
import Entities.Categoria;
import Entities.Pedido;
import Entities.Producto;
import Entities.Usuario;
import dao.CategoriaDAO;
import dao.CategoriaDAOImpl;
import dao.ProductoDAO;
import dao.ProductoDAOImpl;
import dao.UsuarioDAO;
import dao.UsuarioDAOImpl;
import enums.Rol;
import services.PedidoService;
import services.PedidoServiceImpl;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== INICIANDO PRUEBA DEL SISTEMA FOOD STORE ===");

        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
        CategoriaDAO categoriaDAO = new CategoriaDAOImpl();
        ProductoDAO productoDAO = new ProductoDAOImpl();
        PedidoService pedidoService = new PedidoServiceImpl();

        try {
            // 1. Crear y guardar un usuario de prueba
            Usuario cliente = new Usuario();
            cliente.setNombre("Ana");
            cliente.setApellido("Sosa"); 
            cliente.setEmail("Ana10@email.com");
            cliente.setRol(Rol.CLIENTE);
            usuarioDAO.guardar(cliente);

            // 2. Crear y guardar una categoría
            Categoria cat = new Categoria();
            cat.setNombre("Hamburguesas");
            cat.setDescripcion("Hamburguesas caseras con papas");
            categoriaDAO.guardar(cat);
            System.out.println("-> Categoria creada: " + cat.getNombre());

            // 3. Crear y guardar un producto asociado a esa categoría
            Producto prod = new Producto();
            prod.setNombre("Hamburguesa Completa");
            prod.setPrecio(12500.0);
            prod.setCategoria(cat);
            productoDAO.guardar(prod);
            System.out.println("-> Producto registrado: " + prod.getNombre() + " ($" + prod.getPrecio() + ")");

            // 4. Simular el armado de un carrito de compras
            System.out.println("\n=== SIMULANDO CARRITO DE COMPRAS ===");
            Pedido carrito = pedidoService.crearCarrito(cliente);
            
            // Agregamos 2 unidades del producto al carrito
            pedidoService.agregarProducto(carrito, prod, 2);
            System.out.println("-> Se agregaron 2 unidades de " + prod.getNombre());

            // Calcular total usando la lógica del TPI
            double total = carrito.calcularTotal();
            System.out.println("-> Total calculado en el carrito: $" + total);

            // 5. Finalizar y mandar a la base de datos
            System.out.println("\n=== GUARDANDO PEDIDO EN BASE DE DATOS ===");
            pedidoService.finalizarPedido(carrito);
            System.out.println("¡Pedido guardado con exito! Codigo: " + carrito.getCodigo());
            System.out.println("=== PRUEBA FINALIZADA CORRECTAMENTE ===");

        } catch (Exception e) {
            System.out.println("¡ERROR DURANTE LA PRUEBA!");
            e.printStackTrace();
        }
    }
}
