/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */


/**
 *
 * @author macma
 */package controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import modelos.Categoria;

@WebServlet("/ctrlCategorias")
public class ctrlCategorias extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang='es'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>Gestión de Categorías</title>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css' rel='stylesheet'>");
            out.println("</head>");
            out.println("<body class='bg-gray-100'>");

            out.println("<div class='container mx-auto mt-10'>");

            if (accion == null || accion.equals("listar")) {
                ArrayList<Categoria> listaCategorias = Categoria.listarCategorias();

                out.println("<h2 class='text-2xl font-bold mb-4'>Listado de Categorías</h2>");
                out.println("<a href='?accion=nuevo' class='bg-blue-500 text-white px-4 py-2 rounded mb-4 inline-block'>Nueva Categoría</a>");
                out.println("<table class='table-auto w-full bg-white'>");
                out.println("<thead>");
                out.println("<tr class='bg-gray-200'>");
                out.println("<th class='px-4 py-2'>ID</th>");
                out.println("<th class='px-4 py-2'>Nombre</th>");
                out.println("<th class='px-4 py-2'>Acciones</th>");
                out.println("</tr>");
                out.println("</thead>");
                out.println("<tbody>");

                for (Categoria categoria : listaCategorias) {
                    out.println("<tr>");
                    out.println("<td class='border px-4 py-2'>" + categoria.getIdCategoria() + "</td>");
                    out.println("<td class='border px-4 py-2'>" + categoria.getNombreCategoria() + "</td>");
                    out.println("<td class='border px-4 py-2'>");
                    out.println("<a href='?accion=editar&id=" + categoria.getIdCategoria() + "' class='text-blue-500'>Editar</a> | ");
                    out.println("<a href='?accion=eliminar&id=" + categoria.getIdCategoria() + "' class='text-red-500'>Eliminar</a>");
                    out.println("</td>");
                    out.println("</tr>");
                }

                out.println("</tbody>");
                out.println("</table>");

            } else if (accion.equals("nuevo") || accion.equals("editar")) {
                Categoria categoria = null;
                String titulo = "Nueva Categoría";
                String accionFormulario = "insertar";

                if (accion.equals("editar")) {
                    int id = Integer.parseInt(request.getParameter("id"));
                    categoria = Categoria.buscarCategoria(id);
                    titulo = "Editar Categoría";
                    accionFormulario = "actualizar";
                }

                out.println("<h2 class='text-2xl font-bold mb-4'>" + titulo + "</h2>");
                out.println("<form action='?accion=" + accionFormulario + "' method='post'>");

                if (categoria != null) {
                    out.println("<input type='hidden' name='idCategoria' value='" + categoria.getIdCategoria() + "'>");
                }

                out.println("<label for='nombreCategoria'>Nombre:</label>");
                out.println("<input type='text' id='nombreCategoria' name='nombreCategoria' value='" + (categoria != null ? categoria.getNombreCategoria() : "") + "' required>");
                out.println("<button type='submit'>Guardar</button>");
                out.println("</form>");

            } else if (accion.equals("insertar")) {
                String nombreCategoria = request.getParameter("nombreCategoria");
                Categoria.insertarCategoria(nombreCategoria);
                response.sendRedirect("ctrlCategorias?accion=listar");

            } else if (accion.equals("eliminar")) {
                int id = Integer.parseInt(request.getParameter("id"));
                Categoria.eliminarCategoria(id);
                response.sendRedirect("ctrlCategorias?accion=listar");

            } else if (accion.equals("actualizar")) {
                int id = Integer.parseInt(request.getParameter("idCategoria"));
                String nombreCategoria = request.getParameter("nombreCategoria");
                Categoria.actualizarCategoria(id, nombreCategoria);
                response.sendRedirect("ctrlCategorias?accion=listar");
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
