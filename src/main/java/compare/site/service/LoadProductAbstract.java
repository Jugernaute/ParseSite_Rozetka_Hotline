package compare.site.service;

import com.gargoylesoftware.htmlunit.WebClient;
import compare.site.dto.DtoCreator;
import compare.site.entity.ProductAbstract;
import compare.site.methods.SaveProduct;
import compare.site.service.dateUpdate.DateOfUpdateService;
import compare.site.service.rozetka.telephone.TelephoneRozetkaService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class LoadProductAbstract {
     public String sizeOfProductInDb = "";
     public String dateUpdateStr = "";

    @Autowired
    public TelephoneRozetkaService telephoneRozetkaService;
    @Autowired
    public SaveProduct saveProduct;
    @Autowired
    public DateOfUpdateService dateOfUpdateService;
    @Autowired
    public GeneralService<? super ProductAbstract> generalService;
    @Autowired
    public DtoCreator dtoCreator;
    @Autowired
    public SaveOrUpdateDateOfLoadSiteProduct saveOrUpdateDate;

    public abstract ResponseLoad load(DtoCreator siteProductDto, WebClient webClient);
}
