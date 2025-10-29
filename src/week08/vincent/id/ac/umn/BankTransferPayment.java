package week08.vincent.id.ac.umn;

public class BankTransferPayment extends Payment {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	private String bankAccount;
	
	public BankTransferPayment(double amount, String bankAccount) {
		super(amount);
		this.bankAccount = bankAccount;
	}
	
	@Override
	void processPayment() {
		System.out.println("Processing bank transfer payment of $" + amount + " for bank account " + bankAccount);
	}
}
