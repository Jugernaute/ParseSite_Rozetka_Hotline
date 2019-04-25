package compare.site.service;

import com.gargoylesoftware.htmlunit.WebClient;
import compare.site.dto.productSite.ProductSite;
import compare.site.service.mobilluck.tablets.TabletsMobilluckService;
import compare.site.service.mobilluck.telephones.TelephonesMobilluckService;
import compare.site.service.rozetka.tablet.TabletRozetkaService;
import compare.site.service.rozetka.telephone.TelephoneRozetkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Pattern Factory, which save product
 * in DB, depending on variables(site and product) of  {@link ProductSite}
 * */
@Component
public class FactorySaveProducts {
    @Autowired
    private TelephoneRozetkaService telephoneRozetkaService;
    @Autowired
    private TabletRozetkaService tabletRozetkaService;
    @Autowired
    private TabletsMobilluckService tabletMobilluckService;
    @Autowired
    private TelephonesMobilluckService telephonesMobilluckService;

    /**
     * Save and
     * @return {@link ResponseUploadForFactory}, which contains
     * how much a concrete product are in DB and last date upload in DB of this product
     * */
    public ResponseUploadForFactory getFactory(ProductSite productSite, WebClient webClient){
        ResponseUploadForFactory productAbstract = null;
        switch (productSite.getSite()){
            case ROZETKA:
                switch (productSite.getProduct()){
                    case TELEPHONES:
                        /**
                        * save to DB {@link TelephoneRozetkaService}
                        * */
                        productAbstract = telephoneRozetkaService.saveToBase(productSite,webClient);
                        break;
                    case TABLETS:
                        /**
                         * save to DB {@link TabletRozetkaService}
                         * */
                        productAbstract = tabletRozetkaService.saveToBase(productSite,webClient);
                        break;
                }
                break;
            case MOBILLUCK:
                switch (productSite.getProduct()){
                    case TABLETS:
                        /**
                         * save to DB {@link TabletsMobilluckService}
                         * */
                        productAbstract = tabletMobilluckService.saveToBase(productSite, webClient);
                        break;
                    case TELEPHONES:
                        /**
                         * save to DB {@link TelephonesMobilluckService}
                         * */
                        productAbstract = telephonesMobilluckService.saveToBase(productSite, webClient);
                        break;
                }
                break;
                default: return null;
        }
        return productAbstract;
    }
}
