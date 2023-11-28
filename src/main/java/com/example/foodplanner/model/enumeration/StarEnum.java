package com.example.foodplanner.model.enumeration;

public enum StarEnum {
    ONE("⭐"),TWO("⭐⭐"),THREE("⭐⭐⭐"),FOUR("⭐⭐⭐⭐"),FIVE("⭐⭐⭐⭐⭐");

    private final String value;

    StarEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
