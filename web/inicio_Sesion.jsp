<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Iniciar Sesión - ShoeUp</title>
  <link rel="shortcut icon" href="img/logo.png" type="image/x-icon">
  <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
  <style>
    /* Sombra suave para las letras del logo */
    .logo {
      text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.6); /* Sombra tenue para las letras */
    }

    /* Sombra del contorno del logo */
    .logo-container img {
      filter: drop-shadow(2px 2px 4px rgba(0, 0, 0, 0.2)); /* Sombra ligera al contorno del logo */
    }
  </style>
</head>
<body class="bg-gray-50 flex items-center justify-center min-h-screen">

  <div class="w-full max-w-md p-8 rounded-xl shadow-xl bg-white mx-4 md:mx-0">
    
    <div class="flex justify-center mb-8 logo-container">
      <img src="img/logo.png" alt="Logo ShoeUp" class="h-16 w-auto logo">
    </div>

    
    <h2 class="text-center text-3xl font-semibold text-yellow-900 mb-6">Iniciar Sesión</h2>

    <!-- Formulario de inicio de sesión -->
    <form action="IniciarSesion" method="POST" class="space-y-6">
      
      
      <div>
        <label for="email" class="block text-sm font-medium text-gray-700">Correo Electrónico</label>
        <input type="email" id="email" name="email" required class="w-full p-3 border border-yellow-800 rounded-md focus:outline-none focus:ring-2 focus:ring-yellow-500 bg-gray-100 text-gray-800 transition-all duration-300 placeholder-gray-500">
      </div>

      
      <div>
        <label for="password" class="block text-sm font-medium text-gray-700">Contraseña</label>
        <input type="password" id="password" name="password" required class="w-full p-3 border border-yellow-800 rounded-md focus:outline-none focus:ring-2 focus:ring-yellow-500 bg-gray-100 text-gray-800 transition-all duration-300 placeholder-gray-500">
      </div>

      
      <button type="submit" class="w-full bg-yellow-900 text-white py-3 rounded-md font-semibold hover:bg-yellow-800 transition-all duration-300 focus:outline-none focus:ring-2 focus:ring-yellow-500">Iniciar Sesión</button>

      
      <p class="text-center text-sm text-gray-600 mt-4">
        ¿No tienes una cuenta? <a href="registro.jsp" class="text-yellow-900 font-semibold hover:underline">Regístrate</a>
      </p>
    </form>
  </div>

</body>
</html>
