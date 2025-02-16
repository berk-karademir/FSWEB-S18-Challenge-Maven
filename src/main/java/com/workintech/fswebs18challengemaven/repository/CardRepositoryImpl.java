package com.workintech.fswebs18challengemaven.repository;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.entity.Color;
import com.workintech.fswebs18challengemaven.entity.Type;
import com.workintech.fswebs18challengemaven.exceptions.CardException;
import com.workintech.fswebs18challengemaven.util.CardValidation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Slf4j
@Repository
public class CardRepositoryImpl implements CardRepository {

    private EntityManager entityManager;

    @Autowired
    public CardRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }



    @Transactional
    @Override
    public Card save(Card card) {
        CardValidation.checkCard(card);
        entityManager.persist(card);
        return card;
    }


    @Override
    public List<Card> findByColor(String cardColor) {
        Color color = Color.valueOf(cardColor);
        TypedQuery<Card> foundCards = entityManager.createQuery("SELECT c FROM Card c WHERE c.color = :cardColor", Card.class);
        foundCards.setParameter("cardColor", color);
        List<Card> cards = foundCards.getResultList();

        if (cards.isEmpty()) {
            throw new CardException("Card not found" + color, HttpStatus.NOT_FOUND);
        }
        return foundCards.getResultList();

    }


    @Override
    public List<Card> findAll() {
        TypedQuery<Card> allCardsFound = entityManager.createQuery("SELECT c FROM Card c", Card.class);
        return allCardsFound.getResultList();
    }

    @Override
    public List<Card> findByValue(Integer cardValue) {
        TypedQuery<Card> query = entityManager.createQuery("SELECT c FROM Card c WHERE c.value= :value", Card.class);
        query.setParameter("value",cardValue);
        return query.getResultList();
    }




    public Card update(Card card) {
        return entityManager.merge(card);
    }

    @Transactional
    @Override
    public Card remove(Long id) {
        Card foundCard = entityManager.find(Card.class, id);
        if (foundCard == null) {
            throw new CardException("card is not found! card: " + id, HttpStatus.NOT_FOUND);
        }
        entityManager.remove(foundCard);
        return foundCard;
    }
    @Override
    public List<Card> findByType(String type) {
        Type tp = Type.valueOf(type);
        TypedQuery<Card> query = entityManager.createQuery("SELECT c FROM Card c WHERE c.type= :type", Card.class);
        query.setParameter("type",tp);
        return query.getResultList();
    }

}
