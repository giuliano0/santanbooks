import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Register extends JPanel {
    private Santanbooks parent;
	
    private JLabel academicTitle1, bdayLabel1, collegeLabel1, courseLabel1, descriptionLabel1, emailLabel1, 
		genderLabel1, loginTitle1, nameLabel1, personalTitle1, repeatpwordLabel1, title1, pwordLabel1, userLabel1, yearLabel1;
    private ButtonGroup buttonGroup1;
    private JTextField collegeText1, courseText1, emailText1, bdayText1, nameText1, userText1, yearText1;
    private JTextArea descriptionText1;
	private JButton clearButton1, saveButton1;
    private JRadioButton femaleButton1, maleButton1;
    private JScrollPane jScrollPane2;
    private JPasswordField pwordText1, repeatpwordText1;
	
    public Register(Santanbooks cardHolder) {
        initComponents();
        this.parent = cardHolder;
    }

    private void initComponents() {
        buttonGroup1 = new ButtonGroup();
        bdayLabel1 = new JLabel();
        userLabel1 = new JLabel();
        pwordText1 = new JPasswordField();
        pwordLabel1 = new JLabel();
        maleButton1 = new JRadioButton();
        collegeLabel1 = new JLabel();
        personalTitle1 = new JLabel();
        nameLabel1 = new JLabel();
        emailLabel1 = new JLabel();
        descriptionLabel1 = new JLabel();
        bdayText1 = new JTextField();
        collegeText1 = new JTextField();
        emailText1 = new JTextField();
        clearButton1 = new JButton();
        saveButton1 = new JButton();
        femaleButton1 = new JRadioButton();
        courseText1 = new JTextField();
        genderLabel1 = new JLabel();
        yearLabel1 = new JLabel();
        loginTitle1 = new JLabel();
        yearText1 = new JTextField();
        academicTitle1 = new JLabel();
        jScrollPane2 = new JScrollPane();
        descriptionText1 = new JTextArea();
        title1 = new JLabel();
        userText1 = new JTextField();
        repeatpwordLabel1 = new JLabel();
        repeatpwordText1 = new JPasswordField();
        courseLabel1 = new JLabel();
        nameText1 = new JTextField();

        setMaximumSize(new Dimension(800, 600));
        setMinimumSize(new Dimension(800, 600));

        bdayLabel1.setText("Nascimento");

        userLabel1.setText("Nome de usuário");

        pwordLabel1.setText("Senha");

        buttonGroup1.add(maleButton1);
        maleButton1.setText("Masc.");

        collegeLabel1.setText("Universidade");

        personalTitle1.setFont(new Font("DejaVu Sans 13 13", 1, 13)); // NOI18N
        personalTitle1.setText("Informações pessoais:");

        nameLabel1.setText("Nome");

        emailLabel1.setText("E-mail");

        descriptionLabel1.setText("Descreva-se em poucas linhas:");

        bdayText1.setText("DD/MM/YYYY");
        bdayText1.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                bdayText1FocusGained(evt);
            }
            public void focusLost(FocusEvent evt) {
                bdayText1FocusLost(evt);
            }
        });

        clearButton1.setText("Limpar");
        clearButton1.setMaximumSize(new Dimension(45, 30));
        clearButton1.setMinimumSize(new Dimension(45, 30));
        clearButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                clearButton1ActionPerformed(evt);
            }
        });

        saveButton1.setText("Enviar");
        saveButton1.setMaximumSize(new Dimension(45, 30));
        saveButton1.setMinimumSize(new Dimension(45, 30));

        buttonGroup1.add(femaleButton1);
        femaleButton1.setText("Fem.");

        genderLabel1.setText("Sexo");

        yearLabel1.setText("Ano");

        loginTitle1.setFont(new Font("DejaVu Sans 13 13", 1, 13));
        loginTitle1.setText("Informações de login :");

        yearText1.setText("YYYY");
        yearText1.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                yearText1FocusGained(evt);
            }
            public void focusLost(FocusEvent evt) {
                yearText1FocusLost(evt);
            }
        });

        academicTitle1.setFont(new Font("DejaVu Sans 13 13", 1, 13));
        academicTitle1.setText("Informações acadêmicas :");

        descriptionText1.setColumns(20);
        descriptionText1.setLineWrap(true);
        descriptionText1.setRows(5);
        jScrollPane2.setViewportView(descriptionText1);

        title1.setFont(new Font("SansSerif 24 24", 1, 24));
        title1.setText("Registre sua conta");

        repeatpwordLabel1.setText("Redigite sua senha");

        courseLabel1.setText("Curso");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(189, 189, 189)
                .addComponent(loginTitle1)
                .addContainerGap(462, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(188, 188, 188)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(academicTitle1)
                        .addContainerGap())
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(descriptionLabel1)
                            .addContainerGap())
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane2, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
                                .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGap(28, 28, 28)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(userLabel1)
                                        .addComponent(pwordLabel1)
                                        .addComponent(repeatpwordLabel1))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(repeatpwordText1, GroupLayout.Alignment.LEADING)
                                            .addComponent(userText1, GroupLayout.Alignment.LEADING)
                                            .addComponent(pwordText1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(clearButton1, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(saveButton1, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))))
                                .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(courseLabel1)
                                        .addComponent(collegeLabel1))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(courseText1, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(yearLabel1)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(yearText1, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE))
                                        .addComponent(collegeText1, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)))
                                .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(genderLabel1)
                                        .addComponent(emailLabel1)
                                        .addComponent(nameLabel1))
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(emailText1, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(10, 10, 10)
                                            .addComponent(femaleButton1)
                                            .addGap(12, 12, 12)
                                            .addComponent(maleButton1)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                                            .addComponent(bdayLabel1)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(bdayText1, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(nameText1, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)))))
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
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title1)
                .addGap(12, 12, 12)
                .addComponent(personalTitle1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel1)
                    .addComponent(nameText1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(maleButton1)
                    .addComponent(bdayText1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(bdayLabel1)
                    .addComponent(femaleButton1)
                    .addComponent(genderLabel1))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(emailLabel1)
                    .addComponent(emailText1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(descriptionLabel1)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(academicTitle1)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(collegeLabel1)
                    .addComponent(collegeText1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(courseText1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(courseLabel1)
                    .addComponent(yearLabel1)
                    .addComponent(yearText1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(loginTitle1)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(userText1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(userLabel1))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(pwordLabel1)
                    .addComponent(pwordText1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(repeatpwordLabel1)
                    .addComponent(repeatpwordText1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(clearButton1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveButton1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );
    }

    private void bdayText1FocusGained(FocusEvent evt) {
        if(bdayText1.getText().equalsIgnoreCase("DD/MM/YYYY"))
            bdayText1.setText(null);
	}

    private void bdayText1FocusLost(FocusEvent evt) {
        if(bdayText1.getText().equalsIgnoreCase(""))
            bdayText1.setText("DD/MM/YYYY");
	}

    private void clearButton1ActionPerformed(ActionEvent evt) {
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

    private void yearText1FocusGained(FocusEvent evt) {
        if(yearText1.getText().equalsIgnoreCase("YYYY"))
            yearText1.setText(null);
	}
	
    private void yearText1FocusLost(FocusEvent evt) {
        if(yearText1.getText().equalsIgnoreCase(""))
            yearText1.setText("YYYY");
	}
}
