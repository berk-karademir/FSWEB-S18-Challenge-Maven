package com.workintech.fswebs18challengemaven.repository;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.entity.Color;
import com.workintech.fswebs18challengemaven.entity.Type;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
        entityManager.persist(card);
        return card;
    }


    @Override
    public List<Card> findByColor(String cardColor) {
        Color color = Color.valueOf(cardColor);
        TypedQuery<Card> foundCards = entityManager.createQuery("SELECT c FROM Card c WHERE c.color = :cardColor", Card.class);
        foundCards.setParameter("cardColor", color);
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

    @Override
    public List<Card> findByType(String cardType) {
        Type type = Type.valueOf(cardType);
        TypedQuery<Card> foundCards = entityManager.createQuery("SELECT c FROM Card c WHERE c.type = :cardType", Card.class);
        foundCards.setParameter("cardType", type);
        return foundCards.getResultList();
    }

    @Transactional
    @Override
    public Card update(Card card) {
        return entityManager.merge(card);
    }

    @Transactional
    @Override
    public Card remove(Long id) {
        Card foundCard = entityManager.find(Card.class, id);
        entityManager.remove(foundCard);
        return foundCard;
    }
}
