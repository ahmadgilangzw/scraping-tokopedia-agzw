package org.agzw.brick.constant;

public class CommonConstant {
    public static final String PATH_CHROME_DRIVER = "C://sele//chromedriver.exe";
    public static final String SEPARATOR = ";";
    public static final String DOUBLE_QUOTES = "\"";
    public static final String BASE_URL = "https://www.tokopedia.com";
    public static final String TOP_ADS_URL = "https://ta.tokopedia.com/promo";
    public static final String HANDPHONE_PATH = "/p/handphone-tablet/handphone";
    public static final String LAPTOP_PATH = "/p/komputer-laptop/laptop";
    public static final String PAGE = "?page=";
    public static final String XPATH_PRODUCT_LIST = "//div[@data-testid='lstCL2ProductList']/div";
    public static final String XPATH_PRODUCT_LINK = "a[@data-testid='lnkProductContainer']";
    public static final String XPATH_PRODUCT_NAME = "//h1[@data-testid='lblPDPDetailProductName']";
    public static final String XPATH_PRODUCT_DESCRIPTION = "//*[@data-testid='lblPDPDescriptionProduk']";
    public static final String XPATH_PRODUCT_IMG_LINK = "//*[@data-testid='PDPMainImage']";
    public static final String XPATH_PRODUCT_PRICE = "//*[@data-testid='lblPDPDetailProductPrice']";
    public static final String XPATH_PRODUCT_RATING = "//*[@data-testid='lblPDPDetailProductRatingNumber']";
    public static final String XPATH_MERCHANT_NAME = "//*[@data-testid='llbPDPFooterShopName']//h2";

    public static final String DOM_FIRST_TIME_OVERLAY = "div[aria-label=unf-overlay]";

    public static final String HREF = "href";
    public static final String SRC = "src";
    public static final String AMP = "&";
    public static final String PARAM_R = "r=";
    public static final String EMPTY = "";
    public static final String DOT = ".";
    public static final String RUPIAH_SIGN = "Rp";

    public static final String UNDERSCORE = "_";
    public static final String PRODUCT = "Product";
    public static final String CSV_EXT = ".csv";
    public static final String NAME = "TOKOPEDIA-SCRAPING-AGZW";


    public static final String USER_AGENT = "user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) "
                    + "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36";
    public static final String GOOGLE_URL = "https://www.google.com";
    public static final String JS_WINDOW_OPEN = "window.open()";
    public static final String JS_SCROLL_SMALL = "window.scrollBy(0,300)";
    public static final String JS_SCROLL_MEDIUM = "window.scrollBy(0,600)";
}
