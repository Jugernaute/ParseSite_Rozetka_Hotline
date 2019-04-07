package compare.site.service;

import compare.site.dto.productSite.ProductSite;
import compare.site.dto.searchProductPage.DtoSearchObject;
import compare.site.entity.EnumProducts;
import compare.site.entity.ProductAbstract;
import compare.site.entity.rozetka.TabletsRozetka;
import compare.site.entity.rozetka.TelephonesRozetka;
import compare.site.service.general.GeneralService;
import compare.site.service.rozetka.tablet.TabletRozetkaService;
import compare.site.service.rozetka.telephone.TelephoneRozetkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ResponseProductMainPage {
    @Autowired
    private TelephoneRozetkaService telephoneRozetkaService;
    @Autowired
    private GeneralService<? super ProductAbstract> generalService;
    @Autowired
    private TabletRozetkaService tabletRozetkaService;

    public Map response(DtoSearchObject dtoSearchObject) {
        Map listMap = new HashMap<>();
        ProductSite productSite = dtoSearchObject.getProductSite();
        Pageable pageable = dtoSearchObject.getPageable();
        String search = dtoSearchObject.getSearch();

            switch (productSite.getSite()) {
                case ROZETKA:
                    if (productSite.getProduct().equals(EnumProducts.TELEPHONES)) {
                        if (!search.isEmpty()) {
                            Page<TelephonesRozetka> telephones =
                                    telephoneRozetkaService.findAllByModelContains
                                            (
                                                    search, pageable
                                            );
                            listMap.put(telephones.getTotalPages(), telephones.getContent());
                            return listMap;
                        }
                        Page<TelephonesRozetka> telephones =
                                telephoneRozetkaService.findAllPageUsingPageable
                                        (
                                                pageable.getPageNumber(), pageable.getPageSize()
                                        );
                        listMap.put(telephones.getTotalPages(), telephones.getContent());
                        return listMap;
                    } else if (productSite.getProduct().equals(EnumProducts.TABLETS)) {
                        if (!search.isEmpty()) {
                            Page<TabletsRozetka> tablets = tabletRozetkaService.findAllByModelContains
                                    (
                                            search, pageable
                                    );

                            listMap.put(tablets.getTotalPages(), tablets.getContent());
                            return listMap;
                        }

                        Page<TabletsRozetka> tablets = tabletRozetkaService.findAllPageUsingPageable
                                (
                                        pageable.getPageNumber(), pageable.getPageSize()
                                );

                        listMap.put(tablets.getTotalPages(), tablets.getContent());
                        return listMap;
                    }
            }
            return listMap;
        }
    }
