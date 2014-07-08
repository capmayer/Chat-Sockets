/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Mastery
 */




abstract class escuta implements Runnable{
    public javax.swing.JTextArea jta;
    public Socket conexao;
    public javax.swing.JMenuItem jmi;
    public boolean op=false;
    
    public escuta(Socket s){
        conexao = s;
    }
    public void run() {
         try {
                        
                        BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
                        String lido;
                        
                        this.jta.append("Conexao Efetuada!\n");
                        while (true) {
                            if(op){
                                this.jta.append("Desconectado!");
                                break;
                                
                            }
                            lido = entrada.readLine();
                            if (entrada == null) {
                                this.jta.append("\nConexão Perdida!");
                                break;
                            }    
                        
                        
                        
                        this.jta.append(lido+"\n");
                        
                        
                        
                        }
                    }
                    
                    catch (IOException e) {
                        
                       
                    }          
    }
}





public class Cliente extends javax.swing.JFrame{
  

    public Cliente() {
        initComponents();
        setSize(500,450);
        setResizable(false);
        setLocationRelativeTo(this);
        setTitle("Chat Sockets - Jonathan");
        
        bt_atualiza(true);
        
        jMenuItem3.setEnabled(false);
        jTextArea1.setEditable(false);
        jTextArea2.setEditable(false);
        jTextArea1.setLineWrap(true);
        jTextArea2.setLineWrap(true);
        jTextArea3.setLineWrap(true);
        
    }

    //variaveis
    private String host=null,meuNome=null;
    private int porta=0;
    private boolean flag=true;
    PrintStream saida = null;
    public Socket conexao;
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 30, 460, 250);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(310, 30, 170, 250);

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane3.setViewportView(jTextArea3);

        getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(20, 290, 340, 100);

        jButton1.setText("Enviar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(370, 330, 110, 40);

        jLabel3.setText("CHAT");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(230, 10, 60, 14);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Socket/Chat.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 512, 400);

        jMenu1.setText("Arquivo");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem1.setText("Sair");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Opções");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Conectar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Desconectar");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
   
    
    //habilita e desabilita os botao de enviar, para evitar erros
    void bt_atualiza(boolean op){
       if(op){
           jButton1.setEnabled(false);
           
       }else{
           jButton1.setEnabled(true);
           
       }
           
    }
    
    escuta e = new escuta(conexao) {};
    Thread t;
    //botao conectar
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        
        host=JOptionPane.showInputDialog("Informe o IP:");
        porta=Integer.parseInt(JOptionPane.showInputDialog("Informe a Porta:"));
        
        
        try {
            conexao = new Socket(host, porta);
        } catch (IOException ex) {
            
        }


        
        try {
            saida = new PrintStream(conexao.getOutputStream());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro: "+ex);
        }
        meuNome=JOptionPane.showInputDialog("Informe seu nome:");
        saida.println(meuNome);
        
        flag = false;
        try{
            
            
            e.jta=jTextArea1;
            e.conexao=conexao;
            t = new Thread(e);
            t.start();
            bt_atualiza(flag);
            
            jMenuItem2.setEnabled(false);
            jMenuItem3.setEnabled(true);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Erro: "+e);
        }
            
    }//GEN-LAST:event_jMenuItem2ActionPerformed
    

    
    
    
    //botao de enviar
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      saida.println(jTextArea3.getText());
      jTextArea3.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    //botao de sair
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
       t.stop();
        bt_atualiza(true);
        jMenuItem3.setEnabled(false);
        jMenuItem2.setEnabled(true);
        jTextArea1.append("Desconectado do Chat!");
        
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    // End of variables declaration//GEN-END:variables

    
}
   
