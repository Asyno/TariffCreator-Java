
public class ChargeBand 
{
	private int    id;
	private String name;
	private String description;
	private double price;
	private double setup = 0;
	private String prefix;
	private String ccDescription;
	
	ChargeBand (String prefix1, double cost, int n, double setupCharge)
	{
		price         = cost;
		setup         = setupCharge;
		id            = n;
		name          = ("C" + n);
		description   = ("ChargeBand" + n + " p:" + Math.round( (cost)) + " s:" + Math.round( (setupCharge)));
		ccDescription = ("ChargeBand" + n);
		prefix        = prefix1;
	}
	
	String anzeige()
	{
		return name;
	}
	
	void callCategories()
	{
		System.out.println (name + "=" + description);
	}
	
	public int vergleich(double priceVergleich, double setupVergleich)
	{
		if ( (price == priceVergleich) && (setup == setupVergleich) )
			return 1;
		else
			return 0;
	}
	
	public String chargeBands()
	{
		return (name + "=" + description);
	}
	
	public String callCategory()
	{
		return (name + "=" + ccDescription);
	}
	
	public String chargeRates()
	{
		return (name + ",A=\"AllDay\"," + Math.round( (setup) *1000) + ",60000,60000," + Math.round( (price) *1000) + ",0,None,0,0,0,0,1000");
	}
	
	public String dailyRates()
	{
		return(name + ",0=0000:A\r\n" + name + ",1=0000:A\r\n" + name + ",2=0000:A\r\n" + name + ",3=0000:A\r\n" + name + ",4=0000:A\r\n" + name + ",5=0000:A\r\n" + name + ",6=0000:A\r\n" + name + ",7=0000:A\r\n");
	}
	
	public String id()
	{
		return(name);
	}
}
