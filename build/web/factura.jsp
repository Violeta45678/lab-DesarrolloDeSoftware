<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="modelos.CarritoServlet.Producto" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Factura</title>
    <link rel="shortcut icon" href="img/logo.png" type="image/x-icon">
    <style>
        /* Estilos generales */
        body { font-family: Arial, sans-serif; margin: 0; padding: 20px; background-color: #f9f9f9; }
        h1, h2 { text-align: center; }
        table { width: 80%; margin: auto; border-collapse: collapse; margin-top: 20px; }
        table, th, td { border: 1px solid #ccc; padding: 10px; text-align: left; }
        th { background-color: #f0e3d6; }
        .total { text-align: right; font-weight: bold; padding-right: 20px; }
    </style>
</head>
<body>
    <h1>Factura</h1>
    <h2>ShoeUp - Tienda de Zapatos</h2>
    <p><strong>Cliente:</strong> <%= request.getAttribute("clienteNombre") %></p>
    <p><strong>Fecha:</strong> <%= new java.util.Date() %></p>
    <table>
        <thead>
            <tr>
                <th>Producto</th>
                <th>Imagen</th>
                <th>Precio</th>
                <th>Cantidad</th>
                <th>Total</th>
            </tr>
        </thead>
        <tbody>
            <%
                ArrayList<Producto> productos = (ArrayList<Producto>) request.getAttribute("productos");
                double totalFactura = 0;

                if (productos != null) {
                    for (Producto producto : productos) {
                        double totalProducto = producto.getPrecio() * producto.getCantidad();
                        totalFactura += totalProducto;
            %>
            <tr>
                <td><%= producto.getNombre() %></td>
                <td><img src="<%= producto.getImagen() %>" alt="<%= producto.getNombre() %>" style="width: 50px; height: 50px;"></td>
                <td>$<%= producto.getPrecio() %></td>
                <td><%= producto.getCantidad() %></td>
                <td>$<%= totalProducto %></td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="5">No hay productos en la factura</td>
            </tr>
            <%
                }
            %>
        </tbody>
        <tfoot>
            <tr>
                <td colspan="4" class="total">Total Factura:</td>
                <td>$<%= totalFactura %></td>
            </tr>
        </tfoot>
    </table>
</body>
</html>