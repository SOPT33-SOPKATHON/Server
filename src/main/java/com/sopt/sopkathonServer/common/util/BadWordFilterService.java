package com.sopt.sopkathonServer.common.util;

import com.vane.badwordfiltering.BadWordFiltering;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BadWordFilterService {

    private final BadWordFiltering badWordFiltering;
    private final String[] symbols;

    public BadWordFilterService() throws IOException {
        badWordFiltering = new BadWordFiltering();
        symbols = new String[]{"!", "@", "#", "$", "%", "^", "&", "*", "_", " "};

        ClassPathResource resource = new ClassPathResource("badwords.txt");
        try (InputStream inputStream = resource.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            List<String> strings = readFile(reader, ", ");
            badWordFiltering.addAll(strings);
        }
    }

    public String filterString(String string) {
        return badWordFiltering.change(string, symbols);
    }
    public List<String> readFile(BufferedReader reader, String delimiter) throws IOException {
        List<String> badWords = new ArrayList<>();;
        String line;

        while ((line = reader.readLine()) != null) {
            String[] words = line.split(delimiter);
            Collections.addAll(badWords, words);
        }

        return badWords;
    }
}