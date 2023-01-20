package com.inseefr.acdc.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "intl_text")
public class IntlText {
    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "UUID", sequenceName = "UUID", allocationSize = 1)
    private Long id;
    private String lang;
    private String text;

}
