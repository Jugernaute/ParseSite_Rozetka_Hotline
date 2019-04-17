package compare.site.controllers.security;

import com.gargoylesoftware.htmlunit.WebClient;
import compare.site.entity.*;
import compare.site.dto.productSite.CopySiteProductDto;
import compare.site.service.dtoProductSite.DtoCreatorService;
import compare.site.service.general.GeneralService;
import compare.site.service.mobilluck.tablets.TabletsMobilluckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

@Controller
public class SecurityController {

    @Autowired
    DtoCreatorService dtoCreatorService;
    @Autowired
    private GeneralService<? super ProductAbstract> generalService;
    @Autowired
    private TabletsMobilluckService tabletsMobilluckService;


    @GetMapping("/")
    private String start(Model model){
        WebClient webClient = new WebClient();
        webClient.getOptions().setJavaScriptEnabled(false);
//        webClient.waitForBackgroundJavaScript(2000);
        webClient.getOptions().setCssEnabled(false);

//        try {
////            HtmlPage page = webClient.getPage("https://eldorado.ua/tablet-pc/c1039006/");
//            for (int i = 1; i < 5; i++) {
//                HtmlPage page = webClient.getPage("https://www.mobilluck.com.ua/katalog/iplanshet/pages_"+i+"_15.html");
//                List<HtmlAnchor> listPages = page.getByXPath("//a[@class='a-text']");
//                String pages = listPages.get(listPages.size() - 1).asText();
////            List<HtmlAnchor> listCountOfPages = page.getByXPath("//a[@class='page-link']");
//                List<HtmlDivision> listCountOfPages = page.getByXPath("//div[@style='padding: 15px 0 15px 0; clear: left;']");
////            List<HtmlDivision> mainBlock = page.getByXPath("//div[@class='cci2info']");
//                int elementDome = 0;
//                List<HtmlDivision> mainBlock = page.getByXPath("//div[@class='ccitem2']/div[@class='cci2info']");
//                for (HtmlDivision htmlDivision : mainBlock) {
//                    elementDome++;
//
//                    DomElement firstElementChild = htmlDivision.getFirstElementChild();
//                    System.out.println("model > " + firstElementChild.asText());
//                    String href = firstElementChild.getFirstElementChild().getAttribute("href");
//                    String http = "http:";
//                    System.out.println("link > " + http.concat(href));
//
//                    List<HtmlDivision> listDescriptions = page.getByXPath("//div[@style='padding: 15px 0 15px 0; clear: left;']");
//                    HtmlDivision htmlDivision1 = listDescriptions.get(elementDome - 1);
//                    System.out.println("description > " + htmlDivision1.asText());
//
//                    List<HtmlElement> listPrices = htmlDivision.getByXPath("//p[@class='cci2_newprice']");
//                    HtmlElement htmlElement = listPrices.get(elementDome - 1);
//                    String s = htmlElement.asText();
//                    String s1 = s.replaceAll("\\D+", "");
//                    Integer price = Integer.valueOf(s1);
//                    System.out.println("price > " + price);
////                for (HtmlElement listPrice : listPrices) {
////                    System.out.println(">>>>> " + listPrice);
////                }
//
//                    System.out.println(elementDome +" ==================");
//
//                }
//            }
//
//
//
//
//            /*
//            * count of pages with tablets
//            * */
//
////            for (HtmlDivision listCountOfPage : listCountOfPages) {
////                Iterable<DomElement> childElements = listCountOfPage.getChildElements();
////                for (DomElement childElement : childElements) {
////                    System.out.println(childElement);
////                }
////                System.out.println("========================");
////            }
//
////            String numPages = listCountOfPages.get(listCountOfPages.size() - 1).asText();
////            System.out.println(numPages);
//
//
//            /*
//            * *     div[class='text']/p - descriptionProduct
//            * */
//
//            /*
//            * prices of products.
//            * But working only when webClient.getOptions().setJavaScriptEnabled(true);
//            * It settings in WebClientSettings.class from page admin.html
//            * By default is false */
////            List<HtmlSpan> listPrices = page.getByXPath("//div[@class='price-md']/span[@class='value']");
////            for (HtmlSpan listPrice : listPrices) {
////                System.out.println(listPrice.asText());
////            }
//
////            tabletsMobilluckService.deleteAllTablets();
////            int c = 0;
////            List<HtmlElement> totalProduct = page.getByXPath("//li[@class='product-item']");
//
////            for (HtmlElement htmlElement : totalProduct) {
//
////                List<HtmlDivision> item_info = htmlElement.getByXPath("//div[@class='item-info']");
////
////                for (HtmlDivision htmlDivision : item_info) {
////                    Iterable<DomElement> childElements = htmlDivision.getChildElements();
////                    int element = 0;
////                    for (DomElement childElement : childElements) {
////                        element++;
////                        if (element ==1){
////                            //model
//////                            System.out.println(childElement.asText());
////                            //link
//////                            System.out.println(childElement.getFirstElementChild().getAttribute("href"));
////                        }else if (element==3) {
////                            //description
//////                            System.out.println(c+" " + childElement.asText());
////
////                        }
////                    }
////
////                }
////                List<HtmlDivision> priceList = htmlElement.getByXPath("//div[@class='price-md']");
////                for (HtmlDivision htmlDivision : priceList) {
////                    if (htmlDivision.getFirstElementChild().getFirstElementChild()==null){
////                    String priceAsText = htmlDivision.getFirstElementChild().asText();
////                    System.out.println(c + " " + priceAsText);
////                    String priceReplace = priceAsText.replaceAll("\\D+", "");
////                    Integer price = Integer.valueOf(priceReplace);
////                } else {
////                    String priceAsText = htmlDivision.getFirstElementChild().getFirstElementChild().asText();
////                    System.out.println(c + " " + priceAsText);
////                    String priceReplace = priceAsText.replaceAll("\\D+", "");
////                    Integer price = Integer.valueOf(priceReplace);
////                }
////                }
////            }
////            for (HtmlElement htmlDivision : totalProduct) {
////            List<HtmlDivision> byXPath1 = page.getByXPath("//div[@class='price-md']");
////            System.out.println(byXPath1.size());
////            for (HtmlDivision htmlDivision : byXPath1) {
////
////                TabletsMobilluck tabletsHotline = new TabletsMobilluck();
////                c++;
////                /*
////                * price
////                * */
////                if (htmlDivision.getFirstElementChild().getFirstElementChild()==null){
////                    String priceAsText = htmlDivision.getFirstElementChild().asText();
////                    System.out.println(c + " " + priceAsText);
////                    String priceReplace = priceAsText.replaceAll("\\D+", "");
////                    Integer price = Integer.valueOf(priceReplace);
////                    tabletsHotline.setPrice(price);
////                } else {
////                    String priceAsText = htmlDivision.getFirstElementChild().getFirstElementChild().asText();
////                    System.out.println(c + " " + priceAsText);
////                    String priceReplace = priceAsText.replaceAll("\\D+", "");
////                    Integer price = Integer.valueOf(priceReplace);
////                    tabletsHotline.setPrice(price);
////                }
////                /*
////                * description
////                * */
////                List<HtmlDivision> byXPath = htmlDivision.getByXPath("//div[@class='item-info']");
////                HtmlDivision x = byXPath.get(c-1);
////                Iterable<DomElement> childElements = x.getChildElements();
////                int d=0;
////                for (DomElement next : childElements) {
////                    d++;
////                    if (d==1){
////                        //model
////                        System.out.println(c + " " + next.asText());
////                        tabletsHotline.setModel( next.asText());
////
////
////                        // links
////                        /*
////                         * This string need for concat with link from List<HtmlAnchor> listLinks,
////                         * since each element from this list not have full address for link
////                         * */
////                        String addLink = "http://hotline.ua";
////                        DomElement firstElementChild = next.getFirstElementChild();
////                        System.out.println(addLink.concat(firstElementChild.getAttribute("href")));
////                        tabletsHotline.setLinkOnSite( addLink.concat(firstElementChild.getAttribute("href")));
////
////                    } else if (d==3) {
////                        //description
////                        System.out.println(c+" " + next.asText());
////                        tabletsHotline.setDescript(next.asText());
////
////                    }
////                }
////                System.out.println("==========================");
//////                generalService.saveProduct(tabletsHotline);
//
////            }
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

 /*
 * Response to admin.html which browser client want to use;
 * This info need for next (@WebClientSettings)
 * */
        EnumBrowserVersion [] values = EnumBrowserVersion.values();
        List<String> list = new ArrayList<>();
        for (EnumBrowserVersion value : values) {
           list.add(value.toString());
        }
/*
* This map response on Admin.html info about number of each product in base
* and last date of update in base when product was uploaded for each product
* */
        Map<String, CopySiteProductDto> map = dtoCreatorService.adminInfoAboutAllProducts();

        model.addAttribute("enumWeb", list);
        model.addAttribute("map", map);
        return "admin";
    }

    @PostMapping("/ok")
    private String loginOk (){
        return "admin";
    }

//    @GetMapping("/login")
//    private String login (){
//        return "login";
;//    }

    @GetMapping("/login-error")
    private String loginError (){
        return "loginError";
    }

}
