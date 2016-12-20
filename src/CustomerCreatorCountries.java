import java.io.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.*;


public class CustomerCreatorCountries 
{
	JTextField price;
	DefaultMutableTreeNode root  = new DefaultMutableTreeNode("Countries");
	DefaultMutableTreeNode dmtn;
	BufferedReader r             = new BufferedReader(new InputStreamReader(CustomerCreatorCountries.class.getResourceAsStream("/countries.txt")));
	String line = "";
	String[] columnNames              = {"Prefix","Land","Price","Setup"};
	String[][] listData;
	DefaultTableModel tableModel = new DefaultTableModel(listData,columnNames);
	
	public CustomerCreatorCountries()
	{
		try
		{
			line = r.readLine();
			while(line!=null)
			{
				if(!line.equals("-"))
				{
					dmtn = new DefaultMutableTreeNode(line);
					root.add( dmtn );
					setTable(line);
				}
				else
				{
					line = r.readLine();
					while(!line.equals("-"))
					{
						dmtn.add( new DefaultMutableTreeNode(line));
						setTable(line);
						line = r.readLine();
					}
				}
				line = r.readLine();
			}
			
			r.close();
		}
		catch (IOException e)
		{
			System.out.println("Exception: "+e.getMessage());
			JOptionPane.showMessageDialog(null, "Ein Fehler ist aufgetretten! Bitte prüfen Sie die Datei.", "Erorr", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void setTable (String line)
	{
		String prefix = "";
		String name   = "";
		String[] data = new String[4];
		char[] c = line.toCharArray();
		int n    = 1;
		
		while(c[n]!='|')
		{
			prefix += c[n-1];
			n++;
		}
		n++;
		n++;
		while(c.length>n)
		{
			name += c[n];
			n++;
		}
		data[0] = prefix;
		data[1] = name;
		data[2] = "0";
		data[3] = "0";
		tableModel.addRow(data);
	}
	
	public DefaultMutableTreeNode getCountries()
	{
		return root;
	}
	
	public DefaultTableModel getList()
	{
		return tableModel;
	}
}
