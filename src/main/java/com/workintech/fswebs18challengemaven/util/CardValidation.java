package com.workintech.fswebs18challengemaven.util;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.entity.Type;
import com.workintech.fswebs18challengemaven.exceptions.CardException;
import org.springframework.http.HttpStatus;

public class CardValidation {


    public static void checkCard(Card card) {
        if(card.getType() != null && card.getValue() != null && card.getValue() > 0) {
            throw new CardException("A card can not has type and value at the same time", HttpStatus.BAD_REQUEST);
        }

        if(card.getType() != null && card.getType().equals(Type.JOKER) && card.getColor() != null || card.getValue() != null) {
            throw new CardException("Joker can not has color or value! ", HttpStatus.BAD_REQUEST);
        }


    }



}
