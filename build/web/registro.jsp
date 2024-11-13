<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Registro - ShoeUp</title>
  <link rel="shortcut icon" href="img/logo.png" type="image/x-icon">
  <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-white flex items-center justify-center min-h-screen">

  <div class="w-full max-w-md p-6 mt-5 rounded-lg shadow-md mx-4" style="background-color: #C4A484;">
    <div class="flex justify-center mb-6">
      <img src="img/logo.png" alt="Logo ShoeUp" class="h-16 w-auto">
    </div>
    <h2 class="text-center text-3xl font-semibold text-yellow-900 mb-6">Regístrate</h2>
    <form action="RegistrarUsuario" method="post" accept-charset="UTF-8" class="space-y-4">
      <!-- Nombres -->
      <div>
        <label for="nombre_usuario" class="block text-sm font-medium text-gray-700">Nombres: </label>
        <input type="text" id="nombre_usuario" name="nombre_usuario" required class="w-full p-2 border border-yellow-800 rounded-md focus:outline-none focus:border-yellow-900 bg-white">
      </div>
      
      <div>
        <label for="apellido_usuario" class="block text-sm font-medium text-gray-700">Apellidos: </label>
        <input type="text" id="apellido_usuario" name="apellido_usuario" required class="w-full p-2 border border-yellow-800 rounded-md focus:outline-none focus:border-yellow-900 bg-white">
      </div>

      <!-- Correo Electrónico -->
      <div>
        <label for="correo" class="block text-sm font-medium text-gray-700">Correo Electrónico</label>
        <input type="email" id="correo" name="correo" required class="w-full p-2 border border-yellow-800 rounded-md focus:outline-none focus:border-yellow-900 bg-white">
      </div>

      <!-- Dirección -->
      <div>
        <label for="direccion" class="block text-sm font-medium text-gray-700">Dirección</label>
        <input type="text" id="direccion" name="direccion" required class="w-full p-2 border border-yellow-800 rounded-md focus:outline-none focus:border-yellow-900 bg-white">
      </div>

      <!-- Contraseña -->
      <div>
        <label for="contrase" class="block text-sm font-medium text-gray-700">Contraseña</label>
        <input type="password" id="contrase" name="contrase" required class="w-full p-2 border border-yellow-800 rounded-md focus:outline-none focus:border-yellow-900 bg-white">
      </div>

      <!-- Confirmar Contraseña -->
      <div>
        <label for="confirmar_contraseña" class="block text-sm font-medium text-gray-700">Confirmar Contraseña</label>
        <input type="password" id="confirmar_contraseña" name="confirmar_contraseña" required class="w-full p-2 border border-yellow-800 rounded-md focus:outline-none focus:border-yellow-900 bg-white">
      </div>

      <button type="submit" class="w-full bg-yellow-900 text-white py-2 rounded-md font-semibold hover:bg-yellow-800">Registrarse</button>

      <p class="text-center text-sm text-gray-700 mt-4">
        ¿Ya tienes una cuenta? <a href="inicio_Sesion.jsp" class="text-yellow-900 hover:underline">Inicia Sesión</a>
      </p>
    </form>
  </div>
</body>
</html>
