package com.rscinema.finalproject.domain.dto.showtime;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShowTimeDTO {
    private Integer id;
    private String movie;
    private String room;
    private String date;
    private String startTime;
    private String endTime;
    private String readyForNextTime;
    private Double price;
}
