package compare.site.service.rozetka.tablet;

import compare.site.dao.rozetka.TabletsRozetkaDao;
import compare.site.entity.rozetka.TabletsRozetka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TabletRozetkaServiceImpl implements TabletRozetkaService {

    @Autowired
    private TabletsRozetkaDao tabletsDao;

    public Page<TabletsRozetka> findAllPageUsingPageable(int page, int size) {
        return tabletsDao.findAll(PageRequest.of(page, size));
    }

    @Override
    public Page<TabletsRozetka> findAllByModelContains(String s, Pageable pageable) {
        return tabletsDao.findAllByModelContains(s, pageable);
    }


    @Override
    public void saveTablet(TabletsRozetka tablets) {
            tabletsDao.save(tablets);
    }

    @Override
    public List<TabletsRozetka> findAllTablets() {
        return tabletsDao.findAll();
    }
}
