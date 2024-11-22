<%@ page import="java.util.ArrayList" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="modelos.CarritoServlet.Producto" %>

<%
    
    ArrayList<Producto> carrito = (ArrayList<Producto>) session.getAttribute("carrito");
    if (carrito == null) {
        carrito = new ArrayList<>();
    }
%>

<h2>Tu Carrito</h2>

<% if (carrito.isEmpty()) { %>
    <p>Tu carrito está vacío. ¡Añade productos para continuar!</p>
<% } else { %>
    <table>
        <tr>
            <th>Producto</th>
            <th>Imagen</th>
            <th>Precio</th>
            <th>Cantidad</th>
            <th>Total</th>
        </tr>
        <% for (Producto p : carrito) { %>
            <tr>
                <td><%= p.getNombre() %></td>
                <td><img src="<%= p.getImagen() %>" alt="<%= p.getNombre() %>" width="100"></td>
                <td>$<%= p.getPrecio() %></td>
                <td><%= p.getCantidad() %></td>
                <td>$<%= p.getCantidad() * p.getPrecio() %></td>
            </tr>
        <% } %>
    </table>
    <p>Total: $<%= carrito.stream().mapToDouble(p -> p.getCantidad() * p.getPrecio()).sum() %></p>
<% } %>

<a href="factura.jsp">Proceder al Pago</a>
