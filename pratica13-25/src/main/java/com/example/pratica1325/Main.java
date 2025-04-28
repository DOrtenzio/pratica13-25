package com.example.pratica1325;

import java.io.File;

public class Main {
    public static void main(String [] args){
        //Creo il file e salvo tutto in memoria
        FileCSV fileCSV=new FileCSV(new File("src/main/resources/com/example/pratica1325/dortenzio.csv"));
        //Aggiorno o creo il file record.csv
        fileCSV.aggiornaFile();
    }
}
