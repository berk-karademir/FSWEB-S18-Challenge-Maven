package com.workintech.fswebs18challengemaven.controller;


import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {


    private CardRepository cardRepository;

    public CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    // by order on readme
    @GetMapping
    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    @GetMapping("/byColor/{color}")
    public List<Card> findByColor(@PathVariable("color") String cardColor) {
        return cardRepository.findByColor(cardColor);
    }

    @GetMapping("byValue/{value}")
    public List<Card> getAllByValue(@PathVariable("value") Integer value) {
        return cardRepository.findByValue(value);
    }

    @GetMapping("byType/{type}")
    public List<Card> getAllByValue(@PathVariable("type") String type) {
        return cardRepository.findByType(type);
    }


    @PostMapping
    public Card save(@RequestBody Card card) {
        return cardRepository.save(card);
    }



    @PutMapping("/{id}")
    public Card updateWithID(@PathVariable("id") Long id, @RequestBody Card card) {
        card.setId(id);
        return cardRepository.update(card);
    }


    @DeleteMapping("/{id}")
    public Card removeWithID(@PathVariable("id") Long id) {
        return cardRepository.remove(id);
    }

}
