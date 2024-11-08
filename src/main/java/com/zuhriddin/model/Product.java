package com.zuhriddin.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    private int id;
    private String name;
    private int price;
    private String images;
    private String params;
    private String colours;
    private String description;
    private int discount;
    private int fromDelivery;
    private int toDelivery;
    private Date createdDate;
    private Date updatedDate;
    private String createdBy;
    private String updatedBy;
    private boolean active;

    public Product (ResultSet resultSet) throws Exception {
        this.id = resultSet.getInt("id");
        this.name = resultSet.getString("name");
        this.price = resultSet.getInt("price");
        this.images = resultSet.getString("images");
        this.params = resultSet.getString("params");
        this.colours = resultSet.getString("colours");
        this.description = resultSet.getString("description");
        this.discount = resultSet.getInt("discount");
        this.fromDelivery = resultSet.getInt("from_delivery");
        this.toDelivery = resultSet.getInt("to_delivery");
        this.createdDate = resultSet.getDate("created_date");
        this.updatedDate = resultSet.getDate("updated_date");
    }

    private JsonNode setJsonNode(String json) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(json);
    }
}