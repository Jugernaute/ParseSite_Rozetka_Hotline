package compare.site.patternFactory;

import com.gargoylesoftware.htmlunit.WebClient;
import compare.site.dto.DtoCreator;
import compare.site.service.ResponseLoad;
import compare.site.service.TabletsRozetkaLoad;
import compare.site.service.TelephonesRozetkaLoad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FactoryLoadInDB {
    @Autowired
    private TelephonesRozetkaLoad telephonesRozetkaLoad;
    @Autowired
    private TabletsRozetkaLoad tabletsRozetkaLoad;

    public ResponseLoad getFactory(DtoCreator dtoCreator, WebClient webClient){
        ResponseLoad productAbstract = null;
        switch (dtoCreator.getSite()){
            case ROZETKA:
                switch (dtoCreator.getProduct()){
                    case TELEPHONES:
                        productAbstract = telephonesRozetkaLoad.load(dtoCreator,webClient);
                        break;
                    case TABLETS:
                        productAbstract = tabletsRozetkaLoad.load(dtoCreator,webClient);
                        break;
                }
                break;
            case HOTLINE:
                switch (dtoCreator.getProduct()){
                    case TABLETS:
//                        productAbstract = new TabletsHotlineLoad();
                        break;
                    case TELEPHONES:
//                        productAbstract = new TelephonesHotlineLoad();
                        break;
                }
                break;
                default: return null;
        }
        return productAbstract;
    }
}
