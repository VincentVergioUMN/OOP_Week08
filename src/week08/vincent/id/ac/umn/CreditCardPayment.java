package week08.vincent.id.ac.umn;

public class CreditCardPayment extends Payment {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	private String cardNumber;
	
	public CreditCardPayment(double amount, String cardNumber) {
		super(amount);
		this.cardNumber = cardNumber;
	}
	
	@Override
	void processPayment() {
		System.out.println("Processing credit card payment of $" + amount + " for card number " + cardNumber);
	}
}
