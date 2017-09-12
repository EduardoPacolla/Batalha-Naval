/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho01;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Notebook
 */
public class Tabuleiro {
    Scanner sc = new Scanner(System.in);
    int altura, largura;
    int tabuleiroJogador1[][], tabuleiroJogador2[][];
    
    int limiteNavios;
    int qtdNavios;
    int naviosJogador1, naviosJogador2;
    
    void calcularLimiteNavios(){
        limiteNavios = (altura * largura) / 3;
    }
    
    void QtdNaviosJogo(){
        do {
        System.out.println("Digite a quantidade de navios: ");
        System.out.println("Máximo: " + limiteNavios + " navios");
        qtdNavios = sc.nextInt();
        } while (( qtdNavios > limiteNavios) || ( qtdNavios < 1));
    }
    
    void iniciarContadoresNaviosJogadores(){
     naviosJogador1 = qtdNavios;
     naviosJogador2 = qtdNavios;
    }
    
    void tamanhoTabuleiro(){
        System.out.println("Digite a altura do tabuleiro: ");
        altura = sc.nextInt();
        System.out.println("Digite a largura do tabuleiro: ");
        largura = sc.nextInt();
    }
    
    int[][] retornarNovoTabuleiroVazio() {
        return new int[altura][largura];
    }
    
    void iniciandoTamanhosTabuleiros() {
        tabuleiroJogador1 = retornarNovoTabuleiroVazio();
        tabuleiroJogador2 = retornarNovoTabuleiroVazio();
    }
    
    void inserirNaviosTabuleirosJogadores(){
        tabuleiroJogador1 = retornarTabuleiroNavios();
        tabuleiroJogador2 = retornarTabuleiroNavios();
    }
    
    int[][] retornarTabuleiroNavios(){
    int novoTabuleiro[][] = retornarNovoTabuleiroVazio();
        int qtdRestanteNavios = qtdNavios;
        int x= 0, y= 0;
        Random numeroAleatorio = new Random();
        do {
            x = 0;
            y = 0;
            for(int[] linha : novoTabuleiro) {
                for (int coluna : linha) {
                    if (numeroAleatorio.nextInt(100) <= 10) {
                        if(coluna == 0) {
                            novoTabuleiro[x][y] = 1;
                            qtdRestanteNavios--;
                            break;
                        }
                        if(qtdRestanteNavios < 0) {
                            break;
                        }
                    }
                    y++;
                }
                y = 0;
                x++;
                if(qtdRestanteNavios <= 0) {
                    break;
                }  
            }
        } while (qtdRestanteNavios > 0);
        return novoTabuleiro;
    }
    
    void exibirTabuleirosJogadores(String Jogador1, String Jogador2){
        exibirTabuleiro(Jogador1, tabuleiroJogador1, true);
        exibirTabuleiro(Jogador2, tabuleiroJogador2, false);
    }
    
    void exibirTabuleiro(String nomeJogador, int [][] tabuleiro, boolean seuTabuleiro) {
        System.out.println("|----- "+ nomeJogador + " -----|");
        exibirNumerosColunasTabuleiro();
        String linhaDoTabuleiro = "";
        char letraDaLinha = 65;
        for(int[] linha : tabuleiro) {
             linhaDoTabuleiro = (letraDaLinha++) + " |";
            for (int coluna : linha) {
                switch(coluna) {
                    case 0 : // Vazio ou sem ação
                        linhaDoTabuleiro += " |";
                        break;
                    case 1 : // Navio
                        if (seuTabuleiro) {
                            linhaDoTabuleiro += "N|";
                            break;
                        } else {
                            linhaDoTabuleiro += " |";
                            break;
                        }
                    case 2 : // Erro
                        linhaDoTabuleiro += "X|";
                        break;
                        
                    case 3 : // Acertou
                        linhaDoTabuleiro += "O|";
                        break; 
                }
            }
            System.out.println(linhaDoTabuleiro);
        }
    }
    
