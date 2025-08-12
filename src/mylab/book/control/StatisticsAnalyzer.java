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
        if (pub instanceof Novel) return "소설";
        if (pub instanceof Magazine) return "잡지";
        if (pub instanceof ReferenceBook) return "참고서";
        return "기타";
    }

    public void printStatistics(Publication[] publications) {
        DecimalFormat won = new DecimalFormat("#,###");
        DecimalFormat pct = new DecimalFormat("0.00");

        System.out.println("===== 출판물 통계 분석 =====");
       
        System.out.println("1. 타입별 평균 가격:");
        Map<String, Double> avg = calculateAveragePriceByType(publications);
       
        String[] order = {"소설", "참고서", "잡지", "기타"};
        for (String k : order) {
            if (avg.containsKey(k)) {
                System.out.println("   - " + k + ": " + won.format(Math.round(avg.get(k))) + "원");
            }
        }
     
        System.out.println("\n2. 출판물 유형 분포:");
        Map<String, Double> dist = calculatePublicationDistribution(publications);
        for (String k : order) {
            if (dist.containsKey(k)) {
                System.out.println("   - " + k + ": " + pct.format(dist.get(k)) + "%");
            }
        }
      
        double r = calculatePublicationRatioByYear(publications, "2007");
        System.out.println("\n3. 2007년에 출판된 출판물 비율: " + pct.format(r) + "%");
    }
}
