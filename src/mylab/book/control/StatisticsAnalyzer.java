package mylab.book.control;

import mylab.book.entity.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class StatisticsAnalyzer {

    public Map<String, Double> calculateAveragePriceByType(Publication[] publications) {
        Map<String, Integer> sum = new HashMap<>();
        Map<String, Integer> cnt = new HashMap<>();

        for (Publication p : publications) {
            String type = getPublicationType(p);
            sum.put(type, sum.getOrDefault(type, 0) + p.getPrice());
            cnt.put(type, cnt.getOrDefault(type, 0) + 1);
        }

        Map<String, Double> avg = new HashMap<>();
        for (String type : sum.keySet()) {
            avg.put(type, sum.get(type) / (double) cnt.get(type));
        }
        return avg;
    }

    public Map<String, Double> calculatePublicationDistribution(Publication[] publications) {
        Map<String, Integer> count = new HashMap<>();
        for (Publication p : publications) {
            String type = getPublicationType(p);
            count.put(type, count.getOrDefault(type, 0) + 1);
        }
        int total = publications.length;
        Map<String, Double> ratio = new HashMap<>();
        for (String type : count.keySet()) {
            ratio.put(type, (count.get(type) * 100.0) / total);
        }
        return ratio;
    }

    public double calculatePublicationRatioByYear(Publication[] publications, String year) {
        int total = publications.length;
        int hit = 0;
        for (Publication p : publications) {
            String date = p.getPublishDate(); // yyyy-MM-dd
            if (date != null && date.length() >= 4 && date.substring(0, 4).equals(year)) {
                hit++;
            }
        }
        return (hit * 100.0) / total;
    }

    private String getPublicationType(Publication pub) {
        if (pub instanceof Novel) return "�Ҽ�";
        if (pub instanceof Magazine) return "����";
        if (pub instanceof ReferenceBook) return "����";
        return "��Ÿ";
    }

    public void printStatistics(Publication[] publications) {
        DecimalFormat won = new DecimalFormat("#,###");
        DecimalFormat pct = new DecimalFormat("0.00");

        System.out.println("===== ���ǹ� ��� �м� =====");
       
        System.out.println("1. Ÿ�Ժ� ��� ����:");
        Map<String, Double> avg = calculateAveragePriceByType(publications);
       
        String[] order = {"�Ҽ�", "����", "����", "��Ÿ"};
        for (String k : order) {
            if (avg.containsKey(k)) {
                System.out.println("   - " + k + ": " + won.format(Math.round(avg.get(k))) + "��");
            }
        }
     
        System.out.println("\n2. ���ǹ� ���� ����:");
        Map<String, Double> dist = calculatePublicationDistribution(publications);
        for (String k : order) {
            if (dist.containsKey(k)) {
                System.out.println("   - " + k + ": " + pct.format(dist.get(k)) + "%");
            }
        }
      
        double r = calculatePublicationRatioByYear(publications, "2007");
        System.out.println("\n3. 2007�⿡ ���ǵ� ���ǹ� ����: " + pct.format(r) + "%");
    }
}
