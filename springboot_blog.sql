-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-12-2022 a las 15:55:59
-- Versión del servidor: 8.0.31
-- Versión de PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `springboot_blog`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `authorities`
--

CREATE TABLE `authorities` (
  `id` int NOT NULL,
  `user_id` int DEFAULT NULL,
  `authority` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Volcado de datos para la tabla `authorities`
--

INSERT INTO `authorities` (`id`, `user_id`, `authority`) VALUES
(1, 1, 'ROLE_ADMIN'),
(2, 1, 'ROLE_USER'),
(4, 2, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE `categorias` (
  `id` int NOT NULL,
  `categoria` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`id`, `categoria`) VALUES
(1, 'Poemas'),
(2, 'Ficción'),
(3, 'Biografía'),
(4, 'Motivación'),
(5, 'Inspiración'),
(6, 'Deportes'),
(7, 'Recetas'),
(9, 'Desarrollo de software');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comentarios`
--

CREATE TABLE `comentarios` (
  `id` int NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `email` varchar(70) DEFAULT NULL,
  `comentario` varchar(255) DEFAULT NULL,
  `post_id` int DEFAULT NULL,
  `fecha_creacion` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Volcado de datos para la tabla `comentarios`
--

INSERT INTO `comentarios` (`id`, `nombre`, `email`, `comentario`, `post_id`, `fecha_creacion`) VALUES
(1, 'Andres', 'andresp@gmail.com', 'Concuerdo, el post es muy bueno!!!', 3, '2022-12-19'),
(2, 'Juanes', 'juanes@mail.com', 'Es una virtud saber css', 3, '2022-12-19'),
(3, 'Carmelo Valencia', 'carmelo@unisangil.edu.co', 'Me quedo con angular, que gran trabajo hicieron!!\r\n', 8, '2022-12-20'),
(4, 'Lopez', 'lopez10@hotmail.com', 'A aprender Css!!', 3, '2022-12-20'),
(5, 'Olga lov', 'olv@nuevo.com', 'Es magnifico saber css, pero puede resultar tedioso', 3, '2022-12-20'),
(6, 'Andres Felipe', 'andres@mail.com', 'Felicitaciones, ha sido un buen post', 5, '2022-12-20'),
(10, 'admin', 'admin@mail.com', 'Node Js es super potente!!', 5, '2022-12-22');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `posts`
--

CREATE TABLE `posts` (
  `id` int NOT NULL,
  `titulo` varchar(150) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `contenido` text,
  `imagen` varchar(255) DEFAULT NULL,
  `fecha_creacion` date DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Volcado de datos para la tabla `posts`
--

INSERT INTO `posts` (`id`, `titulo`, `contenido`, `imagen`, `fecha_creacion`, `status`, `category_id`, `user_id`) VALUES
(3, 'Truco Css', '<p style=\"font-family: Roboto, &quot;Trebuchet MS&quot;, &quot;Lucida Sans Unicode&quot;, &quot;Lucida Grande&quot;, &quot;Lucida Sans&quot;, Arial, sans-serif; font-size: 23.04px;\">Este artículo nos enseña a realizar una tarea sencilla pero muy útil, para lo que usaremos el lenguaje CSS como única herramienta. Se trata de recortar una línea larga, que no cabe por completo en el espacio de su contenedor, colocando puntos suspensivos \"...\", de manera que el usuario perciba visualmente que se ha realizado un recorte.</p><p style=\"font-family: Roboto, &quot;Trebuchet MS&quot;, &quot;Lucida Sans Unicode&quot;, &quot;Lucida Grande&quot;, &quot;Lucida Sans&quot;, Arial, sans-serif; font-size: 23.04px;\">El atributo que vamos a usar se llama text-overflow y el valor clave que vamos a usar es \"ellipsis\", pero además lo tenemos que usar en conjunto con el atributo overflow y un contenedor que tenga limitado su tamaño en la anchura. Al final, para que funcione, necesitas un contexto bastante definido, pero una vez lo tengas claro, aplicar este efecto será prácticamente inmediato y te ahorrarás algo de programación, aumentando también la versatilidad de tu composición.</p>\r\n\r\n<p style=\"font-family: Roboto, &quot;Trebuchet MS&quot;, &quot;Lucida Sans Unicode&quot;, &quot;Lucida Grande&quot;, &quot;Lucida Sans&quot;, Arial, sans-serif; font-size: 23.04px;\">El atributo que vamos a usar se llama text-overflow y el valor clave que vamos a usar es \"ellipsis\", pero además lo tenemos que usar en conjunto con el atributo overflow y un contenedor que tenga limitado su tamaño en la anchura. Al final, para que funcione, necesitas un contexto bastante definido, pero una vez lo tengas claro, aplicar este efecto será prácticamente inmediato y te ahorrarás algo de programación, aumentando también la versatilidad de tu composición.</p>', '984fcbdf-edc5-48c8-ad30-4230574f751e_trucocss.png', '2022-12-16', '1', 9, 1),
(5, '¿Qué es Node Js?', '<p><span style=\"font-family: Arial; font-size: 20px; background-color: rgb(255, 255, 255);\">As an asynchronous event-driven JavaScript runtime, Node.js is designed to build scalable network applications. In the following \"hello world\" example, many connections can be handled concurrently. Upon each connection, the callback is fired, but if there is no work to be done, Node.js will sleep.</span><br></p>', '5cc8411a-dc56-4b20-ab40-6f970f6fca92_nodejs.png', '2022-12-17', '1', 9, 1),
(6, '¿Cómo usar angular material?', '<p style=\"font-size: medium; line-height: 28px; color: rgba(0, 0, 0, 0.87); font-family: Roboto, &quot;Helvetica Neue Light&quot;, &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Lucida Grande&quot;, sans-serif; background-color: rgb(250, 250, 250);\">Use the Angular CLI\'s installation schematic to set up your Angular Material project by running the following command:</p><p style=\"font-size: medium; line-height: 28px; color: rgba(0, 0, 0, 0.87); font-family: Roboto, &quot;Helvetica Neue Light&quot;, &quot;Helvetica Neue&quot;, Helvetica, Arial, &quot;Lucida Grande&quot;, sans-serif; background-color: rgb(250, 250, 250);\"><strong style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0); font-family: &quot;Open Sans&quot;, Arial, sans-serif; font-size: 14px; text-align: justify; background-color: rgb(255, 255, 255);\">Lorem Ipsum</strong><span style=\"color: rgb(0, 0, 0); font-family: &quot;Open Sans&quot;, Arial, sans-serif; font-size: 14px; text-align: justify; background-color: rgb(255, 255, 255);\">&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</span><br></p>', '69fa4c3a-2f1a-481d-8fd6-0b1f1be1f17c_angular-material.png', '2022-12-17', '1', 9, 1),
(8, 'Angular Vs React', '<p style=\"margin-right: 0px; margin-bottom: 1.5em; margin-left: 0px; padding: 0px; border: 0px; font-variant-numeric: inherit; font-variant-east-asian: inherit; font-stretch: inherit; line-height: inherit; font-family: Lato, sans-serif; font-size: 18px; vertical-align: baseline; min-width: 100%; color: rgb(10, 10, 35);\"><span style=\"font-family: Helvetica;\">React es una librería de JavaScript para el desarrollo UI (Interfaz de usuario). Está gestionado por Facebook y una comunidad de desarrolladores de código abierto.</span></p><p style=\"margin-right: 0px; margin-bottom: 1.5em; margin-left: 0px; padding: 0px; border: 0px; font-variant-numeric: inherit; font-variant-east-asian: inherit; font-stretch: inherit; line-height: inherit; font-family: Lato, sans-serif; font-size: 18px; vertical-align: baseline; min-width: 100%; color: rgb(10, 10, 35);\"><span style=\"font-size: 18px;\">Angular es un framework de JavaScript de código abierto para desarrollo web y móvil. Está basado en TypeScript y está gestionada por el equipo de Angular de Google y la comunidad de desarrolladores de Angular.</span><span style=\"font-family: Helvetica;\"><br></span></p>', '77dcdfbf-ca76-4d53-8342-8e510bec7921_angular.png', '2022-12-17', '1', 9, 1),
(17, '10 consejos / trucos de Javascript que conviene saber', '<p><span style=\"color: rgb(51, 51, 51); font-family: sans-serif; font-size: 19px;\">Función muy utilizada para mostrar un saludo o bienvenida cuando el usuario carga la página y es opcional mostrar otro cuando sale de la página o cierra el navegador.</span><br style=\"color: rgb(51, 51, 51); font-family: sans-serif; font-size: 19px;\"><span style=\"color: rgb(51, 51, 51); font-family: sans-serif; font-size: 19px;\">Para eso se usan las funciones ONLOAD y ONUNLOAD.</span></p><pre style=\"font-family: monospace, monospace; break-inside: avoid; padding: 10px; margin-top: 12px; margin-bottom: 10px; font-size: 15px; line-height: 1.4em; color: rgb(51, 51, 51); word-break: break-all; overflow-wrap: break-word; background-color: rgb(245, 245, 245); border: 1px solid rgb(204, 204, 204);\"><code style=\"white-space: pre-wrap;\">&lt;script&gt;\r\nwindow.onload=function(){alert(\'Bienvenido a esta pagina\');}\r\nwindow.onunload=function(){alert(\'Vuelva en otro momento\');}\r\n&lt;/script&gt;</code></pre><p><br></p>', '41aeb385-0f5e-4777-a010-e96d66784825_javascript.jpg', '2022-12-22', '1', 9, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `id` int NOT NULL,
  `nombres` varchar(70) DEFAULT NULL,
  `apellidos` varchar(100) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(60) NOT NULL,
  `enabled` tinyint(1) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`id`, `nombres`, `apellidos`, `email`, `username`, `password`, `enabled`) VALUES
(1, 'Andrés', 'Pardo', 'admin@mail.com', 'admin', '$2a$10$52xQraLQm0wG0rMnAz.IPuNm/tFRLsVWtM.675eaUQY9aGym0DyMa', 1),
(2, 'Juan', 'Perea', 'jp@mail.com', 'juanp', '$2a$10$Ok5BIHXFJpJFoFUPp.SFYun9O0fS1sh.5yuhuOn/t5wW6yvaFh94y', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `authorities`
--
ALTER TABLE `authorities`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `user_id_UNIQUE` (`user_id`,`authority`) INVISIBLE;

--
-- Indices de la tabla `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `comentarios`
--
ALTER TABLE `comentarios`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_comentarios_posts_idx` (`post_id`);

--
-- Indices de la tabla `posts`
--
ALTER TABLE `posts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_posts_categorias_idx` (`category_id`),
  ADD KEY `fk_posts_users_idx` (`user_id`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username_UNIQUE` (`username`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `authorities`
--
ALTER TABLE `authorities`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `categorias`
--
ALTER TABLE `categorias`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `comentarios`
--
ALTER TABLE `comentarios`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `posts`
--
ALTER TABLE `posts`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `authorities`
--
ALTER TABLE `authorities`
  ADD CONSTRAINT `fk_authorities_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `comentarios`
--
ALTER TABLE `comentarios`
  ADD CONSTRAINT `fk_comentarios_posts` FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `posts`
--
ALTER TABLE `posts`
  ADD CONSTRAINT `fk_posts_categorias` FOREIGN KEY (`category_id`) REFERENCES `categorias` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_posts_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
