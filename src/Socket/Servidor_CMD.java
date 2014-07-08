package Socket;

import java.io.*;

import java.net.*;

import java.util.*;

public  class Servidor_CMD extends Thread {

 private static Scanner entrada = 
         new Scanner(System.in);
 public javax.swing.JTextArea jmi;
 public static void main(String args[]) {
 
 
 // instancia o vetor de clientes conectados

 clientes = new Vector();


 try {
System.out.println("Informe a porta da conexão: ");
     
 int port;
 port =Integer.parseInt( entrada.nextLine());
 
 ServerSocket s = new ServerSocket(port);

 // Loop principal.

 while (true) {

 System.out.print("Esperando alguem se conectar...");

 Socket conexao = s.accept();

 System.out.println(" Conectou!");

 // cria uma nova thread para tratar essa conexão

 Thread t = new Servidor_CMD(conexao);

 t.start();

 // voltando ao loop, esperando mais alguém se conectar.

 }

 }

 catch (IOException e) {

 // caso ocorra alguma excessão de E/S, mostre qual foi.

 System.out.println("IOException: " + e);

 }



 }
 private static int porta;
 
 // Note que a instanciação está no main.

 private static Vector clientes;

 // socket deste cliente

 private Socket conexao;

 // nome deste cliente

 private String meuNome;

 // construtor que recebe o socket deste cliente

 public Servidor_CMD(Socket s) {

 conexao = s;

 }

    Servidor_CMD() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 // execução da thread

 public void run() {

 try {

 // objetos que permitem controlar fluxo de comunicação

 BufferedReader entrada = new BufferedReader(new

 InputStreamReader(conexao.getInputStream()));

 PrintStream saida = new 

 PrintStream(conexao.getOutputStream());

 // primeiramente, espera-se pelo nome do cliente

 meuNome = entrada.readLine();
 System.out.println("Nome do cliente conectado: "+meuNome);

 if (meuNome == null) {return;}

 clientes.add(saida);

 
 
 
 
 sendToAll(saida, " conectou ", "no chat!");
 String linha = entrada.readLine();

 while (linha != null && !(linha.trim().equals(""))) {

 // reenvia a linha para todos os clientes conectados
 
 sendToAll(saida, " disse: ", linha);

 //System.out.println(linha);
 // espera por uma nova linha.

 linha = entrada.readLine();
 

 }

 // Uma vez que o cliente enviou linha em branco, retira-se

 // fluxo de saída do vetor de clientes e fecha-se conexão.

 sendToAll(saida, " saiu ", "do chat!");

 clientes.remove(saida);

 conexao.close();

 }

 catch (IOException e) {

 // Caso ocorra alguma excessão de E/S, mostre qual foi.

 System.out.println("IOException: " + e);

 }

 }

 // enviar uma mensagem para todos, menos para o próprio

 public void sendToAll(PrintStream saida, String acao,

 String linha) throws IOException {

 Enumeration e = clientes.elements();

 while (e.hasMoreElements()) {

 // obtém o fluxo de saída de um dos clientes
 
 PrintStream chat = (PrintStream) e.nextElement();

 // envia para todos, menos para o próprio usuário
 chat.println(meuNome + acao + linha);
 /*if (chat != saida) {
     
    
 }*/

 }

 }

}