package compare.site.service;

import com.gargoylesoftware.htmlunit.WebClient;
import compare.site.dto.productSite.ProductSite;
import compare.site.service.hotline.tablets.TabletsHotlineService;
import compare.site.service.rozetka.tablet.TabletRozetkaService;
import compare.site.service.rozetka.telephone.TelephoneRozetkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FactorySaveProducts {
    @Autowired
    private TelephoneRozetkaService telephoneRozetkaService;
    @Autowired
    private TabletRozetkaService tabletRozetkaService;
    @Autowired
    private TabletsHotlineService tabletsHotlineService;

    public ResponseLoadForFactory getFactory(ProductSite productSite, WebClient webClient){
        ResponseLoadForFactory productAbstract = null;
        switch (productSite.getSite()){
            case ROZETKA:
                switch (productSite.getProduct()){
                    case TELEPHONES:
                        productAbstract = telephoneRozetkaService.saveToBase(productSite,webClient);
                        break;
                    case TABLETS:
                        productAbstract = tabletRozetkaService.saveToBase(productSite,webClient);
                        break;
                }
                break;
            case HOTLINE:
                switch (productSite.getProduct()){
                    case TABLETS:
                        productAbstract = tabletsHotlineService.saveToBase(productSite, webClient);
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
