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
import modelos.Venta;

@WebServlet("/ctrlVenta")
public class ctrlVentas extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang='es'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>Gestión de Ventas - ShoeUp</title>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css' rel='stylesheet'>");
            out.println("</head>");
            out.println("<body class='bg-white'>");
            out.println("<div class='w-full p-6 bg-white border-t-4 border-brown-500'>");

            out.println("<div class='flex items-center justify-between mb-4'>");
            out.println("<div class='flex items-center'>");
            out.println("<img src='img/logo.png' alt='Logo ShoeUp' class='h-8 w-auto mr-2'>");
            out.println("<h2 class='text-2xl font-bold text-brown-900'>Gestión de Ventas</h2>");
            out.println("</div>");

            out.println("<div class='flex gap-4'>");
            out.println("<a href='?accion=nuevo' class='bg-green-500 text-white px-4 py-2 rounded-md'>Agregar Venta</a>");
            out.println("<a href='sidebar.html' class='bg-yellow-700 text-white px-4 py-2 rounded-md'>Regresar al Dashboard</a>");
            out.println("</div>");
            out.println("</div>");

            if (accion == null || accion.equals("listar")) {
                ArrayList<Venta> listaVentas = Venta.listarVentas();

                out.println("<table class='w-full bg-white border border-brown-500'>");
                out.println("<thead>");
                out.println("<tr class='bg-brown-100 text-brown-800'>");
                out.println("<th class='py-2 border border-brown-500'>ID Venta</th>");
                out.println("<th class='py-2 border border-brown-500'>Factura ID</th>");
                out.println("<th class='py-2 border border-brown-500'>Zapato ID</th>");
                out.println("<th class='py-2 border border-brown-500'>Cantidad</th>");
                out.println("<th class='py-2 border border-brown-500'>Precio Unitario</th>");
                out.println("<th class='py-2 border border-brown-500'>Acciones</th>");
                out.println("</tr>");
                out.println("</thead>");
                out.println("<tbody>");

                for (Venta venta : listaVentas) {
                    out.println("<tr class='text-center border border-brown-500 hover:bg-brown-50'>");
                    out.println("<td class='py-2'>" + venta.getIdVenta() + "</td>");
                    out.println("<td class='py-2'>" + venta.getFacturaId() + "</td>");
                    out.println("<td class='py-2'>" + venta.getZapatoId() + "</td>");
                    out.println("<td class='py-2'>" + venta.getCantidad() + "</td>");
                    out.println("<td class='py-2'>" + venta.getPrecioUnitario() + "</td>");
                    out.println("<td class='py-2'>");
                    out.println("<a href='?accion=editar&idVenta=" + venta.getIdVenta() + "' class='text-blue-500 hover:underline'>Editar</a> | ");
                    out.println("<a href='?accion=confirmarEliminar&idVenta=" + venta.getIdVenta() + "' class='text-red-500 hover:underline'>Eliminar</a>");
                    out.println("</td>");
                    out.println("</tr>");
                }

                out.println("</tbody>");
                out.println("</table>");

            } else if (accion.equals("confirmarEliminar")) {
                int idVenta = Integer.parseInt(request.getParameter("idVenta"));
                boolean eliminado = Venta.eliminarVenta(idVenta);

                if (eliminado) {
                    out.println("<p class='text-green-600'>Venta eliminada con éxito</p>");
                } else {
                    out.println("<p class='text-red-600'>Hubo un problema al eliminar la venta</p>");
                }

                out.println("<a href='?accion=listar' class='bg-blue-500 text-white px-4 py-2 rounded-md'>Volver a listar ventas</a>");
            } else if (accion.equals("nuevo")) {
                // Formulario para agregar una nueva venta
                out.println("<h3 class='text-xl font-semibold text-brown-700 mb-4'>Agregar Venta</h3>");
                out.println("<form action='?accion=guardar' method='POST'>");
                out.println("<label for='facturaId' class='block text-brown-700'>Factura ID</label>");
                out.println("<input type='number' name='facturaId' class='w-full p-2 mb-4 border rounded' required>");
                out.println("<label for='zapatoId' class='block text-brown-700'>Zapato ID</label>");
                out.println("<input type='number' name='zapatoId' class='w-full p-2 mb-4 border rounded' required>");
                out.println("<label for='cantidad' class='block text-brown-700'>Cantidad</label>");
                out.println("<input type='number' name='cantidad' class='w-full p-2 mb-4 border rounded' required>");
                out.println("<label for='precioUnitario' class='block text-brown-700'>Precio Unitario</label>");
                out.println("<input type='number' step='0.01' name='precioUnitario' class='w-full p-2 mb-4 border rounded' required>");
                out.println("<button type='submit' class='bg-blue-500 text-white px-6 py-2 rounded'>Guardar Venta</button>");
                out.println("</form>");
                out.println("<a href='?accion=listar' class='bg-gray-500 text-white px-4 py-2 rounded mt-4 inline-block'>Cancelar</a>");

            } else if (accion.equals("guardar")) {
                int facturaId = Integer.parseInt(request.getParameter("facturaId"));
                int zapatoId = Integer.parseInt(request.getParameter("zapatoId"));
                int cantidad = Integer.parseInt(request.getParameter("cantidad"));
                double precioUnitario = Double.parseDouble(request.getParameter("precioUnitario"));

                boolean insertado = Venta.insertarVenta(facturaId, zapatoId, cantidad, precioUnitario);

                if (insertado) {
                    out.println("<p class='text-green-600'>Venta agregada con éxito</p>");
                } else {
                    out.println("<p class='text-red-600'>Hubo un problema al agregar la venta</p>");
                }

                out.println("<a href='?accion=listar' class='bg-blue-500 text-white px-4 py-2 rounded-md'>Volver a listar ventas</a>");
            }

            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
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
