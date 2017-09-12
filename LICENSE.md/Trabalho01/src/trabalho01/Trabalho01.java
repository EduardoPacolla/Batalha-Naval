/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho01;

/**
 *
 * @author Notebook
 */
public class Trabalho01 {
    //Aluno: Eduardo F. C. Pacolla     Ads Noite - Fatec M.Mirim 2017
    //Regras
    // 1- Você escolhe o tamanho do seu mapa
    // 2- O mapa aceita n navios desde que não ultrapasse 1/3 do tamnho total do mapa
    // 3- Os navios são colocados no aleatório
    // 4- Há dois jogadores, jogador1 é o usuário, e o jogador2 é o computador
    // 5- Inserir as posições EX: A2
    // 6- Bom jogo com o computador
    
    
    static Tabuleiro tabuleiro = new Tabuleiro();
    static Jogador jogador = new Jogador();
    
    
     public static void jogando(){
     boolean jogoAtivo = true;
        do{
            tabuleiro.exibirTabuleirosJogadores(jogador.Jogador1, jogador.Jogador2);  
            if (tabuleiro.acaoJogador(jogador.Jogador1,jogador.Jogador2)) {
                if (tabuleiro.naviosJogador2 <= 0){
                    System.out.println(jogador.Jogador1 + " venceu o jogo");
                    break;
                    
                }
               tabuleiro.acaoComputador(jogador.Jogador1, jogador.Jogador2);
               if (tabuleiro.naviosJogador1 <= 0){
                   System.out.println(jogador.Jogador2 + " venceu o jogo");
                    break;
                }
            }
        }while (jogoAtivo);
     }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
       jogador.obterNomesJogadores();
       tabuleiro.tamanhoTabuleiro();
       tabuleiro.calcularLimiteNavios();
       tabuleiro.iniciandoTamanhosTabuleiros();
       tabuleiro.QtdNaviosJogo();
       tabuleiro.iniciarContadoresNaviosJogadores();
       tabuleiro.inserirNaviosTabuleirosJogadores();
       jogando();
  
    }
    
}
