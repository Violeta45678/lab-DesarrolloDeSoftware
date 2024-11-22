package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.conexion;

@WebServlet("/RegistrarUsuario")
public class ServletRegistros extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String nombreUsuario = request.getParameter("nombre_usuario");
        String apellidoUsuario = request.getParameter("apellido_usuario");
        String correo = request.getParameter("correo");
        String direccion = request.getParameter("direccion");
        String contrase = request.getParameter("contrase");
        String confirmarContrasena = request.getParameter("confirmar_contraseña");

        try (PrintWriter out = response.getWriter()) {
            if (nombreUsuario == null || nombreUsuario.trim().isEmpty() ||
                apellidoUsuario == null || apellidoUsuario.trim().isEmpty() ||
                correo == null || correo.trim().isEmpty() ||
                direccion == null || direccion.trim().isEmpty() ||
                contrase == null || contrase.trim().isEmpty() ||
                confirmarContrasena == null || confirmarContrasena.trim().isEmpty()) {

                displayMessage(out, "Error", "Uno o más campos están vacíos.", "registro.jsp");
                return;
            }

            if (!contrase.equals(confirmarContrasena)) {
                displayMessage(out, "Error", "Las contraseñas no coinciden.", "registro.jsp");
                return;
            }

            String hashedPassword = hashPassword(contrase);

            conexion conn = new conexion();
            Connection connection = conn.conectarMYSQL();

            if (connection != null) {
                try {
                    connection.setAutoCommit(false);

                    String queryCorreo = "SELECT id_usuario FROM Usuarios WHERE correo = ?";
                    PreparedStatement psValidarCorreo = connection.prepareStatement(queryCorreo);
                    psValidarCorreo.setString(1, correo);
                    ResultSet rs = psValidarCorreo.executeQuery();

                    if (rs.next()) {
                        displayMessage(out, "Error", "El correo ya está registrado.", "registro.jsp");
                        return;
                    }

                    String usuarioQuery = "INSERT INTO Usuarios (correo, contraseña, rol_id) VALUES (?, ?, 1)";
                    PreparedStatement psUsuario = connection.prepareStatement(usuarioQuery, PreparedStatement.RETURN_GENERATED_KEYS);
                    psUsuario.setString(1, correo);
                    psUsuario.setString(2, hashedPassword);
                    psUsuario.executeUpdate();

                    int idUsuario = 0;
                    ResultSet generatedKeys = psUsuario.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        idUsuario = generatedKeys.getInt(1);
                    }

                    String clienteQuery = "INSERT INTO Clientes (nombre, apellido, direccion, usuario_id) VALUES (?, ?, ?, ?)";
                    PreparedStatement psCliente = connection.prepareStatement(clienteQuery);
                    psCliente.setString(1, nombreUsuario);
                    psCliente.setString(2, apellidoUsuario);
                    psCliente.setString(3, direccion);
                    psCliente.setInt(4, idUsuario);
                    psCliente.executeUpdate();

                    connection.commit();
                    displayMessage(out, "Éxito", "Registro exitoso. ¡Bienvenido a ShoeUp!", "registro.jsp");
                } catch (SQLException e) {
                    try {
                        connection.rollback();
                    } catch (SQLException rollbackEx) {
                        rollbackEx.printStackTrace();
                    }
                    displayMessage(out, "Error", "Error en la base de datos.", "registro.jsp");
                } finally {
                    try {
                        if (connection != null) {
                            connection.setAutoCommit(true);
                            conn.Desconectar();
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            } else {
                displayMessage(out, "Error", "Error de conexión.", "registro.jsp");
            }
        }
    }

    private void displayMessage(PrintWriter out, String title, String message, String redirectPage) {
        String colorTitle = title.equals("Error") ? "text-red-700" : "text-green-700";
        String colorMessage = title.equals("Error") ? "text-red-600" : "text-green-600";
        String colorButton = title.equals("Error") ? "bg-red-700 hover:bg-red-600" : "bg-green-700 hover:bg-green-600";

        out.println("<!DOCTYPE html>");
        out.println("<html lang='es'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>" + title + "</title>");
        out.println("<link href='https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css' rel='stylesheet'>");
        out.println("</head>");
        out.println("<body class='bg-white flex items-center justify-center min-h-screen'>");
        out.println("<div class='max-w-md p-6 bg-[#C4A484] rounded-lg shadow-md text-center'>");  
        out.println("<h1 class='text-3xl font-semibold " + colorTitle + "'>" + title + "</h1>");
        out.println("<p class='" + colorMessage + " mt-4'>" + message + "</p>");
        out.println("<a href='" + redirectPage + "' class='mt-6 inline-block " + colorButton + " text-white py-2 px-4 rounded-md'>Volver</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
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
        return "Servlet para registrar nuevos usuarios y clientes en la base de datos con contraseña encriptada";
    }
}

