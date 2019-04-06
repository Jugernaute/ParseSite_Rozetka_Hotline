package compare.site.service;

import com.gargoylesoftware.htmlunit.WebClient;
import compare.site.dto.productSite.DtoProductSite;
import compare.site.dto.productSite.ProductSite;
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

    public ResponseLoadForFactory getFactory(ProductSite productSite, WebClient webClient){
        ResponseLoadForFactory productAbstract = null;
        switch (productSite.getSite()){
            case ROZETKA:
                switch (productSite.getProduct()){
                    case TELEPHONES:
                        productAbstract = telephoneRozetkaService.load(productSite,webClient);
                        break;
                    case TABLETS:
                        productAbstract = tabletRozetkaService.load(productSite,webClient);
                        break;
                }
                break;
            case HOTLINE:
                switch (productSite.getProduct()){
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
