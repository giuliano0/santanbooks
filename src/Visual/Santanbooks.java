package Visual;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import Classes.User;
import Interfaces.IBusinessObject;
import Interfaces.IDataBase;
import Interfaces.ISQLStatements;
import Interfaces.ISearchEngine;
import Interfaces.ISessionManager;
import SearchEngine.IReceptacleBussinessObject;
import SearchEngine.IReceptacleDataBase;
import anima.component.IRequires;
import anima.component.InterfaceType;
import anima.factory.IGlobalFactory;
import anima.factory.context.componentContext.ComponentContextFactory;

public class Santanbooks extends JFrame {
    public static final String PANEL_HOME = "home";
    public static final String PANEL_REG_USER = "reguser";
    public static final String PANEL_PROFILE = "profile";
    public static final String PANEL_RESULTS = "results";
    public static final String PANEL_BOOK = "book";
    public static final String PANEL_ADD_BOOK = "addbook";

    public static final String SESSION_NOT_LOGGED = "notlogged";
    public static final String SESSION_LOGGED = "logged";

    private JPanel principal, sessionManager;
    private String lastPanel;
    
    private IBusinessObject bo;
	private ISQLStatements sqls;
	private ISearchEngine se;
	private ISessionManager sm;
	private IDataBase db;
	private User usuarioLogado;

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
    
    public JPanel getPanel(String panel) {
    	if (panel.equals(PANEL_HOME))
    		return ((JPanel)principal.getComponent(0));
    	if (panel.equals(PANEL_REG_USER))
    		return ((JPanel)principal.getComponent(1));
    	if (panel.equals(PANEL_PROFILE))
    		return ((JPanel)principal.getComponent(2));
    	if (panel.equals(PANEL_RESULTS))
    		return ((JPanel)principal.getComponent(3));
    	if (panel.equals(PANEL_BOOK))
    		return ((JPanel)principal.getComponent(4));
    	if (panel.equals(PANEL_ADD_BOOK))
    		return ((JPanel)principal.getComponent(5));
    	
    	return null;
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
        initializeFactory();

        setVisible(true);
    }

    private void setHomePage() {
        addPanel(new Home(this), PANEL_HOME);
        addPanel(new RegUser(this), PANEL_REG_USER);
        addPanel(new ShowUser(this), PANEL_PROFILE);
        addPanel(new Results(this), PANEL_RESULTS);
        addPanel(new Book(this), PANEL_BOOK);
        addPanel(new AddBook(this), PANEL_ADD_BOOK);

        sessionManager.add(new SessionManagerLoggedIn(this), SESSION_LOGGED);
        sessionManager.add(new SessionManagerLogin(this), SESSION_NOT_LOGGED);

        changePanel(PANEL_RESULTS);
        ((CardLayout)sessionManager.getLayout()).show(sessionManager, SESSION_NOT_LOGGED);
    }
    
    private void initializeFactory() {
    	try {
    		IGlobalFactory factory = ComponentContextFactory.createGlobalFactory();
    		
    		sqls = factory.createInstance("<http://purl.org/dcc/JavaDB.SQLStatements>",
					"<http://purl.org/dcc/Interfaces.ISQLStatements>");
    		db = factory.createInstance("<http://purl.org/dcc/JavaDB.DataBase>",
    				"<http://purl.org/dcc/Interfaces.IDataBase>");
    		bo = factory.createInstance("<http://purl.org/dcc/DataBase.BusinessObject>",
					"<http://purl.org/dcc/Interfaces.IBusinessObject>");
    		sm = factory.createInstance("<http://purl.org/dcc/Classes.SessionManager>",
					"<http://purl.org/dcc/Interfaces.ISessionManager>");
    		se = factory.createInstance("<http://purl.org/dcc/SearchEngine.SearchEngine>",
    				"<http://purl.org/dcc/Interfaces.ISearchEngine>");
    		
			IRequires<ISQLStatements> connectStatements = db.queryInterface(
					"<http://purl.org/dcc/Interfaces.ISQLStatements>",
					InterfaceType.REQUIRED);

			IRequires<IDataBase> connectDatabase = bo.queryInterface(
					"<http://purl.org/dcc/Interfaces.IDataBase>",
					InterfaceType.REQUIRED);
			
			IRequires<IBusinessObject> connectStatements3 = sm.queryInterface(
					"<http://purl.org/dcc/Interfaces.IBusinessObject>",
					InterfaceType.REQUIRED);
			
			
			
			
			
			IReceptacleDataBase connectStatements4 = se.queryInterface(
					"<http://purl.org/dcc/Interfaces.IDataBase>",
					InterfaceType.REQUIRED);
			
			IReceptacleBussinessObject connectStatements5 = se.queryInterface(
					"<http://purl.org/dcc/Interfaces.IBusinessObject>",
					InterfaceType.REQUIRED);
			
			
			
			connectStatements.connect(sqls);
			connectDatabase.connect(db);			
			connectStatements3.connect(bo);
			connectStatements4.connect(db);
			connectStatements5.connect(bo);
			bo.sign(se);
			se.update();
    	}
    	catch(Exception e) {
    		JOptionPane.showMessageDialog(null, e.getMessage(), "Santanbooks", JOptionPane.ERROR_MESSAGE);
    	}
    }
    
    public IBusinessObject getBusinessObject() {
    	return this.bo;
    }
	public ISQLStatements getStatements() { 
		return this.sqls;
	}
	public ISearchEngine getSearchEngine() {
		return this.se;
	}
	public ISessionManager getSessionManager() {
		return this.sm;
	}
	public IDataBase getDatabase() {
		return this.db;
	}
	
	public void setUser(User u) {
		this.usuarioLogado = u;
	}
	public User getUser() {
		return this.usuarioLogado;
	}
	
	public JPanel getSM() {
		return this.sessionManager;
	}
	
    public static void main(String args[]) {
        Santanbooks novo = new Santanbooks();
    }
}
