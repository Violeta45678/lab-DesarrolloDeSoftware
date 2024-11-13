<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Iniciar Sesión - ShoeUp</title>
  <link rel="shortcut icon" href="img/logo.png" type="image/x-icon">
  <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-white flex items-center justify-center min-h-screen">

  <div class="w-full max-w-md p-6 mt-10 rounded-lg shadow-md mx-4" style="background-color: #C4A484;">
    <div class="flex justify-center mb-6">
      <img src="img/logo.png" alt="Logo ShoeUp" class="h-16 w-auto">
    </div>
    <h2 class="text-center text-3xl font-semibold text-yellow-900 mb-6">Iniciar Sesión</h2>

    <form action="IniciarSesion" method="POST" class="space-y-4">
      <div>
        <label for="email" class="block text-sm font-medium text-gray-700">Correo Electrónico</label>
        <input type="email" id="email" name="email" required class="w-full p-2 border border-yellow-800 rounded-md focus:outline-none focus:border-yellow-900 bg-white">
      </div>

      <div>
        <label for="password" class="block text-sm font-medium text-gray-700">Contraseña</label>
        <input type="password" id="password" name="password" required class="w-full p-2 border border-yellow-800 rounded-md focus:outline-none focus:border-yellow-900 bg-white">
      </div>

      <button type="submit" class="w-full bg-yellow-900 text-white py-2 rounded-md font-semibold hover:bg-yellow-800">Iniciar Sesión</button>

      <p class="text-center text-sm text-gray-700 mt-4">
        ¿No tienes una cuenta? <a href="registro.jsp" class="text-yellow-900 hover:underline">Regístrate</a>
      </p>
    </form>
  </div>

</body>
</html>

