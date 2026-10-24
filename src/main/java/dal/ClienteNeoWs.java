package dal;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;

/**
 * Busca na API NeoWs (Near Earth Object Web Service) da NASA os asteroides que passam perto da Terra.
 * Documentação: https://api.nasa.gov/
 */
public class ClienteNeoWs {

    private static final String ENDERECO_DO_FEED = "https://api.nasa.gov/neo/rest/v1/feed";

    // A chave fica numa variável de ambiente para não ir parar no GitHub.
    // Sem ela, usa a DEMO_KEY da NASA, que funciona com limite baixo de requisições.
    private static final String CHAVE_DA_API = System.getenv().getOrDefault("NASA_API_KEY", "DEMO_KEY");

    private final ObjectMapper leitorDeJson = new ObjectMapper();

    /**
     * Devolve o JSON com os asteroides entre as duas datas (formato AAAA-MM-DD; a API aceita até 7 dias).
     */
    public JsonNode buscarFeed(String dataInicio, String dataFim) throws IOException {
        String endereco = ENDERECO_DO_FEED + "?start_date=" + dataInicio + "&end_date=" + dataFim + "&api_key=" + CHAVE_DA_API;
        HttpURLConnection conexao = (HttpURLConnection) URI.create(endereco).toURL().openConnection();
        conexao.setRequestProperty("accept", "application/json");
        try (InputStream resposta = conexao.getInputStream()) {
            return leitorDeJson.readTree(resposta);
        }
    }
}
