package com.zuhriddin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    private int orderId;
    private String userName;
    private String userPhoneNumber;
    private String userEmail;
    private String orderStatus;
    private String promoCodeValue;
    private String promoCodeType;
    private Date promoCodeStartDate;
    private int promoCodeDays;
    private int promoCodeFixedAmount;
    private int promoCodePercent;
    private int promoCodeMinAmount;
    private Date orderCreatedDate;
    private Date orderUpdatedDate;

    public Order (ResultSet resultSet) throws SQLException {
        this.orderId = resultSet.getInt("order_id");
        this.userName = resultSet.getString("user_name");
        this.userPhoneNumber = resultSet.getString("user_phone_number");
        this.userEmail = resultSet.getString("user_email");
        this.orderStatus = resultSet.getString("order_status");
        this.promoCodeValue = resultSet.getString("promo_code_value");
        this.promoCodeType = resultSet.getString("promo_code_type");
        this.promoCodeStartDate = resultSet.getDate("promo_code_start_date");
        this.promoCodeDays = resultSet.getInt("promo_code_days");
        this.promoCodeFixedAmount = resultSet.getInt("promo_code_fixed_amount");
        this.promoCodePercent = resultSet.getInt("promo_code_percent");
        this.promoCodeMinAmount = resultSet.getInt("promo_code_min_amount");
        this.orderCreatedDate = resultSet.getDate("order_created_date");
        this.orderUpdatedDate = resultSet.getDate("order_updated_date");
    }
}
