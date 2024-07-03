package com.project.bookstore.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserDTO {

    @NotBlank(message = "Username is required")
    private String username;
    
    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Retypepassword is required")
    private String retypepassword;

    private String fullname;

    private String gender;

    private Date dob;

    @NotBlank(message = "phonenumber is required")
    @Size(min = 10, max = 12, message = "phonenumber must be between 10 and 12 characters")
    private String phonenumber;

    private String address;

    private String photourl;

    @NotNull(message = "role_id is required")
    @JsonProperty("role_id")
    private Long roleId;
}
