/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author Ryan Kelly
 */
public class setup extends javax.swing.JPanel {

    /**
     * Creates new form setup
     */
    public setup() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Options = new javax.swing.ButtonGroup();
        buttonGroup = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        MinimaxOptionPlayer1 = new javax.swing.JCheckBox();
        AlphaBetaOptionPlayer1 = new javax.swing.JCheckBox();
        RandomOptionPlayer1 = new javax.swing.JCheckBox();
        sliderMinimaxPlayer1 = new javax.swing.JSlider();
        jSeparator2 = new javax.swing.JSeparator();
        sliderAlphaBetaPlayer1 = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        sliderMinimax1Player2 = new javax.swing.JSlider();
        sliderAlphaBeta1Player2 = new javax.swing.JSlider();
        MinimaxOption1Player2 = new javax.swing.JCheckBox();
        AlphaBetaOption1Player2 = new javax.swing.JCheckBox();
        RandomOption1Player2 = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        Player2 = new javax.swing.JComboBox<>();
        Player1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        buttonGroup.add(MinimaxOptionPlayer1);
        MinimaxOptionPlayer1.setText("Minimax");
        MinimaxOptionPlayer1.setAutoscrolls(true);

        buttonGroup.add(AlphaBetaOptionPlayer1);
        AlphaBetaOptionPlayer1.setSelected(true);
        AlphaBetaOptionPlayer1.setText("Alpha Beta");
        AlphaBetaOptionPlayer1.setAutoscrolls(true);

        buttonGroup.add(RandomOptionPlayer1);
        RandomOptionPlayer1.setText("Random");
        RandomOptionPlayer1.setAutoscrolls(true);

        sliderMinimaxPlayer1.setMajorTickSpacing(1);
        sliderMinimaxPlayer1.setMaximum(6);
        sliderMinimaxPlayer1.setMinimum(1);
        sliderMinimaxPlayer1.setMinorTickSpacing(1);
        sliderMinimaxPlayer1.setPaintLabels(true);
        sliderMinimaxPlayer1.setValue(3);
        sliderMinimaxPlayer1.setAutoscrolls(true);

        sliderAlphaBetaPlayer1.setMajorTickSpacing(1);
        sliderAlphaBetaPlayer1.setMaximum(8);
        sliderAlphaBetaPlayer1.setMinimum(1);
        sliderAlphaBetaPlayer1.setMinorTickSpacing(1);
        sliderAlphaBetaPlayer1.setPaintLabels(true);
        sliderAlphaBetaPlayer1.setValue(5);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Vs");

        sliderMinimax1Player2.setMajorTickSpacing(1);
        sliderMinimax1Player2.setMaximum(6);
        sliderMinimax1Player2.setMinimum(1);
        sliderMinimax1Player2.setMinorTickSpacing(1);
        sliderMinimax1Player2.setPaintLabels(true);
        sliderMinimax1Player2.setValue(3);
        sliderMinimax1Player2.setAutoscrolls(true);

        sliderAlphaBeta1Player2.setMajorTickSpacing(1);
        sliderAlphaBeta1Player2.setMaximum(8);
        sliderAlphaBeta1Player2.setMinimum(1);
        sliderAlphaBeta1Player2.setMinorTickSpacing(1);
        sliderAlphaBeta1Player2.setPaintLabels(true);
        sliderAlphaBeta1Player2.setValue(5);

        buttonGroup2.add(MinimaxOption1Player2);
        MinimaxOption1Player2.setSelected(true);
        MinimaxOption1Player2.setText("Minimax");
        MinimaxOption1Player2.setAutoscrolls(true);

        buttonGroup2.add(AlphaBetaOption1Player2);
        AlphaBetaOption1Player2.setText("Alpha Beta");
        AlphaBetaOption1Player2.setAutoscrolls(true);

