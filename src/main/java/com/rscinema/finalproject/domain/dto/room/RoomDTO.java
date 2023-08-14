package com.rscinema.finalproject.domain.dto.room;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
    private Integer id;
    private String name;
    private String roomSize;
    private Integer rowNum;
    private Integer rowSize;
}
