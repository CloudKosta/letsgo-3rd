package com.travel.letsgospringboot.postschedule.vo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportPostScheduleVO {
    private String postId;
    private String reporterId;
    private String reason;
    private String status;

}
