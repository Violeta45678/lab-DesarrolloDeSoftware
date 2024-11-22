<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ShoeUp - Tienda de Zapatos</title>
    <link rel="shortcut icon" href="img/logo.png" type="image/x-icon">
    <style>
        body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f0e3d6; }
        header { display: flex; justify-content: space-between; align-items: center; padding: 20px 50px; background-color: #d3b59c; }
        header nav a { text-decoration: none; color: #000; margin: 0 15px; font-weight: bold; }
        header button { background-color: #d9534f; color: white; border: none; padding: 10px 15px; border-radius: 5px; cursor: pointer; }
        .hero { display: flex; justify-content: center; align-items: center; padding: 50px; background-color: #e6ccb2; text-align: center; }
        .hero-text { display: flex; flex-direction: column; justify-content: center; align-items: center; text-align: center; }
        .hero-text h1 { font-size: 48px; color: #000; }
        .hero-text img { margin-top: 20px; max-width: 100px; height: auto; }
        .products, .offers { padding: 50px; background-color: #fff; }
        .products h2, .offers h2 { text-align: center; color: #000; margin-bottom: 30px; }
        .product-grid, .offer-grid { display: grid; grid-template-columns: repeat(auto-fit, minmax(250px, 1fr)); gap: 20px; }
        .product-card, .offer-card { border: 1px solid #ccc; border-radius: 8px; overflow: hidden; background-color: #f9f9f9; text-align: center; padding: 15px; }
        .product-card img, .offer-card img {
            width: 100%;
            height: 200px;
            object-fit: cover;
            transition: transform 0.3s ease-in-out;
        }
        .product-card img:hover, .offer-card img:hover {
            transform: scale(1.1);
        }
        .product-card h3, .offer-card h3 { margin: 10px 0; font-size: 18px; color: #000; }
        .product-card p, .offer-card p { margin: 5px 0; font-size: 16px; color: #555; }
        .offer-card p { color: #d9534f; font-weight: bold; }
        footer { text-align: center; padding: 20px; background-color: #d3b59c; color: #000; }
        .header-buttons {
            display: flex;
            gap: 15px;
        }
        .btn {
            background-color: #d9534f;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 5px;
            text-decoration: none;
            display: inline-flex;
            align-items: center;
            justify-content: center;
            font-weight: bold;
            cursor: pointer;
            text-align: center;
        }
        .btn:hover {
            background-color: #c9302c;
        }
        .form-inline {
            margin: 0;
        }
    </style>
</head>
<body>
    <header>
        <div>
            <h1>ShoeUp</h1>
        </div>
        <div class="header-buttons">
            <a href="Carrito.jsp" class="btn">Ver Carrito</a>
            <form action="CerrarSesion" method="get" class="form-inline">
                <button type="submit" class="btn">Cerrar Sesión</button>
            </form>
        </div>
    </header>

    <section class="hero">
        <div class="hero-text">
            <h1>Bienvenido a ShoeUp</h1>
            <img src="img/logo.png" alt="Zapatería">
        </div>
    </section>

    <section class="products">
        <h2>Nuestros Productos</h2>
        <div class="product-grid">
            <%-- Clase Producto para productos dinámicos --%>
            <%
                class Producto {
                    String nombre, imagen;
                    double precio;

                    Producto(String nombre, String imagen, double precio) {
                        this.nombre = nombre;
                        this.imagen = imagen;
                        this.precio = precio;
                    }
                }

                Producto[] productos = {
                    new Producto("Botines Elegantes", "img/botascaballero.jpeg", 59.99),
                    new Producto("Zapatillas Deportivas", "img/zapatosdeportes.jpeg", 39.99),
                    new Producto("Sandalias de Verano", "img/zandaliasverano.jpeg", 29.99),
                    new Producto("Zapatos Casual", "img/calzado casual.jpeg", 49.99),
                    new Producto("Botas de Invierno", "img/nieve .jpeg", 79.99),
                    new Producto("Mocasines Clásicos", "img/zapatomocasines.jpeg", 44.99),
                    new Producto("Dama ", "img/zapatos dama.jpeg", 45.99),
                    new Producto("Botas Caballero", "img/botatas.jpeg", 50.99),
                    new Producto(" Clásicos Dama", "img/botasdama.jpeg", 44.99),
                    new Producto("Zapatilla Casuales ", "img/casual.jpeg", 44.99)
                };

                for (Producto producto : productos) {
                
            %>
            <div class="product-card">
                <img src="<%= producto.imagen %>" alt="<%= producto.nombre %>">
                <h3><%= producto.nombre %></h3>
                <p>Precio: $<%= producto.precio %></p>
                
                

                <%-- Botón para ver detalles del producto --%>
                <a href="detalleproducto.jsp?nombre=<%= producto.nombre %>&precio=<%= producto.precio %>&imagen=<%= producto.imagen %>">
                    <button class="btn">Ver Detalles</button>
                </a>
            </div>
            <% } %>
        </div>
    </section>

    <section class="offers">
        <h2>Ofertas Especiales</h2>
        <div class="offer-grid">
            <%-- Ofertas especiales dinámicas --%>
            <%
                Producto[] ofertas = {
                    new Producto("Zapatillas Running", "img/deportivos.jpeg", 49.99),
                    new Producto("Caballero", "img/caballeros.jpeg", 19.99),
                    new Producto("Clásicos", "img/dama.jpg", 34.99)
                };

                for (Producto oferta : ofertas) {
            %>
            <div class="offer-card">
                <img src="<%= oferta.imagen %>" alt="<%= oferta.nombre %>">
                <h3><%= oferta.nombre %></h3>
                <p>¡Oferta: $<%= oferta.precio %>!</p>
            </div>
            <% } %>
        </div>
    </section>

    <footer>
        &copy; 2024 ShoeUp. Todos los derechos reservados.
    </footer>
</body>
</html>
