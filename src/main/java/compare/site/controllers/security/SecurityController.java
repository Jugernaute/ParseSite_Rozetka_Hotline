package compare.site.controllers.security;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import compare.site.entity.*;
import compare.site.dto.productSite.CopySiteProductDto;
import compare.site.entity.hotline.TabletsHotline;
import compare.site.service.dtoProductSite.DtoCreatorService;
import compare.site.service.general.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.*;

@Controller
public class SecurityController {

    @Autowired
    DtoCreatorService dtoCreatorService;
    @Autowired
    private GeneralService<? super ProductAbstract> generalService;


    @GetMapping("/")
    private String start(Model model){
        WebClient webClient = new WebClient();
        webClient.getOptions().setJavaScriptEnabled(false);
//        webClient.waitForBackgroundJavaScript(4000);
        webClient.getOptions().setCssEnabled(false);

        try {
            HtmlPage page = webClient.getPage("https://hotline.ua/computer/planshety/?p=2");
            List<HtmlAnchor> listCountOfPages = page.getByXPath("//a[@class='pages']");
            /*
            * count of pages with tablets
            * */
            String numPages = listCountOfPages.get(listCountOfPages.size() - 1).asText();

            /*
            * all product on this site are in tag <li class = "product-item">
            *     which includes all necessary info about tablets.
            *     From every @li get :
            *     p[class='h4'] - modelProduct;

            *
            * */
            List<HtmlElement> listModels = page.getByXPath("//div[@class='item-info']/p");

            /*
            * This string need for concat with link from List<HtmlAnchor> listLinks,
            * since each element from this list not have full address for link*/
            String addLink = "http://hotline.ua";
            List<HtmlAnchor> listLinks = page.getByXPath("//div[@class='item-info']/p[@class='h4']/a");
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
//            for (HtmlSpan listPrice : listPrices) {
//                System.out.println(listPrice.asText());
//            }


            int c = 0;
            List<HtmlElement> totalProduct = page.getByXPath("//li[@class='product-item']");
//            for (HtmlElement htmlDivision : totalProduct) {
            List<HtmlDivision> byXPath1 = page.getByXPath("//div[@class='price-md']");
            for (HtmlDivision htmlDivision : byXPath1) {
                c++;
                /*
                * price
                * */
                if (htmlDivision.getFirstElementChild().getFirstElementChild()==null){
                    System.out.println(c + " " + htmlDivision.getFirstElementChild().asText());
                } else {
                    System.out.println(c + " " + htmlDivision.getFirstElementChild().getFirstElementChild().asText());
                }
                /*
                * description
                * */
                List<HtmlDivision> byXPath = htmlDivision.getByXPath("//div[@class='item-info']");
                HtmlDivision x = byXPath.get(c-1);
                Iterable<DomElement> childElements = x.getChildElements();
                int d=0;
                for (DomElement next : childElements) {
                    d++;
                    if (d==1){
                        //model
                        System.out.println(c + " " + next.asText());
                        // links
                        DomElement firstElementChild = next.getFirstElementChild();
                        System.out.println(addLink.concat(firstElementChild.getAttribute("href")));
                    } else if (d==3) {
                        //description
                        System.out.println(c+" " + next.asText());
                    }
                }
                System.out.println("==========================");
                /*
                * model
                * */
//                System.out.println(c+" "+byXPath.get(c-1).getFirstElementChild().asText());

            }

//                List<HtmlSpan> byXPath = htmlDivision.getByXPath("//div[@class='price-md']/span[@class='value']");
//                List<HtmlSpan> byXPath = htmlDivision.getByXPath("//span[@class='value']");
//            System.out.println(byXPath.size());

//                TabletsHotline tabletsHotline = new TabletsHotline();
//                System.out.println();
//                System.out.println("=================");
//                System.out.println(c + " " + listModels.get(Math.toIntExact(c-1)).asText());
//                System.out.println(c + " " + addLink.concat(listLinks.get(Math.toIntExact(c-1)).getHrefAttribute()));
//                System.out.println(c + " " + listDescriptions.get(Math.toIntExact(c-1)).asText());
//                System.out.println(c + " " + listPrices.get(Math.toIntExact(c - 1)).asText().replaceAll("\\D+",""));
//                tabletsHotline.setModel( listModels.get(Math.toIntExact(c-1)).asText());
//                tabletsHotline.setLinkOnSite( addLink.concat(listLinks.get(Math.toIntExact(c-1)).getHrefAttribute()));
//                tabletsHotline.setDescript(listDescriptions.get(Math.toIntExact(c-1)).asText());
//                String priceStr = listPrices.get(Math.toIntExact(c - 1)).asText().replaceAll("\\D+","");
//                Integer price = Integer.valueOf(priceStr);
//                tabletsHotline.setPrice(price);
//                generalService.saveProduct(tabletsHotline);
//            }


        } catch (IOException e) {
            e.printStackTrace();
        }

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
