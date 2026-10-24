-- Cria o banco e a tabela usados pelo programa (rodar uma vez no MySQL)
CREATE DATABASE IF NOT EXISTS projetojulia;

USE projetojulia;

DROP TABLE IF EXISTS objeto;
CREATE TABLE objeto (
    id int auto_increment primary key,
    nome varchar(100),
    distancia float,       -- distância da Terra na aproximação, em km
    velocidade float,      -- velocidade relativa, em km/h
    tamanho float,         -- diâmetro mínimo estimado, em km
    risco varchar(20),     -- "true" se a NASA classifica como potencialmente perigoso
    dataaprox varchar(15)  -- data da aproximação (AAAA-MM-DD)
);
