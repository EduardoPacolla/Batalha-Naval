/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho01;

import java.util.Scanner;

/**
 *
 * @author Notebook
 */
public class Jogador {
    Scanner sc = new Scanner(System.in);
    String Jogador1, Jogador2;
    
    void obterNomesJogadores(){
            System.out.println("Digite o nome do Jogador 01: ");
            Jogador1 = sc.nextLine();
            System.out.println("Digite o nome do Jogador 02: ");
            Jogador2 = sc.nextLine();
    
    }
}
