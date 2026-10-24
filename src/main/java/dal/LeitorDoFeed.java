package dal;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.List;
import model.Asteroide;

/**
 * Transforma o JSON da API NeoWs em uma lista de asteroides.
 */
public class LeitorDoFeed {

    public static int quantidadeInformada(JsonNode feed) {
        return feed.path("element_count").asInt();
    }

    public static List<Asteroide> lerAsteroides(JsonNode feed) {
        List<Asteroide> asteroides = new ArrayList<>();
        // near_earth_objects agrupa os asteroides por data: {"2024-07-07": [...], "2024-07-08": [...]}
        for (JsonNode asteroidesDoDia : feed.path("near_earth_objects")) {
            for (JsonNode asteroide : asteroidesDoDia) {
                asteroides.add(lerAsteroide(asteroide));
            }
        }
        return asteroides;
    }

    private static Asteroide lerAsteroide(JsonNode asteroide) {
        JsonNode aproximacao = asteroide.path("close_approach_data").path(0);
        return new Asteroide(
                asteroide.path("name").asText(),
                (float) aproximacao.path("miss_distance").path("kilometers").asDouble(),
                (float) aproximacao.path("relative_velocity").path("kilometers_per_hour").asDouble(),
                (float) asteroide.path("estimated_diameter").path("kilometers").path("estimated_diameter_min").asDouble(),
                asteroide.path("is_potentially_hazardous_asteroid").asBoolean(),
                aproximacao.path("close_approach_date").asText());
    }
}
