import java.io.*;
import java.util.Locale;

import javax.swing.JOptionPane;

public class Creator 
{
	public static void Create(String pathIn, String pathOut, String tariffName, String tariffID, Boolean currency) 
	{
		File file = new File(pathIn);
		int n  = 0; 	// Anzahl char in einer Zeile
		int n1 = 0;		// Anzahl Countrys
		int n2 = 0;		// Zähler für CB abfrage
		int n3 = 0;		// Anzahl der ChargeBands
		int error = 0;
		int vergleich;
		int lineNumber = 0;
		String directory = pathOut;
        String line      = "";// Zeile des txt files
        String id        = "";// Vorwahl
        String name      = "";// Beschreibung
        String price     = "";// Preis
        String setup     = "0";// SetupCharge
        String tarName   = tariffName;
        String tarID     = tariffID;
        char[] c;
        Country[] count         = new Country[100000];
        ChargeBand[] chargeBand = new ChargeBand[1000];
        int curr = 1;
        if(currency)
        	curr = 100;
        
        try {
            BufferedReader r = new BufferedReader(new FileReader(file));
            
//Datei Zeilenweise einlesen
            stop:
            while((line = r.readLine()) != null)
            {
                c = line.toCharArray();
                id    = "";
                name  = "";
                price = "";
                n = 0;
                lineNumber++;
                
                if (!line.equals(";;"))
                {
	                while (c[n] != ';')
	                {
	                	id += c[n];
	                	n++;
	                }
	                while (n2<n)
	                {
	                	if ( Character.isDigit(c[n2]) )
	                		n2++;
	                	else
	                	{
	                		error = 1;
	                		break stop;
	                	}
	                }
	                n2 = 0;
	                n++;
	                
	                while (c[n] != ';')
	                {
	                	name += c[n];
	                	n++;
	                }
	                n++;
	                
	                try
	                {
		                while (c[n]!=';')
		                {
		                	price += c[n];
		                	n++;
		                }
		                price = (Double.parseDouble(String.format(Locale.ENGLISH, price.replaceAll(",",".")))*curr)+"";
		                
		                if (c[n] == ';')
		                {	
			                n++;
			                
			                while(c.length>n)
			                {
			                	setup += c[n];
			                	n++;
			                }
			                setup = (Double.parseDouble(String.format(Locale.ENGLISH, setup.replaceAll(",",".")))*curr)+"";
		                }
	                }
	                catch (ArrayIndexOutOfBoundsException e)
	                {
	                	n=0;
	                }
	                catch (NumberFormatException e)
	                {
	                	JOptionPane.showMessageDialog(null, "There was an error detected in line"+lineNumber+".", "Error", JOptionPane.ERROR_MESSAGE);
	                }
                }
                //Ende einlesen
                
                //erstellen des ersten CB
                try
                {
                if (n3 == 0)
	                {
	                	chargeBand[0] = new ChargeBand(id, Double.parseDouble(price), 0, Double.parseDouble(setup) );
	                	n3++;
	                }
                
	                //erstellen aller neuen CBs
	                if (n3 > 0)
	                {
	                	vergleich = 0;
	                	while ( !(vergleich == 1) && (n2 < n3) )
	                	{
	                		vergleich = chargeBand[n2].vergleich(Double.parseDouble(price), Double.parseDouble(setup));
	                		n2++;
	                	}
	            		if (vergleich == 0)
	            		{
	            			chargeBand[n3] = new ChargeBand(id, Double.parseDouble(price), n3, Double.parseDouble(setup) );
							n3++;
							n2 = n3;
	            		}
	            		
	                }
                }
                catch (NumberFormatException e)
                {
                	JOptionPane.showMessageDialog(null, "Please insert a price in line"+lineNumber+".", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
                //erstellen des Country
                count[n1] = new Country( id, name, Double.parseDouble(price), chargeBand[(n2-1)].id() );
                n1++;
                
                setup = "0";
				price = "0";
				n2    = 0;
            }
            // __________________________________________________
            // ChargeBand benennen
            
            
            // __________________________________________________
            // Datei schreiben
            if (error == 0)
	        {
	           	FileWriter writer;
        		File file2;
        		file2 = new File( directory + File.separator + (tariffName.replaceAll(" ", "_") ) +".inf");
        		n = 0;
        		
        		try
        		{
        			writer = new FileWriter(file2);
        			
        			//Carrier
        			writer.write("[Carrier]");
        			writer.write("\r\n");
        			writer.write("Name=" + tariffName);
        			writer.write("\r\n");
        			writer.write("ChargeStructure=Standard");
        			writer.write("\r\n");
        			writer.write("Ident=" + tariffID);
        			writer.write("\r\n");
        			writer.write("MeterRate=0");
        			writer.write("\r\n");
        			writer.write("DefaultChargeBand=" + (chargeBand[n3-1].anzeige()));
        			writer.write("\r\n");
        			writer.write("NetworkDigits=");
        			writer.write("\r\n");
        			writer.write("\r\n");
        			
        			//CallCategories
        			writer.write("[CallCategories]");
        			writer.write("\r\n");
        			while (n < n3)
        			{
        				writer.write(chargeBand[n].callCategory());
        				writer.write("\r\n");
        				n++;
        			}
        			n = 0;
        			writer.write("\r\n");
        			
        			//ChargeBands
        			writer.write("[ChargeBands]");
        			writer.write("\r\n");
        			while (n < n3)
        			{
        				writer.write(chargeBand[n].chargeBands());
        				writer.write("\r\n");
        				n++;
        			}
        			n=0;
        			writer.write("\r\n");
        			
        			//ChargeRates
        			writer.write("[ChargeRates]");
        			writer.write("\r\n");
        			while (n < n3)
        			{
        				writer.write(chargeBand[n].chargeRates());
        				writer.write("\r\n");
        				n++;
        			}
        			n=0;
        			writer.write("\r\n");
        			
        			//DailyRates
        			writer.write("[DailyRates]");
        			writer.write("\r\n");
        			while (n < n3)
        			{
        				writer.write(chargeBand[n].dailyRates());
        				n++;
        			}
        			n=0;
        			writer.write("\r\n");
        			
        			//Destinations
        			writer.write("[Destinations]");
        			writer.write("\r\n");
        			while (n < n1)
        			{
        				writer.write(count[n].destinations());
        				writer.write("\r\n");
        				n++;
        			}
        			
        			writer.flush();
        			writer.close();
        		}
        		
        		catch (IOException e)
        		{
        			JOptionPane.showMessageDialog(null, "Ein Fehler ist aufgetretten! Bitte prüfen Sie die zu schreibende Datei.", "Error", JOptionPane.ERROR_MESSAGE);
        		}
            	
            	
        		r.close();
        		JOptionPane.showMessageDialog(null, "The Process is done!", "Sucess", JOptionPane.PLAIN_MESSAGE);
            }
            else
            {
            	error = 0;
            	r.close();
            	JOptionPane.showMessageDialog(null, "ErrorID:002 - Please check your file at Line: "+lineNumber+". You have not set a compatible input file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } 
        
//Errors
        catch (IOException e)
        {
        	System.out.println("Exception: "+e.getMessage());
        	JOptionPane.showMessageDialog(null, "ErrorID:003 - Please check your file. You have not set a compatible input file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
        	JOptionPane.showMessageDialog(null, "ErrorID:004 - Please check your file. You have not set a compatible input file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
	}
}
