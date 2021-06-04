package com.mystore.mystore.helper;

import com.mystore.mystore.entity.ProductDetailesOfCart;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author hinas
 */
public class JsonToJavaObject {

    public List<ProductDetailesOfCart> GetJavaObjects(String str) {
        List<ProductDetailesOfCart> product = new ArrayList<ProductDetailesOfCart>();

        char[] x = str.toCharArray();
        for (int i = 0; i < x.length; i++) {
            if (x[i] == '\\') {
                x[i] = ' ';
            }
        }
        String s = String.valueOf(x);
        
        Pattern ID = Pattern.compile(" \"productId \":(\\d+),");
        Pattern NAME = Pattern.compile(" \"productName \": \"([^\"]*) \",");
        Pattern QUANT = Pattern.compile(" \"productQuantity \":(\\d+),");
        Pattern PRICE = Pattern.compile(" \"productPrice \":([+-]?([0-9]*[.])?[0-9]+)");
        
        //Pattern p = Pattern.compile(":");

        Matcher matcher_ID = ID.matcher(s);
        Matcher matcher_NAME = NAME.matcher(s);
        Matcher matcher_QUANT = QUANT.matcher(s);
        Matcher matcher_PRICE = PRICE.matcher(s);

        while (matcher_ID.find()) {
            matcher_NAME.find();
            matcher_QUANT.find();
            matcher_PRICE.find();

            product.add(new ProductDetailesOfCart(Integer.parseInt(matcher_ID.group(1)), matcher_NAME.group(1), Integer.parseInt(matcher_QUANT.group(1)), Float.parseFloat(matcher_PRICE.group(1))));
        }

        return product;
    }
}
