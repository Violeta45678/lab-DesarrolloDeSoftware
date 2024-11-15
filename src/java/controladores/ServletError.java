package controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletError extends HttpServlet {

    // Método para procesar las solicitudes
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");

    // Obtención del código de error desde la solicitud
    Integer codigoEstado = (Integer) request.getAttribute("jakarta.servlet.error.status_code");

    // Verifica si el atributo no es nulo
    if (codigoEstado == null) {
        // Si no existe el atributo, se puede manejar el error de forma predeterminada
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error desconocido");
        return;
    }

    // Comprobación del código de estado HTTP
    switch (codigoEstado) {
        case 404:
            request.getRequestDispatcher("/error404.html").forward(request, response);
            break;
        case 500:
            request.getRequestDispatcher("/error500.html").forward(request, response);
            break;
        default:
            request.getRequestDispatcher("/error500.html").forward(request, response);
            break;
    }
}


    // Método doGet para manejar solicitudes GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    // Método doPost para manejar solicitudes POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    // Método para obtener la descripción del Servlet
    @Override
    public String getServletInfo() {
        return "Manejador de errores";
    }
}
