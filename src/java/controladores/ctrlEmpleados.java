package controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import modelos.Empleado;

@WebServlet("/ctrlEmpleado")
public class ctrlEmpleados extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang='es'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>Gestión de Empleados - ShoeUp</title>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css' rel='stylesheet'>");
            out.println("</head>");
            out.println("<body class='bg-white'>");

            out.println("<div class='w-full p-6 bg-white border-t-4 border-brown-500'>");

            out.println("<div class='flex items-center justify-between mb-4'>");
            out.println("<div class='flex items-center'>");
            out.println("<img src='img/logo.png' alt='Logo ShoeUp' class='h-8 w-auto mr-2'>");
            out.println("<h2 class='text-2xl font-bold text-brown-900'>Gestión de Empleados</h2>");
            out.println("</div>");

            out.println("<div class='flex gap-4'>");
            out.println("<a href='?accion=nuevo' class='bg-green-500 text-white px-4 py-2 rounded-md'>Agregar Empleado</a>");
            out.println("<a href='sidebar.html' class='bg-yellow-700 text-white px-4 py-2 rounded-md'>Regresar al Dashboard</a>");
            out.println("</div>");
            out.println("</div>");

            if (accion == null || accion.equals("listar")) {
                ArrayList<Empleado> listaEmpleados = Empleado.listarEmpleados();

                out.println("<table class='w-full bg-white border border-brown-500'>");
                out.println("<thead>");
                out.println("<tr class='bg-brown-100 text-brown-800'>");
                out.println("<th class='py-2 border border-brown-500'>ID</th>");
                out.println("<th class='py-2 border border-brown-500'>Nombre</th>");
                out.println("<th class='py-2 border border-brown-500'>Apellido</th>");
                out.println("<th class='py-2 border border-brown-500'>Rol</th>");
                out.println("<th class='py-2 border border-brown-500'>Acciones</th>");
                out.println("</tr>");
                out.println("</thead>");
                out.println("<tbody>");

                for (Empleado empleado : listaEmpleados) {
                    out.println("<tr class='text-center border border-brown-500 hover:bg-brown-50'>");
                    out.println("<td class='py-2'>" + empleado.getIdEmpleado() + "</td>");
                    out.println("<td class='py-2'>" + empleado.getNombre() + "</td>");
                    out.println("<td class='py-2'>" + empleado.getApellido() + "</td>");
                    out.println("<td class='py-2'>" + empleado.getRolId() + "</td>");
                    out.println("<td class='py-2'>");
                    out.println("<a href='?accion=editar&idEmpleado=" + empleado.getIdEmpleado() + "' class='text-blue-500 hover:underline'>Editar</a> | ");
                    out.println("<a href='?accion=confirmarEliminar&idEmpleado=" + empleado.getIdEmpleado() + "' class='text-red-500 hover:underline'>Eliminar</a>");
                    out.println("</td>");
                    out.println("</tr>");
                }

                out.println("</tbody>");
                out.println("</table>");

            } else if (accion.equals("confirmarEliminar")) {
                int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));

                out.println("<div class='flex justify-center items-center min-h-screen'>");
                out.println("<div class='bg-white p-8 rounded shadow-lg text-center max-w-xs'>");
                out.println("<h2 class='text-xl font-bold text-red-600 mb-4'>Confirmar Eliminación</h2>");
                out.println("<p class='text-gray-700 mb-6'>¿Estás seguro de que deseas eliminar este empleado?</p>");
                out.println("<div class='flex justify-between'>");
                out.println("<a href='?accion=eliminar&idEmpleado=" + idEmpleado + "' class='bg-red-600 text-white px-4 py-2 rounded hover:bg-red-700'>Eliminar</a>");
                out.println("<a href='?accion=listar' class='bg-gray-400 text-white px-4 py-2 rounded hover:bg-gray-500'>Cancelar</a>");
                out.println("</div>");
                out.println("</div>");
                out.println("</div>");

            } else if (accion.equals("nuevo") || accion.equals("editar")) {
                Empleado empleado = null;
                String tituloFormulario = "Agregar Empleado";
                String accionFormulario = "insertar";
                String correo = "";

                if (accion.equals("editar")) {
                    int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
                    empleado = Empleado.buscarEmpleado(idEmpleado);
                    tituloFormulario = "Actualizar Empleado";
                    accionFormulario = "actualizar";

                    if (empleado != null) {
                        correo = empleado.getCorreo();
                    }
                }

                out.println("<div class='mt-6 bg-brown-100 p-6 rounded-lg shadow-md border border-brown-500 max-w-xl mx-auto'>");
                out.println("<h3 class='text-xl font-semibold text-brown-800 mb-4'>" + tituloFormulario + "</h3>");
                out.println("<form action='?accion=" + accionFormulario + "' method='post'>");

                if (empleado != null) {
                    out.println("<input type='hidden' name='idEmpleado' value='" + empleado.getIdEmpleado() + "'>");
                }

                out.println("<label class='block text-brown-700 font-medium mb-2'>Nombre:</label>");
                out.println("<input type='text' name='nombre' value='" + (empleado != null ? empleado.getNombre() : "") + "' class='w-full mb-4 px-4 py-2 border border-brown-500 rounded focus:outline-none' required>");
                
                out.println("<label class='block text-brown-700 font-medium mb-2'>Apellido:</label>");
                out.println("<input type='text' name='apellido' value='" + (empleado != null ? empleado.getApellido() : "") + "' class='w-full mb-4 px-4 py-2 border border-brown-500 rounded focus:outline-none' required>");
                
                out.println("<label class='block text-brown-700 font-medium mb-2'>Rol ID:</label>");
                out.println("<input type='number' name='rolId' value='" + (empleado != null ? empleado.getRolId() : "") + "' class='w-full mb-4 px-4 py-2 border border-brown-500 rounded focus:outline-none' required>");

                out.println("<label class='block text-brown-700 font-medium mb-2'>Correo:</label>");
                out.println("<input type='email' name='correo' value='" + correo + "' class='w-full mb-4 px-4 py-2 border border-brown-500 rounded focus:outline-none' required>");
                
                out.println("<label class='block text-brown-700 font-medium mb-2'>Contraseña:</label>");
                out.println("<input type='password' name='contrasena' " + (accion.equals("nuevo") ? "required" : "") + " placeholder='" + (accion.equals("nuevo") ? "Contraseña requerida" : "Opcional para actualizar") + "' class='w-full mb-4 px-4 py-2 border border-brown-500 rounded focus:outline-none'>");

                out.println("<div class='flex justify-between'>");
                out.println("<button type='submit' class='bg-yellow-800 text-white py-2 px-4 rounded-md font-semibold hover:bg-yellow-700'>" + (accion.equals("nuevo") ? "Guardar" : "Actualizar") + "</button>");
                out.println("<a href='?accion=listar' class='bg-red-700 text-white py-2 px-4 rounded-md font-semibold hover:bg-red-500'>Cancelar</a>");
                out.println("</div>");
                out.println("</form>");
                out.println("</div>");

            } else if (accion.equals("insertar")) {
                String nombre = request.getParameter("nombre");
                String apellido = request.getParameter("apellido");
                int rolId = Integer.parseInt(request.getParameter("rolId"));
                String correo = request.getParameter("correo");
                String contrasena = request.getParameter("contrasena");

                boolean resultado = Empleado.insertarEmpleado(nombre, apellido, rolId, correo, contrasena);

                if (resultado) {
                    response.sendRedirect("ctrlEmpleado?accion=listar");
                } else {
                    out.println("<p class='text-red-500'>Error al agregar el empleado y usuario.</p>");
                }

            } else if (accion.equals("eliminar")) {
                int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
                boolean resultado = Empleado.eliminarEmpleado(idEmpleado);

                if (resultado) {
                    response.sendRedirect("ctrlEmpleado?accion=listar");
                } else {
                    out.println("<p class='text-red-500'>Error al eliminar el empleado.</p>");
                }

            } else if (accion.equals("actualizar")) {
                int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
                String nombre = request.getParameter("nombre");
                String apellido = request.getParameter("apellido");
                int rolId = Integer.parseInt(request.getParameter("rolId"));
                String correo = request.getParameter("correo");
                String contrasena = request.getParameter("contrasena");

                boolean resultado;
                if (contrasena == null || contrasena.isEmpty()) {
                    resultado = Empleado.actualizarEmpleado(idEmpleado, nombre, apellido, rolId, correo, null);
                } else {
                    resultado = Empleado.actualizarEmpleado(idEmpleado, nombre, apellido, rolId, correo, contrasena);
                }

                if (resultado) {
                    response.sendRedirect("ctrlEmpleado?accion=listar");
                } else {
                    out.println("<p class='text-red-500'>Error al actualizar el empleado.</p>");
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
