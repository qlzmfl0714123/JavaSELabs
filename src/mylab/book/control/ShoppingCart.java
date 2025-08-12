package mylab.book.control;

import mylab.book.entity.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Publication> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    public void addItem(Publication item) {
        items.add(item);
        System.out.println(item.getTitle() + "��(��) ��ٱ��Ͽ� �߰��Ǿ����ϴ�.");
    }

    public boolean removeItem(String title) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getTitle().equals(title)) {
                Publication removed = items.remove(i);
                System.out.println(removed.getTitle() + "��(��) ��ٱ��Ͽ��� ���ŵǾ����ϴ�.");
                return true;
            }
        }
        System.out.println("�ش� ������ ���ǹ��� ã�� �� �����ϴ�.");
        return false;
    }

    public void displayCart() {
        DecimalFormat won = new DecimalFormat("#,###");
        System.out.println("====== ��ٱ��� ���� ======");
        for (int i = 0; i < items.size(); i++) {
            Publication p = items.get(i);
            System.out.println((i + 1) + ". " + p.getTitle() + " - " + won.format(p.getPrice()) + "��");
        }
        int total = calculateTotalPrice();
        int discounted = calculateDiscountedPrice();
        System.out.println("�� ����: " + won.format(total) + "��");
        System.out.println("���� ���� ����: " + won.format(discounted) + "��");
    }

    public int calculateTotalPrice() {
        int sum = 0;
        for (Publication p : items) sum += p.getPrice();
        return sum;
    }

    public int calculateDiscountedPrice() {
        double total = 0;
        for (Publication item : items) {
            if (item instanceof Magazine) {
                total += item.getPrice() * 0.9;   
            } else if (item instanceof Novel) {
                total += item.getPrice() * 0.85;  
            } else if (item instanceof ReferenceBook) {
                total += item.getPrice() * 0.8;   
            } else {
                total += item.getPrice();
            }
        }
        return (int) Math.round(total);
    }

    public void printStatistics() {
        int magazineCount = 0, novelCount = 0, referenceBookCount = 0;
        for (Publication item : items) {
            if (item instanceof Magazine) magazineCount++;
            else if (item instanceof Novel) novelCount++;
            else if (item instanceof ReferenceBook) referenceBookCount++;
        }
        System.out.println("====== ��ٱ��� ��� ======");
        System.out.println("����: " + magazineCount + "��");
        System.out.println("�Ҽ�: " + novelCount + "��");
        System.out.println("����: " + referenceBookCount + "��");
        System.out.println("�� ���ǹ�: " + items.size() + "��");
    }

    public static void main(String[] args) {
        Publication m1 = new Magazine("����ũ�μ���Ʈ", "2007-10-01", 328, 9900, "�ſ�");
        Publication m2 = new Magazine("�濵����ǻ��", "2007-10-03", 316, 9000, "�ſ�");
        Publication n1 = new Novel("���߿�", "2007-07-01", 396, 9800, "����������������", "����Ҽ�");
        Publication n2 = new Novel("���ѻ꼺", "2007-04-14", 383, 11000, "����", "���ϼҼ�");
        Publication r1 = new ReferenceBook("�ǿ��������α׷���", "2007-01-14", 496, 25000, "����Ʈ�������");

        ShoppingCart cart = new ShoppingCart();
        cart.addItem(m1);
        cart.addItem(m2);
        cart.addItem(n1);
        cart.addItem(n2);
        cart.addItem(r1);

        cart.displayCart();
        cart.printStatistics();

        cart.removeItem("���߿�");
        cart.displayCart();
    }
}