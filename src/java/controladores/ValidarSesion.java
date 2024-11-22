package controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class ValidarSesion extends HttpServlet {

    private static final String ROL_GERENTE = "Gerente";
    private static final String ROL_VENDEDOR = "Vendedor";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener la sesión existente, pero no crear una nueva si no existe
        HttpSession session = request.getSession(false);

        try {
            if (session != null && session.getAttribute("usuario") != null) {
                String rol = (String) session.getAttribute("rol");

                // Redirigir según el rol del usuario
                switch (rol) {
                    case ROL_GERENTE:
                        response.sendRedirect("sidebar.html");
                        break;
                    case ROL_VENDEDOR:
                        response.sendRedirect("index.jsp");
                        break;
                    default:
                        // Rol desconocido, redirigir al inicio de sesión
                        response.sendRedirect("inicio_Sesion.jsp");
                        break;
                }
            } else {
                // Si no hay sesión activa, redirigir al inicio de sesión
                response.sendRedirect("inicio_Sesion.jsp");
            }
        } catch (Exception e) {
            // Manejo de errores genéricos (opcional)
            e.printStackTrace();
            response.sendRedirect("error500.html"); // Redirigir a una página de error
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para validar sesión y redirigir a la vista correspondiente";
    }
}
