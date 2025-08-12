package mylab.book.control;

import mylab.book.entity.*;
import java.text.DecimalFormat;

public class ManageBook {

    public static void main(String[] args) {
        Publication[] publications = {
            new Magazine("����ũ�μ���Ʈ", "2007-10-01", 328, 9900, "�ſ�"),
            new Magazine("�濵����ǻ��", "2007-10-03", 316, 9000, "�ſ�"),
            new Novel("���߿�", "2007-07-01", 396, 9800, "����������������", "����Ҽ�"),
            new Novel("���ѻ꼺", "2007-04-14", 383, 11000, "����", "���ϼҼ�"),
            new ReferenceBook("�ǿ��������α׷���", "2007-01-14", 496, 25000, "����Ʈ�������"),
            new Novel("�ҳ��̿´�", "2014-05-01", 216, 15000, "�Ѱ�", "����Ҽ�"),
            new Novel("�ۺ������ʴ´�", "2021-09-09", 332, 15120, "�Ѱ�", "����Ҽ�")
        };

        System.out.println("==== ���� ���� ��� ====");
        for (int i = 0; i < publications.length; i++) {
            System.out.println((i + 1) + ". " + publications[i].toString());
        }

        int idx = 6;
        Publication target = publications[idx];
        int before = target.getPrice();
        DecimalFormat won = new DecimalFormat("#,###");

        System.out.println("\n==== ���� ���� ====");
        System.out.println(target.getTitle() + " ���� �� ����: " + won.format(before) + "��");
        modifyPrice(target);
        int after = target.getPrice();
        System.out.println(target.getTitle() + " ���� �� ����: " + won.format(after) + "��");
        System.out.println("����: " + won.format(before - after) + "��");

       
        System.out.println();
        new StatisticsAnalyzer().printStatistics(publications);
    }

    public static void modifyPrice(Publication publication) {
        int currentPrice = publication.getPrice();
        if (publication instanceof Magazine) {
            publication.setPrice((int) (currentPrice * 0.6));
        } else if (publication instanceof Novel) {
            publication.setPrice((int) (currentPrice * 0.8));
        } else if (publication instanceof ReferenceBook) {
            publication.setPrice((int) (currentPrice * 0.9));
        }
    }
}