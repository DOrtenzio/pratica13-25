package com.example.pratica1325;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class GestoreCSV {
    private ArrayList<Record> contenutoFile;
    private ArrayList<String> intestazione;
    private ArrayList<Record> medie;

    public GestoreCSV(File urlFile) {
        contenutoFile=new ArrayList<Record>();
        intestazione=new ArrayList<String>();
        medie=new ArrayList<Record>();

        caricaInMemoria(urlFile);
    }

    //getter e setter
    public ArrayList<Record> getContenutoFile() { return contenutoFile; }
    public void setContenutoFile(ArrayList<Record> contenutoFile) { this.contenutoFile = contenutoFile; }
    public ArrayList<String> getIntestazione() { return intestazione; }
    public void setIntestazione(ArrayList<String> intestazione) { this.intestazione = intestazione; }

    //Caricamento
    private void caricaInMemoria(File urlFile){
        try (BufferedReader rd=new BufferedReader(new FileReader(urlFile))){
            String s=rd.readLine(); //Non salvo in memoria
            intestazione.add(s.split(";")[0]);
            intestazione.add(s.split(";")[1]);
            intestazione.add(s.split(";")[2]);
            while ((s=rd.readLine())!=null){
                String [] sSplit=s.split(";");
                if (sSplit.length==3) {
                    contenutoFile.add(new Record(Integer.parseInt(sSplit[0]), sSplit[1], Double.parseDouble(sSplit[2])));
                    ricercaAggiornaMedia(new Record(0,sSplit[1], Double.parseDouble(sSplit[2])));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //metodi per modellare
    public boolean addRecord(Record record){ return this.contenutoFile.add(record); }
    public boolean removeRecord(Record record){ return this.contenutoFile.remove(record); }
    public int ricercaRecord(Record record){ return this.contenutoFile.indexOf(record); }

    //Metodo ricerca ed aggiorna
    private void ricercaAggiornaMedia(Record rg){
        int index=medie.indexOf(rg);
        if (index!=-1){ //regione già presente quindi calcolo la media
            Record r=medie.get(index);
            r.setPercDiffusione((r.getPercDiffusione()+rg.getPercDiffusione())/2);
        }else { //regione nuova
            medie.add(rg);
        }
    }

    //Aggiornamento dei file
    public void aggiornaFile(){
        Collections.sort(medie); //ordino
        try (PrintWriter pw=new PrintWriter(new FileWriter("pratica13-25/src/main/resources/com/example/pratica1325/report.csv"))){
            pw.println(intestazione.get(1)+";"+intestazione.get(2));
            for (Record r:medie){
                pw.println(r.simileToString());
            }
            pw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Html
    public void aggiornaHtml(){
        Collections.sort(medie); //ordino
        try (PrintWriter pw=new PrintWriter(new FileWriter("pratica13-25/src/main/resources/com/example/pratica1325/report.html"))){
            pw.print("<!DOCTYPE html>\n<html lang=\"it\">\n<head>\n<title>CSV VISUALIZZATO</title>\n" +
                            "<style>\n" +
                            "body {\n" +
                            "    font-family: Arial, sans-serif;\n" +
                            "    background-color: #f4f4f4;\n" +
                            "    display: flex;\n" +
                            "    flex-direction: column;\n" +
                            "    justify-content: center;\n" +
                            "}\n" +
                            "td {\n" +
                            "  text-align: center;\n" +
                            "}\n"+
                            "</style>\n" +
                            "</head>\n<body>\n<h1>Tabella dei valori contenuti</h1>\n<h6>A seguire si ritrova il contenuto del file csv.</h6>\n");
            pw.print("<table style=\"border: 1px solid #FF0000;\">\n<tr>\n<th>"+intestazione.get(1)+"</th>\n<th>"+intestazione.get(2)+"</th>\n</tr>");
            for (Record r:medie){
                pw.println("<tr>");
                pw.println("<td>"+r.simileToString().split(";")[0]+"</td>");
                pw.println("<td>"+r.simileToString().split(";")[1]+"</td>");
                pw.println("</tr>");
            }
            pw.println("</table>\n</body>\n</html>");
            pw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
