package com.zuhriddin.model;

import com.zuhriddin.model.enumaration.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private int id;
    private String name;
    private String phoneNumber;
    private String password;
    private UserRole role;
    private String email;
    private String[] privileges;
    private Date createdDate;
    private Date updatedDate;

    public User (ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.name = resultSet.getString("name");
        this.phoneNumber = resultSet.getString("phone_number");
        this.role = UserRole.valueOf(resultSet.getString("role"));
        this.email = resultSet.getString("email");
        if (resultSet.getArray("privileges") != null) {
            this.privileges = (String[]) resultSet.getArray("privileges").getArray();
        } else {
            this.privileges = null;
        }
        this.createdDate = resultSet.getDate("created_date");
        this.updatedDate = resultSet.getDate("updated_date");
    }

    public User (String name, String phoneNumber, String password, UserRole role,
                 String email, String[] privileges) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = role;
        this.email = email;
        this.privileges = privileges;
    }
}
