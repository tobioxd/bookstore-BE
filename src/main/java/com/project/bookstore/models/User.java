package com.project.bookstore.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, length = 255)
    private String username;

    @Column(name = "password" , nullable = false, length = 255)
    private String password;

    @Column(name = "fullname" , length = 255)
    private String fullname;

    @Column(name = "gender" , length =  255)
    private String gender;

    @Column(name = "dob")
    private Date dob;
    
    @Column(name = "phonenumber" , nullable = false , length =  255)
    private String phonenumber;

    @Column(name = "address" , length =  255)
    private String address;

    @Column(name = "photourl" , length =  255)
    private String photourl;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role roleId;

}
