package compare.site.service;

import com.gargoylesoftware.htmlunit.WebClient;
import compare.site.dto.productSite.DtoProductSite;
import compare.site.service.rozetka.tablet.TabletRozetkaService;
import compare.site.service.rozetka.telephone.TelephoneRozetkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FactoryLoadGoodsInBase {
    @Autowired
    private TelephoneRozetkaService telephoneRozetkaService;
    @Autowired
    private TabletRozetkaService tabletRozetkaService;

    public ResponseLoadForFactory getFactory(DtoProductSite dtoCreator, WebClient webClient){
        ResponseLoadForFactory productAbstract = null;
        switch (dtoCreator.getSite()){
            case ROZETKA:
                switch (dtoCreator.getProduct()){
                    case TELEPHONES:
                        productAbstract = telephoneRozetkaService.load(dtoCreator,webClient);
                        break;
                    case TABLETS:
                        productAbstract = tabletRozetkaService.load(dtoCreator,webClient);
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
