import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Santanbooks extends JFrame {
    public static final String PANEL_HOME = "home";
    public static final String PANEL_REG_USER = "reguser";
    public static final String PANEL_EDIT_PROFILE = "editprofile";
    public static final String PANEL_PROFILE = "profile";
    public static final String PANEL_RESULTS = "results";
    public static final String PANEL_BOOK = "book";
    public static final String PANEL_ADD_BOOK = "addbook";

    public static final String SESSION_NOT_LOGGED = "notlogged";
    public static final String SESSION_LOGGED = "logged";

    private JPanel principal, sessionManager;
    private String lastPanel;

    public Santanbooks() {
        super("Santanbooks");
        initializeForm();
    }

    public void addPanel(JPanel panel, String desc) {
        principal.add(panel,desc);
    }

    public void changePanel(String desc) {
        lastPanel = desc;
        ((CardLayout)principal.getLayout()).show(principal, desc);
    }

    public void lastPanel() {
        ((CardLayout)principal.getLayout()).show(principal, lastPanel);
    }

    private void initializeForm() {
        /* JFrame Properties */
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        setSize(800,650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        /* Session Manager Properties */
        sessionManager = new JPanel();
        sessionManager.setLayout(new CardLayout());
        sessionManager.setBounds(0,0,800,50);

        /* Principal Properties */
        principal = new JPanel();
        principal.setLayout(new CardLayout());
        principal.setBounds(0,50,800,600);

        getContentPane().add(sessionManager);
        getContentPane().add(principal);

        setHomePage();

        setVisible(true);
    }

    private void setHomePage() {
        addPanel(new Home(this), PANEL_HOME);
        addPanel(new RegUser(this), PANEL_REG_USER);
        addPanel(new ShowUser(this), PANEL_PROFILE);
        addPanel(new Results(this), PANEL_RESULTS);
        addPanel(new Book(this), PANEL_BOOK);
        addPanel(new AddBook(this), PANEL_ADD_BOOK);
        addPanel(new EditProfile(this), PANEL_EDIT_PROFILE);

        sessionManager.add(new SessionManagerLoggedIn(this), SESSION_LOGGED);
        sessionManager.add(new SessionManagerLogin(this), SESSION_NOT_LOGGED);

        changePanel(PANEL_EDIT_PROFILE);
        ((CardLayout)sessionManager.getLayout()).show(sessionManager, SESSION_LOGGED);
    }

    public static void main(String args[]) {
        Santanbooks novo = new Santanbooks();
    }
}
