import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.tree.*;

import System.CheckTreeManager;
import System.OnlyIntEditor;
import System.NullEditor;


public class CustomerCreator implements ActionListener
{
	//Window Objects
	JPanel main                       = new JPanel();
	JPanel creator                    = new JPanel();
	JPanel pricePanel                 = new JPanel();
	JPanel setupPanel                 = new JPanel();
	JPanel pricebuttonPanel           = new JPanel();
	JPanel toolPanel                  = new JPanel();
	JPanel treePanel                  = new JPanel();
	JPanel listPanel                  = new JPanel();
	JPanel cardPanel                  = new JPanel();
	JTextField price                  = new JTextField(5);
	JTextField setup                  = new JTextField(5);
	JButton changePrice               = new JButton("OK");
	JButton changeSetup               = new JButton("OK");
	JButton toolList                  = new JButton(" List ");
	JButton toolTree                  = new JButton(" Tree ");
	JButton toolClear                 = new JButton("Clear All");
	JButton toolGenerate              = new JButton("Start Generate");
	JSplitPane split                  = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	JToolBar toolBar                  = new JToolBar("Tariff Creator");
	CardLayout cardLayout             = new CardLayout();
	
	//JTree Objects
	CustomerCreatorCountries CCC      = new CustomerCreatorCountries();
	DefaultTreeModel treeModel        = new DefaultTreeModel(CCC.getCountries());
	JTree tree                        = new JTree( treeModel );
	JScrollPane scroll                = new JScrollPane(tree);
	CheckTreeManager checkTreeManager = new CheckTreeManager(tree);
	TreePath path;
	
	//JTable Objects
	String[][] rowData;
	String[] columnNames              = {"Prefix","Land","Price","Setup"};
	DefaultTableModel tableModel      = new DefaultTableModel(rowData,columnNames);
	JTable table                      = new JTable(tableModel);
	JScrollPane scroll2               = new JScrollPane(table);
	final TableRowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(tableModel);
	
	//List Objects
	OnlyIntEditor editInt            = new OnlyIntEditor();
	NullEditor editNull              = new NullEditor();
	String[][] listRowData           ;
	String[] listColumnNames         = {"Prefix","Land","Price","Setup"};
	DefaultTableModel listTableModel = new DefaultTableModel(listRowData,listColumnNames);
	JTable tableList                 = new JTable(CCC.getList());
	JScrollPane scrollList           = new JScrollPane(tableList);
	final TableRowSorter<TableModel> listRowSorter = new TableRowSorter<TableModel>(tableList.getModel());
	
	int tableTree=0;
	String[][] data = new String[10000][4];
	int index = 0;
	int n = 0;
	int n1= 0;
	int n2= 0;
	
	public CustomerCreator()
	{
		table.setRowSorter(rowSorter);
		tableList.setRowSorter(listRowSorter);
		tableList.getColumn("Prefix").setCellEditor(editNull);
		tableList.getColumn("Land").setCellEditor(editNull);
		tableList.getColumn("Price").setCellEditor(editInt);
		tableList.getColumn("Setup").setCellEditor(editInt);
		tableList.getColumn("Land").setPreferredWidth(200);
		
		// JTree Settings
		TreeSelectionModel tsm = new DefaultTreeSelectionModel();
		tsm.setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
		tree.setSelectionModel(tsm);
		tree.setRootVisible(true);
		tree.setMinimumSize(null);
		table.setEnabled(false);
		
		creator.setLayout( new BoxLayout( creator, BoxLayout.X_AXIS ) );
		pricebuttonPanel.setLayout(new BoxLayout(pricebuttonPanel, BoxLayout.Y_AXIS));
		main.setLayout( new BoxLayout( main, BoxLayout.Y_AXIS ) );
		
		changePrice.addActionListener(this);
		changePrice.setActionCommand("changePrice");
		changeSetup.addActionListener(this);
		changeSetup.setActionCommand("changeSetup");
		price.addActionListener(this);
		price.setActionCommand("changePrice");
		setup.addActionListener(this);
		setup.setActionCommand("changeSetup");
		toolList.addActionListener(this);
		toolList.setActionCommand("list");
		toolTree.addActionListener(this);
		toolTree.setActionCommand("tree");
		toolClear.addActionListener(this);
		toolClear.setActionCommand("clear");
		toolGenerate.addActionListener(this);
		toolGenerate.setActionCommand("generate");
		
		toolBar.setFloatable(false);
		toolBar.setRollover(true);
		toolBar.setSize(20,200);
		toolBar.add(toolTree);
		toolTree.setEnabled(false);
		toolBar.add(toolList);
		toolBar.add(toolClear);
		toolBar.add(toolGenerate);
		toolPanel.add(toolBar,BorderLayout.WEST);
		
		scroll.setMinimumSize(new Dimension(275,100));
		
		split.setLeftComponent(scroll);
		split.setRightComponent(scroll2);
		split.setDividerLocation( 0.5 );
		split.setAlignmentX(Component.RIGHT_ALIGNMENT);
		split.setMinimumSize(new Dimension(700,100) );
		
		pricePanel.add(new JLabel("Insert the Price p.Min.: "));
		pricePanel.add(price);
		pricePanel.add(changePrice);
		setupPanel.add(new JLabel("Insert the Setup Price: "));
		setupPanel.add(setup);
		setupPanel.add(changeSetup);
		pricebuttonPanel.add(pricePanel);
		pricebuttonPanel.add(setupPanel);
		creator.add(split);
		treePanel.add(creator);
		treePanel.add(pricebuttonPanel);
		listPanel.add(scrollList);
		
		cardPanel.setLayout(cardLayout);
		cardPanel.add(treePanel,"treeCreator");
		cardPanel.add(listPanel,"listCreator");
		
		main.add(toolPanel);
		main.add(cardPanel);
	}
	
