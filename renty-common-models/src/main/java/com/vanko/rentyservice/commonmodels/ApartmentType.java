package com.vanko.rentyservice.commonmodels;

public enum ApartmentType {
    SINGLE_ROOM(1),
    DOUBLE_ROOM(2),
    TRIPLE_ROOM(3),
    MULTIPLE_ROOM(4);

    private final int value;

    ApartmentType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ApartmentType fromValue(int value) {
        for (ApartmentType status : ApartmentType.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unexpected value: " + value);
    }
}