    void exibirNumerosColunasTabuleiro() {
        int numeroDaColuna = 1;
        String numeroDoTabuleiro = "   ";
        
        for(int i = 0; i < largura; i++) {
            numeroDoTabuleiro += (numeroDaColuna++) + " ";
        }
        System.out.println(numeroDoTabuleiro);
    }

    
    String valorDigitadoJogador(){
        System.out.println("Digite a posição do seu tiro:");
        return sc.next();
    }
    
    boolean verificarPosicoes(int[] posicoes){
        boolean retorno = true;
            if (posicoes[0] > altura){
                //erro
                System.out.println("A posição das letras não pode ser maior que : " + (char) (altura + 64));
                retorno = false;
            }
            if (posicoes[1] > largura -1){
                System.out.println("A posição numérica não pode ser maior que : " + largura);
                retorno = false;
            }
        return retorno;
    }
    
    boolean validarTiroDigitado(String tiroJogador){
        int quantidadeNumeros = (largura > 10) ? 2 : 1;
        String expressaoVerificacao = "^[A-Za-z]{1}[0-9]{" + quantidadeNumeros + "}$";
        return tiroJogador.matches(expressaoVerificacao);
    }
    
    int[] retornarPosicoes(String tiroJogador){
        String tiro = tiroJogador.toLowerCase();
        int[] retorno = new int [2];
        retorno[0] = tiro.charAt(0) - 97;
        retorno[1] = Integer.parseInt(tiro.substring(1)) - 1;
        return retorno;
    }
    
    void inserirValoresAcaoTabuleiro(int [] posicoes, int numeroJogador, 
                                     String Jogador1, String Jogador2){
        if (numeroJogador == 1) {
            if (tabuleiroJogador2[posicoes[0]][posicoes[1]] == 1) {
                tabuleiroJogador2[posicoes[0]][posicoes[1]] = 3;
                naviosJogador2--;
                System.out.println(" O "+ Jogador1 +  " acertou um navio!");
            } else {
                tabuleiroJogador2[posicoes[0]][posicoes[1]] = 2;
                System.out.println("O "+ Jogador1 +  " errou o tiro!");
            }
        } else {
            if (tabuleiroJogador1[posicoes[0]][posicoes[1]] == 1) {
                tabuleiroJogador1[posicoes[0]][posicoes[1]] = 3;
                naviosJogador1--;
                System.out.println("O "+ Jogador2 + " acertou um navio!");
            } else {
                tabuleiroJogador1[posicoes[0]][posicoes[1]] = 2;
                System.out.println("O "+ Jogador2 +  " errou o tiro!");
            }
        } 
    }
    
    boolean acaoJogador(String Jogador1, String Jogador2){
        boolean acaoValida = true;
        String tiroJogador = valorDigitadoJogador();
            if (validarTiroDigitado(tiroJogador)) {
                int [] posicoes = retornarPosicoes(tiroJogador);
                if (verificarPosicoes(posicoes)){
                    inserirValoresAcaoTabuleiro(posicoes, 1, Jogador1, Jogador2);
                } else {
                acaoValida = false;
                }
            } else {
                System.out.println("Posição inválida");
                acaoValida = false;
        }
        return acaoValida;
    }
    
    void acaoComputador(String Jogador1, String Jogador2){
                Random jogadaPC = new Random();
                int[] posicoes = new int[2];
                int numeroGerado = jogadaPC.nextInt(altura);
                posicoes[0] = (numeroGerado == altura) ? --numeroGerado : numeroGerado;
                numeroGerado = jogadaPC.nextInt(largura);
                posicoes[1] = (numeroGerado == largura) ? --numeroGerado : numeroGerado;
                inserirValoresAcaoTabuleiro(posicoes, 2, Jogador1, Jogador2);
  
  
  }
    
   
     
    }