	public void actionPerformed(ActionEvent evt)
	{
		
		//_________________________________________________________________
		//show List
		if(evt.getActionCommand().equals("list"))
		{
			clear();
			toolTree.setEnabled(true);
			toolList.setEnabled(false);
			cardLayout.show(cardPanel,"listCreator");
		}
		
		//_________________________________________________________________
		//show Tree
		if(evt.getActionCommand().equals("tree"))
		{
			clear();
			toolTree.setEnabled(false);
			toolList.setEnabled(true);
			cardLayout.show(cardPanel, "treeCreator");
		}
		
		//_________________________________________________________________
		//generate Tariff
		if(evt.getActionCommand().equals("generate"))
		{
			n=0;
			while(tableModel.getRowCount()>n)
			{
				System.out.println( tableModel.getValueAt(n,0)+";"+tableModel.getValueAt(n,1)+";"+tableModel.getValueAt(n,2)+";"+tableModel.getValueAt(n,3) );
				n++;
			}
			if(n==0)
				{
				while(tableList.getModel().getRowCount()>n)
				{
					System.out.println(tableList.getModel().getValueAt(n,0)+";"+tableList.getModel().getValueAt(n,1)+";"+tableList.getModel().getValueAt(n,2)+";"+tableList.getModel().getValueAt(n,3) );
					n++;
				}
			}
			n=0;
			
			FileWriter writer;
    		File file = new File( "TariffTable.csv");
    		n = 0;
    		
    		try
    		{
    			writer = new FileWriter(file);
    			while(tableModel.getRowCount()>n)
    			{
    				writer.write( tableModel.getValueAt(n,0)+";"+tableModel.getValueAt(n,1)+";"+tableModel.getValueAt(n,2)+";"+tableModel.getValueAt(n,3)+"\r\n" );
    				n++;
    			}
    			if(n==0)
    				{
    				while(tableList.getModel().getRowCount()>n)
    				{
    					writer.write(tableList.getModel().getValueAt(n,0)+";"+tableList.getModel().getValueAt(n,1)+";"+tableList.getModel().getValueAt(n,2)+";"+tableList.getModel().getValueAt(n,3)+"\r\n" );
    					n++;
    				}
    			}
    			n=0;
    			
    			writer.flush();
    			writer.close();
    		}
    		catch (IOException e)
            {
            	System.out.println("Exception: "+e.getMessage());
            	JOptionPane.showMessageDialog(null, "Writing Error.", "Erorr", JOptionPane.ERROR_MESSAGE);
            }
		}
		
		//_________________________________________________________________
		//Clear all prices
		if(evt.getActionCommand().equals("clear"))
		{
			clear();
		}
		//_________________________________________________________________
		//Price per Min value change
		if(evt.getActionCommand().equals("changePrice"))
		{
			boolean b = TariffCreator.isNotNumeric(price.getText());
			TreePath checkedPaths[] = checkTreeManager.getSelectionModel().getSelectionPaths();
			DefaultMutableTreeNode root = (DefaultMutableTreeNode) treeModel.getRoot();
			
			if(!b)
			{
				n             = 0;
				n1            = 0;
				int length    = 0;
				String name   = "";
				String prefix = "";
				String setup  = "";
				char[] c;
				
				if(root.equals(((DefaultMutableTreeNode) checkedPaths[0].getLastPathComponent())))
				{
					//All selected
					while(n<root.getChildCount())
					{
						length = root.getChildAt(n).toString().length();
						c      = root.getChildAt(n).toString().toCharArray();
						
						name   = label(c,length);
						prefix = prefix(c);
						setup  = setup(root.getChildAt(n).toString());
						
						((DefaultMutableTreeNode) root.getChildAt(n)).setUserObject( name +" | "+ price.getText() +" | "+ setup );
						treeModel.nodeChanged(root.getChildAt(n));
						setTable(name,price.getText(),setup);
						name   = "";
						prefix = "";
						setup  = "";
						
						if(!root.getChildAt(n).isLeaf())
						{
							while(n1<root.getChildAt(n).getChildCount())
							{
								length = root.getChildAt(n).getChildAt(n1).toString().length();
								c      = root.getChildAt(n).getChildAt(n1).toString().toCharArray();
								
								name   = label(c,length);
								prefix = prefix(c);
								setup  = setup(root.getChildAt(n).getChildAt(n1).toString());
								
								((DefaultMutableTreeNode) root.getChildAt(n).getChildAt(n1)).setUserObject( name +" | "+ price.getText() +" | "+ setup );
								treeModel.nodeChanged(root.getChildAt(n).getChildAt(n1));
								setTable(name,price.getText(),setup);
								name   = "";
								prefix = "";
								setup  = "";
								n1++;
							}
						}
						n++;
						n1=0;
					}
					n=0;
				}
				else
				{
					//The first leaf
					while(n < checkedPaths.length)
					{
						length = ((DefaultMutableTreeNode) checkedPaths[n].getLastPathComponent()).toString().length();
						c      = ((DefaultMutableTreeNode) checkedPaths[n].getLastPathComponent()).toString().toCharArray();
						
						name   = label(c,length);
						prefix = prefix(c);
						setup  = setup(((DefaultMutableTreeNode) checkedPaths[n].getLastPathComponent()).toString());
						
						((DefaultMutableTreeNode) checkedPaths[n].getLastPathComponent()).setUserObject( name +" | "+ price.getText() +" | "+ setup );
						treeModel.nodeChanged(((DefaultMutableTreeNode) checkedPaths[n].getLastPathComponent()));
						
						setTable(name,price.getText(),setup);
						name   ="";
						prefix ="";
						setup  ="";
						n++;
					}
					n=0;
					
					
					//all Childs
					while(n < checkedPaths.length)
					{
						while(n2 < ((DefaultMutableTreeNode) checkedPaths[n].getLastPathComponent()).getChildCount())
						{
							length = ((DefaultMutableTreeNode) checkedPaths[n].getLastPathComponent()).getChildAt(n2).toString().length();
							c      = ((DefaultMutableTreeNode) checkedPaths[n].getLastPathComponent()).getChildAt(n2).toString().toCharArray();
							
							name   = label(c,length);
							prefix = prefix(c);
							setup  = setup(((DefaultMutableTreeNode) checkedPaths[n].getLastPathComponent()).getChildAt(n2).toString());
							
							((DefaultMutableTreeNode) ((DefaultMutableTreeNode) checkedPaths[n].getLastPathComponent()).getChildAt(n2)).setUserObject( name +" | "+ price.getText() +" | "+ setup );
							treeModel.nodeChanged(((DefaultMutableTreeNode) checkedPaths[n].getLastPathComponent()).getChildAt(n2));
							
							setTable(name,price.getText(),setup);
							n1=0;
							n2++;
							name   ="";
							prefix ="";
							setup  ="";
						}
						n2=0;
						n++;
					}
					n=0;
				}
			}
			else
				JOptionPane.showMessageDialog(null, "Please insert only numbers as price.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		//_________________________________________________________________
		//Price Setup value change
		if(evt.getActionCommand().equals("changeSetup"))
		{
			boolean b = TariffCreator.isNotNumeric(setup.getText());
			TreePath checkedPaths[] = checkTreeManager.getSelectionModel().getSelectionPaths();
			DefaultMutableTreeNode root = (DefaultMutableTreeNode) treeModel.getRoot();
			
			if(!b)
			{
				n             = 0;
				n1            = 0;
				int length    = 0;
				String name   = "";
				String prefix = "";
				String price  = "";
				char[] c;
				
				if(root.equals(((DefaultMutableTreeNode) checkedPaths[0].getLastPathComponent())))
				{
					//All selected
					while(n<root.getChildCount())
					{
						length = root.getChildAt(n).toString().length();
						c      = root.getChildAt(n).toString().toCharArray();
						
						name   = label(c,length);
						prefix = prefix(c);
						price  = price(root.getChildAt(n).toString());

						((DefaultMutableTreeNode) root.getChildAt(n)).setUserObject( name +" | "+ price +" | "+setup.getText());
						treeModel.nodeChanged(root.getChildAt(n));
						setTable(name,price,setup.getText());
						name   = "";
						prefix = "";
						
						if(!root.getChildAt(n).isLeaf())
						{
							while(n1<root.getChildAt(n).getChildCount())
							{
								length = root.getChildAt(n).getChildAt(n1).toString().length();
								c      = root.getChildAt(n).getChildAt(n1).toString().toCharArray();
								
								name   = label(c,length);
								prefix = prefix(c);
								price  = price(root.getChildAt(n).getChildAt(n1).toString());
								
								((DefaultMutableTreeNode) root.getChildAt(n).getChildAt(n1)).setUserObject( name +" | "+ price +" | "+setup.getText() );
								treeModel.nodeChanged(root.getChildAt(n).getChildAt(n1));
								setTable(name,price,setup.getText());
								name   = "";
								prefix = "";
								n1++;
							}
						}
						n++;
						n1=0;
					}
					n=0;
				}
				else
				{
					//Multi Selection
					while(n < checkedPaths.length)
					{
						length = ((DefaultMutableTreeNode) checkedPaths[n].getLastPathComponent()).toString().length();
						c      = ((DefaultMutableTreeNode) checkedPaths[n].getLastPathComponent()).toString().toCharArray();
						
						name   = label(c,length);
						prefix = prefix(c);
						price  = price(((DefaultMutableTreeNode) checkedPaths[n].getLastPathComponent()).toString());
						
						((DefaultMutableTreeNode) checkedPaths[n].getLastPathComponent()).setUserObject( name +" | "+ price +" | "+setup.getText() );
						treeModel.nodeChanged(((DefaultMutableTreeNode) checkedPaths[n].getLastPathComponent()));
						
						setTable(name,price,setup.getText());
						
						while(n2 < ((DefaultMutableTreeNode) checkedPaths[n].getLastPathComponent()).getChildCount())
						{
							length = ((DefaultMutableTreeNode) checkedPaths[n].getLastPathComponent()).getChildAt(n2).toString().length();
							c      = ((DefaultMutableTreeNode) checkedPaths[n].getLastPathComponent()).getChildAt(n2).toString().toCharArray();
							
							name   = label(c,length);
							prefix = prefix(c);
							price  = price(((DefaultMutableTreeNode) checkedPaths[n].getLastPathComponent()).getChildAt(n2).toString());
							
							((DefaultMutableTreeNode) ((DefaultMutableTreeNode) checkedPaths[n].getLastPathComponent()).getChildAt(n2)).setUserObject( name +" | "+ price +" | "+setup.getText() );
							treeModel.nodeChanged(((DefaultMutableTreeNode) checkedPaths[n].getLastPathComponent()).getChildAt(n2));
							
							setTable(name,price,setup.getText());
							n1=0;
							n2++;
							name   ="";
							prefix ="";
							price  ="";
						}
						n2=0;
						
						name   ="";
						prefix ="";
						price  ="";
						n++;
					}
					n=0;
					
				}	
			}
			else
				JOptionPane.showMessageDialog(null, "Please insert only numbers as price.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void clear ()
	{
		// clear all settings
		String node;
		n=0;
		n2=0;
		
		while(n< ((DefaultMutableTreeNode) treeModel.getRoot()).getChildCount() )
		{
			node = ((DefaultMutableTreeNode) treeModel.getRoot()).getChildAt(n).toString();
			((DefaultMutableTreeNode) ((DefaultMutableTreeNode) treeModel.getRoot()).getChildAt(n)).setUserObject(clear(node));
			treeModel.nodeChanged(((DefaultMutableTreeNode) treeModel.getRoot()).getChildAt(n));
			
			if(!((DefaultMutableTreeNode) treeModel.getRoot()).getChildAt(n).isLeaf())
			{
				while(n2 < ((DefaultMutableTreeNode) treeModel.getRoot()).getChildAt(n).getChildCount())
				{
					node = ((DefaultMutableTreeNode) treeModel.getRoot()).getChildAt(n).getChildAt(n2).toString();
					((DefaultMutableTreeNode) ((DefaultMutableTreeNode) treeModel.getRoot()).getChildAt(n).getChildAt(n2)).setUserObject(clear(node));
					treeModel.nodeChanged(((DefaultMutableTreeNode) treeModel.getRoot()).getChildAt(n).getChildAt(n2));
					n2++;
				}
				n2=0;
			}
			
			node="";
			n++;
		}
		node="";
		n2=0;
		n=0;
		
		while(tableModel.getRowCount()>0)
		{
			tableModel.removeRow(0);
		}
		
		while(tableList.getModel().getRowCount()>n)
		{
			tableList.getModel().setValueAt("0",n,2);
			tableList.getModel().setValueAt("0",n,3);
			n++;
		}
		n=0;
	}
	
	public String label (char[] c, int length)
	{
		String label = "";
		int count    = 0;
		
		while (c[count]!='|')
		{
			label += c[count];
			count++;
		}
	    label += "|";
	    count++;
	    count++;
	    while (count < length)
	    {
	    	if (c[count]!='|')
	    		label += c[count-1];
	    	else
	    		count = length+1;
	    	count++;
	    }
	    if ( (count<=length) && (c[count-1]!='|') )
    		label += c[count-1];
		
		return label;	    
	}
	
	public String prefix (char[] c)
	{
		String prefix = "";
		int count     = 0;
		
		while (c[count]!='|')
		{
			prefix += c[count];
			count++;
		}
		
		return prefix;
	}
	
	public void setTable (String name, String price, String setup)
	{
		int    n      = 1;
		char[] c      = name.toCharArray();
		Boolean check = true;
		String prefix = "";
		String land   = "";
		String test   = "";
		
		while(c[n]!='|')
		{
			prefix+=c[n-1];
			n++;
		}
		n++;
		n++;
		
		while(name.length()>n)
		{	
			land+=c[n];
			n++;
		}
		n=0;
		String[] data = {prefix,land,price,setup};
		
		while(n<tableModel.getRowCount())
		{
			test += tableModel.getValueAt(n,0);
			if(test.equals(prefix))
			{
				tableModel.setValueAt(prefix,n,0);
				tableModel.setValueAt(land,n,1);
				tableModel.setValueAt(price,n,2);
				tableModel.setValueAt(setup,n,3);
				check = false;
				n = tableModel.getRowCount()+1;
			}
			n++;
			test = "";
		}
		if(check)
			tableModel.addRow(data);
		n=0;
	}
	
	public String clear (String name)
	{
		int n = 0;
		char[]c;
		String returnName = "";
		
		c=name.toCharArray();
		while(c[n]!='|')
		{
			returnName += c[n];
			n++;
		}
		n++;
		while(n<c.length)
		{
			if (c[n]!='|')
				returnName += c[n-1];
			else
				n=c.length+1;
			n++;
		}
		if(n==c.length)
			returnName += c[n-1];
		
		n=0;
		return returnName;
	}
	
	public String price (String label)
	{
		String price = " 0 ";
		char[] c     = label.toCharArray();
		
	    int n00 =0;
	    int n01 =0;
	
	    while(n00<c.length)
	    {
	    	if(c[n00]=='|')
	    		n01++;
	    	n00++;
	    }
	    n00=0;
	    
	    if(n01>1)
	    {
	    	price="";
	    	while(c[n00]!='|')
	    		n00++;
	    	n00++;
	    	while(c[n00]!='|')
	    		n00++;
	    	n00++;
	    	n00++;
	    	n00++;
	    	while(c[n00]!='|')
	    	{
	    		price+=c[n00-1];
				n00++;
	    	}
	    }
	    n00=0;
	    n01=0;
		
		return price;
	}
	
	public String setup(String label)
	{
		String setup = "0";
		int n00      = 0;
		int n01      = 0;
		char[] c     = label.toCharArray();
		
		while(c.length>n00)
		{
			if(c[n00]=='|')
				n01++;
			n00++;
		}
		n00=0;
		
		if(n01>1)
		{
			setup="";
			while(c[n00]!='|')
				n00++;
			n00++;
			while(c[n00]!='|')
				n00++;
			n00++;
			while(c[n00]!='|')
				n00++;
			n00++;
			n00++;
			n00++;
			while(c.length>n00)
			{
				setup+=c[n00-1];
				n00++;
			}
			setup+=c[n00-1];
		}
		
		return setup;
	}
	
	public JPanel main ()
	{
		return main;
	}
}
