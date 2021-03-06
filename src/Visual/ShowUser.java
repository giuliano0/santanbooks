package Visual;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ShowUser.java
 *
 * Created on 17/06/2011, 23:19:05
 */

/**
 *
 * @author Renato
 */
public class ShowUser extends javax.swing.JPanel implements ActionListener {
    private Santanbooks parent;
    /** Creates new form ShowUser */
    public ShowUser(Santanbooks p) {
        this.parent = p;
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        descriptionLabel1 = new javax.swing.JLabel();
        nameLabel2 = new javax.swing.JLabel();
        personalTitle2 = new javax.swing.JLabel();
        academicTitle1 = new javax.swing.JLabel();
        bdayLabel1 = new javax.swing.JLabel();
        emailLabel1 = new javax.swing.JLabel();
        courseLabel1 = new javax.swing.JLabel();
        collegeLabel1 = new javax.swing.JLabel();
        genderLabel1 = new javax.swing.JLabel();
        yearLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel1.setText("<USERNAME>");

        descriptionLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        descriptionLabel1.setText("Sobre <USERNAME>:");

        nameLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        nameLabel2.setText("Nome:");

        personalTitle2.setFont(new java.awt.Font("DejaVu Sans 13 13", 1, 13)); // NOI18N
        personalTitle2.setText("Informações pessoais:");

        academicTitle1.setFont(new java.awt.Font("DejaVu Sans 13 13", 1, 13)); // NOI18N
        academicTitle1.setText("Informações acadêmicas :");

        bdayLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        bdayLabel1.setText("Nascimento:");

        emailLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        emailLabel1.setText("E-mail:");

        courseLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        courseLabel1.setText("Curso:");

        collegeLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        collegeLabel1.setText("Universidade:");

        genderLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        genderLabel1.setText("Sexo:");

        yearLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        yearLabel1.setText("Ano:");

        jLabel2.setText("<NOME DO USUARIO>");

        jLabel3.setText("<SEXO>");

        jLabel4.setText("<NASCIMENTO>");

        jLabel5.setText("<EMAIL>");

        jLabel6.setText("<DESCRIÇAO DO USUARIO>");

        jLabel7.setText("<UNIVERSIDADE>");

        jLabel8.setText("<CURSO>");

        jLabel9.setText("<ANO>");

        jButton1.setText("Voltar");
        jButton1.addActionListener(this);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(320, 320, 320)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(collegeLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(230, 230, 230)
                        .addComponent(courseLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addComponent(yearLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(360, 360, 360)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(academicTitle1)
                            .addComponent(jLabel6)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(191, 191, 191)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(nameLabel2)
                            .addComponent(genderLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(68, 68, 68)
                                .addComponent(bdayLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4))
                            .addComponent(jLabel2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(descriptionLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(emailLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5))
                            .addComponent(personalTitle2))))
                .addGap(205, 205, 205))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jLabel1)
                .addGap(21, 21, 21)
                .addComponent(personalTitle2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel2)
                    .addComponent(jLabel2))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(genderLabel1)
                    .addComponent(bdayLabel1)
                    .addComponent(jLabel4))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailLabel1)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(descriptionLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(academicTitle1)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(collegeLabel1)
                    .addComponent(jLabel7))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(courseLabel1)
                    .addComponent(jLabel8))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(yearLabel1)
                    .addComponent(jLabel9))
                .addGap(36, 36, 36)
                .addComponent(jButton1))
        );
    }
    
    public void updateForm() {
        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel1.setText(this.parent.getUser().getName());

        descriptionLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        descriptionLabel1.setText("Sobre " + this.parent.getUser().getName() + ":");

        nameLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        nameLabel2.setText("Nome:");

        personalTitle2.setFont(new java.awt.Font("DejaVu Sans 13 13", 1, 13)); // NOI18N
        personalTitle2.setText("Informações pessoais:");

        academicTitle1.setFont(new java.awt.Font("DejaVu Sans 13 13", 1, 13)); // NOI18N
        academicTitle1.setText("Informações acadêmicas :");

        bdayLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        bdayLabel1.setText("Nascimento:");

        emailLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        emailLabel1.setText("E-mail:");

        courseLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        courseLabel1.setText("Curso:");

        collegeLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        collegeLabel1.setText("Universidade:");

        genderLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        genderLabel1.setText("Sexo:");

        yearLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        yearLabel1.setText("Ano:");

        jLabel2.setText(this.parent.getUser().getUsername());

        jLabel3.setText(this.parent.getUser().getGender() ? "M" : "F");

        jLabel4.setText(this.parent.getUser().getBirthday().toString());

        jLabel5.setText(this.parent.getUser().getEmail());

        jLabel6.setText(this.parent.getUser().getSelfDescription());

        jLabel7.setText(this.parent.getUser().getCollege());

        jLabel8.setText(this.parent.getUser().getCourse());

        jLabel9.setText(this.parent.getUser().getIngressYear().toString());
    }

    private javax.swing.JLabel academicTitle1;
    private javax.swing.JLabel bdayLabel1;
    private javax.swing.JLabel collegeLabel1;
    private javax.swing.JLabel courseLabel1;
    private javax.swing.JLabel descriptionLabel1;
    private javax.swing.JLabel emailLabel1;
    private javax.swing.JLabel genderLabel1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel nameLabel2;
    private javax.swing.JLabel personalTitle2;
    private javax.swing.JLabel yearLabel1;
    // End of variables declaration//GEN-END:variables
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.parent.changePanel(Santanbooks.PANEL_HOME);
	}
}
