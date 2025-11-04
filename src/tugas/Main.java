package tugas;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    static ArrayList<Item> ListOfItems = new ArrayList<Item>();
    static ArrayList<Payment> ListOfPayments = new ArrayList<Payment>();
    static Scanner s = new Scanner(System.in);

    public static void seedData() {
        ListOfItems.add(new Item("Kulkas", "Electronic", 4800000));
        ListOfItems.add(new Item("TV", "Electronic", 1280000));
        ListOfItems.add(new Item("Laptop", "Computer", 6000000));
        ListOfItems.add(new Item("PC", "Computer", 12000000));
    }

    public static void printItem(Item item) {
        System.out.println("Nama\t: " + item.getName());
        System.out.println("Tipe\t: " + item.getType());
        System.out.println("Harga\t: " + item.getPrice());
    }

    public static void main(String[] args) {
        seedData();
        int opt = -1;
        do {
            System.out.println("----Program Toko Elektronik----");
            System.out.println("1. Pesan Barang");
            System.out.println("2. Lihat Pesanan");
            System.out.print("Pilihan : ");
            opt = readInt();

            if (opt == 1) {
                menuPesanBarang();
            } else if (opt == 2) {
                menuLihatPesanan();
            } else {
                System.out.println("Salah Input");
            }
        } while (opt != 0);
    }

    private static void menuPesanBarang() {
        System.out.println("----Daftar Barang----");
        for (int i = 0; i < ListOfItems.size(); i++) {
            System.out.println("No  	: " + (i + 1));
            printItem(ListOfItems.get(i));
            System.out.println("--------------------");
        }
        System.out.print("Pilih : ");
        int pick = readInt();
        if (pick < 1 || pick > ListOfItems.size()) {
            System.out.println("Pilihan tidak valid.");
            return;
        }
        Item chosen = ListOfItems.get(pick - 1);
        System.out.println("Nama  : " + chosen.getName());
        System.out.println("Tipe  : " + chosen.getType());
        System.out.println("Harga : " + chosen.getPrice());

        System.out.println("----Tipe pembayaran----");
        System.out.println("1. Cash");
        System.out.println("2. Credit");
        System.out.print("Pilih : ");
        int payType = readInt();

        if (payType == 1) {
            System.out.print("Bayar (Y/N): ");
            String yn = s.next().trim();
            Cash c = new Cash(chosen);
            if (yn.equalsIgnoreCase("Y")) {
                int paid = c.pay();
                ListOfPayments.add(c);
                System.out.println("Harga Pembayaran : " + paid);
                System.out.println("Transaksi telah dibayar");
            } else {
                ListOfPayments.add(c);
                System.out.println("Transaksi telah disimpan");
            }
        } else if (payType == 2) {
            System.out.print("Lama Cicilan (3/6/9/12): ");
            int choosenInstallment = readInt();

            if (!(choosenInstallment == 3 || choosenInstallment == 6
                    || choosenInstallment == 9 || choosenInstallment == 12)) {
                System.out.println("Pilihan cicilan tidak valid. Menggunakan default 3 bulan.");
                choosenInstallment = 3;
            }

            Credit cr = new Credit(chosen, choosenInstallment);
            ListOfPayments.add(cr);

            int perInstall = chosen.getPrice() / choosenInstallment;
            System.out.println("Lama Cicilan (" + choosenInstallment + "): " + cr.getInstallment());
            System.out.println("Harga Pembayaran per cicilan : " + perInstall);

            System.out.print("Bayar sekarang? (Y/N): ");
            String yn2 = s.next().trim();
            if (yn2.equalsIgnoreCase("Y")) {
                int remainingInst = choosenInstallment - cr.getInstallment();
                System.out.print("Bayar berapa cicilan sekarang? (1 - " + remainingInst + "): ");
                int howMany = readInt();
                if (howMany < 1) howMany = 1;
                if (howMany > remainingInst) howMany = remainingInst;

                int totalPaid = 0;
                for (int i = 0; i < howMany; i++) {
                    int paid = cr.pay();
                    totalPaid += paid;
                    if (cr.isPaidOff()) {
                        int alreadyPaidWithoutRemainder = (cr.getInstallment() - 1) * (chosen.getPrice() / choosenInstallment);
                        int finalPart = chosen.getPrice() - alreadyPaidWithoutRemainder;
                        totalPaid = alreadyPaidWithoutRemainder + finalPart;
                        break;
                    }
                }
                System.out.println("Bayar : " + totalPaid);
                if (cr.isPaidOff()) {
                    System.out.println("Transaksi telah dibayar lunas");
                } else {
                    System.out.println("Transaksi tersimpan");
                }
            } else {
                System.out.println("Transaksi tersimpan");
            }

        } else {
            System.out.println("Tipe pembayaran tidak valid.");
        }
    }

    private static void menuLihatPesanan() {
        System.out.println("----Daftar Transaksi----");
        if (ListOfPayments.isEmpty()) {
            System.out.println("Belum ada transaksi.");
            return;
        }
        for (int i = 0; i < ListOfPayments.size(); i++) {
            Payment p = ListOfPayments.get(i);
            System.out.println("No\t\t: " + (i + 1));
            System.out.println("Nama\t\t: " + p.getItemName());
            System.out.println("Tipe\t\t: " + p.getItem().getType());
            System.out.println("Status\t\t: " + p.getStatus());
            System.out.println("Sisa Pembayaran\t: " + p.getRemainingAmount());
            if (p instanceof Credit) {
                Credit cr = (Credit) p;
                System.out.println("Cicilan (dibayar/max)\t: " + cr.getInstallment() + " / " + cr.getMaxInstallmentAmount());
            }
            System.out.println("----------------------------");
        }

        System.out.print("Pilih No Transaksi : ");
        int pick = readInt();
        if (pick < 1 || pick > ListOfPayments.size()) {
            System.out.println("No Transaksi tidak valid.");
            return;
        }
        processPaymentOnTransaction(pick - 1);
    }

    private static void processPaymentOnTransaction(int idx) {
        Payment p = ListOfPayments.get(idx);
        System.out.println("Nama\t\t: " + p.getItemName());
        System.out.println("Tipe\t\t: " + p.getItem().getType());
        System.out.println("STATUS\t\t: " + p.getStatus());
        System.out.println("Sisa Pembayaran\t: " + p.getRemainingAmount());

        if (p.isPaidOff()) {
            System.out.println("Transaksi telah lunas");
            return;
        }

        if (p instanceof Cash) {
            System.out.print("Bayar sekarang? (Y/N): ");
            String yn = s.next().trim();
            if (yn.equalsIgnoreCase("Y")) {
                int paid = p.pay();
                System.out.println("Harga Pembayaran : " + paid);
                System.out.println("Transaksi telah dibayar lunas");
            } else {
                System.out.println("Transaksi tersimpan");
            }
        } else if (p instanceof Credit) {
            Credit cr = (Credit) p;
            int remainingInst = cr.getMaxInstallmentAmount() - cr.getInstallment();
            System.out.println("Sisa cicilan : " + remainingInst);
            System.out.print("Bayar cicilan sekarang? (Y/N): ");
            String yn = s.next().trim();
            if (yn.equalsIgnoreCase("Y")) {
                System.out.print("Bayar berapa cicilan? (1 - " + remainingInst + "): ");
                int howMany = readInt();
                if (howMany < 1) howMany = 1;
                if (howMany > remainingInst) howMany = remainingInst;

                int totalPaid = 0;
                for (int i = 0; i < howMany; i++) {
                    int paid = cr.pay();
                    totalPaid += paid;
                    if (cr.isPaidOff()) {
                        int alreadyPaidWithoutRemainder = (cr.getInstallment() - 1) * (cr.getItem().getPrice() / cr.getMaxInstallmentAmount());
                        int finalPart = cr.getItem().getPrice() - alreadyPaidWithoutRemainder;
                        totalPaid = alreadyPaidWithoutRemainder + finalPart;
                        break;
                    }
                }
                System.out.println("Bayar : " + totalPaid);
                if (cr.isPaidOff()) {
                    System.out.println("Transaksi telah dibayar lunas");
                } else {
                    System.out.println("Transaksi tersimpan");
                }
            } else {
                System.out.println("Transaksi tersimpan");
            }
        } else {
            System.out.println("Jenis pembayaran tidak dikenali.");
        }
    }

    private static int readInt() {
        int val = -1;
        try {
            val = s.nextInt();
        } catch (Exception ex) {
            s.next();
            val = -1;
        }
        return val;
    }
}