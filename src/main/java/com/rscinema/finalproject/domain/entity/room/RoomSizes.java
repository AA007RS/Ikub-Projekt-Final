package com.rscinema.finalproject.domain.entity.room;

import com.rscinema.finalproject.domain.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum RoomSizes {

    SMALL("SMALL",3,4),
    MEDIUM("MEDIUM",5,6),
    LARGE("LARGE",7,8);

    private final String type;
    private final Integer row_num;
    private final Integer row_size;

    public static RoomSizes fromValue(String roomType){
        return Arrays.stream(RoomSizes.values())
                .filter(el -> el.type.equalsIgnoreCase(roomType))
                .findFirst()
                .orElseThrow(()->new ResourceNotFoundException(String
                        .format("Room type %s is not found!",roomType)));
    }

    public String getType() {
        return type;
    }

    public Integer getRow_num() {
        return row_num;
    }

    public Integer getRow_size() {
        return row_size;
    }
}
