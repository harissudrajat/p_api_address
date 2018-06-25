package com.psdkp.api.address.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class SubDistrict {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "districtId", referencedColumnName = "id")
    private District district;

    private String code;
    private String name;
}
