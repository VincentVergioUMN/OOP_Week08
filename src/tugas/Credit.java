package tugas;

public class Credit extends Payment {

    protected Integer installment;
    protected Integer maxInstallmentAmount;

    public Credit(Item item, Integer maxInstallmentAmount) {
        super(item);
        this.maxInstallmentAmount = (maxInstallmentAmount != null && maxInstallmentAmount > 0)
                ? maxInstallmentAmount
                : 1;
        this.installment = 0;
    }

    @Override
    public int pay() {
        if (isPaidOff) {
            return 0;
        }

        if (maxInstallmentAmount == 0) {
            isPaidOff = true;
            installment = 1;
            return item.getPrice();
        }

        int perInstallment = item.getPrice() / maxInstallmentAmount;

        installment = installment + 1;

        if (installment >= maxInstallmentAmount) {
            isPaidOff = true;
        }

        return perInstallment;
    }

    @Override
    public String getClassName() {
        return "CREDIT";
    }

    public Integer getInstallment() {
        return installment;
    }

    public Integer getMaxInstallmentAmount() {
        return maxInstallmentAmount;
    }

    @Override
    public int getRemainingAmount() {
        if (isPaidOff) {
            return 0;
        }
        int price = item.getPrice();
        int perInstallment = price / maxInstallmentAmount;
        int paidSoFar = installment * perInstallment;
        int remaining = price - paidSoFar;

        if (installment >= maxInstallmentAmount) {
            remaining = 0;
        }

        if (remaining < 0) {
            remaining = 0;
        }

        return remaining;
    }
}