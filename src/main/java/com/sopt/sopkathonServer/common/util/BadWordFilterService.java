package com.sopt.sopkathonServer.common.util;

import com.vane.badwordfiltering.BadWordFiltering;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class BadWordFilterService {

    private final BadWordFiltering badWordFiltering;
    private final String[] symbols;

    public BadWordFilterService() throws IOException {
        badWordFiltering = new BadWordFiltering();
        symbols = new String[]{"!", "@", "#", "$", "%", "^", "&", "*", "_", " "};

        ClassPathResource resource = new ClassPathResource("badwords.txt");
        File file = resource.getFile();
        badWordFiltering.readFile(file, ",");
    }

    public String filterString(String string) {
        return badWordFiltering.change(string, symbols);
    }

}
