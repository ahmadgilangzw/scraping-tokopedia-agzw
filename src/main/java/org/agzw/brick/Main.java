package org.agzw.brick;

import org.agzw.brick.exception.FailedScrappingException;
import org.agzw.brick.service.TokopediaScrapperService;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.logging.Level;

public class Main {
    private static final String ERROR = "for running this jar please run like this: java -jar {jar_file} [count] \n" +
            "example : java -jar scraping-tokopedia-agzw-1.1.0-1.jar 100";

    public static void main(String[] args) {
        java.util.logging.Logger.getLogger("org.openqa").setLevel(Level.OFF);
        if (args.length > 0) {
            int count = 100;
            if (NumberUtils.isDigits(args[0])) {
                count = NumberUtils.createInteger(args[0]);
            }

            System.out.println("Extracting " + count + " " + "product(s)...");
            TokopediaScrapperService service = new TokopediaScrapperService();
            try {
                String csv = service.downloadProductListCsv(count);
                System.out.println("Extraction is successfully saved to " + csv);
            } catch (FailedScrappingException e) {
                System.err.println("Failed. Please try again.");
            }

        } else {
            System.err.println(ERROR);
        }
    }
}
