package com.workintech.fswebs18challengemaven.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "card", schema = "casino")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "value")
    private Integer value;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type")
    private Type type;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "color")
    private Color color;



}
