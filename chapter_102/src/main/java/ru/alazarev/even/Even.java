package ru.alazarev.even;

import java.io.InputStream;
import java.util.Scanner;
/**
 * Class Even решение задачи части 002. 1. Проверить байтовый поток [#858].
 *
 * @author Aleksey Lazarev
 * @since 14.01.2019
 */
public class Even {
    /**
     * Method check even number or not.
     *
     * @param in InputStream.
     * @return Result check.
     */
    boolean isNumber(InputStream in) {
        boolean result = false;
        try (Scanner scanner = new Scanner(in)) {
            if (scanner.hasNextInt()) {
                result = scanner.nextInt() % 2 == 0;
            } else {
                throw new RuntimeException("Not number");
            }
        }
        return result;
    }

//    Реализовать сервис
//    boolean isNumber(InputStream in);
//    метод должен проверить. что в байтовом потоке записано четное число.
//    Все потоки должны быть обернуты через try-resources. Даже, если это ByteArrayInputStream
}