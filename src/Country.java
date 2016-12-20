
public class Country 
{
	private String id;
	private String name;
	private String cB;
	private Double price;
	
	Country (String num, String land, Double cost, String CB)
	{
		id    = num;
		name  = land;
		price = cost;
		cB    = CB;
	}
	
	void anzeige()
	{
		System.out.println ("1 | " + id + " | 2 | " + name + "  \t| 3 | " + price);
	}
	
	public String destinations()
	{
		return(id + "=\"" + name + "\"," + cB + "," + cB);
	}
	
}
