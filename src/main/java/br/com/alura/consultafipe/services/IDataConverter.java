package br.com.alura.consultafipe.services;

import java.util.List;

public interface IDataConverter {
    <T> T getData(String json, Class<T> clazz);
    <T> List<T> getDataList(String json, Class<T> clazz);
}
