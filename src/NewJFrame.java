import java.awt.event.ItemEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.media.Manager;
//import javax.media.MediaLocator;
//import javax.media.NoPlayerException;
//import javax.media.Player;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
//import javax.sound.sampled.LineUnavailableException;
import javax.swing.DefaultListModel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ahusson
 */
public class NewJFrame extends javax.swing.JFrame {
    private GUIController controller;
    
    /**
     * Creates new form NewJFrame
     */
    public NewJFrame() {
        this.requestsModel = new DefaultListModel<Runnable>();
        initComponents();
    }
    
    public void setController(GUIController controller) {
        this.controller = controller;
    }
    /**
     *
     * @param airports
     */
    public void setAirports(ArrayList<Airport> airports) {

        this.mapPanel1.setAirports(airports);
//
//        Old interface for create a new flight
//        jComboBox1.removeAllItems();
//        jComboBox2.removeAllItems();
//        for (Airport airport: airports) {
//            jComboBox1.addItem(airport);
//            jComboBox2.addItem(airport);
//        }

    }

    public void setPlanes(Collection<Plane> planes) {
        this.mapPanel1.setPlanes(planes);
    }

    public void repaintMap() {
        this.mapPanel1.repaint();
    }

    /**
     *
     * @param req
     */
    public void addRequest(Runnable req) {
        this.requestsModel.addElement(req);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        eclosionsAutoacceptBox = new javax.swing.JCheckBox();
        decollagesAutoaccept = new javax.swing.JCheckBox();
        destructionsAutoacceptBox = new javax.swing.JCheckBox();
        DragonsButton2 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<Runnable>();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        mapPanel1 = new MapPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);

        eclosionsAutoacceptBox.setText("Accepter toutes les éclosions");
        eclosionsAutoacceptBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                eclosionsAutoacceptBoxItemStateChanged(evt);
            }
        });
        eclosionsAutoacceptBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eclosionsAutoacceptBoxActionPerformed(evt);
            }
        });

        decollagesAutoaccept.setText("Accepter tous les décollages");
        decollagesAutoaccept.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                decollagesAutoacceptItemStateChanged(evt);
            }
        });
        decollagesAutoaccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decollagesAutoacceptActionPerformed(evt);
            }
        });

        destructionsAutoacceptBox.setText("Accepter toutes les destructions !");
        destructionsAutoacceptBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                destructionsAutoacceptBoxItemStateChanged(evt);
            }
        });
        destructionsAutoacceptBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                destructionsAutoacceptBoxActionPerformed(evt);
            }
        });

        DragonsButton2.setText("WHERE ARE MY DRAGONS");
        DragonsButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DragonsButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(destructionsAutoacceptBox)
                    .addComponent(decollagesAutoaccept, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(eclosionsAutoacceptBox, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addComponent(DragonsButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(eclosionsAutoacceptBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(decollagesAutoaccept)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(destructionsAutoacceptBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(DragonsButton2)
                .addContainerGap())
        );

        jList1.setModel(this.requestsModel);
        jList1.setFocusable(false);
        jScrollPane1.setViewportView(jList1);

        jLabel5.setText("Requêtes à accepter (sélection multiple possible)");

        jButton1.setText("Accepter");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Mourir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel2.setLayout(null);

        javax.swing.GroupLayout mapPanel1Layout = new javax.swing.GroupLayout(mapPanel1);
        mapPanel1.setLayout(mapPanel1Layout);
        mapPanel1Layout.setHorizontalGroup(
            mapPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 758, Short.MAX_VALUE)
        );
        mapPanel1Layout.setVerticalGroup(
            mapPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 726, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mapPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(mapPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        setBounds(0, 0, 1290, 809);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        List<Runnable> tasks = this.jList1.getSelectedValuesList();
        for (Runnable task : tasks) {
            task.run();
            this.requestsModel.removeElement(task);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void eclosionsAutoacceptBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eclosionsAutoacceptBoxActionPerformed

   
    }//GEN-LAST:event_eclosionsAutoacceptBoxActionPerformed

    private void decollagesAutoacceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decollagesAutoacceptActionPerformed

    }//GEN-LAST:event_decollagesAutoacceptActionPerformed

    private void destructionsAutoacceptBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_destructionsAutoacceptBoxActionPerformed

    }//GEN-LAST:event_destructionsAutoacceptBoxActionPerformed

    private void DragonsButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DragonsButton2ActionPerformed
//        Player player;
//        try {
//            player = Manager.createPlayer(new MediaLocator(new File("dragons.wav").toURI().toURL()));
//            player.start();
//        } catch (Exception ex) {
//            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
        Clip clip;
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File("dragons.wav")));
            clip.start();
        } catch (Exception ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_DragonsButton2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        List<Runnable> tasks = this.jList1.getSelectedValuesList();
        for (Runnable task : tasks) {
            this.requestsModel.removeElement(task);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void eclosionsAutoacceptBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_eclosionsAutoacceptBoxItemStateChanged
        if (evt.getStateChange() == ItemEvent.DESELECTED) {
            this.controller.UnAutoAccept(TaskType.REQUEST_NEWFLIGHT);
        } else if (evt.getStateChange() == ItemEvent.SELECTED) {
            this.controller.autoAccept(TaskType.REQUEST_NEWFLIGHT);
        }
    }//GEN-LAST:event_eclosionsAutoacceptBoxItemStateChanged

    private void decollagesAutoacceptItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_decollagesAutoacceptItemStateChanged
     if (evt.getStateChange() == ItemEvent.DESELECTED) {
            this.controller.UnAutoAccept(TaskType.REQUEST_TAKEOFF);
        } else if (evt.getStateChange() == ItemEvent.SELECTED) {
            this.controller.autoAccept(TaskType.REQUEST_TAKEOFF);
        }
    }//GEN-LAST:event_decollagesAutoacceptItemStateChanged

    private void destructionsAutoacceptBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_destructionsAutoacceptBoxItemStateChanged
     if (evt.getStateChange() == ItemEvent.DESELECTED) {
            this.controller.UnAutoAccept(TaskType.REQUEST_LANDING);
        } else if (evt.getStateChange() == ItemEvent.SELECTED) {
            this.controller.autoAccept(TaskType.REQUEST_LANDING);
        }
    }//GEN-LAST:event_destructionsAutoacceptBoxItemStateChanged

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
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }
    DefaultListModel<Runnable> requestsModel;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DragonsButton2;
    private javax.swing.JCheckBox decollagesAutoaccept;
    private javax.swing.JCheckBox destructionsAutoacceptBox;
    private javax.swing.JCheckBox eclosionsAutoacceptBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList<Runnable> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private MapPanel mapPanel1;
    // End of variables declaration//GEN-END:variables
}
