package com.design.senior.what2eat.DatabaseComponents.Enums;

/**
 * Created by KJ on 3/5/2018.
 */

public enum AllergyType {
    MILK,
    EGGS,
    FISH,
    SHELLFISH,
    TREENUTS,
    PEANUTS,
    WHEAT,
    SOY,
    NONE;

    @Override
    public String toString() {
        switch(this) {
            case MILK:
                return "Milk";
            case EGGS:
                return "Eggs";
            case FISH:
                return "Fish";
            case SHELLFISH:
                return "Shellfish";
            case TREENUTS:
                return "Treenuts";
            case PEANUTS:
                return "Peanuts";
            case WHEAT:
                return "Wheat";
            case SOY:
                return "Soy";
            case NONE:
                return "None";
            default: throw new IllegalArgumentException();
        }
    }
}
