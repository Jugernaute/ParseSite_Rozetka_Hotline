package compare.site.service;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import org.springframework.stereotype.Component;
/**
 * This class setting {@link WebClient}
 * */
@Component
public class WebClientSettings {
/**
 * @param setCssEnabled - default <tt>false</tt>.
 * @param setJavaScriptEnabled  - that parsing price of product we must enable
 *                              this by value <tt>true</tt> or disable by value <tt>false</tt>.
 * @param setThrowExceptionOnScriptError - default <tt>false</tt>.
 * @param waitForBackgroundJavaScript - in ms, using when {@param setJavaScriptEnabled} is <tt>true</tt>
 *
 */
    public static WebClient webClientSettings(boolean setCssEnabled,
                                              boolean setJavaScriptEnabled,
                                              boolean setThrowExceptionOnScriptError,
                                              long waitForBackgroundJavaScript)
    {
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setCssEnabled(setCssEnabled);
        webClient.getOptions().setJavaScriptEnabled(setJavaScriptEnabled);
        webClient.getOptions().setThrowExceptionOnScriptError(setThrowExceptionOnScriptError);
        webClient.waitForBackgroundJavaScript(waitForBackgroundJavaScript);
        return webClient;
    }
}
