package net.msk.scoreboard.persistence.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.msk.scoreboard.model.GameHighlight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameHighlightListConverter implements AttributeConverter<List<GameHighlight>, String> {

    private static final Logger logger = LoggerFactory.getLogger(GameHighlightListConverter.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<GameHighlight> gameHighlights) {
        String gameHighlightJson = null;
        try {
            gameHighlightJson = objectMapper.writeValueAsString(gameHighlights);
        } catch (final JsonProcessingException e) {
            logger.error("JSON writing error", e);
        }

        return gameHighlightJson;
    }

    @Override
    public List<GameHighlight> convertToEntityAttribute(String gameHighlightsJson) {
        List<GameHighlight> gameHighlights = null;
        try {
            //gameHighlights = objectMapper.readValue(gameHighlightsJson, List.class);
            gameHighlights = objectMapper.readValue(gameHighlightsJson, new TypeReference<ArrayList<GameHighlight>>() { });
        } catch (final IOException e) {
            logger.error("JSON reading error", e);
        }

        return gameHighlights;
    }
}
