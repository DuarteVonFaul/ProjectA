package com.sge.boavista.Utils;

import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

@Component
public class LogUtil {

    private static final DateUtils dateUtils = new DateUtils();

    public static void salvar(String mensagem) {

        try (FileWriter fileWriter = new FileWriter("log.txt", true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {

            LocalDateTime localDate = LocalDateTime.now();

            System.out.println(String.format("[%s] %s", dateUtils.formatLocalDateTimeToDatabaseStyle(localDate), mensagem));
            printWriter.println(String.format("[%s] %s", dateUtils.formatLocalDateTimeToDatabaseStyle(localDate), mensagem));

        } catch (IOException e) {
            System.err.println("Ocorreu um erro ao tentar salvar no arquivo de log: " + "log.txt");
            e.printStackTrace();
        }
    }
}
