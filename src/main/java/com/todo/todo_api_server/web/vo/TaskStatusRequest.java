package com.todo.todo_api_server.web.vo;

import com.todo.todo_api_server.constants.TaskStatus;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TaskStatusRequest {
    private TaskStatus status;
}
