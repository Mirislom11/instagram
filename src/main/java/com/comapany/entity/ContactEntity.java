package com.comapany.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
@Setter
@Getter
public class ContactEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String phone;

    @OneToOne(mappedBy = "contact", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private ProfileEntity profile;
}
