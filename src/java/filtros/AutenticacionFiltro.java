package filtros;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*") // Protege todas las rutas
public class AutenticacionFiltro implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String uri = httpRequest.getRequestURI();

        // Verificar si la solicitud es para páginas protegidas
        if (uri.endsWith("sidebar.html") || uri.endsWith("index.jsp")) {
            HttpSession session = httpRequest.getSession(false);

            if (session != null && session.getAttribute("usuario") != null) {
                chain.doFilter(request, response); // Continuar si hay sesión válida
            } else {
                httpResponse.sendRedirect("inicio_Sesion.jsp"); // Redirigir al login
            }
        } else {
            chain.doFilter(request, response); // Continuar si no es una página protegida
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
}
