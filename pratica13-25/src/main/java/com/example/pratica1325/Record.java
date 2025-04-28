package com.example.pratica1325;

import java.util.Objects;

public class Record implements Comparable<Record>{
    private int anno;
    private String regione;
    private double percDiffusione;

    //Costruttore
    public Record(int anno, String regione, double percDiffusione){
        this.anno = anno;
        this.regione = regione;
        this.percDiffusione = percDiffusione;
    }

    //Getter e setter
    public int getAnno() { return anno; }
    public void setAnno(int anno) { this.anno = anno; }
    public String getRegione() { return regione; }
    public void setRegione(String regione) { this.regione = regione; }
    public double getPercDiffusione() { return percDiffusione; }
    public void setPercDiffusione(double percDiffusione) { this.percDiffusione = percDiffusione; }

    //Equals e to string
    @Override
    public String toString() {
        return  anno + regione + percDiffusione;
    }

    public String simileToString() {
        return  regione+";"+ percDiffusione;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Record) {
            Record record = (Record) o;
            return anno == record.getAnno() && percDiffusione==record.getPercDiffusione() && regione.equalsIgnoreCase(record.getRegione());
        }
        return false;
    }

    //Compare to
    @Override
    public int compareTo(Record o) {
        if (percDiffusione==o.getPercDiffusione()) return 0;
        else if (percDiffusione>o.getPercDiffusione()) return 1;
        else return -1;
    }

}
