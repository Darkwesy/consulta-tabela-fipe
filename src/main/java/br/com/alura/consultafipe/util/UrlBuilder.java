package br.com.alura.consultafipe.util;

public class UrlBuilder {
    private final String BASEURL = "https://parallelum.com.br/fipe/api/v1/";

    public String buildUrl(String option) {
        return switch (option) {
            case "1" -> BASEURL + "carros/marcas";
            case "2" -> BASEURL + "motos/marcas";
            case "3" -> BASEURL + "caminhoes/marcas";
            default -> throw new IllegalArgumentException("Opção inválida!");
        };
    }

    public String buildUrlBrand(String option, String latestUrl) {
        if (option.isEmpty()) {
            throw new IllegalArgumentException("Opção inválida!");
        }

        return latestUrl + "/" + option + "/modelos";
    };

    public String buildUrlModel(String option, String latestUrl) {
        if (option.isEmpty()) {
            throw new IllegalArgumentException("Opção inválida!");
        }
        return latestUrl + "/" + option + "/anos";
    }

    public String buildUrlYear(String year, String latestUrl) {
        if (year.isEmpty()) {
            throw new IllegalArgumentException("Opção inválida!");
        }
        return latestUrl + "/" + year;
    }
}
