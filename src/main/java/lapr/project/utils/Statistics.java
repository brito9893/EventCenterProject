package lapr.project.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Daniela Alves
 */
public class Statistics {
    private Statistics() {
    }

    public static <T> List<T> getRandom(List<T> list, int count) {
        ArrayList<T> newList = new ArrayList<>();
        if (list.size() < count) {
            return list;
        }

        Collections.shuffle(list);
        for (int i = 0; i < count; i++) {
            newList.add(list.get(i));
        }

        return newList;
    }
}
