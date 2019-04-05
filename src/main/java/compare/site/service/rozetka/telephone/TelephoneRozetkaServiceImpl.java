package compare.site.service.rozetka.telephone;

import compare.site.dao.rozetka.TelephonesRozetkaDao;
import compare.site.entity.rozetka.TelephonesRozetka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TelephoneRozetkaServiceImpl implements TelephoneRozetkaService{
    @Autowired
    TelephonesRozetkaDao telephonesDao;

    @Override
    public void saveTelephone(TelephonesRozetka telephones) {
            telephonesDao.save(telephones);
    }

    @Override
    public void deleteAllTelephones() {
        telephonesDao.deleteAll();
    }

    @Override
    public List<TelephonesRozetka> findAllTelephones() {
        return telephonesDao.findAll();
    }


    @Override
    public Page<TelephonesRozetka> findAllPageUsingPageable(int page, int size) {
        return telephonesDao.findAll(PageRequest.of(page, size));
    }

    @Override
    public Page<TelephonesRozetka> findAllByModelContains(String s, Pageable pageable) {
        return telephonesDao.findAllByModelContains(s, pageable);
    }
}
