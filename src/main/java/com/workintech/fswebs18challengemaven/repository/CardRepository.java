package com.workintech.fswebs18challengemaven.repository;

import com.workintech.fswebs18challengemaven.entity.Card;

import java.util.List;

public interface CardRepository {

    Card save(Card card);

    List<Card> findByColor(String cardColor);

    List<Card> findAll();

    List<Card> findByValue(Integer cardValue);


    Card update(Card card);

    Card remove(Long id);

    List<Card> findByType(String type);

}
