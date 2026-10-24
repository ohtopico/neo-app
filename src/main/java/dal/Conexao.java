package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Abre conexões com o MySQL. Endereço, usuário e senha podem ser trocados por variáveis de ambiente.
 */
public class Conexao {

    private static final String ENDERECO = configuracao("NEO_BANCO_URL", "jdbc:mysql://localhost/projetojulia");
    private static final String USUARIO = configuracao("NEO_BANCO_USUARIO", "root");
    private static final String SENHA = configuracao("NEO_BANCO_SENHA", "root");

    public static Connection abrir() throws SQLException {
        return DriverManager.getConnection(ENDERECO, USUARIO, SENHA);
    }

    private static String configuracao(String variavel, String padrao) {
        return System.getenv().getOrDefault(variavel, padrao);
    }
}
