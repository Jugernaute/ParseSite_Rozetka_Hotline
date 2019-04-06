package compare.site.service.dtoProductSite;

import compare.site.dto.productSite.DtoProductSite;
import compare.site.dto.productSite.ProductSite;
import compare.site.entity.EnumProducts;
import compare.site.entity.EnumSite;
import compare.site.entity.ProductAbstract;
import compare.site.entity.hotline.TabletsHotline;
import compare.site.entity.hotline.TelephonesHotline;
import compare.site.entity.rozetka.TabletsRozetka;
import compare.site.entity.rozetka.TelephonesRozetka;
import compare.site.dto.productSite.CopySiteProductDto;
import compare.site.service.general.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DtoCreatorServiceImpl implements DtoCreatorService {
    @Autowired
    private DtoProductSite dtoProductSite;
    @Autowired
    private GeneralService<? super ProductAbstract> generalService;
    @Autowired
    private ProductSite productSite;

    @Override
    public Map<String, CopySiteProductDto> adminInfoAboutAllProducts() {
        List listTabletsRozetka = generalService.findAllProducts(TabletsRozetka.class);

        List listTelephonesHotline = generalService.findAllProducts(TelephonesHotline.class);
        List listTabletsHotline = generalService.findAllProducts(TabletsHotline.class);

        Map<String, CopySiteProductDto> map = new HashMap<>();

        for (EnumSite site : EnumSite.values()){
            for (EnumProducts products : EnumProducts.values()){
                switch (site){
                    case ROZETKA:
                        if (products == EnumProducts.TELEPHONES) {
                            List listTelephonesRozetka = generalService.findAllProducts(TelephonesRozetka.class);
                            productSite.create(EnumSite.ROZETKA.name(), EnumProducts.TELEPHONES.name());
                            dtoProductSite.createDto(productSite);
                            if (listTelephonesRozetka != null) {
                                dtoProductSite.setSize(listTelephonesRozetka.size());
                            } else {
                                dtoProductSite.setSize(0);
                            }
                            CopySiteProductDto copyClass = new CopySiteProductDto(dtoProductSite);
                            map.put("rozetkaTelephones", copyClass);

                        } else if (products == EnumProducts.TABLETS) {
                            productSite.create(site.name(), products.name());
                            dtoProductSite.createDto(productSite);
                            if (listTabletsRozetka != null) {
                                dtoProductSite.setSize(listTabletsRozetka.size());
                            } else {
                                dtoProductSite.setSize(0);
                            }
                            CopySiteProductDto copyClass = new CopySiteProductDto(dtoProductSite);
                            map.put("rozetkaTablets", copyClass);
                        }
                        break;
                    case HOTLINE:
                        if (products == EnumProducts.TELEPHONES) {
                            productSite.create(site.name(), products.name());
                            dtoProductSite.createDto(productSite);
                            if (listTelephonesHotline != null) {
                                dtoProductSite.setSize(listTelephonesHotline.size());
                            } else {
                                dtoProductSite.setSize(0);
                            }
                            CopySiteProductDto copyClass = new CopySiteProductDto(dtoProductSite);
                            map.put("hotlineTelephones", copyClass);
                        } else if (products == EnumProducts.TABLETS) {
                            productSite.create(site.name(), products.name());
                            dtoProductSite.createDto(productSite);
                            if (listTabletsHotline != null) {
                                dtoProductSite.setSize(listTabletsHotline.size());
                            } else {
                                dtoProductSite.setSize(0);
                            }
                            CopySiteProductDto copyClass = new CopySiteProductDto(dtoProductSite);
                            map.put("hotlineTablets", copyClass);
                            break;
                        }
                }
            }
        }
        return map;
    }
}
