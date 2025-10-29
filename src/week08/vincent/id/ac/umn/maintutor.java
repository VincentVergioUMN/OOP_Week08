package week08.vincent.id.ac.umn;

public class maintutor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Payment creditCardPayment = new CreditCardPayment(100.0, "1234-5678-9012-3456");
		Payment bankTransferPayment = new BankTransferPayment(300.0, "9876543210");
	
		creditCardPayment.paymentDetails();
		creditCardPayment.processPayment();
	
		bankTransferPayment.paymentDetails();
		bankTransferPayment.processPayment();
	}
	
}
