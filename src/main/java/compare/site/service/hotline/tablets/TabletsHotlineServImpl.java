package compare.site.service.hotline.tablets;

import compare.site.dao.hotline.TabletsHotlineDao;
import compare.site.entity.hotline.TabletsHotline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TabletsHotlineServImpl implements TabletsHotlineService {
    @Autowired
    TabletsHotlineDao hotlineDao;

    @Override
    public void saveTablets(TabletsHotline telephones) {
    hotlineDao.save(telephones);
    }

    @Override
    public List<TabletsHotline> findAllTablets() {
        return hotlineDao.findAll();
    }

    @Override
    public Page<TabletsHotline> findAllPageUsingPageable(int page, int size) {
        return hotlineDao.findAll(PageRequest.of(page,size));
    }

    @Override
    public Page<TabletsHotline> findAllByModelContains(String s, Pageable pageable) {
        return hotlineDao.findAllByModelContains(s, pageable);
    }
}
