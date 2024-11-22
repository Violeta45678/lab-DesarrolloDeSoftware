package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import modelos.conexion;

@WebServlet("/IniciarSesion")
public class Servelet_Inicio_Secion extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String correo = request.getParameter("email");
        String contrasena = request.getParameter("password");

        conexion conn = new conexion();
        Connection connection = conn.conectarMYSQL();

        try (PrintWriter out = response.getWriter()) {
            if (connection != null) {
                try {
                    String query = "SELECT u.id_usuario, u.contraseña, r.nombre_rol FROM Usuarios u JOIN Roles r ON u.rol_id = r.id_rol WHERE u.correo = ?";
                    PreparedStatement ps = connection.prepareStatement(query);
                    ps.setString(1, correo);
                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        String storedPassword = rs.getString("contraseña");
                        String rol = rs.getString("nombre_rol");

                        if (storedPassword.equals(hashPassword(contrasena))) {
                            HttpSession session = request.getSession();
                            session.setAttribute("id_usuario", rs.getInt("id_usuario"));
                            session.setAttribute("rol", rol);
                            session.setAttribute("usuario", correo); // Atributo que el filtro usa para validar sesión

                            // Redirigir a la ruta protegida según el rol
                            response.sendRedirect(getRedirectPage(rol));
                        } else {
                            displayError(out, "Contraseña incorrecta.");
                        }
                    } else {
                        displayError(out, "Usuario no encontrado.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    displayError(out, "Error en la base de datos.");
                } finally {
                    if (connection != null) {
                        conn.Desconectar();
                    }
                }
            } else {
                displayError(out, "Error de conexión.");
            }
        }
    }

    private void displayError(PrintWriter out, String errorMessage) {
        out.println("<!DOCTYPE html>");
        out.println("<html lang='es'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Error de Inicio de Sesión</title>");
        out.println("<link href='https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css' rel='stylesheet'>");
        out.println("</head>");
        out.println("<body class='bg-white flex items-center justify-center min-h-screen'>");
        out.println("<div class='max-w-md p-6 bg-white rounded-lg shadow-md text-center'>");
        out.println("<h1 class='text-3xl font-semibold text-red-700'>ERROR</h1>");
        out.println("<p class='text-red-600 mt-4'>" + errorMessage + "</p>");
        out.println("<a href='inicio_Sesion.jsp' class='mt-6 inline-block bg-red-700 text-white py-2 px-4 rounded-md hover:bg-red-600'>Volver al Inicio de Sesión</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

    private String getRedirectPage(String rol) {
        switch (rol) {
            case "Cliente":
                return "vista_cliente.jsp";
            case "Vendedor":
                return "index.jsp";
            case "Gerente":
                return "sidebar.html";
                default:
                return "inicio_Sesion.html";
        }
    }
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder(2 * encodedHash.length);
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al encriptar la contraseña", e);
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para iniciar sesión y redirigir a la vista correspondiente según el rol";
    }
}

