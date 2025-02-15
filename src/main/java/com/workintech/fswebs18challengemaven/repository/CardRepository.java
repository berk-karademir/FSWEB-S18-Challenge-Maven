package com.workintech.fswebs18challengemaven.repository;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.entity.Color;

import java.util.List;

public interface CardRepository {

    Card save(Card card);

    List<Card> findByColor(String cardColor);

    List<Card> findAll();

    List<Card> findByValue(Integer cardValue);

    List<Card> findByType(String cardType);

    Card update(Card card);

    Card remove(Long id);

}
