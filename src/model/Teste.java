/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author jose
 */
public class Teste {

    public static void main(String[] args) {
        Modelo m = new Modelo();
        File file = new File("Instancias");
        File arquivos[] = file.listFiles();
        System.out.println("Escolha o grafo:");
        for (int i = 0; i < arquivos.length; i++) {
            System.out.println(i + "-" + arquivos[i].getName());

        }
        int es = 0;
        String esc = JOptionPane.showInputDialog("Escolha o grafo pelo número ao lado esquerdo!");
        try {
            es = Integer.parseInt(esc);
        } catch (NumberFormatException e) {
            System.out.println("Número inválido");
        }
        Process processo;
        int menorCor=-1;
        boolean isSatisfativel=false;
        if (es >= 0 && es < arquivos.length) {
            m.GerarMatrizAdjacencia(arquivos[es]);
            long tempoInicio = System.currentTimeMillis();
           for (int i = 1; i <=m.getMaxCor(); i++) {
            
                m.gerarFNC(arquivos[es], i);

                try {
                    processo = Runtime.getRuntime().exec("glucose "+arquivos[es].getName()+".cnf");
                    BufferedReader in = new BufferedReader(new InputStreamReader(processo.getInputStream()));

                    String line;
                    
                    while ((line = in.readLine()) != null) {
                        if (line.equals("s SATISFIABLE")) {
                            System.out.println("É satisfazível");
                            System.out.println(line);
                            isSatisfativel=true;
                        } else {
                        
                           // System.out.println(line);
                            isSatisfativel=false;
                        }
                    }
                    processo.waitFor();
                } catch (IOException ex) {
                    Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if(isSatisfativel){
                    menorCor=i;
                   break;
                }
                

            }
            long tempoFim=System.currentTimeMillis()-tempoInicio;
            
            double segundos=(double)tempoFim;
            String arquivo=arquivos[es].getName()+".cnf";
           
            imprimirArquivo(arquivo);
            
            System.out.println("Quantidade minima de cores:"+menorCor);
          
             System.out.printf("%.3f ms%n", (tempoFim) / 1000d);




        }

    }
    
    public static void imprimirArquivo(String nomeArquivo){
        File file = new File(nomeArquivo);
        try {
            FileReader ler = new FileReader(file);
            
            BufferedReader lerArq = new BufferedReader(ler);
            
            
              String s;
            String[] split;

            while ((s = lerArq.readLine()) != null) {
            
                System.out.println(s);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
