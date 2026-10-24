# Asteroides próximos da Terra

Programa em Java (Swing) que consulta a API **Asteroids - NeoWs** da NASA, guarda os asteroides que vão passar perto da Terra num banco MySQL e mostra:

- quantos objetos foram encontrados no período;
- um gráfico com a quantidade de asteroides por data;
- os seis mais próximos e a tabela completa, que pode ser ordenada por distância, tamanho, velocidade ou risco.

Ao abrir, o programa pesquisa os próximos 7 dias. Para outro período, digite as datas (AAAA-MM-DD, até 7 dias) e clique em **Pesquisar**.

## Tela
<img src="Ilustrativo.PNG" alt="Tela do programa com o contador de objetos próximos, a data e hora, a tabela das próximas aproximações e um gráfico de barras com a quantidade de asteroides por data">

## Como executar
1. Crie o banco rodando o script `criacaobdProjetoNEO.sql` no MySQL.
2. Crie sua chave gratuita em https://api.nasa.gov/ e coloque-a na variável de ambiente `NASA_API_KEY`. Sem a variável, o programa usa a `DEMO_KEY` da NASA, que tem um limite baixo de consultas por hora.
3. Se o seu MySQL não usa o usuário e a senha `root`, defina também `NEO_BANCO_USUARIO` e `NEO_BANCO_SENHA` (e `NEO_BANCO_URL`, se o banco não estiver em `localhost`).
4. Execute com `mvn compile exec:java` ou abra o projeto no NetBeans.

## Organização do código
| Pacote | Classe | O que faz |
|---|---|---|
| `dal` | `ClienteNeoWs` | chama a API da NASA |
| `dal` | `LeitorDoFeed` | transforma o JSON da API em asteroides |
| `dal` | `AsteroideDAO` | grava e consulta os asteroides no banco |
| `dal` | `Conexao` | abre a conexão com o MySQL |
| `model` | `Asteroide`, `ContagemPorData`, `Ordenacao` | dados usados pela tela |
| `view` | `FrmProjeto` | tela principal |

O arquivo `feed.json` é um exemplo de resposta da API, de 07/07/2024 a 14/07/2024.

## Tecnologias
Java 21, Swing, Maven, MySQL, Jackson e JFreeChart.

## Autora
Júlia Roberta Quoos Alves
