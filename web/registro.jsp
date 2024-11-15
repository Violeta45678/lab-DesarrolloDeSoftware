<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Registro - ShoeUp</title>
  <link rel="shortcut icon" href="img/logo.png" type="image/x-icon">
  <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
  <style>
    /* Sombra y borde en el logo para mejorar la visibilidad */
    .logo {
      filter: drop-shadow(0px 4px 6px rgba(0, 0, 0, 0.3)); /* Sombra tenue en el logo */
    }
  </style>
</head>
<body class="bg-gray-50 flex items-center justify-center min-h-screen">

  <div class="w-full max-w-md p-8 rounded-xl shadow-xl bg-white mx-4 md:mx-0">
    <!-- Logo con fondo y sombra tenue -->
    <div class="flex justify-center mb-8">
      <img src="img/logo.png" alt="Logo ShoeUp" class="h-16 w-auto logo">
    </div>

    <!-- Título -->
    <h2 class="text-center text-3xl font-semibold text-yellow-900 mb-6">Regístrate</h2>

    <!-- Formulario de registro -->
    <form action="RegistrarUsuario" method="POST" class="space-y-6">
      
      <!-- Campo de nombres -->
      <div>
        <label for="nombre_usuario" class="block text-sm font-medium text-gray-700">Nombres</label>
        <input type="text" id="nombre_usuario" name="nombre_usuario" required class="w-full p-3 border border-yellow-800 rounded-md focus:outline-none focus:ring-2 focus:ring-yellow-500 bg-gray-100 text-gray-800 transition-all duration-300 placeholder-gray-500">
      </div>

      <!-- Campo de apellidos -->
      <div>
        <label for="apellido_usuario" class="block text-sm font-medium text-gray-700">Apellidos</label>
        <input type="text" id="apellido_usuario" name="apellido_usuario" required class="w-full p-3 border border-yellow-800 rounded-md focus:outline-none focus:ring-2 focus:ring-yellow-500 bg-gray-100 text-gray-800 transition-all duration-300 placeholder-gray-500">
      </div>

      <!-- Campo de correo electrónico -->
      <div>
        <label for="correo" class="block text-sm font-medium text-gray-700">Correo Electrónico</label>
        <input type="email" id="correo" name="correo" required class="w-full p-3 border border-yellow-800 rounded-md focus:outline-none focus:ring-2 focus:ring-yellow-500 bg-gray-100 text-gray-800 transition-all duration-300 placeholder-gray-500">
      </div>

      <!-- Campo de dirección -->
      <div>
        <label for="direccion" class="block text-sm font-medium text-gray-700">Dirección</label>
        <input type="text" id="direccion" name="direccion" required class="w-full p-3 border border-yellow-800 rounded-md focus:outline-none focus:ring-2 focus:ring-yellow-500 bg-gray-100 text-gray-800 transition-all duration-300 placeholder-gray-500">
      </div>

      <!-- Campo de contraseña -->
      <div>
        <label for="contrase" class="block text-sm font-medium text-gray-700">Contraseña</label>
        <input type="password" id="contrase" name="contrase" required class="w-full p-3 border border-yellow-800 rounded-md focus:outline-none focus:ring-2 focus:ring-yellow-500 bg-gray-100 text-gray-800 transition-all duration-300 placeholder-gray-500">
      </div>

      <!-- Campo de confirmar contraseña -->
      <div>
        <label for="confirmar_contraseña" class="block text-sm font-medium text-gray-700">Confirmar Contraseña</label>
        <input type="password" id="confirmar_contraseña" name="confirmar_contraseña" required class="w-full p-3 border border-yellow-800 rounded-md focus:outline-none focus:ring-2 focus:ring-yellow-500 bg-gray-100 text-gray-800 transition-all duration-300 placeholder-gray-500">
      </div>

      <!-- Botón de registro -->
      <button type="submit" class="w-full bg-yellow-900 text-white py-3 rounded-md font-semibold hover:bg-yellow-800 transition-all duration-300 focus:outline-none focus:ring-2 focus:ring-yellow-500">Registrarse</button>

      <!-- Enlace para iniciar sesión -->
      <p class="text-center text-sm text-gray-600 mt-4">
        ¿Ya tienes una cuenta? <a href="inicio_Sesion.jsp" class="text-yellow-900 font-semibold hover:underline">Inicia Sesión</a>
      </p>
    </form>
  </div>

</body>
</html>
