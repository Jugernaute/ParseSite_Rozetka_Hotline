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


public abstract class UploadProductAbstract {

    @Autowired
    public GeneralService<? super ProductAbstract> generalService;
    @Autowired
    public DateOfUpdateService dateOfUpdateService;
    @Autowired
    public NumberConcreteProductInBase numberConcreteProductInBase;


    public ProductSite productSite;
    protected String dateUpdateStr = "";
    /* how many products are on site*/
    protected static long nums = 0;

     /**
     * @param productSite - include names of site and product
     * @param httpLink - link on site with concrete product
     * */
    protected void saveProduct(ProductSite productSite, WebClient webClient, String httpLink) {
        /**
         * this numeric calculate how many products are on page (@numPages) of site.
         * When page change to another, the value of this numeric
         * is passed to another number {@link nums}
         * */
        long count = 0;
        try {
            /**
              * @exception java.io.IOException if an IO problem occurs.
              * @exception java.net.MalformedURLException if an error occurred when creating a URL object
              *
              * @param httpLink url site with one page, come from service level
              *                 where number of page - changes.
              */
            HtmlPage page = webClient.getPage(httpLink);
            switch (productSite.getSite()){
                case ROZETKA:

                    /* main domElement with all info about product,
                     * that contains description, link, price and model */
                    List<HtmlDivision> mainBlock = page.getByXPath("//div[@class='g-i-tile-i-box-desc']");

                    /*links on product*/
                    List<HtmlAnchor> listLinks = page.getByXPath("//a[@class='responsive-img centering-child-img']");

                    /**
                     * prices on product,
                     * but at this moment prices product dont used,
                     * because this requires {<code>setJavaScriptEnabled=true</code>} {@link WebClientSettings}
                     * and this will lead to a fairly long download of the information
                     */
                    List<HtmlDivision> listPrice = page.getByXPath("//div[@class='g-price-uah']");


                    for (HtmlDivision htmlDivision : mainBlock)
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
//                            String priceAsText = listPrice.get(Math.toIntExact(count - 1)).asText();
//                            int price = Integer.valueOf(priceAsText.replaceAll("\\D+", ""));
//                            telephones.setPrice(price);
                            generalService.saveProduct(telephones);
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
                    numberConcreteProductInBase.setNum(nums);
                    break;
                case MOBILLUCK:
                    /* main domElement with all info about product,
                     * that contains description, link, price and model */
                    List<HtmlDivision> mainBlock2 = page.getByXPath("//div[@class='ccitem2']/div[@class='cci2info']");

                    for (HtmlDivision htmlDivision : mainBlock2) {
                        count++;

                        /*
                         * prices of products.
                         * Sometimes(depending of site), get price is possible when webClient.getOptions().setJavaScriptEnabled(true)
                         * In this case, its working without inclusion JavaScript
                         * It settings in WebClientSettings.class from page admin.html
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
                        DomElement model = htmlDivision.getFirstElementChild();

                        /*
                        *link
                        * */
                        String href = model.getFirstElementChild().getAttribute("href");
                        String http = "http:";

                        if (productSite.getProduct().equals(EnumProducts.TELEPHONES))
                        {
                            TelephonesMobilluck telephonesMobilluck = new TelephonesMobilluck();
                            telephonesMobilluck.setModel(model.asText());
                            telephonesMobilluck.setDescript(description);
                            telephonesMobilluck.setLinkOnSite(http.concat(href));
                            telephonesMobilluck.setPrice(price);
                            generalService.saveProduct(telephonesMobilluck);
                        }
                        else if (productSite.getProduct().equals(EnumProducts.TABLETS))
                        {
                            TabletsMobilluck tabletsMobilluck = new TabletsMobilluck();
                            tabletsMobilluck.setModel(model.asText());
                            tabletsMobilluck.setDescript(description);
                            tabletsMobilluck.setLinkOnSite(http.concat(href));
                            tabletsMobilluck.setPrice(price);
                            generalService.saveProduct(tabletsMobilluck);
                        }
                    }
                    nums+=count;
                numberConcreteProductInBase.setNum(nums);
                    break;
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

