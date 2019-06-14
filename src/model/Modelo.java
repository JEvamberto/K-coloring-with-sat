package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Modelo {
    
    private FileReader ler;
    private int [][] matriz;
    private int vertices;
    
  
    public Modelo(){
   
    
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
                    int tamMatriz=Integer.parseInt(split[2]);
                    this.makeMatriz(tamMatriz+1);//Solução para um problema o +1
                    this.vertices=tamMatriz;
                }
                
                 if (split[0].equals("e")) {
                     int v1=Integer.parseInt(split[1]);
                     int v2=Integer.parseInt(split[2]);
                     this.addAdjacencia(v1, v2);
                   
                }
                
                

                
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
  private void makeMatriz(int tam){
        this.matriz= new int[tam][tam]; 
    }
  private void addAdjacencia(int v1, int v2){
      this.matriz[v1][v2]=1;
      this.matriz[v2][v1]=1;
  }

    public int[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(int[][] matriz) {
        this.matriz = matriz;
    }
    
    
    
    public void gerarFNC(){
        int cor=1;
        if (cor==1) {
            String fnc = "";
        for (int i = 1; i <= vertices; i++) {
             fnc=""+i+cor+"\n";
             System.out.print(fnc);//Escrever no arquivo
            
        }
         
            
            for (int i = 0; i < this.matriz.length; i++) {
                for (int j = 0; j < this.matriz.length; j++) {
                    if (this.matriz[i][j]==1) {
                        fnc=""+(i*-1)+" "+(j*-1)+"\n";
                        System.out.print(fnc);
                        
                        /*vou ṕegar um vertice colorir 11 e comparar com seus vizinhos -21*/
                    }
                }
            }
         
            
        }
       
        
        
    
    }
    
}
