package com.zuhriddin.service;

import com.zuhriddin.dao.ProductDao;
import com.zuhriddin.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

@RequiredArgsConstructor
public class ProductService {
    private final ProductDao productDao;

    public List<Product> listProducts() {
        return productDao.getProducts();
    }

    public Product addProduct(HttpServletRequest req, String[] parameterNames, String[] parameterTypes,
                              String[] parameterValues, String name, int price, String description, int discount,
                              int fromDelivery, int toDelivery, String email) throws ServletException, IOException {
        JSONObject paramJson = createJsonFromArray(parameterNames, parameterTypes, parameterValues);
        String params = paramJson.toString();
        String colour = createColoursJson(req);
        String images = createImageJson(req.getParts());
        Product product = new Product(name, price, images, params, colour, description, discount, fromDelivery, toDelivery, email);
        return productDao.addProduct(product);
    }

    public void deleteProduct(int productId) {
        productDao.deleteProduct(productId);
    }

    public Product updateProduct(Product product) {
        return productDao.updateProduct(product);
    }

    private String createImageJson(Collection<Part> parts) throws IOException {
        JSONArray imagePaths = new JSONArray();
        for (Part part: parts) {
            if ("images[]".equals(part.getName())) {
                String filePath = saveImage(part);
                JSONObject imagePath = new JSONObject();
                imagePath.put("path", filePath);
                imagePaths.put(imagePath);
            }
        }
        return imagePaths.toString();
    }

    private String createColoursJson(HttpServletRequest req) throws ServletException, IOException {
        Map<String, List<String>> colourImageMap = new HashMap<>();
        for (Part part: req.getParts()) {
            String partName = part.getName();

            if (partName.startsWith("colourName-")) {
                String label = req.getParameter(partName);
                colourImageMap.put(label, new ArrayList<>());
            }
        }

        for (Part part: req.getParts()) {
            String partName = part.getName();

            if (partName.startsWith("colourImages-")) {
                String labelIndex = partName.split("-")[1].replace("[]", "");
                String label = req.getParameter("colourName-" + labelIndex);

                String filePath = saveImage(part);
                colourImageMap.get(label).add(filePath);
                System.out.println("colourName-" + labelIndex + " = " + label);
                System.out.println(partName + " = " + filePath);
            }
        }

        JSONArray array = new JSONArray();
        for (Map.Entry<String, List<String>> map: colourImageMap.entrySet()) {
            JSONObject json = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            for (String filePath: map.getValue()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("path", filePath);
                jsonArray.put(jsonObject);
            }
            json.put(map.getKey(), jsonArray);
            array.put(json);
        }
        return array.toString();
    }

    private String saveImage(Part imagesPart) throws IOException {
        String fileName = Paths.get(imagesPart.getSubmittedFileName()).getFileName().toString();
        String filePath = "C:\\Zuhriddin\\Java\\PDP\\Jakarta_Web\\Olcha_Store\\src\\main\\resources\\images\\upload\\" + fileName;
        imagesPart.write(filePath);
        return filePath;
    }

    private JSONObject createJsonFromArray(String[] names, String[] types, String[] values) {
        JSONObject jsonObject = new JSONObject();

        if (names != null) {
            for (int i = 0; i < names.length; i++) {
                String key = names[i];
                String type = types[i];
                String value = values[i];

                if (type.equals("number")) {
                    int val = 0;
                    if (value != null) {
                        val = Integer.parseInt(value);
                    }
                    jsonObject.put(key, val);
                } else {
                    jsonObject.put(key, value);
                }
            }
        }
        return jsonObject;
    }
}
