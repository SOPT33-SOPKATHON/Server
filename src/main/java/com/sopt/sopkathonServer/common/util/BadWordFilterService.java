package com.sopt.sopkathonServer.common.util;

import com.vane.badwordfiltering.BadWordFiltering;
import org.springframework.stereotype.Service;

@Service
public class BadWordFilterService {

    private final BadWordFiltering badWordFiltering;
    private final String[] symbols;

    public BadWordFilterService() {
        badWordFiltering = new BadWordFiltering();
        symbols = new String[]{"!", "@", "#", "$", "%", "^", "&", "*", "_", " "};

        String url = "https://github.com/SOPT33-SOPKATHON/Server/files/13509831/badword.txt";
        badWordFiltering.readURL(url, ",");
    }

    public String filterString(String string) {
        return badWordFiltering.change(string, symbols);
    }

}
