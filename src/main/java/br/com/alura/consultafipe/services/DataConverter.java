package br.com.alura.consultafipe.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class DataConverter implements IDataConverter {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T getData(String json, Class<T> tclass) {
        try {
            return mapper.readValue(json, tclass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> getDataList(String json, Class<T> tclass) {
        CollectionType list = mapper
                .getTypeFactory()
                .constructCollectionType(List.class, tclass);

        try {
            return mapper.readValue(json, list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
