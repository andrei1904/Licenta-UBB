import java.util.*;
import java.util.concurrent.ScheduledFuture;

public class MyMap {
    Map<Integer, List<Student>> map;

    public MyMap() {
        map = new TreeMap<>(new ComparatorMedie());
    }

    static class ComparatorMedie implements Comparator<Integer>{
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    }

    public void add(Student s) {
        int medie = (int) Math.round(s.getMedia());

        if (map.containsKey(medie)) {
            List<Student> list = map.get(medie);
            list.add(s);

        } else {
            List<Student> list = new ArrayList<>();
            list.add(s);

            map.put(medie, list);
        }
    }

    public Set<Map.Entry<Integer, List<Student>>> getEntries() {
        return map.entrySet();
    }
}
