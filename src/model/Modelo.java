package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Modelo {

    private FileReader ler;
    private FileWriter fileWrite;
    private int[][] matriz;
    private int vertices;

    public Modelo() {

    }

    public void GerarMatrizAdjacencia() {

        try {
            ler = new FileReader(new File("/home/jose/NetBeansProjects/K-coloring-with-sat/K-coloring-with-sat/myciel3.col"));
            BufferedReader lerArq = new BufferedReader(ler);
            String s;
            String[] split;

            while ((s = lerArq.readLine()) != null) {
                split = s.split(" ");

                if (split[0].equals("p")) {
                    int tamMatriz = Integer.parseInt(split[2]);
                    this.makeMatriz(tamMatriz + 1);//Solução para um problema o +1
                    this.vertices = tamMatriz;
                }

                if (split[0].equals("e")) {
                    int v1 = Integer.parseInt(split[1]);
                    int v2 = Integer.parseInt(split[2]);
                    this.addAdjacencia(v1, v2);

                }

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void makeMatriz(int tam) {
        this.matriz = new int[tam][tam];
    }

    private void addAdjacencia(int v1, int v2) {
        this.matriz[v1][v2] = 1;
        this.matriz[v2][v1] = 1;
    }

    public int[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(int[][] matriz) {
        this.matriz = matriz;
    }

    public void gerarFNC(String nomeArquivo) {
        int cor = 4;
        int clausula=0;
        int variaveis=0;
        String inicio="p cnf";
        ArrayList list = new <String>ArrayList();
        File file= new File("Instancias");
        File arquivos[]= file.listFiles();
        
        try {
            fileWrite = new FileWriter(new File(nomeArquivo+".cnf"), true);

            if (cor == 1) {
                String fnc = "";
                for (int i = 1; i <= vertices; i++) {
                    fnc = "" + i + cor + " 0\n";
                    /* System.out.print(fnc);//Escrever no arquivo*/
                    variaveis++;
                    clausula++;
                    list.add(fnc);
                   // fileWrite.write(fnc);
           

                }
                       

                for (int i = 0; i < this.matriz.length; i++) {
                    for (int j = 0; j < this.matriz.length; j++) {
                        if (this.matriz[i][j] == 1) {
                            fnc = "" + (i * -1) + "1" + " " + (j * -1) + "1" + " 0\n";
                            /*   System.out.print(fnc);//escrever no arquivo*/
                            clausula++;
                            list.add(fnc);
                            // fileWrite.write(fnc);

                        }
                    }
                }
                
                inicio=inicio+" "+variaveis+" "+clausula+"\n"; 
                list.add(0, inicio);
               //fileWrite.flush();
              //  fileWrite.close();

            } else if (cor > 1) {
                String fnc = "";

                for (int i = 1; i <= this.vertices; i++) {
                    for (int j = 1; j <= cor; j++) {

                        fnc = fnc + i + j + " ";
                        variaveis++;
                      
                    }
                    fnc=fnc+"0\n";
                    
                    //System.out.println(fnc);
                    clausula++;
                    list.add(fnc);
                      //fileWrite.write(fnc);
                    fnc = "";
                }
                fnc = "";
                String auxFnc;
                for (int i = 1; i <= this.vertices; i++) {

                    for (int j = 1; j <= cor; j++) {
                        fnc = "-" + i + j;
                        for (int k = 1; k <= cor; k++) {
                            if (j != k) {
                                fnc = fnc + " " + "-" + i + k + " 0\n";
                                /*System.out.println(fnc);Escrever no arquivo*/
                                clausula++;
                                list.add(fnc);
                               // fileWrite.write(fnc+"\n");
                                
                            }
                            fnc = "";
                            fnc = "-" + i + j;

                        }
                    }

                }

                String fnc1 = "";

                for (int i = 0; i < this.matriz.length; i++) {
                    for (int j = 0; j < this.matriz.length; j++) {
                        if (this.matriz[i][j] == 1) {

                            for (int k = 1; k <= cor; k++) {
                                fnc1 = "-" + i + k;
                                for (int l = 1; l <= cor; l++) {
                                    if (k == l) {
                                        fnc1 = fnc1 + " " + "-" + j + l + " 0\n";
                                        clausula++;
                                        list.add(fnc1);
                                     //  fileWrite.write(fnc1+"\n");
                                    }

                                }
                                fnc1 = "";
                            }
    
                        }
                    }
                }
             
                inicio=inicio+" "+variaveis+" "+clausula+"\n"; 
                list.add(0, inicio);
            }
           
            
            for(int i=0; i<list.size(); i++){
               
                this.fileWrite.write((String) list.get(i));
                
            }
               this.fileWrite.flush();
               this.fileWrite.close();

        } catch (IOException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
