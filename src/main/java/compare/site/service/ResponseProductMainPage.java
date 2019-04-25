package compare.site.service;

import compare.site.dto.productSite.ProductSite;
import compare.site.dto.searchProduct.DtoSearchObject;
import compare.site.entity.EnumProducts;
import compare.site.entity.mobilluck.TabletsMobilluck;
import compare.site.entity.mobilluck.TelephonesMobilluck;
import compare.site.entity.rozetka.TabletsRozetka;
import compare.site.entity.rozetka.TelephonesRozetka;
import compare.site.service.mobilluck.tablets.TabletsMobilluckService;
import compare.site.service.mobilluck.telephones.TelephonesMobilluckService;
import compare.site.service.rozetka.tablet.TabletRozetkaService;
import compare.site.service.rozetka.telephone.TelephoneRozetkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * This class load on main page {<path>..templates/main.html</path>}
 * products which depending from
 * {@link DtoSearchObject}
 */
@Component
public class ResponseProductMainPage {
    @Autowired
    private TelephoneRozetkaService telephoneRozetkaService;
    @Autowired
    private TabletRozetkaService tabletRozetkaService;
    @Autowired
    private TabletsMobilluckService tabletsMobilluckService;
    @Autowired
    private TelephonesMobilluckService telephonesMobilluckService;


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
                            String s = Integer.toString(telephones.getTotalPages())+"."+Long.toString(telephones.getTotalElements());
                            listMap.put(s, telephones.getContent());

                            return listMap;
                        }
                        Page<TelephonesRozetka> telephones =
                                telephoneRozetkaService.findAllPageUsingPageable
                                        (
                                                pageable.getPageNumber(), pageable.getPageSize()
                                        );
                        String s = Integer.toString(telephones.getTotalPages())+"."+Long.toString(telephones.getTotalElements());
                        listMap.put(s, telephones.getContent());
                        return listMap;
                    } else if (productSite.getProduct().equals(EnumProducts.TABLETS)) {
                        if (!search.isEmpty()) {
                            Page<TabletsRozetka> tablets = tabletRozetkaService.findAllByModelContains
                                    (
                                            search, pageable
                                    );
                            String s = Integer.toString(tablets.getTotalPages())+"."+Long.toString(tablets.getTotalElements());
                            listMap.put(s, tablets.getContent());
                            return listMap;
                        }

                        Page<TabletsRozetka> tablets = tabletRozetkaService.findAllPageUsingPageable
                                (
                                        pageable.getPageNumber(), pageable.getPageSize()
                                );

                        String s = Integer.toString(tablets.getTotalPages())+"."+Long.toString(tablets.getTotalElements());
                        listMap.put(s, tablets.getContent());
                        return listMap;
                    }
                case MOBILLUCK:
                    if (productSite.getProduct().equals(EnumProducts.TABLETS)){
                        if (!search.isEmpty()) {
                            Page<TabletsMobilluck> tablets =
                                    tabletsMobilluckService.findAllByModelContains
                                            (
                                                    search, pageable
                                            );
                            String s = Integer.toString(tablets.getTotalPages())+"."+Long.toString(tablets.getTotalElements());
                            listMap.put(s, tablets.getContent());

                            return listMap;
                        }
                        Page<TabletsMobilluck> telephones =
                                tabletsMobilluckService.findAllPageUsingPageable
                                        (
                                                pageable.getPageNumber(), pageable.getPageSize()
                                        );
                        String s = Integer.toString(telephones.getTotalPages())+"."+Long.toString(telephones.getTotalElements());
                        listMap.put(s, telephones.getContent());
                        return listMap;
                    } else if (productSite.getProduct().equals(EnumProducts.TELEPHONES)){
                        if (!search.isEmpty()) {
                            Page<TelephonesMobilluck> tablets =
                                    telephonesMobilluckService.findAllByModelContains
                                            (
                                                    search, pageable
                                            );
                            String s = Integer.toString(tablets.getTotalPages())+"."+Long.toString(tablets.getTotalElements());
                            listMap.put(s, tablets.getContent());

                            return listMap;
                        }
                        Page<TelephonesMobilluck> telephones =
                                telephonesMobilluckService.findAllPageUsingPageable
                                        (
                                                pageable.getPageNumber(), pageable.getPageSize()
                                        );
                        String s = Integer.toString(telephones.getTotalPages())+"."+Long.toString(telephones.getTotalElements());
                        listMap.put(s, telephones.getContent());
                        return listMap;
                    }
            }
            return listMap;
        }
    }
