package DAO;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import javax.json.stream.JsonGenerationException;

public class JSONManager {

    ObjectMapper objectMapper = new ObjectMapper();

    public <T> T parseJson(String json, Class<T> targetType) throws Exception {
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        return objectMapper.readValue(json, targetType);
    }

    public <T> String serializeJson(T o) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        String stringaJSON = "";
        try {
            stringaJSON = mapper.writeValueAsString(o);
        } catch (JsonGenerationException e) {
            System.out.println(e.getMessage());
        } catch (JsonMappingException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return stringaJSON;
    }
}

