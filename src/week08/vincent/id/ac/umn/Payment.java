package week08.vincent.id.ac.umn;

public abstract class Payment {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	protected double amount;
	
	public Payment(double amount) {
		this.amount = amount;
	}
	
	abstract void processPayment();
	
	public void paymentDetails() {
		System.out.println("Processing payment of $" + amount);
	}
}
