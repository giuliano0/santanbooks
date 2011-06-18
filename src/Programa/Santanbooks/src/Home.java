import javax.swing.*;
import java.awt.*;

public class Home extends JPanel {
    private Santanbooks parent;
    
    private JButton btnBuscar, btnLogin;
    private JPasswordField edSenha;
    private JTextField edUsuario, edBusca;
    private JLabel jLabel1, jLabel10, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, lblCadastro, lblComentarios, lblLivros, lblUsuarios;
	
    public Home(Santanbooks cardHolder) {
        initComponents();
        this.parent = cardHolder;
    }

    private void initComponents() {
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        edBusca = new JTextField();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        btnLogin = new JButton();
        btnBuscar = new JButton();
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();
        lblUsuarios = new JLabel();
        lblLivros = new JLabel();
        jLabel10 = new JLabel();
        lblCadastro = new JLabel();
        lblComentarios = new JLabel();
        edUsuario = new JTextField();
        edSenha = new JPasswordField();

        setPreferredSize(new Dimension(800, 600));
        setLayout(null);

        jLabel1.setIcon(new ImageIcon(getClass().getResource("/Santanbooks.png")));
        add(jLabel1);
        jLabel1.setBounds(150, 310, 557, 117);

        jLabel2.setText("Buscar livros:");
        add(jLabel2);
        jLabel2.setBounds(400, 43, 64, 14);
        add(edBusca);
        edBusca.setBounds(470, 40, 260, 20);

        jLabel3.setFont(new Font("Tahoma", 1, 14));
        jLabel3.setText("Login:");
        add(jLabel3);
        jLabel3.setBounds(51, 38, 43, 17);

        jLabel4.setText("Usuário:");
        add(jLabel4);
        jLabel4.setBounds(50, 93, 40, 14);

        jLabel5.setText("Senha:");
        add(jLabel5);
        jLabel5.setBounds(50, 123, 34, 14);

        btnLogin.setFont(new Font("Tahoma", 1, 12));
        btnLogin.setText("Login!");
        add(btnLogin);
        btnLogin.setBounds(90, 150, 108, 40);

        btnBuscar.setText("Buscar");
        add(btnBuscar);
        btnBuscar.setBounds(740, 38, 65, 23);

        jLabel6.setText("Usuários Ativos:");
        add(jLabel6);
        jLabel6.setBounds(490, 110, 90, 14);

        jLabel7.setText("Livros Cadastrados:");
        add(jLabel7);
        jLabel7.setBounds(490, 140, 100, 14);

        lblUsuarios.setText("0");
        add(lblUsuarios);
        lblUsuarios.setBounds(610, 110, 50, 14);

        lblLivros.setText("0");
        add(lblLivros);
        lblLivros.setBounds(610, 140, 50, 14);

        jLabel10.setText("Comentários:");
        add(jLabel10);
        jLabel10.setBounds(490, 170, 80, 14);

        lblCadastro.setText("Ainda não é cadastado? Cadastre-se já!");
        lblCadastro.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblCadastro.addMouseListener(new event.MouseAdapter() {
            public void mouseClicked(event.MouseEvent evt) {
                lblCadastroMouseClicked(evt);
            }
        });
        add(lblCadastro);
        lblCadastro.setBounds(55, 200, 210, 14);

        lblComentarios.setText("0");
        add(lblComentarios);
        lblComentarios.setBounds(610, 170, 50, 14);
        add(edUsuario);
        edUsuario.setBounds(110, 90, 140, 20);
        add(edSenha);
        edSenha.setBounds(110, 120, 140, 20);
    }

    private void lblCadastroMouseClicked(event.MouseEvent evt) {
        this.parent.changePanel(Santanbooks.REGISTER);
    }
}
