package org.agzw.brick.service;

import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.common.annotations.VisibleForTesting;
import org.agzw.brick.exception.FailedScrappingException;
import org.agzw.brick.model.Product;
import org.agzw.brick.scrapper.TokopediaScrapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.agzw.brick.constant.CommonConstant.CSV_EXT;
import static org.agzw.brick.constant.CommonConstant.NAME;
import static org.agzw.brick.constant.CommonConstant.PRODUCT;
import static org.agzw.brick.constant.CommonConstant.UNDERSCORE;

public class TokopediaScrapperService {

    private TokopediaScrapper scrapper;

    public TokopediaScrapperService() {
        scrapper = new TokopediaScrapper();
    }

    @VisibleForTesting
    TokopediaScrapperService(TokopediaScrapper scrapper) {
        this.scrapper = scrapper;
    }

    public String downloadProductListCsv(int count)
            throws FailedScrappingException {
        String filename = PRODUCT + UNDERSCORE + NAME + UNDERSCORE + System.currentTimeMillis() + CSV_EXT;
        List<Product> products = scrapper.extractProductList(count);

        CsvMapper csvMapper = new CsvMapper();
        csvMapper.enable(Feature.IGNORE_UNKNOWN);
        csvMapper.addMixIn(Product.class, Product.ProductFormat.class);
        CsvSchema schema = csvMapper.schemaFor(Product.class)
                .withHeader()
                .withColumnSeparator(';');

        try {
            File file = new File(filename);
            csvMapper.writer(schema).writeValue(file, products);
            return filename;
        } catch (IOException | RuntimeException e) {
            throw new FailedScrappingException(e.getMessage());
        }
    }
}
