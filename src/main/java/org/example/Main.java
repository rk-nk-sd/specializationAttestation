package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class Main {

    public static Map<String, Long> map = new HashMap<>();
    public static long wordCount = 0;
    public static String maxLongWord = "";

    public static void main(String[] args) {
        getFileDataInfo();
        System.out.printf("Общее количество слов в файле: %s\n", wordCount);
        System.out.printf("Самое длинное слово - %s\n", maxLongWord);
        map.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed()).forEach(System.out::println);

    }

    public static void getFileDataInfo() {
        try(BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            List<String> list = Arrays.asList(reader.readLine().replaceAll("[\\s]{2,}", " ").split(" "));
            for (String s : list) {
                if (map.containsKey(s)) {
                    long tmp = map.get(s);
                    map.put(s, ++tmp);
                } else {
                    map.put(s, 1L);
                }
            }
            wordCount = list.size();

            map.entrySet().stream().filter(e -> e.getKey().length() > maxLongWord.length()).forEach(e -> maxLongWord = e.getKey());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}