package modelos;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import jakarta.servlet.http.HttpSession;  


public class CarritoServlet extends HttpServlet {
    
    // Definición de la clase Producto dentro del servlet
    public static class Producto {
        private String nombre;
        private String imagen;
        private double precio;
        private int cantidad;

        // Constructor
        public Producto(String nombre, String imagen, double precio, int cantidad) {
            this.nombre = nombre;
            this.imagen = imagen;
            this.precio = precio;
            this.cantidad = cantidad;
        }

        // Getters y setters
        public String getNombre() { return nombre; }
        public String getImagen() { return imagen; }
        public double getPrecio() { return precio; }
        public int getCantidad() { return cantidad; }
        public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener la sesión
        HttpSession session = request.getSession();

        // Recuperar el carrito de la sesión (si existe)
        ArrayList<Producto> carrito = (ArrayList<Producto>) session.getAttribute("carrito");
        if (carrito == null) {
            carrito = new ArrayList<>();
        }

        // Obtener los parámetros del producto
        String nombre = request.getParameter("nombre");
        String imagen = request.getParameter("imagen");
        double precio = 0.0;

        try {
            precio = Double.parseDouble(request.getParameter("precio"));
        } catch (NumberFormatException e) {
            // En caso de error al convertir el precio, puedes redirigir o mostrar un error
            response.sendRedirect("error.jsp?mensaje=Error+al+agregar+producto+al+carrito");
            return;
        }

        // Comprobar si el producto ya está en el carrito
        boolean encontrado = false;
        for (Producto p : carrito) {
            if (p.getNombre().equals(nombre)) {
                // Si el producto ya existe, incrementar la cantidad
                p.setCantidad(p.getCantidad() + 1);
                encontrado = true;
                break;
            }
        }

        // Si el producto no fue encontrado, agregarlo al carrito
        if (!encontrado) {
            carrito.add(new Producto(nombre, imagen, precio, 1));
        }

        // Guardar el carrito actualizado en la sesión
        session.setAttribute("carrito", carrito);

        // Redirigir al carrito para mostrar los productos
        response.sendRedirect("Carrito.jsp");
    }
}
