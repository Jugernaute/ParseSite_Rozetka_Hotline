package compare.site.service.hotline.telephones;

import compare.site.dao.hotline.TelephonesHotlineDao;
import compare.site.entity.hotline.TelephonesHotline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TelephonesHotlineServImpl implements TelephonesHotlineService {
    @Autowired
    TelephonesHotlineDao hotlineDao;

    @Override
    public void saveTelephone(TelephonesHotline telephones) {
    hotlineDao.save(telephones);
    }

    @Override
    public List<TelephonesHotline> findAllTelephones() {
        return hotlineDao.findAll();
    }

    @Override
    public Page<TelephonesHotline> findAllPageUsingPageable(int page, int size) {
        return hotlineDao.findAll(PageRequest.of(page, size));
    }

    @Override
    public Page<TelephonesHotline> findAllByModelContains(String s, Pageable pageable) {
        return hotlineDao.findAllByModelContains(s, pageable);
    }
}
