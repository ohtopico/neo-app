package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Asteroide;
import model.ContagemPorData;
import model.Ordenacao;

/**
 * Guarda e consulta os asteroides na tabela "objeto".
 */
public class AsteroideDAO {

    public void inserir(List<Asteroide> asteroides) throws SQLException {
        String sql = "INSERT INTO objeto (nome, distancia, velocidade, tamanho, risco, dataaprox) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conexao = Conexao.abrir(); PreparedStatement comando = conexao.prepareStatement(sql)) {
            for (Asteroide asteroide : asteroides) {
                comando.setString(1, asteroide.nome());
                comando.setFloat(2, asteroide.distanciaKm());
                comando.setFloat(3, asteroide.velocidadeKmH());
                comando.setFloat(4, asteroide.diametroMinimoKm());
                comando.setString(5, String.valueOf(asteroide.potencialmentePerigoso()));
                comando.setString(6, asteroide.dataDeAproximacao());
                comando.executeUpdate();
            }
        }
    }

    public List<Asteroide> listar(Ordenacao ordenacao) throws SQLException {
        String sql = "SELECT * FROM objeto ORDER BY " + ordenacao.coluna();
        List<Asteroide> asteroides = new ArrayList<>();
        try (Connection conexao = Conexao.abrir();
             PreparedStatement comando = conexao.prepareStatement(sql);
             ResultSet linhas = comando.executeQuery()) {
            while (linhas.next()) {
                asteroides.add(new Asteroide(
                        linhas.getString("nome"),
                        linhas.getFloat("distancia"),
                        linhas.getFloat("velocidade"),
                        linhas.getFloat("tamanho"),
                        Boolean.parseBoolean(linhas.getString("risco")),
                        linhas.getString("dataaprox")));
            }
        }
        return asteroides;
    }

    public List<ContagemPorData> contarPorData() throws SQLException {
        String sql = "SELECT dataaprox, COUNT(*) AS quantidade FROM objeto GROUP BY dataaprox ORDER BY dataaprox";
        List<ContagemPorData> contagens = new ArrayList<>();
        try (Connection conexao = Conexao.abrir();
             PreparedStatement comando = conexao.prepareStatement(sql);
             ResultSet linhas = comando.executeQuery()) {
            while (linhas.next()) {
                contagens.add(new ContagemPorData(linhas.getString("dataaprox"), linhas.getInt("quantidade")));
            }
        }
        return contagens;
    }

    public void apagarTodos() throws SQLException {
        try (Connection conexao = Conexao.abrir(); PreparedStatement comando = conexao.prepareStatement("TRUNCATE objeto")) {
            comando.executeUpdate();
        }
    }
}
