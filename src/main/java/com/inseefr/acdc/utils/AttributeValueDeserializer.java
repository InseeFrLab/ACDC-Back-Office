package com.inseefr.acdc.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.util.ArrayList;

public class AttributeValueDeserializer extends JsonDeserializer<ArrayList<String>> {
    @Override
    public ArrayList<String> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JsonNode node = mapper.readTree(p);

        ArrayList<String> values = new ArrayList<>();
        if (node.isObject()) {
            String id = node.get("id").asText();
            String type = node.get("type").asText();
            String media = node.get("media").asText();
            boolean paperQuestionnaire = node.get("paperQuestionnaire").asBoolean();
            values.add("{\"id\":\"" + id + "\",\"type\":\"" + type + "\",\"media\":\"" + media + "\",\"paperQuestionnaire\":" + paperQuestionnaire + "}");
        } else if (node.isArray()) {
            for (final JsonNode element : node) {
                values.add(element.asText());
            }
        } else {
            values.add(node.asText());
        }

        return values;
    }
}

