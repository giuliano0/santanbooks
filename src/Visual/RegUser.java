package Visual;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JOptionPane;

import Classes.User;

public class RegUser extends javax.swing.JPanel implements ActionListener {
    private Santanbooks parent;

    public RegUser(Santanbooks p) {
        this.parent = p;
        initComponents();
    }

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
        setPreferredSize(new java.awt.Dimension(800, 600));
        setRequestFocusEnabled(false);
        setLayout(null);

        bdayLabel1.setText("Nascimento");
        add(bdayLabel1);
        bdayLabel1.setBounds(430, 116, 55, 14);

        userLabel1.setText("Nome de usuário");
        add(userLabel1);
        userLabel1.setBounds(227, 435, 80, 14);
        add(pwordText1);
        pwordText1.setBounds(315, 464, 217, 20);

        pwordLabel1.setText("Senha");
        add(pwordLabel1);
        pwordLabel1.setBounds(277, 467, 30, 14);

        buttonGroup1.add(maleButton1);
        maleButton1.setText("Masc.");
        add(maleButton1);
        maleButton1.setBounds(287, 112, 53, 23);

        collegeLabel1.setText("Universidade");
        add(collegeLabel1);
        collegeLabel1.setBounds(188, 337, 62, 14);

        personalTitle1.setFont(new java.awt.Font("DejaVu Sans 13 13", 1, 13));
        personalTitle1.setText("Informações pessoais:");
        add(personalTitle1);
        personalTitle1.setBounds(189, 55, 140, 16);

        nameLabel1.setText("Nome");
        add(nameLabel1);
        nameLabel1.setBounds(189, 85, 27, 14);

        emailLabel1.setText("E-mail");
        add(emailLabel1);
        emailLabel1.setBounds(189, 149, 28, 14);

        descriptionLabel1.setText("Descreva-se em poucas linhas:");
        add(descriptionLabel1);
        descriptionLabel1.setBounds(189, 177, 148, 14);

        bdayText1.setText("DD/MM/YYYY");
        bdayText1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                bdayText1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                bdayText1FocusLost(evt);
            }
        });
        add(bdayText1);
        bdayText1.setBounds(489, 113, 99, 20);
        add(collegeText1);
        collegeText1.setBounds(255, 334, 333, 20);
        add(emailText1);
        emailText1.setBounds(225, 146, 363, 20);

        clearButton1.setText("Limpar");
        clearButton1.setMaximumSize(new java.awt.Dimension(45, 30));
        clearButton1.setMinimumSize(new java.awt.Dimension(45, 30));
        clearButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButton1ActionPerformed(evt);
            }
        });
        add(clearButton1);
        clearButton1.setBounds(315, 526, 82, 23);

        saveButton1.setText("Enviar");
        saveButton1.setMaximumSize(new java.awt.Dimension(45, 30));
        saveButton1.setMinimumSize(new java.awt.Dimension(45, 30));
        add(saveButton1);
        saveButton1.setBounds(405, 526, 82, 23);
        saveButton1.addActionListener(this);

        buttonGroup1.add(femaleButton1);
        femaleButton1.setText("Fem.");
        add(femaleButton1);
        femaleButton1.setBounds(225, 112, 49, 23);
        add(courseText1);
        courseText1.setBounds(255, 365, 250, 20);

        genderLabel1.setText("Sexo");
        add(genderLabel1);
        genderLabel1.setBounds(192, 116, 24, 14);

        yearLabel1.setText("Ano");
        add(yearLabel1);
        yearLabel1.setBounds(513, 368, 19, 14);

        loginTitle1.setFont(new java.awt.Font("DejaVu Sans 13 13", 1, 13));
        loginTitle1.setText("Informações de login :");
        add(loginTitle1);
        loginTitle1.setBounds(189, 400, 142, 18);

        yearText1.setText("YYYY");
        yearText1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                yearText1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                yearText1FocusLost(evt);
            }
        });
        add(yearText1);
        yearText1.setBounds(538, 365, 50, 20);

        academicTitle1.setFont(new java.awt.Font("DejaVu Sans 13 13", 1, 13)); // NOI18N
        academicTitle1.setText("Informações acadêmicas :");
        add(academicTitle1);
        academicTitle1.setBounds(189, 304, 166, 18);

        descriptionText1.setColumns(20);
        descriptionText1.setLineWrap(true);
        descriptionText1.setRows(5);
        jScrollPane2.setViewportView(descriptionText1);

        add(jScrollPane2);
        jScrollPane2.setBounds(188, 197, 400, 96);

        title1.setFont(new java.awt.Font("SansSerif 24 24", 1, 24));
        title1.setText("Registre sua conta");
        add(title1);
        title1.setBounds(282, 11, 214, 32);
        add(userText1);
        userText1.setBounds(315, 432, 217, 20);

        repeatpwordLabel1.setText("Redigite sua senha");
        add(repeatpwordLabel1);
        repeatpwordLabel1.setBounds(216, 498, 91, 14);
        add(repeatpwordText1);
        repeatpwordText1.setBounds(315, 495, 217, 20);

        courseLabel1.setText("Curso");
        add(courseLabel1);
        courseLabel1.setBounds(222, 368, 28, 14);
        add(nameText1);
        nameText1.setBounds(225, 82, 363, 20);
    }

    private void bdayText1FocusGained(java.awt.event.FocusEvent evt) {
        if(bdayText1.getText().equalsIgnoreCase("DD/MM/YYYY"))
            bdayText1.setText(null);
}

    private void bdayText1FocusLost(java.awt.event.FocusEvent evt) {
        if(bdayText1.getText().equalsIgnoreCase(""))
            bdayText1.setText("DD/MM/YYYY");
}

    private void clearButton1ActionPerformed(java.awt.event.ActionEvent evt) {
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
}

    private void yearText1FocusGained(java.awt.event.FocusEvent evt) {
        if(yearText1.getText().equalsIgnoreCase("YYYY"))
            yearText1.setText(null);
}

    private void yearText1FocusLost(java.awt.event.FocusEvent evt) {
        if(yearText1.getText().equalsIgnoreCase(""))
            yearText1.setText("YYYY");
}

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

	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent arg0) {
		User novo = new User();
		
		novo.setName(nameText1.getText());
		novo.setAccessLevel(false);
		novo.setBirthday(new Date(new java.util.Date(bdayText1.getText()).getTime()));
		novo.setCollege(collegeText1.getText());
		novo.setCourse(courseText1.getText());
		novo.setEmail(emailText1.getText());
		novo.setGender(!femaleButton1.isSelected());
		novo.setIngressYear(new Date(Integer.parseInt(yearText1.getText()), 1, 1));
		novo.setPassword(pwordText1.getText());
		novo.setSelfDescription(descriptionText1.getText());
		novo.setUsername(userText1.getText());
		
		if (this.parent.getBusinessObject().insertUser(novo))
			JOptionPane.showMessageDialog(null, "Usuário salvo com sucesso!");
		
		this.parent.lastPanel();
	}
}
