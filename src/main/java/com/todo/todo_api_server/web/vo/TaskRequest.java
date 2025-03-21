package com.todo.todo_api_server.web.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TaskRequest {

    private String title;

    private String description;

    @JsonDeserialize(using = LocalDateDeserializer.class) //해당 클래스는 dto로 json을 받는다. 따라서 json->LocalDate객체 로 변환해야함
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") //들어오고 나가는 json의 날짜 형식
    private LocalDate dueDate;
}
