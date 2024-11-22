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

@WebServlet("/ctrlFormasDePago")
public class ctrlFormasDePago extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            if (accion == null || accion.equals("listar")) {
                ArrayList<FormaDePago> listaFormasDePago = FormaDePago.listarFormasDePago();

                out.println("<h1>Lista de Formas de Pago</h1>");
                for (FormaDePago forma : listaFormasDePago) {
                    out.println("<p>" + forma.getIdFormaPago() + " - " + forma.getNombreFormaPago() + "</p>");
                }
            } else if (accion.equals("insertar")) {
                String nombreFormaPago = request.getParameter("nombreFormaPago");

                boolean resultado = FormaDePago.insertarFormaDePago(nombreFormaPago);
                out.println("<p>" + (resultado ? "Forma de pago insertada exitosamente" : "Error al insertar forma de pago") + "</p>");
            } else if (accion.equals("actualizar")) {
                int idFormaPago = Integer.parseInt(request.getParameter("idFormaPago"));
                String nombreFormaPago = request.getParameter("nombreFormaPago");

                boolean resultado = FormaDePago.actualizarFormaDePago(idFormaPago, nombreFormaPago);
                out.println("<p>" + (resultado ? "Forma de pago actualizada exitosamente" : "Error al actualizar forma de pago") + "</p>");
            } else if (accion.equals("eliminar")) {
                int idFormaPago = Integer.parseInt(request.getParameter("idFormaPago"));

                boolean resultado = FormaDePago.eliminarFormaDePago(idFormaPago);
                out.println("<p>" + (resultado ? "Forma de pago eliminada exitosamente" : "Error al eliminar forma de pago") + "</p>");
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
