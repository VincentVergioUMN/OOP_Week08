package week08.vincent.id.ac.umn;

public class Item {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	private String Name;
	private String Type;
	private int Price;
	
	public Item(String name, String type, int price) {
		this.Name=name;
		this.Type=type;
		this.Price=price;
	}
	
	public String getName() {
		return Name;
	}
	
	public void setName(String name) {
		Name = name;
	}
	
	public String getType() {
		return Type;
	}
	
	public void setType(String type) {
		Type = type;
	}
	
	public int getPrice() {
		return Price;
	}
	
	public void setPrice(int price) {
		Price = price;
	}	
}
