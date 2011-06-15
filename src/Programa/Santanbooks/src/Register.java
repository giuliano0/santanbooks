/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Register.java
 *
 * Created on Jun 14, 2011, 4:15:45 PM
 */



/**
 *
 * @author ra073668
 */
public class Register extends javax.swing.JPanel {
    private Santanbooks parent;
    /** Creates new form Register */
    public Register(Santanbooks cardHolder) {
        initComponents();
        this.parent = cardHolder;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        bdayLabel1 = new javax.swing.JLabel();
        userLabel1 = new javax.swing.JLabel();
        pwordText1 = new javax.swing.JPasswordField();
        pwordLabel1 = new javax.swing.JLabel();
        maleButton1 = new javax.swing.JRadioButton();
        collegeLabel1 = new javax.swing.JLabel();
        personalTitle1 = new javax.swing.JLabel();
        nameLabel1 = new javax.swing.JLabel();
        emailLabel1 = new javax.swing.JLabel();
        descriptionLabel1 = new javax.swing.JLabel();
        bdayText1 = new javax.swing.JTextField();
        collegeText1 = new javax.swing.JTextField();
        emailText1 = new javax.swing.JTextField();
        clearButton1 = new javax.swing.JButton();
        saveButton1 = new javax.swing.JButton();
        femaleButton1 = new javax.swing.JRadioButton();
        courseText1 = new javax.swing.JTextField();
        genderLabel1 = new javax.swing.JLabel();
        yearLabel1 = new javax.swing.JLabel();
        loginTitle1 = new javax.swing.JLabel();
        yearText1 = new javax.swing.JTextField();
        academicTitle1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        descriptionText1 = new javax.swing.JTextArea();
        title1 = new javax.swing.JLabel();
        userText1 = new javax.swing.JTextField();
        repeatpwordLabel1 = new javax.swing.JLabel();
        repeatpwordText1 = new javax.swing.JPasswordField();
        courseLabel1 = new javax.swing.JLabel();
        nameText1 = new javax.swing.JTextField();

        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));

        bdayLabel1.setText("Nascimento");

        userLabel1.setText("Nome de usuário");

        pwordLabel1.setText("Senha");

        buttonGroup1.add(maleButton1);
        maleButton1.setText("Masc.");

        collegeLabel1.setText("Universidade");

        personalTitle1.setFont(new java.awt.Font("DejaVu Sans 13 13", 1, 13)); // NOI18N
        personalTitle1.setText("Informações pessoais:");

        nameLabel1.setText("Nome");

        emailLabel1.setText("E-mail");

        descriptionLabel1.setText("Descreva-se em poucas linhas:");

        bdayText1.setText("DD/MM/YYYY");
        bdayText1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                bdayText1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                bdayText1FocusLost(evt);
            }
        });

        clearButton1.setText("Limpar");
        clearButton1.setMaximumSize(new java.awt.Dimension(45, 30));
        clearButton1.setMinimumSize(new java.awt.Dimension(45, 30));
        clearButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButton1ActionPerformed(evt);
            }
        });

        saveButton1.setText("Enviar");
        saveButton1.setMaximumSize(new java.awt.Dimension(45, 30));
        saveButton1.setMinimumSize(new java.awt.Dimension(45, 30));

        buttonGroup1.add(femaleButton1);
        femaleButton1.setText("Fem.");

        genderLabel1.setText("Sexo");

        yearLabel1.setText("Ano");

        loginTitle1.setFont(new java.awt.Font("DejaVu Sans 13 13", 1, 13)); // NOI18N
        loginTitle1.setText("Informações de login :");

        yearText1.setText("YYYY");
        yearText1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                yearText1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                yearText1FocusLost(evt);
            }
        });

        academicTitle1.setFont(new java.awt.Font("DejaVu Sans 13 13", 1, 13)); // NOI18N
        academicTitle1.setText("Informações acadêmicas :");

        descriptionText1.setColumns(20);
        descriptionText1.setLineWrap(true);
        descriptionText1.setRows(5);
        jScrollPane2.setViewportView(descriptionText1);

        title1.setFont(new java.awt.Font("SansSerif 24 24", 1, 24)); // NOI18N
        title1.setText("Registre sua conta");

        repeatpwordLabel1.setText("Redigite sua senha");

        courseLabel1.setText("Curso");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(189, 189, 189)
                .addComponent(loginTitle1)
                .addContainerGap(462, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(188, 188, 188)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(academicTitle1)
                        .addContainerGap())
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(descriptionLabel1)
                            .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGap(28, 28, 28)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(userLabel1)
                                        .addComponent(pwordLabel1)
                                        .addComponent(repeatpwordLabel1))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(repeatpwordText1, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(userText1, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(pwordText1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(clearButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(saveButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(courseLabel1)
                                        .addComponent(collegeLabel1))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(courseText1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(yearLabel1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(yearText1, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE))
                                        .addComponent(collegeText1, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(genderLabel1)
                                        .addComponent(emailLabel1)
                                        .addComponent(nameLabel1))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(emailText1, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(10, 10, 10)
                                            .addComponent(femaleButton1)
                                            .addGap(12, 12, 12)
                                            .addComponent(maleButton1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                                            .addComponent(bdayLabel1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(bdayText1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(nameText1, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)))))
                            .addGap(207, 207, 207)))))
            .addGroup(layout.createSequentialGroup()
                .addGap(188, 188, 188)
                .addComponent(personalTitle1)
                .addContainerGap(464, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(282, 282, 282)
                .addComponent(title1)
                .addContainerGap(286, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title1)
                .addGap(12, 12, 12)
                .addComponent(personalTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel1)
                    .addComponent(nameText1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maleButton1)
                    .addComponent(bdayText1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bdayLabel1)
                    .addComponent(femaleButton1)
                    .addComponent(genderLabel1))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailLabel1)
                    .addComponent(emailText1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(descriptionLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(academicTitle1)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(collegeLabel1)
                    .addComponent(collegeText1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(courseText1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(courseLabel1)
                    .addComponent(yearLabel1)
                    .addComponent(yearText1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(loginTitle1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userText1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userLabel1))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pwordLabel1)
                    .addComponent(pwordText1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(repeatpwordLabel1)
                    .addComponent(repeatpwordText1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clearButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bdayText1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_bdayText1FocusGained
        if(bdayText1.getText().equalsIgnoreCase("DD/MM/YYYY"))
            bdayText1.setText(null);
}//GEN-LAST:event_bdayText1FocusGained

    private void bdayText1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_bdayText1FocusLost
        if(bdayText1.getText().equalsIgnoreCase(""))
            bdayText1.setText("DD/MM/YYYY");
}//GEN-LAST:event_bdayText1FocusLost

    private void clearButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButton1ActionPerformed
        nameText1.setText(null);
        buttonGroup1.clearSelection();
        bdayText1.setText("DD/MM/YYYY");
        emailText1.setText(null);
        collegeText1.setText(null);
        courseText1.setText(null);
        yearText1.setText("YYYY");
        userText1.setText(null);
        pwordText1.setText(null);
        repeatpwordText1.setText(null);
        descriptionText1.setText(null);
}//GEN-LAST:event_clearButton1ActionPerformed

    private void yearText1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_yearText1FocusGained
        if(yearText1.getText().equalsIgnoreCase("YYYY"))
            yearText1.setText(null);
}//GEN-LAST:event_yearText1FocusGained

    private void yearText1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_yearText1FocusLost
        if(yearText1.getText().equalsIgnoreCase(""))
            yearText1.setText("YYYY");
}//GEN-LAST:event_yearText1FocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel academicTitle1;
    private javax.swing.JLabel bdayLabel1;
    private javax.swing.JTextField bdayText1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton clearButton1;
    private javax.swing.JLabel collegeLabel1;
    private javax.swing.JTextField collegeText1;
    private javax.swing.JLabel courseLabel1;
    private javax.swing.JTextField courseText1;
    private javax.swing.JLabel descriptionLabel1;
    private javax.swing.JTextArea descriptionText1;
    private javax.swing.JLabel emailLabel1;
    private javax.swing.JTextField emailText1;
    private javax.swing.JRadioButton femaleButton1;
    private javax.swing.JLabel genderLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel loginTitle1;
    private javax.swing.JRadioButton maleButton1;
    private javax.swing.JLabel nameLabel1;
    private javax.swing.JTextField nameText1;
    private javax.swing.JLabel personalTitle1;
    private javax.swing.JLabel pwordLabel1;
    private javax.swing.JPasswordField pwordText1;
    private javax.swing.JLabel repeatpwordLabel1;
    private javax.swing.JPasswordField repeatpwordText1;
    private javax.swing.JButton saveButton1;
    private javax.swing.JLabel title1;
    private javax.swing.JLabel userLabel1;
    private javax.swing.JTextField userText1;
    private javax.swing.JLabel yearLabel1;
    private javax.swing.JTextField yearText1;
    // End of variables declaration//GEN-END:variables

}
