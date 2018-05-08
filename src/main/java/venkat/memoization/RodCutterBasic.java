package venkat.memoization;

import java.util.List;
import java.util.function.Function;

import static venkat.memoization.Memoizer.callMemoized;

class RodCutterBasic {
    private final List<Integer> prices;

    RodCutterBasic(final List<Integer> pricesForLength) {
        prices = pricesForLength;
    }

    int maxProfit(final int rodLength) {
        return callMemoized(
                (final Function<Integer, Integer> func, final Integer length) -> {
                    int profit = length <= prices.size() ? prices.get(length - 1) : 0;
                    for(int i = 1; i < length; i++) {
                        int priceWhenCut = func.apply(i) + func.apply(length - i);
                        if (profit < priceWhenCut) {
                            profit = priceWhenCut;
                        }
                    }
                    return profit;
                }, rodLength);
    }
}