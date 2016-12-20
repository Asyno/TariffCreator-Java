import java.io.*;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;

public class OverviewCreator
{
	public static void create(String path, String pathOut)
	{
		File file            = new File(path);
		char[] c;
		String fileOut       = pathOut;
		String name          = "";
		String line          = "";             // Zeile des txt file
		String head          = "";
		String test          = "";
		String test1 = "";
		String[] chargeBand  = new String[1000];
        String[] price       = new String[1000];
        String[] prefix      = new String[100000];
        int prefixId         = 0;
        int priceId          = 0;
		int n                = 0;
		int n1               = 0;
		int n2               = 0;
		
		try 
		{
            BufferedReader r = new BufferedReader(new FileReader(file));
            
//Datei Zeilenweise einlesen
            
            
            while(!line.startsWith("[CallCategories]"))
            {
            	line = r.readLine();
                if (line.startsWith("Name="))
                {
                	head += line + "\r\n";
                	n = line.length();
                	c = line.toCharArray();
                	
                	while (c[n1]!='=')
                	{
                		n1++;
                	}
                	n1++;
                	while (n1<n)
                	{
                		name += c[n1];
                		n1++;
                	}
                	n  = 0;
                	n1 = 0;
                }
                if (line.startsWith("ChargeStructure="))
                	head += line + "\r\n";
                if (line.startsWith("Ident="))
                	head += line + "\r\n";
                if (line.startsWith("MeterRate="))
                	head += line + "\r\n";
                if (line.startsWith("DefaultChargeBand="))
                	head += line + "\r\n\r\n\r\n";
            }
            
            while(!line.startsWith("[ChargeBands]"))
            {
            	line = r.readLine();
            }
            
            while(!line.startsWith("[ChargeRates]"))
            {
            	line = r.readLine();
            	chargeBand[n] = line;
            	n++;
            }
            
            head += "Charge Band Name;Price per Call;Price per Min\r\n";
            while(!line.startsWith("[DailyRates]"))
            {
            	line = r.readLine();
            	c = line.toCharArray();
            	
            	if (line.contains(","))
            	{
            		
            		//ChargeBand
	            	while(c[n1]!=',')
	            	{
	            		test += c[n1];
	            		n1 ++;
	            	}
	            	price[priceId] = "";
	            	price[priceId] += test;
	            	
	            	while(!chargeBand[n2].startsWith(test))
	            		n2++;
	            		
	            	head += chargeBand[n2];
	            	
	            	test = "";
	            	n2=0;
	            	
	            	
	            	while(c[n1]!=',')
	            		n1 ++;
	            	n1++;
	            	while(c[n1]!=',')
	            		n1 ++;
	            	n1++;
	            	
	            	
	            	//Setup Charge
	            	while(c[n1]!=',')
	            	{
	            		test += c[n1];
	            		n1 ++;
	            	}
	            	DecimalFormat f = new DecimalFormat("#0.00");
	            	test = "" + Math.round( (Double.parseDouble(test)/100000) *100)/100.00;
	            	head += ";Price per call: " + (f.format(Double.parseDouble(test)));
	            	price[priceId] += ";"+ (f.format(Double.parseDouble(test)));
	            	test = "";
	            	n1 ++;
	            	
	            	
	            	while(c[n1]!=',')
	            		n1 ++;
	            	n1++;
	            	while(c[n1]!=',')
	            		n1 ++;
	            	n1++;
	            	
	            	
	            	//Charge per min
	            	while(c[n1]!=',')
	            	{
	            		test += c[n1];
	            		n1 ++;
	            	}
	            	test = "" + Math.round( (Double.parseDouble(test)/100000) *100)/100.00;
	            	head += ";Price per Min: " + (f.format(Double.parseDouble(test)));
	            	price[priceId] += ";"+ (f.format(Double.parseDouble(test)));
	            	test = "";
	            	n1 ++;
	            	
	            	
	            	test = "";
	            	head += "\r\n";
	            	n1 = 0;
	            	priceId ++;
            	}
            }
            n1 = 0;
            test = "";
            head += "\r\n \r\n";
            
            while(!line.startsWith("[Destinations]"))
            {
               	line = r.readLine();
            }
            
            
            head += "Prefix and Country;Charge Band;Price per Call;Price per Min\r\n";
            while((line = r.readLine()) != null)
            {
            	c = line.toCharArray();
            	prefix[prefixId] = "";
            	while(c[n1]!='"')
            	{
            		prefix[prefixId] += c[n1];
            		n1 ++;
            	}
            	n1 ++;
            	while(c[n1]!='"')
            	{
            		prefix[prefixId] += c[n1];
            		n1 ++;
            	}
            	
            	n1 ++;
            	n1 ++;
            	
            	while(c[n1]!=',')
            		n1++;
            	n1++;
            	
            	while(c.length>n1)
            	{
            		test += c[n1];
            		n1 ++;
            	}
            	
            	n1 = 0;
            	prefix[prefixId] += ";";
            	
            	
            	while (n1 < priceId)
            	{
            		if (price[n1].startsWith(test))
            		{
            			prefix[prefixId] += price[n1];
            			n1 = priceId+1;
            		}
            		
            		n1 ++;
            	}
            	
            	
            	
            	test = "";
            	n  = 0;
            	n1 = 0;
            	prefix[prefixId] += "\r\n";
            	prefixId ++;
            }
            r.close();
            //end data input
            
            //start write data
            FileWriter writer;
    		File file2;
    		file2 = new File(fileOut+"/TariffTable_" + (name.replaceAll(" ", "_") ) +".csv");
    		n1 = 0;
    		
    		try
    		{
    			writer = new FileWriter(file2);
    			
    			//Over view
    			writer.write(head);
    			writer.write(test1);
    			
    			while (n1 < prefixId)
    			{
    				writer.write(prefix[n1]);
    				n1 ++;
    			}
    			n1 = 0;
    			writer.flush();
    			writer.close();
    		}
    		catch (IOException e)
            {
            	System.out.println("Exception: "+e.getMessage());
            	JOptionPane.showMessageDialog(null, "Please check your file. You have not set a compatible input file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
    			
		}
        catch (IOException e)
        {
        	System.out.println("Exception: "+e.getMessage());
        	JOptionPane.showMessageDialog(null, "Please check your file. You have not set a compatible input file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
        	JOptionPane.showMessageDialog(null, "Please check your file. You have not set a compatible input file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
		JOptionPane.showMessageDialog(null, "Sucess!", "Test", JOptionPane.INFORMATION_MESSAGE);

	}
}
