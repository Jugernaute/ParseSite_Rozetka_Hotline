package compare.site.service;

import com.gargoylesoftware.htmlunit.WebClient;
import compare.site.dto.productSite.DtoProductSite;
import compare.site.entity.ProductAbstract;
import compare.site.methods.SaveProduct;
import compare.site.service.dateUpdate.DateOfUpdateService;
import compare.site.service.general.GeneralService;
import compare.site.service.rozetka.telephone.TelephoneRozetkaService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class LoadProductAbstract {
     protected String sizeOfProductInDb = "";
     protected String dateUpdateStr = "";

    @Autowired
    public TelephoneRozetkaService telephoneRozetkaService;
    @Autowired
    public SaveProduct saveProduct;
    @Autowired
    public DateOfUpdateService dateOfUpdateService;
    @Autowired
    public GeneralService<? super ProductAbstract> generalService;
    @Autowired
    public DtoProductSite dtoProductSite;

    public abstract ResponseLoadForFactory load(DtoProductSite siteProductDto, WebClient webClient);
}
