package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.LottoRank;
import lotto.domain.LottoStatisticsResult;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String NEW_LINE = "\n";
    private static final String DELIMITER = ", ";

    private OutputView() {}

    public static void printPurchasedLottos(List<Lotto> lottos) {
        System.out.printf("%d개를 구매했습니다." + NEW_LINE, lottos.size());
        for (Lotto lotto : lottos) {
            List<LottoNumber> lottoNumbers = lotto.getNumbers();
            System.out.printf("[%s]" + NEW_LINE, lottoNumbers.stream()
                            .map(LottoNumber::toString)
                    .collect(Collectors.joining(DELIMITER)));
        }
        System.out.println();
    }

    public static void printResult(LottoStatisticsResult result) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        List<LottoRank> ranks = List.of(LottoRank.FOURTH, LottoRank.THIRD, LottoRank.SECOND, LottoRank.FIRST);
        ranks.forEach(rank -> System.out.println(getStringFormat(rank, result.getCountByRank(rank))));
        System.out.printf("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)", result.getProfit());
    }

    private static String getStringFormat(LottoRank rank, int numberOfRank) {
        return String.format("%d개 일치 (%s원)- %d개", rank.getMatchCount(), rank.getReward(), numberOfRank);
    }

}
