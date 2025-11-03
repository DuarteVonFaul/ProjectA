package com.sge.boavista.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Utils {


    public static <T> List<List<T>> dividirComStreams(List<T> lista, int tamanho) {
        if (lista == null || lista.isEmpty() || tamanho <= 0) {
            return new ArrayList<>();
        }

        // Calcula o número de sublistas que serão criadas
        final int numeroDeSublistas = (lista.size() + tamanho - 1) / tamanho;

        return IntStream.range(0, numeroDeSublistas)
                .mapToObj(i -> {
                    int inicio = i * tamanho;
                    int fim = Math.min(inicio + tamanho, lista.size());
                    return lista.subList(inicio, fim);
                })
                .collect(Collectors.toList());
    }


}
