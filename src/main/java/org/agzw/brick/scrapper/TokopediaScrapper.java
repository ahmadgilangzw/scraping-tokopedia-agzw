package org.agzw.brick.scrapper;

import org.agzw.brick.configuration.ConfigurationWebDriver;
import org.agzw.brick.exception.FailedScrappingException;
import org.agzw.brick.model.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.agzw.brick.constant.CommonConstant.AMP;
import static org.agzw.brick.constant.CommonConstant.BASE_URL;
import static org.agzw.brick.constant.CommonConstant.DOT;
import static org.agzw.brick.constant.CommonConstant.EMPTY;
import static org.agzw.brick.constant.CommonConstant.HANDPHONE_PATH;
import static org.agzw.brick.constant.CommonConstant.HREF;
import static org.agzw.brick.constant.CommonConstant.PAGE;
import static org.agzw.brick.constant.CommonConstant.PARAM_R;
import static org.agzw.brick.constant.CommonConstant.RUPIAH_SIGN;
import static org.agzw.brick.constant.CommonConstant.SRC;
import static org.agzw.brick.constant.CommonConstant.TOP_ADS_URL;
import static org.agzw.brick.constant.CommonConstant.XPATH_MERCHANT_NAME;
import static org.agzw.brick.constant.CommonConstant.XPATH_PRODUCT_DESCRIPTION;
import static org.agzw.brick.constant.CommonConstant.XPATH_PRODUCT_IMG_LINK;
import static org.agzw.brick.constant.CommonConstant.XPATH_PRODUCT_LINK;
import static org.agzw.brick.constant.CommonConstant.XPATH_PRODUCT_LIST;
import static org.agzw.brick.constant.CommonConstant.XPATH_PRODUCT_NAME;
import static org.agzw.brick.constant.CommonConstant.XPATH_PRODUCT_PRICE;
import static org.agzw.brick.constant.CommonConstant.XPATH_PRODUCT_RATING;

public class TokopediaScrapper {

    public List<Product> extractProductList(int count) throws FailedScrappingException {
        final ConfigurationWebDriver webDriver = new ConfigurationWebDriver();
        final List<Product> products = new ArrayList<>(count);
        final String baseUrl = getUrl();

        try {
            List<String> tabs = webDriver.prepareTwoTabs();
            for (int page = 1; products.size() < count; page++) {
                String url = baseUrl + PAGE + page;
                final List<WebElement> items = webDriver.getElementListByScrollingDown(url,
                        XPATH_PRODUCT_LIST, tabs.get(0)); // switch to main tab

                for (WebElement item : items) {
                    String path = item.findElement(By.xpath(XPATH_PRODUCT_LINK)).getAttribute(HREF);
                    if (isTopAdsLink(path)) {
                        path = extractTopAdsLink(path);
                    }

                    webDriver.getWebpage(path, tabs.get(1)); //switch to new tab

                    // trigger lazy load
                    webDriver.scrollDownSmall();
                    webDriver.waitOnElement(XPATH_MERCHANT_NAME);

                    products.add(extractProduct(webDriver, path));

                    if (products.size() == count) {
                        break;
                    }
                    webDriver.switchTab(tabs.get(0)); //switches to main tab
                }

            }
        } catch (Exception e) {
            throw new FailedScrappingException(e.getMessage());
        } finally {
            webDriver.quit();
        }

        return products;
    }

    private Product extractProduct(ConfigurationWebDriver webDriver, String path) {
        String productName = webDriver.getText(XPATH_PRODUCT_NAME);
        String desc = webDriver.getText(XPATH_PRODUCT_DESCRIPTION);
        String imageLink = webDriver.getText(XPATH_PRODUCT_IMG_LINK, SRC);
        String price = webDriver.getText(XPATH_PRODUCT_PRICE).split(RUPIAH_SIGN)[1].replace(DOT, EMPTY);
        String merchant = webDriver.getText(XPATH_MERCHANT_NAME);
        String rating = webDriver.getText(XPATH_PRODUCT_RATING);

        return Product.builder()
                .productName(productName)
                .description(desc)
                .imageLink(imageLink)
                .merchant(merchant)
                .price(Double.parseDouble(price))
                .rating(rating == null || rating.isEmpty() ? 0.0 : Double.parseDouble(rating))
                .build();
    }

    private String getUrl() {
        return BASE_URL + HANDPHONE_PATH;
    }
    private boolean isTopAdsLink(String path) {
        return path.contains(TOP_ADS_URL);
    }
    private String extractTopAdsLink(String path) throws IOException {
        return URLDecoder.decode(path.substring(path.indexOf(PARAM_R) + 2).split(AMP)[0],
                StandardCharsets.UTF_8.name());
    }
}
