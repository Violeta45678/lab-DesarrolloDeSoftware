/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */


/**
 *
 * @author macma
 */
package controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import modelos.Cliente;

@WebServlet("/ctrlClientes")
public class ctrlClientes extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            if (accion == null || accion.equals("listar")) {
                ArrayList<Cliente> listaClientes = Cliente.listarClientes();

                out.println("<h1>Lista de Clientes</h1>");
                for (Cliente cliente : listaClientes) {
                    out.println("<p>" + cliente.getIdCliente() + " - " + cliente.getNombre() + " " + cliente.getApellido() + "</p>");
                }
            } else if (accion.equals("insertar")) {
                String nombre = request.getParameter("nombre");
                String apellido = request.getParameter("apellido");
                String direccion = request.getParameter("direccion");
                int usuarioId = Integer.parseInt(request.getParameter("usuarioId"));

                boolean resultado = Cliente.insertarCliente(nombre, apellido, direccion, usuarioId);
                out.println("<p>" + (resultado ? "Cliente insertado exitosamente" : "Error al insertar cliente") + "</p>");
            } else if (accion.equals("actualizar")) {
                int idCliente = Integer.parseInt(request.getParameter("idCliente"));
                String nombre = request.getParameter("nombre");
                String apellido = request.getParameter("apellido");
                String direccion = request.getParameter("direccion");
                int usuarioId = Integer.parseInt(request.getParameter("usuarioId"));

                boolean resultado = Cliente.actualizarCliente(idCliente, nombre, apellido, direccion, usuarioId);
                out.println("<p>" + (resultado ? "Cliente actualizado exitosamente" : "Error al actualizar cliente") + "</p>");
            } else if (accion.equals("eliminar")) {
                int idCliente = Integer.parseInt(request.getParameter("idCliente"));

                boolean resultado = Cliente.eliminarCliente(idCliente);
                out.println("<p>" + (resultado ? "Cliente eliminado exitosamente" : "Error al eliminar cliente") + "</p>");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
