package org.example.clientService.dto;

import lombok.*;
import org.apache.commons.lang.time.DurationFormatUtils;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessLogsDTO {
    private LocalDateTime timeStart;
    private LocalDateTime TimeEnd;
    private Long duration;
//    @ToString.Exclude
//    private ClientDTO clientDTO;

    @Override
    public String toString() {
//        String duratationPretty = DurationFormatUtils.formatDuration(duration*1000, "hh:mm:ss");
        return "AccessLogsDTO{" +
                "timeStart=" + timeStart +
                ", TimeEnd=" + TimeEnd;
    }
}
