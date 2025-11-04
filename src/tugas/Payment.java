package tugas;

public abstract class Payment {

    protected boolean isPaidOff;
    protected Item item;

    public Payment() {
        this.isPaidOff = false;
        this.item = null;
    }

    public Payment(Item item) {
        this.isPaidOff = false;
        this.item = item;
    }

    public abstract int pay();
    public abstract String getClassName();

    public boolean isPaidOff() {
        return isPaidOff;
    }

    public Item getItem() {
        return item;
    }

    public String getItemName() {
        return item.getName();
    }

    public String getStatus() {
        if (isPaidOff) {
            return "FINISHED";
        }
        return "ON PROGRESS";
    }

    public int getRemainingAmount() {
        if (isPaidOff) {
            return 0;
        }
        return item.getPrice();
    }
}