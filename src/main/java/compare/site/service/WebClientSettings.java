package compare.site.service;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import org.springframework.stereotype.Component;

@Component
public class WebClientSettings {

    public static WebClient webClientSettings(boolean setCssEnabled, boolean setJavaScriptEnabled,
                                       boolean setThrowExceptionOnScriptError, long waitForBackgroundJavaScript){
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setCssEnabled(setCssEnabled);
        webClient.getOptions().setJavaScriptEnabled(setJavaScriptEnabled);
        webClient.getOptions().setThrowExceptionOnScriptError(setThrowExceptionOnScriptError);
        webClient.waitForBackgroundJavaScript(waitForBackgroundJavaScript);
        return webClient;
    }
}
