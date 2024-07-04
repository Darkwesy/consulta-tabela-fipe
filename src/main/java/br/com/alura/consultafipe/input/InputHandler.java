package br.com.alura.consultafipe.input;

import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner = new Scanner(System.in);

    public String getOption() {
        var menu = """
                Olá, seja bem vindo ao Consulta FIPE
                               \s
                ***Opções de tipos de veículos***
                               \s
                [1] -> Carros
                [2] -> Motos
                [3] -> Caminhões
                               \s
                Digite uma das opções para consultar:\s
               \s""";

        System.out.println(menu);
        return scanner.nextLine();
    }

    public String getBrand() {
        var menu = "Escolha uma das marcas acima pelo seu código: ";
        System.out.println(menu);
        return scanner.nextLine();
    }

    public String getCar() {
        var menu = "Escolha o veículo desejado: ";
        System.out.println(menu);
        return scanner.nextLine();
    }

    public String getCarVariants(){
        var menu = "Escolha o código da versão desejada do veículo: ";
        System.out.println(menu);
        return scanner.nextLine();
    }
}
