package com.project.bookstore.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class OrderDTO {

    @JsonProperty("user_id")
    @Min(value = 1, message = "userid must be greater than 0")
    private long userid;

    private String fullname;

    private String email;

    @NotBlank(message = "phonenumber is required")
    @Size(min = 10, max = 12, message = "phonenumber must be between 10 and 12 characters")
    private String phonenumber;

    @NotBlank(message = "address is required")
    private String address;

    private String note;

    @JsonProperty("order_date")
    private Date orderdate;

    private String status;

    @NotNull(message = "totalpaid must not be null")
    @Min(value = 0, message = "totalPaid must be greater than 0")
    @JsonProperty("total_paid")
    private Float totalpaid;

    @JsonProperty("shipping_method")
    private String shippingmethod;

    @JsonProperty("payment_method")
    private String paymentmethod;

    @JsonProperty("shipping_date")
    private Date shippingdate;

}
