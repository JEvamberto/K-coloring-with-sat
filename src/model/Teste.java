/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author jose
 */
public class Teste {
    
    
    public static void main(String[] args) {
        Modelo m = new Modelo();
        m.GerarMatrizAdjacencia();
        int count=0;
        for (int i = 0; i < m.getMatriz().length; i++) {
            for (int j = 0; j < m.getMatriz().length; j++) {
                System.out.print(m.getMatriz()[i][j]);
                
            
            }
            
            System.out.println("");
        }
    m.gerarFNC();
    }
    
    
    
}
