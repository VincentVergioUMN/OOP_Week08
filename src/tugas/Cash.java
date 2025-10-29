package tugas;

public class Cash extends Payment {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public Cash(Item item) {
		super(item);
	}
	
	public int pay() {
		if(isPaidOff) {
			return 0;
		}
		isPaidOff = true;
		return this.item.getPrice();
	}
	
	public String getClassName() {
		return "CASH";
	}
	
}
