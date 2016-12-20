import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.prefs.*;

import javax.swing.*;

public class TariffCreator extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int[] ENDDATUM     = {30,11,2014};
	private final boolean isDemo     = false;
	private String version           = "TariffCreator 1.20";
	private JLabel heading1          = new JLabel(" ");
	private JLabel heading2          = new JLabel("Please choose your prepared file");
	private JLabel heading3          = new JLabel("in order to create a new tariff.");
	private JLabel heading4          = new JLabel("Afterwards set a name and an ID.");
	private JLabel heading5          = new JLabel(" ");
	private JLabel heading6          = new JLabel(" ");
	private JLabel heading7          = new JLabel(" ");
	private JLabel heading8          = new JLabel("In order to read out a prepared tariff     ");
	private JLabel heading9          = new JLabel("choose the path to your file.");
	private JLabel heading10         = new JLabel(" ");
	private JLabel heading11         = new JLabel(" ");
	private JLabel heading12         = new JLabel(" ");
	private JLabel puffer1           = new JLabel(" ");
	private JLabel puffer2           = new JLabel(" ");
	private JLabel puffer3           = new JLabel(" ");
	private JLabel puffer4           = new JLabel(" ");
	private JLabel puffer5           = new JLabel(" ");
	private JLabel puffer6           = new JLabel(" ");
	private JLabel nameLabel         = new JLabel("Tariff Name:");
	private JLabel idLabel           = new JLabel("Tariff ID:");
	private JLabel labelIn           = new JLabel("Tariff File");
	private JLabel labelOut          = new JLabel("Output Folder");
	private JLabel adminLogin        = new JLabel("Admin Login");
	private JButton doit             = new JButton("Start");
	private JButton doit2            = new JButton("Start");
	private JButton search           = new JButton("Search for");
	private JButton searchOutOver    = new JButton("Search for");
	private JButton searchIn         = new JButton("Search for");
	private JButton searchOut        = new JButton("Search for");
	private JButton random           = new JButton("Random");
	private JButton color            = new JButton("C");
	private JButton color2           = new JButton("C");
	private JButton help             = new JButton("?");
	private JButton login            = new JButton("Tiger Login");
	private JButton loginPass        = new JButton("Login");
	private JButton logout           = new JButton("LogOut");
	private JPasswordField pass      = new JPasswordField(10);
	private JPanel headbox           = new JPanel();
	private JPanel headbox2          = new JPanel();
	private JPanel headingBut        = new JPanel();
	private JPanel headingBut2       = new JPanel();
	private JPanel headingAll        = new JPanel();
	private JPanel headingAll2       = new JPanel();
	private JPanel fileIn            = new JPanel();
	private JPanel fileOut           = new JPanel();
	private JPanel file              = new JPanel();
	private JPanel fileOutOver       = new JPanel();
	private JPanel namePanel         = new JPanel();
	private JPanel idPanel           = new JPanel();
	private JPanel butPanel          = new JPanel();
	private JPanel butPanel2         = new JPanel();
	private JPanel mainPanel         = new JPanel();
	private JPanel boxOpt            = new JPanel();
	public  JPanel boxOpt2           = new JPanel();
	private JPanel boxMain           = new JPanel();
	private JPanel boxBlanc          = new JPanel();
	private JPanel boxBlanc2         = new JPanel();
	private JPanel passPanel         = new JPanel();
	private JPanel currPanel         = new JPanel();
	private JPanel cardPanel         = new JPanel();
	private JPanel main1             = new JPanel();
	private JPanel main2             = new JPanel();
	private JPanel main3             = new JPanel();
	private JPanel main4             = new JPanel();
	private JTextField path          = new JTextField(20);
	private JTextField pathOutOver   = new JTextField(20);
	private JTextField pathIn        = new JTextField(20);
	private JTextField pathOut       = new JTextField(20);
	private JTextField name          = new JTextField(17);
	private JTextField id            = new JTextField(10);
	private JRadioButton opt1        = new JRadioButton("Create Tariff",false);
	private JRadioButton opt2        = new JRadioButton("Create Overview",false);
	private JRadioButton opt3        = new JRadioButton("Custom Creator",false);
	private ButtonGroup opt          = new ButtonGroup();
	private JRadioButton minorOpt    = new JRadioButton("Minor Currency",true);
	private JRadioButton majorOpt    = new JRadioButton("Major Currency",false);
	private ButtonGroup currency     = new ButtonGroup();
	private CardLayout cardLayout    = new CardLayout();
	private CustomerCreator CCreator = new CustomerCreator();
	
	private String currFileIn;
	private String currFileOut;
	private String currName;
	private String currID;
	
	private int n = 0;
	private boolean curr = false;
	
	private GregorianCalendar date = new GregorianCalendar();
	private int day            = date.get(Calendar.DAY_OF_MONTH);
	private int month          = date.get(Calendar.MONTH)+1;
	private int year           = date.get(Calendar.YEAR);
	
	public TariffCreator()
	{
		Preferences prefs;
		prefs = Preferences.userRoot().node(this.getClass().getName());
		String lastPathOut = "lastPathOut";
		pathOut.setText(prefs.get(lastPathOut,""));
		pathOutOver.setText(prefs.get(lastPathOut,""));
		
		getContentPane().setBackground(Color.GRAY);
		mainPanel.setBackground(null);
		boxOpt.setBackground(null);
		opt1.setBackground(null);
		opt2.setBackground(null);
		opt3.setBackground(null);
		minorOpt.setBackground(null);
		majorOpt.setBackground(null);
		currPanel.setBackground(null);
		boxMain.setBackground(null);
		boxBlanc.setBackground(null);
		headingBut.setBackground(null);
		headingBut2.setBackground(null);
		headingAll.setBackground(null);
		headingAll2.setBackground(null);
		namePanel.setBackground(null);
		butPanel.setBackground(null);
		butPanel2.setBackground(null);
		headbox.setBackground(null);
		headbox2.setBackground(null);
		idPanel.setBackground(null);
		fileIn.setBackground(null);
		fileOut.setBackground(null);
		file.setBackground(null);
		fileOutOver.setBackground(null);
		boxBlanc2.setBackground(null);
		CCreator.main().setBackground(null);
		
		help.addActionListener(this);
		help.setActionCommand("help");
		color.addActionListener(this);
		color.setActionCommand("color");
		color2.addActionListener(this);
		color2.setActionCommand("color");
		doit.addActionListener(this);
		doit.setActionCommand("doit");
		doit2.addActionListener(this);
		doit2.setActionCommand("doit2");
		search.addActionListener(this);
		search.setActionCommand("searchIn");
		searchOutOver.setActionCommand("searchOut");
		searchOutOver.addActionListener(this);
		searchIn.addActionListener(this);
		searchIn.setActionCommand("searchIn");
		searchOut.addActionListener(this);
		searchOut.setActionCommand("searchOut");
		random.addActionListener(this);
		random.setActionCommand("random");
		login.addActionListener(this);
		login.setActionCommand("login");
		logout.addActionListener(this);
		logout.setActionCommand("logout");
		loginPass.addActionListener(this);
		loginPass.setActionCommand("loginPass");
		pass.addActionListener(this);
		pass.setActionCommand("loginPass");
		opt1.setActionCommand("opt1");
		opt1.addActionListener(this);
		opt2.setActionCommand("opt2");
		opt2.addActionListener(this);
		opt3.setActionCommand("opt3");
		opt3.addActionListener(this);
		minorOpt.setActionCommand("minorOpt");
		minorOpt.addActionListener(this);
		majorOpt.setActionCommand("majorOpt");
		majorOpt.addActionListener(this);
		
		headbox.setLayout( new BoxLayout( headbox, BoxLayout.Y_AXIS ) );
		headbox2.setLayout( new BoxLayout( headbox2, BoxLayout.Y_AXIS ) );
		headingBut.setLayout( new BoxLayout( headingBut, BoxLayout.Y_AXIS ) );
		headingBut2.setLayout( new BoxLayout( headingBut2, BoxLayout.Y_AXIS ) );
		currPanel.setLayout( new BoxLayout( currPanel, BoxLayout.Y_AXIS));
		boxOpt.setLayout(new BoxLayout(boxOpt,BoxLayout.Y_AXIS));
		boxMain.setLayout(new BoxLayout(boxMain,BoxLayout.Y_AXIS));
		main1.setLayout(new BoxLayout(main1, BoxLayout.Y_AXIS));
		main1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		main2.setLayout(new BoxLayout(main2, BoxLayout.Y_AXIS));
		main2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		main3.setLayout(new BoxLayout(main3, BoxLayout.Y_AXIS));
		main3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		//leftside menu
		opt.add(opt1); // --
		opt.add(opt2); // --
		opt.add(opt3); // Radio Button Group
		boxOpt.add(login);
		boxOpt.add(opt3); // without login, just opt3 (custom creator) is visible
		//tariff creator
		currency.add(minorOpt); // --
		currency.add(majorOpt); // Radio Button group
		headbox.add(heading1);
		headbox.add(heading2);
		headbox.add(heading3);
		headbox.add(heading4);
		headbox.add(heading5);
		headbox.add(heading6);
		headingBut.add(help);
		headingBut.add(color);
		headingAll.add(headbox);
		headingAll.add(headingBut);
		fileIn.add(labelIn);
		fileIn.add(searchIn);
		fileIn.add(pathIn);
		fileOut.add(labelOut);
		fileOut.add(searchOut);
		fileOut.add(pathOut);
		namePanel.add(nameLabel);
		namePanel.add(name);
		idPanel.add(idLabel);
		idPanel.add(random);
		idPanel.add(id);
		currPanel.add(minorOpt);
		currPanel.add(majorOpt);
		butPanel.add(doit);
		main1.add(headingAll);
		main1.add(fileIn);
		main1.add(fileOut);
		main1.add(namePanel);
		main1.add(idPanel);
		main1.add(currPanel);
		main1.add(butPanel);
		//tariff overview
		headbox2.add(heading7);
		headbox2.add(heading8);
		headbox2.add(heading9);
		headbox2.add(heading10);
		headbox2.add(heading11);
		headbox2.add(heading12);
		headingBut2.add(color2);
		headingAll2.add(headbox2);
		headingAll2.add(headingBut2);
		fileOutOver.add(new JLabel("Output Folder:"));
		fileOutOver.add(searchOutOver);
		fileOutOver.add(pathOutOver);
		file.add(new JLabel("Inf File:"));
		file.add(search);
		file.add(path);
		butPanel2.add(doit2);
		main2.add(headingAll);
		main2.add(fileOutOver);
		main2.add(file);
		main2.add(butPanel2);
		//Custom Creator
		main3.add(CCreator.main());
		boxMain.add(mainPanel);
		boxMain.add(puffer4);
		//main3.add(boxMain);
		// login Panel
		passPanel.add(adminLogin);
		passPanel.add(pass);
		passPanel.add(loginPass);
		main4.add(passPanel);
		
		cardPanel.setLayout(cardLayout);
		cardPanel.add(main1, "main1"); // Tariff Creator
		cardPanel.add(main2, "main2"); // Tariff Overview
		cardPanel.add(main3, "main3"); // Custom Creator
		cardPanel.add(main4, "login"); // login Panel
		
		if(isDemo)
		{
			if( versionTest() )
			{
				setTitle (version+" - Demo - "+ENDDATUM[0]+"."+ENDDATUM[1]+"."+ENDDATUM[2]);
				add(puffer5,BorderLayout.NORTH);
				add(boxOpt,BorderLayout.WEST);
				add(cardPanel,BorderLayout.CENTER);
				add(boxBlanc,BorderLayout.EAST);
				add(puffer6,BorderLayout.SOUTH);
				add(new JLabel(day+"."+month+"."+year),BorderLayout.SOUTH);
			}
			else
			{
				setTitle("Demo End");
				JPanel demo = new JPanel();
				demo.setLayout(new BoxLayout(demo,BoxLayout.Y_AXIS));
				demo.add(new JLabel("The licence is not valid anymore!"));
				demo.add(new JLabel("Please contact the author."));
				demo.add(new JLabel(" "));
				demo.add(new JLabel("jan.benten@tigertms.de"));
				add(demo);
			}
		}
		else
		{
			setTitle (version);
			if(prefs.getBoolean("isEngineer", false))
			{
				setTitle (version+" - Engineer");
				pass.setText("");
				boxOpt.removeAll();
				boxOpt.add(logout);
				boxOpt.add(opt3);
				boxOpt.add(opt1);
				boxOpt.add(opt2);
				setSize(980,622);
				repaint();
			}
			add(puffer5,BorderLayout.NORTH);
			add(boxOpt,BorderLayout.WEST);
			add(cardPanel,BorderLayout.CENTER);
			add(boxBlanc,BorderLayout.EAST);
			add(puffer6,BorderLayout.SOUTH);
		}
		path.setEditable(false);
		pathOutOver.setEditable(false);
		pathIn.setEditable(false);
		pathOut.setEditable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private boolean versionTest()
	{
		GregorianCalendar dateEND   = new GregorianCalendar(ENDDATUM[2], ENDDATUM[1]-1, ENDDATUM[0]);
		Preferences prefs;
		prefs = Preferences.userRoot().node(this.getClass().getName());
		String versionDemo = "versionDemo";
		String lastRun  = "lastRun";
		
		//prefs.putBoolean(versionDemo,true);
		//prefs.put(lastRun, ""+date.getTimeInMillis());
		
		if( (Double.parseDouble(""+date.getTimeInMillis()) < Double.parseDouble((""+dateEND.getTimeInMillis()))) && (Double.parseDouble(prefs.get(lastRun, "0")) < Double.parseDouble((""+date.getTimeInMillis()))) && (prefs.getBoolean(versionDemo,true)) )
		{
			prefs.put(lastRun, ""+date.getTimeInMillis());
			return true;
		}
		else
		{
			prefs.putBoolean(versionDemo,false);
			return false;
		}
	}
	
	public void actionPerformed(ActionEvent evt)
	{
		Preferences prefs;
		prefs = Preferences.userRoot().node(this.getClass().getName());
		String lastPathOut = "lastPathOut";
		currFileOut = pathOut.getText();
		
		// -----------------------------------------------------------------
		// Show login
		if (evt.getActionCommand().equals("login"))
		{
			cardLayout.show(cardPanel,"login");
		}
		
		if (evt.getActionCommand().equals("logout"))
		{
			boxOpt.removeAll();
			boxOpt.add(login);
			boxOpt.add(opt3);
			setSize(981,652);
			repaint();
		}
		
		if (evt.getActionCommand().equals("loginPass"))
		{
			char[] input = pass.getPassword();
			boolean isCorrect  = false;
			boolean isEngineer = false;
		    char[] correctPassword = {'t','a','r','i','f','f','q','u','e','e','n'};
		    String correctEngineer = "t1G3rU53r";

		    if (input.length != correctPassword.length) {
		        isCorrect = false;
		    } else {
		        isCorrect = Arrays.equals (input, correctPassword);
		    }
		    if (input.length != correctEngineer.length()) {
		        isEngineer = false;
		    } else {
		        isEngineer = Arrays.equals (input, correctEngineer.toCharArray());
		    }
			 
			if (isCorrect)
			{
				pass.setText("");
				boxOpt.removeAll();
				boxOpt.add(logout);
				boxOpt.add(opt3);
				boxOpt.add(opt1);
				boxOpt.add(opt2);
				//setSize(980,622);
				repaint();
				cardLayout.show(cardPanel, "main1");
			}
			else
			{
				if(isEngineer)
				{
					prefs.putBoolean("isEngineer", true);
					setTitle (version+" - Engineer");
					pass.setText("");
					boxOpt.removeAll();
					boxOpt.add(logout);
					boxOpt.add(opt3);
					boxOpt.add(opt1);
					boxOpt.add(opt2);
					setSize(980,622);
					repaint();
				}
				else
					JOptionPane.showMessageDialog(null, "The entered password is incorrect!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		// -----------------------------------------------------------------
		// Show Tariff Creator
		if (evt.getActionCommand().equals("opt1"))
		{
			cardLayout.show(cardPanel,"main1");
		}
		
		// -----------------------------------------------------------------
		// Show Tariff Overview
		if (evt.getActionCommand().equals("opt2"))
		{
			cardLayout.show(cardPanel,"main2");
		}
		
		// -----------------------------------------------------------------
		// Show Custom Creator
		if (evt.getActionCommand().equals("opt3"))
		{
			cardLayout.show(cardPanel,"main3");
		}
		
		if (evt.getActionCommand().equals("minorOpt"))
			curr = false;
		
		if (evt.getActionCommand().equals("majorOpt"))
			curr = true;
		
		if (evt.getActionCommand().equals("color"))
		{
			if (n==6)
				n=0;
			
			if (n == 5)
			{
				getContentPane().setBackground(Color.GRAY);
				repaint();
				n++;
			}
			if (n == 4)
			{
				getContentPane().setBackground(Color.ORANGE);
				repaint();
				n++;
			}
			if (n == 3)
			{
				getContentPane().setBackground(Color.RED);
				repaint();
				n++;
			}
			if (n == 2)
			{
				getContentPane().setBackground(Color.GREEN);
				repaint();
				n++;
			}
			if (n == 1)
			{
				getContentPane().setBackground(Color.YELLOW);
				repaint();
				n++;
			}
			if (n == 0)
			{
				getContentPane().setBackground(Color.BLUE);
				repaint();
				n++;
			}
		}
		
		if (evt.getActionCommand().equals("searchIn"))
		{
			JFileChooser fc = new JFileChooser();
			fc.setDialogTitle("Select folder");
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

			if(fc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){}
			String currFil = fc.getSelectedFile().getAbsolutePath();
			pathIn.setText(currFil);
			path.setText(currFil);
			repaint();
			currFileIn = currFil;
		}
		
		if (evt.getActionCommand().equals("searchOut"))
		{
			JFileChooser fc = new JFileChooser();
			fc.setDialogTitle("Select folder");
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			if(fc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){}
			String currFil = fc.getSelectedFile().getAbsolutePath();
			pathOut.setText(currFil);
			pathOutOver.setText(currFil);
			prefs.put(lastPathOut,currFil);
			repaint();
			currFileOut = pathOut.getText();
		}
		
		if (evt.getActionCommand().equals("random"))
		{
			int n = 0;
			String idTemp = "";
			Random rand = new Random();
			while (n < 4)
			{
				idTemp = (rand.nextInt(10)+"" + idTemp);
				n++;
			}
			id.setText(idTemp);
		}
		
		if (evt.getActionCommand().equals("doit"))
		{
			try
			{
				currFileOut = pathOut.getText();
				currName    = name.getText();
				currID      = id.getText();
				
				if ((currName.equals(null))||(currName.equals(""))||(currName.equals(" ")))
					JOptionPane.showMessageDialog(null, "You have not set a name yet. Please check your input.", "Error", JOptionPane.ERROR_MESSAGE);
				else
				{
					if ( (currFileOut.equals(null)) || (currFileOut.equals("")) )
						JOptionPane.showMessageDialog(null, "Please check your output Folder. You have not chosen an output folder yet.", "Error", JOptionPane.ERROR_MESSAGE);
					else
					{
						
						if ((currID.equals(null))||(currID.equals(""))||(isNotNumeric(currID)))
							JOptionPane.showMessageDialog(null, " Please check your input. There is no ID or a wrong ID! You can only use digits here.", "Error", JOptionPane.ERROR_MESSAGE);
						else
							Creator.Create(currFileIn, currFileOut, currName, currID, curr);
					}
				}
			}
			catch (NullPointerException e)
	        {
	        	JOptionPane.showMessageDialog(null, "ErrorID:001 - Please check your file. You have not set a compatible input file.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
		}
		
		if (evt.getActionCommand().equals("doit2"))
		{
			try
			{
				OverviewCreator.create(currFileIn, currFileOut);
			}
			catch (NullPointerException e)
	        {
	        	JOptionPane.showMessageDialog(null, "Please check your file. You have not set a file yet.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
		}
		
		if (evt.getActionCommand().equals("help"))
			JOptionPane.showMessageDialog(null, "The pattern of the input file must be as follows: \nprefix;name;price;setup charge (optional) \n\nE.g.: \n001;USA;100;10 \n\nAll prices must be set in your minor unit of currency.", "Help", JOptionPane.QUESTION_MESSAGE);
	}
	
	public static boolean isNotNumeric(String str)  
	{  
		try  
			{  
		    	Double.parseDouble(str);  
			}  
		catch(NumberFormatException nfe)  
			{  
				return true;  
			}  
		return false;
	}
	
	public static void main(String[] args) 
	{
		TariffCreator tariffApp = new TariffCreator();
		
		tariffApp.setSize(980,650);
		tariffApp.setVisible(true);
	}
}
