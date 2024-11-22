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
import modelos.FormaDePago;

@WebServlet("/ctrlFormaDePago")
public class ctrlFormasDePago extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang='es'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>Gestión de Formas de Pago - ShoeUp</title>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css' rel='stylesheet'>");
            out.println("</head>");
            out.println("<body class='bg-white'>");

            out.println("<div class='w-full p-6 bg-white border-t-4 border-brown-500'>");

            out.println("<div class='flex items-center justify-between mb-4'>");
            out.println("<div class='flex items-center'>");
            out.println("<img src='img/logo.png' alt='Logo ShoeUp' class='h-8 w-auto mr-2'>");
            out.println("<h2 class='text-2xl font-bold text-brown-900'>Gestión de Formas de Pago</h2>");
            out.println("</div>");

            out.println("<div class='flex gap-4'>");
            out.println("<a href='?accion=nuevo' class='bg-green-500 text-white px-4 py-2 rounded-md'>Agregar Forma de Pago</a>");
            out.println("<a href='sidebar.html' class='bg-yellow-700 text-white px-4 py-2 rounded-md'>Regresar al Dashboard</a>");
            out.println("</div>");
            out.println("</div>");

            if (accion == null || accion.equals("listar")) {
                ArrayList<FormaDePago> listaFormasDePago = FormaDePago.listarFormasDePago();

                out.println("<table class='w-full bg-white border border-brown-500'>");
                out.println("<thead>");
                out.println("<tr class='bg-brown-100 text-brown-800'>");
                out.println("<th class='py-2 border border-brown-500'>ID</th>");
                out.println("<th class='py-2 border border-brown-500'>Forma de Pago</th>");
                out.println("<th class='py-2 border border-brown-500'>Acciones</th>");
                out.println("</tr>");
                out.println("</thead>");
                out.println("<tbody>");

                for (FormaDePago formaDePago : listaFormasDePago) {
                    out.println("<tr class='text-center border border-brown-500 hover:bg-brown-50'>");
                    out.println("<td class='py-2'>" + formaDePago.getIdFormaPago() + "</td>");
                    out.println("<td class='py-2'>" + formaDePago.getNombreFormaPago() + "</td>");
                    out.println("<td class='py-2'>");
                    out.println("<a href='?accion=editar&idFormaPago=" + formaDePago.getIdFormaPago() + "' class='text-blue-500 hover:underline'>Editar</a> | ");
                    out.println("<a href='?accion=confirmarEliminar&idFormaPago=" + formaDePago.getIdFormaPago() + "' class='text-red-500 hover:underline'>Eliminar</a>");
                    out.println("</td>");
                    out.println("</tr>");
                }

                out.println("</tbody>");
                out.println("</table>");

            } else if (accion.equals("confirmarEliminar")) {
                int idFormaPago = Integer.parseInt(request.getParameter("idFormaPago"));

                out.println("<div class='flex justify-center items-center min-h-screen'>");
                out.println("<div class='bg-white p-8 rounded shadow-lg text-center max-w-xs'>");
                out.println("<h2 class='text-xl font-bold text-red-600 mb-4'>Confirmar Eliminación</h2>");
                out.println("<p class='text-gray-700 mb-6'>¿Estás seguro de que deseas eliminar esta forma de pago?</p>");
                out.println("<div class='flex justify-between'>");
                out.println("<a href='?accion=eliminar&idFormaPago=" + idFormaPago + "' class='bg-red-600 text-white px-4 py-2 rounded hover:bg-red-700'>Eliminar</a>");
                out.println("<a href='?accion=listar' class='bg-gray-400 text-white px-4 py-2 rounded hover:bg-gray-500'>Cancelar</a>");
                out.println("</div>");
                out.println("</div>");
                out.println("</div>");

            } else if (accion.equals("nuevo") || accion.equals("editar")) {
                FormaDePago formaDePago = null;
                String tituloFormulario = "Agregar Forma de Pago";
                String accionFormulario = "insertar";

                if (accion.equals("editar")) {
                    int idFormaPago = Integer.parseInt(request.getParameter("idFormaPago"));
                    formaDePago = FormaDePago.buscarFormaDePago(idFormaPago);
                    tituloFormulario = "Actualizar Forma de Pago";
                    accionFormulario = "actualizar";
                }

                out.println("<div class='mt-6 bg-brown-100 p-6 rounded-lg shadow-md border border-brown-500 max-w-xl mx-auto'>");
                out.println("<h3 class='text-xl font-semibold text-brown-800 mb-4'>" + tituloFormulario + "</h3>");
                out.println("<form action='?accion=" + accionFormulario + "' method='post'>");

                if (formaDePago != null) {
                    out.println("<input type='hidden' name='idFormaPago' value='" + formaDePago.getIdFormaPago() + "'>");
                }

                out.println("<label class='block text-brown-700 font-medium mb-2'>Forma de Pago:</label>");
                out.println("<input type='text' name='nombreFormaPago' value='" + (formaDePago != null ? formaDePago.getNombreFormaPago() : "") + "' class='w-full mb-4 px-4 py-2 border border-brown-500 rounded focus:outline-none' required>");

                out.println("<div class='flex justify-between'>");
                out.println("<button type='submit' class='bg-yellow-800 text-white py-2 px-4 rounded-md font-semibold hover:bg-yellow-700'>" + (accion.equals("nuevo") ? "Guardar" : "Actualizar") + "</button>");
                out.println("<a href='?accion=listar' class='bg-red-700 text-white py-2 px-4 rounded-md font-semibold hover:bg-red-500'>Cancelar</a>");
                out.println("</div>");
                out.println("</form>");
                out.println("</div>");

            } else if (accion.equals("insertar")) {
                String nombreFormaPago = request.getParameter("nombreFormaPago");

                boolean resultado = FormaDePago.insertarFormaDePago(nombreFormaPago);

                if (resultado) {
                    response.sendRedirect("ctrlFormaDePago?accion=listar");
                } else {
                    out.println("<p class='text-red-500'>Error al insertar la forma de pago.</p>");
                }

            } else if (accion.equals("actualizar")) {
                int idFormaPago = Integer.parseInt(request.getParameter("idFormaPago"));
                String nombreFormaPago = request.getParameter("nombreFormaPago");

                boolean resultado = FormaDePago.actualizarFormaDePago(idFormaPago, nombreFormaPago);

                if (resultado) {
                    response.sendRedirect("ctrlFormaDePago?accion=listar");
                } else {
                    out.println("<p class='text-red-500'>Error al actualizar la forma de pago.</p>");
                }

            } else if (accion.equals("eliminar")) {
                int idFormaPago = Integer.parseInt(request.getParameter("idFormaPago"));
                boolean resultado = FormaDePago.eliminarFormaDePago(idFormaPago);

                if (resultado) {
                    response.sendRedirect("ctrlFormaDePago?accion=listar");
                } else {
                    out.println("<p class='text-red-500'>Error al eliminar la forma de pago.</p>");
                }
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
