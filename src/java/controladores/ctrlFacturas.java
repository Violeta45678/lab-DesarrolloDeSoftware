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
import java.sql.Date;
import java.util.ArrayList;
import modelos.Factura;

@WebServlet("/ctrlFacturas")
public class ctrlFacturas extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            if (accion == null || accion.equals("listar")) {
                ArrayList<Factura> listaFacturas = Factura.listarFacturas();

                out.println("<h1>Lista de Facturas</h1>");
                for (Factura factura : listaFacturas) {
                    out.println("<p>" + factura.getIdFactura() + " - Fecha: " + factura.getFecha() + ", Total: $" + factura.getTotal() + "</p>");
                }
            } else if (accion.equals("insertar")) {
                Date fecha = Date.valueOf(request.getParameter("fecha"));
                int clienteId = Integer.parseInt(request.getParameter("clienteId"));
                int empleadoId = Integer.parseInt(request.getParameter("empleadoId"));
                double total = Double.parseDouble(request.getParameter("total"));

                boolean resultado = Factura.insertarFactura(fecha, clienteId, empleadoId, total);
                out.println("<p>" + (resultado ? "Factura insertada exitosamente" : "Error al insertar factura") + "</p>");
            } else if (accion.equals("actualizar")) {
                int idFactura = Integer.parseInt(request.getParameter("idFactura"));
                Date fecha = Date.valueOf(request.getParameter("fecha"));
                int clienteId = Integer.parseInt(request.getParameter("clienteId"));
                int empleadoId = Integer.parseInt(request.getParameter("empleadoId"));
                double total = Double.parseDouble(request.getParameter("total"));

                boolean resultado = Factura.actualizarFactura(idFactura, fecha, clienteId, empleadoId, total);
                out.println("<p>" + (resultado ? "Factura actualizada exitosamente" : "Error al actualizar factura") + "</p>");
            } else if (accion.equals("eliminar")) {
                int idFactura = Integer.parseInt(request.getParameter("idFactura"));

                boolean resultado = Factura.eliminarFactura(idFactura);
                out.println("<p>" + (resultado ? "Factura eliminada exitosamente" : "Error al eliminar factura") + "</p>");
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
