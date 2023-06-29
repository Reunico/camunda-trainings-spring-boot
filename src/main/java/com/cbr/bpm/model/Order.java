package com.cbr.bpm.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Entity
@Table(name = "party_order")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid")
    private UUID id;
    @JsonAlias("name.findName")
    private String fullName;
    @JsonAlias("name.title")
    private String title;
    @JsonAlias("random.number")
    private Long number;
    @JsonAlias("finance.amount")
    private BigDecimal amount;
    @JsonAlias("address.countryCode")
    private String countryCode;
    private String description;
    private String contractor;
    private Date orderDate;

    public Order() {
    }
}
