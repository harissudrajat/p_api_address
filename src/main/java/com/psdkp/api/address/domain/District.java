package com.psdkp.api.address.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cityId", referencedColumnName = "id")
    private City city;

    private String code;
    private String name;
}
