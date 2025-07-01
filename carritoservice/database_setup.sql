-- Script para configurar la base de datos en Laragon
-- Ejecutar este script en phpMyAdmin o HeidiSQL de Laragon

-- Crear la base de datos si no existe
CREATE DATABASE IF NOT EXISTS perfulandia_carritos_01v;

-- Usar la base de datos
USE perfulandia_carritos_01v;

-- Verificar que la base de datos se creó correctamente
SHOW DATABASES;

-- Mostrar las tablas (se crearán automáticamente con JPA)
SHOW TABLES; 