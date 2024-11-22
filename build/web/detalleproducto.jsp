<%@  page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detalles del Producto - ShoeUp</title>
    <link rel="shortcut icon" href="img/logo.png" type="image/x-icon">
    
    <style>
        body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f0e3d6; }
        header { display: flex; justify-content: space-between; align-items: center; padding: 20px 50px; background-color: #d3b59c; }
        header nav a { text-decoration: none; color: #000; margin: 0 15px; font-weight: bold; }
        header button { background-color: #d9534f; color: white; border: none; padding: 10px 15px; border-radius: 5px; cursor: pointer; }
        .product-detail { padding: 50px; background-color: #fff; display: flex; justify-content: space-between; }
        .product-detail img { max-width: 400px; height: auto; object-fit: cover; border-radius: 10px; }
        .product-info { max-width: 50%; }
        .product-info h2 { font-size: 36px; color: #000; }
        .product-info p { font-size: 20px; color: #555; }
        .product-info select { padding: 10px; margin: 10px 0; font-size: 16px; }
        .product-info button { background-color: #d9534f; color: white; border: none; padding: 10px 15px; border-radius: 5px; font-weight: bold; cursor: pointer; }
        footer { text-align: center; padding: 20px; background-color: #d3b59c; color: #000; }
    </style>
</head>
<body>
    <header>
        <div>
            <h1>ShoeUp</h1>
        </div>
        <div class="header-buttons">
            <form action="CerrarSesion" method="get" class="form-inline">
                <button type="submit" class="btn">Cerrar Sesión</button>
            </form>
        </div>
    </header>

    <section class="product-detail">
        <div class="product-info">
            <h2><%= request.getParameter("nombre") %></h2>
            <p>Precio: $<%= request.getParameter("precio") %></p>
            
            <form method="post" action="CarritoServlet">
                <input type="hidden" name="nombre" value="<%= request.getParameter("nombre") %>">
                <input type="hidden" name="imagen" value="<%= request.getParameter("imagen") %>">
                <input type="hidden" name="precio" value="<%= request.getParameter("precio") %>">
                
                <label for="talla">Selecciona una talla:</label>
                <select name="talla" id="talla">
                    <option value="36">36</option>
                    <option value="37">37</option>
                    <option value="38">38</option>
                    <option value="39">39</option>
                    <option value="40">40</option>
                    <option value="41">41</option>
                    <option value="42">42</option>
                </select>

                <button type="submit">Agregar al Carrito</button>
            </form>
        </div>
        <div>
            <img src="<%= request.getParameter("imagen") %>" alt="<%= request.getParameter("nombre") %>">
        </div>
    </section>

    <footer>
        &copy; 2024 ShoeUp. Todos los derechos reservados.
    </footer>
</body>
</html>
