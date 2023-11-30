package com.sopt.sopkathonServer.common.util;

import com.vane.badwordfiltering.BadWordFiltering;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;

@Service
public class BadWordFilterService {

    private final BadWordFiltering badWordFiltering;
    private final String[] symbols;

    public BadWordFilterService() throws MalformedURLException {
        badWordFiltering = new BadWordFiltering();
        symbols = new String[]{"!", "@", "#", "$", "%", "^", "&", "*", "_", " "};

        String filePath = "badwords.txt";
        badWordFiltering.readFile(filePath, ",");
    }

    public String filterString(String string) {
        return badWordFiltering.change(string, symbols);
    }

}
