package com.example.veiculos.util;


import android.util.Base64;

public class Base64Custom {

    public static String codificar(String s){
        return Base64.encodeToString(s.getBytes(),Base64.DEFAULT).toString().replaceAll("(\\n|\\r)","");
    }

    public static String decodificar(String s){
        return Base64.decode(s.getBytes(),Base64.DEFAULT).toString();
    }
}
