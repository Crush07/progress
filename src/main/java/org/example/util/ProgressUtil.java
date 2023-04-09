package org.example.util;

import org.example.entity.Element;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProgressUtil {

    public static<T,R,S extends Number,O extends Element> List<O> sort(List<T> list, Function<T,S> getScoreFunction, Function<T,R> groupByFunction, Class<O> returnClazz) throws Exception{

        List<O> rankingList = new ArrayList<>();
        Map<R, List<T>> collect = list.stream().collect(Collectors.groupingBy(groupByFunction));
        for (Map.Entry<R, List<T>> entry : collect.entrySet()) {

            List<T> value = entry.getValue();
            double temp = 1;
            double sum = 0;
            for (int i = 1; i < value.size(); ++i) {
                double v = (Double) getScoreFunction.apply(value.get(i)) - (Double)getScoreFunction.apply(value.get(i-1));
                if (temp >= 1 && (int) v / 10 >= 0 || temp <= 1 && (int) v / 10 <= 0) {
                    temp += v / 100 * 0.1;
                } else {
                    temp = 1 + v / 100 * 0.1;
                }
                sum += v * Math.abs(temp);
            }
            O o = returnClazz.getDeclaredConstructor().newInstance();
            o.setWeight(sum);
            rankingList.add(o);
        }

        return rankingList.stream().sorted(Comparator.comparing(O::getWeight).reversed()).collect(Collectors.toList());
    }

}
