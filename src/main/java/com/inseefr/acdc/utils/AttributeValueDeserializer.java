package com.inseefr.acdc.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;

public class AttributeValueDeserializer extends StdDeserializer<String> {

    public AttributeValueDeserializer() {
        this(null);
    }

    public AttributeValueDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public String deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        if (node.isArray()) {
            ArrayNode arrayNode = (ArrayNode) node;
            StringBuilder sb = new StringBuilder();
            for (JsonNode n : arrayNode) {
                if (n.isTextual()) {
                    sb.append(n.asText()).append(",");
                }
            }
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
                return sb.toString();
            }
            return null;
        } else {
            return node.asText();
        }
    }
}
