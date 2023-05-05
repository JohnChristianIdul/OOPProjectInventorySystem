/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package inventorysystem;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hernah
 */
public class VerificationPanel extends javax.swing.JFrame {

    /**
     * Creates new form VerificationPanel
     */
    Connect conn;
    DefaultTableModel tbl;
    public VerificationPanel() {
        initComponents();
        conn = new Connect();
        tbl = (DefaultTableModel) tblVerification.getModel();
        displayTable();
    }
    
    public void displayTable() {
            ArrayList<Verification> verify = conn.displayVerification();
            for(Verification v: verify){
                Subscriber s = v.getSubscriber();
                Object[] data = {v.getTransactionID(),v.getUsername(),v.getAmount(),s.getPayable(),s.getSubscribertype(),v.getTransactionType(),v.getStatus()};
                tbl.addRow(data);  
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tblVerification = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnPending = new javax.swing.JButton();
        btnApproved = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtAmount = new javax.swing.JTextField();
        txtUsername = new javax.swing.JTextField();
        txtSubscription = new javax.swing.JTextField();
        txtTransaction = new javax.swing.JTextField();
        btnApprove = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnReject = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtPayable = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(862, 441));

        tblVerification.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Transaction ID", "Username", "Payment", "Payable", "Subscription", "Transaction", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblVerification.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVerificationMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblVerification);

        jLabel1.setText("Filter Status");

        btnPending.setText("Pending");

        btnApproved.setText("Approved");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Username");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Payment");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Subscription ");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Transaction");

        txtAmount.setEditable(false);

        txtUsername.setEditable(false);

        txtSubscription.setEditable(false);

        txtTransaction.setEditable(false);

        btnApprove.setText("Approve");
        btnApprove.setEnabled(false);
        btnApprove.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnApproveMouseClicked(evt);
            }
        });

        jButton1.setText("Rejected");

        btnReject.setText("Reject");
        btnReject.setEnabled(false);
        btnReject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRejectMouseClicked(evt);
            }
        });

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Payable");

        txtPayable.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPending)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnApproved)
                        .addGap(12, 12, 12)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPayable, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSubscription, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTransaction, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnApprove)
                                .addGap(12, 12, 12)
                                .addComponent(btnReject)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(btnPending)
                        .addGap(46, 46, 46)
                        .addComponent(jLabel2)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel3)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel6)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnApproved)
                            .addComponent(jButton1))
                        .addGap(43, 43, 43)
                        .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(txtPayable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtSubscription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTransaction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnApprove)
                            .addComponent(btnReject))))
                .addGap(8, 8, 8))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblVerificationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVerificationMouseClicked
        // TODO add your handling code here:
        int index=tblVerification.getSelectedRow();
        String username = (String) tblVerification.getValueAt(index,1);
        Double payment = (Double) tblVerification.getValueAt(index, 2);
        Double payable = (Double) tblVerification.getValueAt(index, 3);
        String subscription = (String) tblVerification.getValueAt(index, 4);
        String transaction = (String) tblVerification.getValueAt(index, 5);
        txtUsername.setText(username);
        txtAmount.setText(Double.toString(payment));
        txtPayable.setText(Double.toString(payable));
        txtSubscription.setText(subscription);
        txtTransaction.setText(transaction);
        btnApprove.setEnabled(true);
        btnReject.setEnabled(true);
    }//GEN-LAST:event_tblVerificationMouseClicked

    private void btnApproveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnApproveMouseClicked
        // TODO add your handling code here:
        int index=tblVerification.getSelectedRow();
        int transactionID = (Integer) tblVerification.getValueAt(index,0);
        conn.approveTransaction(transactionID);
        tbl.setRowCount(0);
        displayTable();
        btnApprove.setEnabled(false);
        btnReject.setEnabled(false);
    }//GEN-LAST:event_btnApproveMouseClicked

    private void btnRejectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRejectMouseClicked
        // TODO add your handling code here:
        int index=tblVerification.getSelectedRow();
        int transactionID = (Integer) tblVerification.getValueAt(index,0);
        conn.rejectTransaction(transactionID);
        tbl.setRowCount(0);
        displayTable();
        btnApprove.setEnabled(false);
        btnReject.setEnabled(false);
    }//GEN-LAST:event_btnRejectMouseClicked

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
            java.util.logging.Logger.getLogger(VerificationPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VerificationPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VerificationPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VerificationPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VerificationPanel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApprove;
    private javax.swing.JButton btnApproved;
    private javax.swing.JButton btnPending;
    private javax.swing.JButton btnReject;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblVerification;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JTextField txtPayable;
    private javax.swing.JTextField txtSubscription;
    private javax.swing.JTextField txtTransaction;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}