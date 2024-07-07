import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Task5 {

    public static void main(String[] args) {
        String url = "https://flipkart.com/products";
        List<String[]> data = new ArrayList<>();

        // Adding header
        data.add(new String[]{"Product Name", "Price", "Rating"});

        try {
            Document doc = Jsoup.connect(url).get();
            Elements products = doc.select(".product"); 

            for (Element product : products) {
                String name = product.select(".product-name").text(); 
                String price = product.select(".product-price").text(); 
                String rating = product.select(".product-rating").text(); 

                data.add(new String[]{name, price, rating});
            }

            writeDataToCSV("products.csv", data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeDataToCSV(String fileName, List<String[]> data) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(fileName))) {
            writer.writeAll(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

