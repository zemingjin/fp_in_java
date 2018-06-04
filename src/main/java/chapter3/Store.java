package chapter3;

import java.util.List;
import static chapter3.CollectionUtilities.*;

public class Store {
    public static void main(String[] args) {
        Product toothPaste = new Product("Tooth paste", new Price(1.5), new Weight(0.5));
        Product toothBrush = new Product("Tooth brush", new Price(3.5), new Weight(0.3));
        List<OrderLine> order = list(
                new OrderLine(toothPaste, 2),
                new OrderLine(toothBrush, 3));
        Price price = Price.ZERO;
        Weight weight = Weight.ZERO;
        for (OrderLine orderLine : order) {
            price = price.add(orderLine.getAmount());
            weight = weight.add(orderLine.getWeight());
        } }
}
