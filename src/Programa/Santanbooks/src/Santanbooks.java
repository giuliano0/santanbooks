package Programa.Santanbooks.src;

import *;
import *;

@SuppressWarnings("serial")
public class Santanbooks extends JFrame {
    
    public static final String HOME_PAGE = "home";
    public static final String REGISTER = "register";
    
    private JPanel jpExibicao;
	
    public Santanbooks() {
        super("Santanbooks");
        setSize(800,600);
        setLocationRelativeTo(null);
        initComponents();
        
        this.addPanel(new Home(this), HOME_PAGE);
        this.addPanel(new Register(this), REGISTER);
        
        this.changePanel(HOME_PAGE);
    }
    
    public void addPanel(JPanel novo, String nome) {
        this.jpExibicao.add(novo,nome);
    }
    
    public void changePanel(String nome) {
        ((CardLayout)this.jpExibicao.getLayout()).show(this.jpExibicao, nome);
    }

    private void initComponents() {

        jpExibicao = new JPanel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        setMinimumSize(new Dimension(800, 600));
        setName("Santanbooks");
        setResizable(false);

        jpExibicao.setLayout(new CardLayout());

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jpExibicao, GroupLayout.PREFERRED_SIZE, 820, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jpExibicao, GroupLayout.PREFERRED_SIZE, 622, GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }                    

    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");   
                }
                catch (Exception e) {}
                
                new Santanbooks().setVisible(true);
            }
        });
    }
}