        buttonGroup2.add(RandomOption1Player2);
        RandomOption1Player2.setText("Random");
        RandomOption1Player2.setAutoscrolls(true);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        Player2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Human", "AI", " " }));
        Object selected = Player2.getSelectedItem();
        if (selected.toString().equals("AI")) {
            MinimaxOption1Player2.setEnabled(true);
            sliderMinimax1Player2.setEnabled(true);
            AlphaBetaOption1Player2.setEnabled(true);
            sliderAlphaBeta1Player2.setEnabled(true);
            RandomOption1Player2.setEnabled(true);
        } else if (selected.toString().equals("Human")) {
            MinimaxOption1Player2.setEnabled(false);
            sliderMinimax1Player2.setEnabled(false);
            AlphaBetaOption1Player2.setEnabled(false);
            sliderAlphaBeta1Player2.setEnabled(false);
            RandomOption1Player2.setEnabled(false);
        }
        Player2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Player2ActionPerformed(evt);
            }
        });

        Player1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AI", "Human", " " }));
        selected = Player1.getSelectedItem();
        if (selected.toString().equals("AI")) {
            MinimaxOptionPlayer1.setEnabled(true);
            sliderMinimaxPlayer1.setEnabled(true);
            AlphaBetaOptionPlayer1.setEnabled(true);
            sliderAlphaBetaPlayer1.setEnabled(true);
            RandomOptionPlayer1.setEnabled(true);
        } else if (selected.toString().equals("Human")) {
            MinimaxOptionPlayer1.setEnabled(false);
            sliderMinimaxPlayer1.setEnabled(false);
            AlphaBetaOptionPlayer1.setEnabled(false);
            sliderAlphaBetaPlayer1.setEnabled(false);
            RandomOptionPlayer1.setEnabled(false);
        }
        Player1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Player1ActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(255, 0, 0));
        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Red");

        jLabel3.setBackground(new java.awt.Color(255, 0, 0));
        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("Black");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RandomOptionPlayer1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(MinimaxOptionPlayer1)
                            .addComponent(AlphaBetaOptionPlayer1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sliderMinimaxPlayer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sliderAlphaBetaPlayer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RandomOption1Player2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(MinimaxOption1Player2)
                            .addComponent(AlphaBetaOption1Player2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sliderMinimax1Player2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sliderAlphaBeta1Player2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(22, 22, 22))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 663, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(Player1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95)
                .addComponent(Player2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(127, 127, 127))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(165, 165, 165))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Player1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Player2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(MinimaxOptionPlayer1)
                            .addComponent(sliderMinimaxPlayer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AlphaBetaOptionPlayer1)
                            .addComponent(sliderAlphaBetaPlayer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(RandomOptionPlayer1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(MinimaxOption1Player2)
                            .addComponent(sliderMinimax1Player2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AlphaBetaOption1Player2)
                            .addComponent(sliderAlphaBeta1Player2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(RandomOption1Player2))
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Player2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Player2ActionPerformed
        Object selected = Player2.getSelectedItem();
        if (selected.toString().equals("AI")) {
            MinimaxOption1Player2.setEnabled(true);
            sliderMinimax1Player2.setEnabled(true);
            AlphaBetaOption1Player2.setEnabled(true);
            sliderAlphaBeta1Player2.setEnabled(true);
            RandomOption1Player2.setEnabled(true);
        } else if (selected.toString().equals("Human")) {
            MinimaxOption1Player2.setEnabled(false);
            sliderMinimax1Player2.setEnabled(false);
            AlphaBetaOption1Player2.setEnabled(false);
            sliderAlphaBeta1Player2.setEnabled(false);
            RandomOption1Player2.setEnabled(false);
        }
    }//GEN-LAST:event_Player2ActionPerformed

    private void Player1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Player1ActionPerformed
        Object selected = Player1.getSelectedItem();
        if (selected.toString().equals("AI")) {
            MinimaxOptionPlayer1.setEnabled(true);
            sliderMinimaxPlayer1.setEnabled(true);
            AlphaBetaOptionPlayer1.setEnabled(true);
            sliderAlphaBetaPlayer1.setEnabled(true);
            RandomOptionPlayer1.setEnabled(true);
        } else if (selected.toString().equals("Human")) {
            MinimaxOptionPlayer1.setEnabled(false);
            sliderMinimaxPlayer1.setEnabled(false);
            AlphaBetaOptionPlayer1.setEnabled(false);
            sliderAlphaBetaPlayer1.setEnabled(false);
            RandomOptionPlayer1.setEnabled(false);
        }
    }//GEN-LAST:event_Player1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JCheckBox AlphaBetaOption1Player2;
    public javax.swing.JCheckBox AlphaBetaOptionPlayer1;
    public javax.swing.JCheckBox MinimaxOption1Player2;
    public javax.swing.JCheckBox MinimaxOptionPlayer1;
    private javax.swing.ButtonGroup Options;
    public javax.swing.JComboBox<String> Player1;
    public javax.swing.JComboBox<String> Player2;
    public javax.swing.JCheckBox RandomOption1Player2;
    public javax.swing.JCheckBox RandomOptionPlayer1;
    public javax.swing.ButtonGroup buttonGroup;
    private javax.swing.ButtonGroup buttonGroup1;
    public javax.swing.ButtonGroup buttonGroup2;
    public javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    public javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    public javax.swing.JSeparator jSeparator3;
    public javax.swing.JSlider sliderAlphaBeta1Player2;
    public javax.swing.JSlider sliderAlphaBetaPlayer1;
    public javax.swing.JSlider sliderMinimax1Player2;
    public javax.swing.JSlider sliderMinimaxPlayer1;
    // End of variables declaration//GEN-END:variables
}
