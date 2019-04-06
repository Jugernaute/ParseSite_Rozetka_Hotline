package compare.site.service.general;

import compare.site.entity.ProductAbstract;
import compare.site.entity.rozetka.TabletsRozetka;
import compare.site.entity.rozetka.TelephonesRozetka;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GeneralService<T extends ProductAbstract> {
    T saveProduct (T t);
    List findAllProducts(Class<?> aClass);
    Page<T> findAllPageUsingPageable(int page, int size);
    Page<T> findAllByModelContains(String s, Pageable pageable);

}
