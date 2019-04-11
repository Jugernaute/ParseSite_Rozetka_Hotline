package compare.site.service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import compare.site.dto.productSite.ProductSite;
import compare.site.entity.EnumProducts;
import compare.site.entity.ProductAbstract;
import compare.site.entity.hotline.TabletsHotline;
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

    public abstract ResponseLoadForFactory saveToBase(ProductSite productSite, WebClient webClient);

    protected void saveProduct(ProductSite productSite, WebClient webClient, String httpLink) {
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
                case HOTLINE:
                        /*
                         * all product on this site are in tag <li class = "product-item">
                         *     which includes all necessary info about tablets.
                         *     From every @li get :
                         *     p[class='h4'] - modelProduct;
                         * */
                        List<HtmlElement> listModels = page.getByXPath("//div[@class='item-info']/p");

                        /*
                         * This string need for concat with link from List<HtmlAnchor> listLinks,
                         * since each element from this list not have full address for link*/
                        String addLink = "http://hotline.ua";
                        List<HtmlAnchor> listLinks2 = page.getByXPath("//div[@class='item-info']/p[@class='h4']/a");
                        /*
                         * *     div[class='text']/p - descriptionProduct
                         * */
                        List<HtmlElement> listDescriptions = page.getByXPath("//div[@class='text']/p");

                        /*
                         * prices of products.
                         * But working only when webClient.getOptions().setJavaScriptEnabled(true);
                         * It settings in WebClientSettings.class from page admin.html
                         * By default is false */
                        List<HtmlSpan> listPrices = page.getByXPath("//div[@class='price-md']/span[@class='value']");


                        count = 0;
                        List<HtmlElement> totalProduct = page.getByXPath("//li[@class='product-item']");
                    System.out.println(totalProduct.size());
                        for (HtmlElement htmlDivision : totalProduct) {
                            count++;
                            TabletsHotline tabletsHotline = new TabletsHotline();

                            tabletsHotline.setModel( listModels.get(Math.toIntExact(count-1)).asText());
                            tabletsHotline.setLinkOnSite( addLink.concat(listLinks2.get(Math.toIntExact(count-1)).getHrefAttribute()));
                            tabletsHotline.setDescript(listDescriptions.get(Math.toIntExact(count-1)).asText());
                            String priceStr = listPrices.get(Math.toIntExact(count - 1)).asText().replaceAll("\\D+","");
                            Integer price = Integer.valueOf(priceStr);
                            tabletsHotline.setPrice(price);
                            System.out.println(count);
                            generalService.saveProduct(tabletsHotline);
                        }
                    nums+=count;
                    numberConcreteProductFromBase.setNum(nums);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
};

