package com.project.bookstore.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "phoneNumber is required")
    private String phoneNumber;

    private String address;

    private String photoUrl;

    @NotNull(message = "role_id is required")
    @JsonProperty("role_id")
    private Long roleId;
}
