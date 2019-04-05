package compare.site.service.dtoCreator;

import compare.site.dto.DtoCreator;
import compare.site.entity.EnumProducts;
import compare.site.entity.EnumSite;
import compare.site.entity.ProductAbstract;
import compare.site.entity.hotline.TabletsHotline;
import compare.site.entity.hotline.TelephonesHotline;
import compare.site.entity.rozetka.TabletsRozetka;
import compare.site.entity.rozetka.TelephonesRozetka;
import compare.site.service.CopySiteProductDto;
import compare.site.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DtoCreatorServiceImpl implements DtoCreatorService {
    @Autowired
    DtoCreator dtoCreator;
    @Autowired
    GeneralService<? super ProductAbstract> generalService;

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
                            dtoCreator.createDto(EnumSite.ROZETKA.name(), EnumProducts.TELEPHONES.name());
                            if (listTelephonesRozetka != null) {
                                dtoCreator.setSize(listTelephonesRozetka.size());
                            } else {
                                dtoCreator.setSize(0);
                            }
                            CopySiteProductDto copyClass = new CopySiteProductDto(dtoCreator);
                            map.put("rozetkaTelephones", copyClass);

                        } else if (products == EnumProducts.TABLETS) {
                            dtoCreator.createDto(site.name(), products.name());
                            if (listTabletsRozetka != null) {
                                dtoCreator.setSize(listTabletsRozetka.size());
                            } else {
                                dtoCreator.setSize(0);
                            }
                            CopySiteProductDto copyClass = new CopySiteProductDto(dtoCreator);
                            map.put("rozetkaTablets", copyClass);
                        }
                        break;
                    case HOTLINE:
                        if (products == EnumProducts.TELEPHONES) {
                            dtoCreator.createDto(site.name(), products.name());
                            if (listTelephonesHotline != null) {
                                dtoCreator.setSize(listTelephonesHotline.size());
                            } else {
                                dtoCreator.setSize(0);
                            }
                            CopySiteProductDto copyClass = new CopySiteProductDto(dtoCreator);
                            map.put("hotlineTelephones", copyClass);
                        } else if (products == EnumProducts.TABLETS) {
                            dtoCreator.createDto(site.name(), products.name());
                            if (listTabletsHotline != null) {
                                dtoCreator.setSize(listTabletsHotline.size());
                            } else {
                                dtoCreator.setSize(0);
                            }
                            CopySiteProductDto copyClass = new CopySiteProductDto(dtoCreator);
                            map.put("hotlineTablets", copyClass);
                            break;
                        }
                }
            }
        }
        return map;
    }
}
