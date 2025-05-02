package com.example.pratica1325;

import java.io.File;

public class Main {
    public static void main(String [] args){
        //Creo il file e salvo tutto in memoria
        GestoreCSV gestoreCSV =new GestoreCSV(new File("pratica13-25/src/main/resources/com/example/pratica1325/dortenzio.csv"));
        //Aggiorno o creo il file record.csv
        gestoreCSV.aggiornaFile();
        //Aggiorno html
        gestoreCSV.aggiornaHtml();
    }
}
