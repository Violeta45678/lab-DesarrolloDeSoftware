<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Date" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - Gestión de Productos y Ventas</title>
<style>
    /* General body styles */
    body {
        font-family: Arial, sans-serif;
        background-color: #f8f8f8;
        margin: 0;
        padding: 0;
        overflow-x: hidden; /* Eliminar barra de desplazamiento horizontal */
    }

    /* Header styling */
    header {
        background-color: #2C3E50;
        color: white;
        padding: 20px;
        text-align: center;
        display: flex;
        flex-direction: column; /* Apilamos los elementos en columna */
        justify-content: center;
        align-items: center;
        position: relative; /* Necesario para posicionar el botón de cerrar sesión en la esquina */
    }

    header h1 {
        margin: 0;
    }

    header p {
        margin-top: 10px; /* Espacio entre el título y el texto */
    }

    /* Logout button positioned at the top right */
    .logout-button {
        background-color: #3498db; /* Fondo azul */
        color: white; /* Texto blanco */
        text-decoration: none;
        padding: 10px 20px;
        border-radius: 8px;
        font-size: 14px;
        font-weight: bold;
        border: 2px solid white;
        display: inline-block;
        transition: background-color 0.3s, transform 0.3s;
        text-align: center;
        position: absolute;
        top: 20px; /* Espacio desde el top */
        right: 20px; /* Espacio desde la derecha */
    }

    .logout-button:hover {
        background-color: #1d6fa5;
        transform: scale(1.05);
        border-color: #f1f1f1;
    }

    /* Main content styles */
    main {
        padding: 40px;
        display: flex;
        justify-content: space-between;
        flex-wrap: wrap;
    }

    .menu-option {
        background-color: white;
        width: 220px;
        margin: 20px;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        text-align: center;
        cursor: pointer;
        transition: transform 0.3s ease;
    }

    .menu-option:hover {
        transform: scale(1.05);
    }

    /* Footer styling */
    footer {
        text-align: center;
        padding: 20px;
        background-color: #333;
        color: white;
        position: fixed;
        width: 100%;
        bottom: 0; /* Fijar el footer al final de la página */
        left: 0;
    }

    /* Remove horizontal scrollbar */
    body, html {
        overflow-x: hidden;
    }
</style>


</head>
<body>

<header>
    <h1>Bienvenido al Dashboard</h1>
    <p>Área de administración para empleados</p>
    <div>
        <a href="cerrarSesion" class="logout-button">
            Cerrar Sesión
        </a>
    </div>
</header>


<main>
    <!-- Opciones del menú -->
    <div class="menu-option" onclick="window.location.href='/gestionProductos'">
    <h2>Gestionar Productos</h2>
    <p>Añadir, editar, actualizar o cambiar el estado de productos.</p>
</div>

<div class="menu-option" onclick="window.location.href='/ventaProductos'">
    <h2>Realizar Venta</h2>
    <p>Accede a la sección de ventas y genera facturas.</p>
</div>

    
    <!-- Filtros adicionales y resumen de acciones -->
    <div class="menu-option">
        <h2>Filtros de Búsqueda</h2>
        <p>Filtra los productos por categorías para realizar acciones más rápido.</p>
    </div>
</main>

<footer>
    <p>&copy; 2024 ShoeUp. Todos los derechos reservados.</p>
</footer>

</body>
</html>

