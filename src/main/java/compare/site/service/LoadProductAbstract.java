package compare.site.service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import compare.site.dto.productSite.ProductSite;
import compare.site.entity.EnumProducts;
import compare.site.entity.ProductAbstract;
import compare.site.entity.mobilluck.TabletsMobilluck;
import compare.site.entity.mobilluck.TelephonesMobilluck;
import compare.site.entity.rozetka.TabletsRozetka;
import compare.site.entity.rozetka.TelephonesRozetka;
import compare.site.service.dateUpdate.DateOfUpdateService;
import compare.site.service.general.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

public abstract class LoadProductAbstract {

    @Autowired
    public GeneralService<? super ProductAbstract> generalService;

    @Autowired
    public DateOfUpdateService dateOfUpdateService;
    @Autowired
    public GetNumberConcreteProductFromBase numberConcreteProductFromBase;


    public ProductSite productSite;
    protected String dateUpdateStr = "";
    protected static long nums = 0;

//    public abstract ResponseLoadForFactory saveToBase(ProductSite productSite, WebClient webClient);

    protected void saveProduct(ProductSite productSite, WebClient webClient, String httpLink) {
        /*
         * this number calculate how many products are on this(@numPages) page,
         * when page change to another, the value of this number ( @c )
         * is passed to another number (@count)
         * */
        long count = 0;
        try {
            HtmlPage page = webClient.getPage(httpLink);
            switch (productSite.getSite()){
                case ROZETKA:
                    /*
                     * main block with all info about telephone
                     * */
                    List<HtmlDivision> byXPath2 = page.getByXPath("//div[@class='g-i-tile-i-box-desc']");

                    List<HtmlAnchor> listLinks = page.getByXPath("//a[@class='responsive-img centering-child-img']");
                    List<HtmlDivision> listPrice = page.getByXPath("//div[@class='g-price-uah']");
                    for (HtmlDivision htmlDivision : byXPath2)
                    {
                        count++;
                        List<HtmlDivision> listModel = htmlDivision.getByXPath("//div[@class='g-i-tile-i-title clearfix']");
                        String description = htmlDivision.getLastElementChild().asText();

                        if (productSite.getProduct().equals(EnumProducts.TELEPHONES))
                        {
                            TelephonesRozetka telephones = new TelephonesRozetka();
                            telephones.setModel(listModel.get(Math.toIntExact(count - 1)).asText());
                            telephones.setDescript(description);
                            telephones.setLinkOnSite(listLinks.get(Math.toIntExact(count - 1)).getHrefAttribute());
                            generalService.saveProduct(telephones);
            //                    telephones.setPriceTelephone(Integer.parseInt(price));
                        }
                            else if (productSite.getProduct().equals(EnumProducts.TABLETS))
                        {
                            TabletsRozetka tablets = new TabletsRozetka();
                            tablets.setModel(listModel.get(Math.toIntExact(count - 1)).asText());
                            tablets.setDescript(description);
                            tablets.setLinkOnSite(listLinks.get(Math.toIntExact(count - 1)).getHrefAttribute());
            //                    tablets.setPriceTelephone(Integer.parseInt(price));
                            generalService.saveProduct(tablets);
                        }
                    }
                    nums+=count;
                    numberConcreteProductFromBase.setNum(nums);
                case MOBILLUCK:
                    /*
                    * main block with product, which include all info about this
                    * */
                    List<HtmlDivision> mainBlock = page.getByXPath("//div[@class='ccitem2']/div[@class='cci2info']");
                    /*
                    * using this cycle, get
                    * @modelProduct,
                    * @linkProduct,
                    * @descriptionProduct,
                    * @priceProduct
                    * */
                    for (HtmlDivision htmlDivision : mainBlock) {
                        count++;
                        /*
                         * prices of products.
                         * Sometimes(depending of site), get price is possible when webClient.getOptions().setJavaScriptEnabled(true)
                         * In this case, its working without inclusion JavaScript
                         * It settings in WebClientSettings.class from page admin.html
                         * By default is false
                         * */

                        List<HtmlElement> listPrices = htmlDivision.getByXPath("//p[@class='cci2_newprice']");
                        HtmlElement htmlElement = listPrices.get(Math.toIntExact(count) - 1);
                        String priceAsText = htmlElement.asText();
                        String priceReplace = priceAsText.replaceAll("\\D+", "");
                        Integer price = Integer.valueOf(priceReplace);

                        /*
                         * description
                         * */
                        List<HtmlDivision> listDescriptions = page.getByXPath("//div[@style='padding: 15px 0 15px 0; clear: left;']");
                        HtmlDivision htmlDivision1 = listDescriptions.get(Math.toIntExact(count) - 1);
                        String description = htmlDivision1.asText();

                        /*
                        *model
                        */
                        DomElement firstElementChild = htmlDivision.getFirstElementChild();

                        /*
                        *link
                        * */
                        String href = firstElementChild.getFirstElementChild().getAttribute("href");
                        String http = "http:";

                        if (productSite.getProduct().equals(EnumProducts.TELEPHONES))
                        {
                            TelephonesMobilluck telephonesMobilluck = new TelephonesMobilluck();
                            telephonesMobilluck.setModel(firstElementChild.asText());
                            telephonesMobilluck.setDescript(description);
                            telephonesMobilluck.setLinkOnSite(http.concat(href));
                            telephonesMobilluck.setPrice(price);
                            generalService.saveProduct(telephonesMobilluck);
                        }
                        else if (productSite.getProduct().equals(EnumProducts.TABLETS))
                        {
                            TabletsMobilluck tabletsMobilluck = new TabletsMobilluck();
                            tabletsMobilluck.setModel(firstElementChild.asText());
                            tabletsMobilluck.setDescript(description);
                            tabletsMobilluck.setLinkOnSite(http.concat(href));
                            tabletsMobilluck.setPrice(price);
                            generalService.saveProduct(tabletsMobilluck);
                        }
                    }
                nums+=count;
                numberConcreteProductFromBase.setNum(nums);
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

