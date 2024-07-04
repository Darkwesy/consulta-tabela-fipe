package br.com.alura.consultafipe.principal;

import br.com.alura.consultafipe.input.InputHandler;
import br.com.alura.consultafipe.model.Data;
import br.com.alura.consultafipe.model.Vehicle;
import br.com.alura.consultafipe.model.VehicleModels;
import br.com.alura.consultafipe.services.DataConverter;
import br.com.alura.consultafipe.services.FipeService;
import br.com.alura.consultafipe.util.UrlBuilder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Principal {
    private final InputHandler inputHandler = new InputHandler();
    private final UrlBuilder urlBuilder = new UrlBuilder();
    private final FipeService apiService = new FipeService();
    private final DataConverter dataConverter = new DataConverter();

    public void runner() {
        String opcao = inputHandler.getOption();
        String url;
        String urlBrands;
        String urlModel;
        String urlYear;

        try {
            url = urlBuilder.buildUrl(opcao);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        var jsonBrands = apiService.getData(url);
        var brands = dataConverter.getDataList(jsonBrands, Data.class);
        brands.stream().sorted(Comparator.comparing(Data::code)).forEach(v -> System.out.println("Código: " + v.code() + " | Marca: " + v.name() ));

        opcao = inputHandler.getBrand();

        try {
            urlBrands = urlBuilder.buildUrlBrand(opcao, url);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        var jsonVehicles = apiService.getData(urlBrands);
        var vehiclesModels = dataConverter.getData(jsonVehicles, VehicleModels.class);

        System.out.print("\nModelos desta marca: ");
        vehiclesModels.models().stream().sorted(Comparator.comparing(Data::code)).forEach(v -> System.out.println("Código: " + v.code() + " | Modelo: " + v.name() ));

        var vehicleName = inputHandler.getCar();
        List<Data> filterModels = vehiclesModels.models().stream().filter(m -> m.name().toLowerCase().contains(vehicleName.toLowerCase())).collect(Collectors.toList());

        System.out.println("\nModelos Filtrados");
        filterModels.forEach(v -> System.out.println("Código: " + v.code() + " | Modelo: " + v.name() ));

        var modelCode = inputHandler.getCarVariants();

        try {
            urlModel = urlBuilder.buildUrlModel(modelCode, urlBrands);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        var jsonModels = apiService.getData(urlModel);
        List<Data> years = dataConverter.getDataList(jsonModels, Data.class);
        List<Vehicle> vehicles = new ArrayList<>();

        for (int i = 0; i < years.size(); i++) {
            urlYear = urlBuilder.buildUrlYear(years.get(i).code(), urlModel);
            var jsonYears = apiService.getData(urlYear);
            Vehicle vehicle = dataConverter.getData(jsonYears, Vehicle.class);
            vehicles.add(vehicle);
        }

        System.out.println("\nTodos os veículos filtrados com avaliações por ano:\n");
        vehicles.forEach(v -> System.out.println("Modelo: " + v.model() + " | Ano: " + v.year() + " | Tipo De Combustivel: " + v.fuelType() + " | Preço: " + v.price()));
    }
}
