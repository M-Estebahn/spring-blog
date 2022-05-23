package com.codeup.springblog.services;

import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class StringService {

    public String getWordInCaps(String word){

        return word.toUpperCase(Locale.ROOT);
    }

    public String limitLength(String string,int limit){
        return string.substring(0,limit)+"...";
    }

}
