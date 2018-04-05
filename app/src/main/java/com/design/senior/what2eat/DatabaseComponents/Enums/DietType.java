package com.design.senior.what2eat.DatabaseComponents.Enums;

/**
 * Created by KJ on 3/5/2018.
 */

public enum DietType {
    VEGAN,
    VEGETARIAN,
    PESCETARIAN,
    PALEO,
    NONE;

    @Override
    public String toString() {
        switch(this) {
            case VEGAN:
                return "Vegan";
            case VEGETARIAN:
                return "Vegetarian";
            case PESCETARIAN:
                return "Pescetarian";
            case PALEO:
                return "Paelolithic";
            case NONE:
                return "None";
            default: throw new IllegalArgumentException();
        }
    }

    public int getToleranceLevel() {
        switch(this) {
            case VEGAN:
                return 4;
            case VEGETARIAN:
                return 3;
            case PESCETARIAN:
                return 2;
            case PALEO:
                return 1;
            case NONE:
                return 0;
            default: throw new IllegalArgumentException();
        }
    }
}
