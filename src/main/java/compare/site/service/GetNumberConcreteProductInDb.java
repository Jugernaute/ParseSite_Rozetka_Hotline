package compare.site.service;

import compare.site.entity.ProductAbstract;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class GetNumberConcreteProductInDb {
    public static String getNumber(Map<Long, List<? super ProductAbstract>> longListMap) {
        long key = 0;
        Set<Map.Entry<Long, List<? super ProductAbstract>>> entries = longListMap.entrySet();
        for (Map.Entry<Long, List<? super ProductAbstract>> next : entries) {
            key = next.getKey();
        }
        return String.valueOf(key);
    }
}
