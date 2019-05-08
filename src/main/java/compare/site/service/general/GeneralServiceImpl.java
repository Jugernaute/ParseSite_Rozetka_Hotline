package compare.site.service.general;

import compare.site.dao.general.GeneralDao;
import compare.site.dao.mobilluck.TabletsMobilluckDao;
import compare.site.dao.mobilluck.TelephonesMobilluckDao;
import compare.site.dao.rozetka.TabletsRozetkaDao;
import compare.site.dao.rozetka.TelephonesRozetkaDao;
import compare.site.entity.ProductAbstract;
import compare.site.entity.mobilluck.TabletsMobilluck;
import compare.site.entity.mobilluck.TelephonesMobilluck;
import compare.site.entity.rozetka.TabletsRozetka;
import compare.site.entity.rozetka.TelephonesRozetka;
import compare.site.service.general.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class GeneralServiceImpl<T extends ProductAbstract> implements GeneralService<T> {
    @Autowired
    private GeneralDao<T> generalDao;
    @Autowired
    private TelephonesRozetkaDao telephonesRozetkaDao;
    @Autowired
    private TabletsRozetkaDao tabletsRozetkaDao;
    @Autowired
    private TelephonesMobilluckDao telephonesMobilluckDao;
    @Autowired
    private TabletsMobilluckDao tabletsMobilluckDao;

    public void saveProduct(T t) {
        generalDao.save(t);
    }

    @Override
    public List findAllProducts(Class<?> aClass) {
        if (aClass.equals(TelephonesRozetka.class)){
            return findAllTelephonesRozetka();
        }else if(aClass.equals(TabletsRozetka.class)){
            return findAllTabletsRozetka();
        }else if (aClass.equals(TabletsMobilluck.class)){
            return findAllTabletsMobilluck();
        }else if (aClass.equals(TelephonesMobilluck.class)){
            return findAllTelephonesMobilluck();
        }
        return null;
    }

    private List<TabletsRozetka> findAllTabletsRozetka (){
        return tabletsRozetkaDao.findAll();
    }

    private List<TelephonesRozetka> findAllTelephonesRozetka () {
        return telephonesRozetkaDao.findAll();
    }
    private List<TabletsMobilluck> findAllTabletsMobilluck () {
        return tabletsMobilluckDao.findAll();
    }
    private List<TelephonesMobilluck> findAllTelephonesMobilluck () {
        return telephonesMobilluckDao.findAll();
    }

    @Override
    public Page<T> findAllPageUsingPageable(int page, int size) {
        return generalDao.findAll(PageRequest.of(page, size));
    }

    @Override
    public Page<T> findAllByModelContains(String s, Pageable pageable) {
        try {
            return generalDao.findAllByModelContains(s, pageable);
        }
        catch (Exception e){
            System.out.println("error -> "+ e.getMessage());
        }
        return null;
    }
}
