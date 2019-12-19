/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import static java.lang.Float.parseFloat;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.sql.PooledConnection;
import javax.swing.JOptionPane;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.pool.OracleConnectionPoolDataSource;

/**
 *
 * @author jacks
 */
public class Relatorio2 extends javax.swing.JFrame {

    /**
     * Creates new form Relatorio2
     */
    public Relatorio2() {
        initComponents();
    }
    public Relatorio2(String NomeSetor) {
        initComponents();
        initComponents();
        ResultSet rs = null;
        OraclePreparedStatement ps = null;
        Float totalSalario = 0f, totalINSS = 0f, totalFgts = 0f;
            try{
                System.out.println(NomeSetor);
                OracleConnectionPoolDataSource ds = new OracleConnectionPoolDataSource();
                ds.setURL("jdbc:oracle:thin:@localhost:1521:xe");
                ds.setUser("folhapagamento");
                ds.setPassword("senha");
                PooledConnection pc = ds.getPooledConnection();
                Connection conn=pc.getConnection();
                
                String SQL = "SELECT * from Setor where Nome_S = '"+NomeSetor+"'";
                
                System.out.println(SQL);
                ps = (OraclePreparedStatement)conn.prepareStatement(SQL);
                rs = ps.executeQuery();
                if(rs.next()){
                    System.out.println("rs");
                    String cod = rs.getString("Cod_S");
                    String SQL2 = "SELECT * from Funcionario_Setor where fk_pk_Cod_S = "+cod+"";
                    OraclePreparedStatement ps2 = (OraclePreparedStatement)conn.prepareStatement(SQL2);
                    ResultSet rs2 = ps2.executeQuery();
                    if(rs2.next()){
                        do{
                            System.out.println(rs2.getString("fk_pk_Cpf_F"));
                            String SQL3 = "SELECT * from Funcionario_Cargo where fk_pk_Cpf_F = "+rs2.getString("fk_pk_Cpf_F")+"";
                            String SQL4 = "SELECT * from pagamento where Fk_pk_Cpf_F = "+rs2.getString("fk_pk_Cpf_F")+"";
                            OraclePreparedStatement ps3 = (OraclePreparedStatement)conn.prepareStatement(SQL3);
                            OraclePreparedStatement ps4 = (OraclePreparedStatement)conn.prepareStatement(SQL4);
                            ResultSet rs3 = ps3.executeQuery();
                            ResultSet rs4 = ps4.executeQuery();
                            if(rs3.next()){
                                do{
                                    String SQL5 = "SELECT * from cargo where Cod_C = "+rs3.getString("fk_pk_Cod_C")+"";
                                    OraclePreparedStatement ps5 = (OraclePreparedStatement)conn.prepareStatement(SQL5);
                                    ResultSet rs5 = ps5.executeQuery();
                                    if(rs5.next())
                                        do{
                                            totalSalario = totalSalario + parseFloat(rs5.getString("Valor_base"));
                                        }while (rs5.next());
                                }while (rs3.next());
                            }
                            if(rs4.next()){
                                do{
                                    totalINSS = totalINSS + parseFloat(rs4.getString("INSS_P"));
                                    totalFgts = totalFgts + parseFloat(rs4.getString("fgts_p"));
                                }while (rs4.next());
                            }
                            
                        }while(rs2.next());
                    }
                    
                }else{
                    JOptionPane.showMessageDialog(null, "Não existem Setores cadastrados!");
                    
                }
                
                setorTxt.setText(NomeSetor);
                totalsalariosTxt.setText("R$"+totalSalario.toString());
                inssTxt.setText("R$"+totalINSS.toString());
                fgtsTxt.setText("R$"+totalFgts.toString());
                try {
                    ps.close();
                    conn.close();
                    Thread.sleep(500);
                }
                catch(Exception e) { }
                }catch(Exception ex){
                }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        setorTxt = new javax.swing.JLabel();
        totalsalariosTxt = new javax.swing.JLabel();
        fgtsTxt = new javax.swing.JLabel();
        inssTxt = new javax.swing.JLabel();

        jLabel8.setText("jLabel8");

        jLabel9.setText("jLabel9");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel5.setFont(new java.awt.Font("Bariol Regular", 1, 24)); // NOI18N
        jLabel5.setText("Relatório");

        jButton1.setText("Fechar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("SETOR:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Total Salários:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Total FGTS");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Total INSS:");

        setorTxt.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        totalsalariosTxt.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        fgtsTxt.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        inssTxt.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(225, 225, 225)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inssTxt)
                            .addComponent(fgtsTxt)
                            .addComponent(totalsalariosTxt)
                            .addComponent(setorTxt)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(203, 203, 203)
                        .addComponent(jLabel5)))
                .addContainerGap(228, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel5)
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(setorTxt))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(totalsalariosTxt))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(fgtsTxt))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(inssTxt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Relatorio2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Relatorio2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Relatorio2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Relatorio2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Relatorio2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fgtsTxt;
    private javax.swing.JLabel inssTxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel setorTxt;
    private javax.swing.JLabel totalsalariosTxt;
    // End of variables declaration//GEN-END:variables
}
