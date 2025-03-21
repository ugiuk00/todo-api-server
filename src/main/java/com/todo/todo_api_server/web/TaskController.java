package com.todo.todo_api_server.web;

import com.todo.todo_api_server.constants.TaskStatus;
import com.todo.todo_api_server.model.Task;
import com.todo.todo_api_server.model.TaskRequest;
import com.todo.todo_api_server.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * @controller는 뷰(템플릿 엔진), @restcontroller는 restApi(json)을 응답으로 내릴 때, 사용하는 것이 일반적
 * */
@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    /**
     * 새로운 할 일 추가
     *
     * @param req 추가하고자 하는 할 일
     * @return 추가된 할 일
     */
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskRequest req) {
        var result = this.taskService.add(req.getTitle(), req.getDescription(), req.getDueDate());
        return ResponseEntity.ok(result);
    }

    /**
     * 특정 마감일에 해당하는 할일 목록을 반환
     *
     * @param dueDate 할일의 마감일
     * @return 마감일에 해당하는 할일 목록
     */
    @GetMapping
    public ResponseEntity<List<Task>> getTask(
            @RequestParam(value = "due_date", required = false)
            String dueDate
    ) { //optional을 requestparam(required false)으로 대체 가능
        List<Task> result;

        if (dueDate != null) {
            result = this.taskService.getByDueDate(dueDate);
        } else {
            result = this.taskService.getAll();
        }

        return ResponseEntity.ok(result);
    }

    /**
     * 특정 ID에 해당하는 할일을 조회
     *
     * @param id 할일 ID
     * @return ID에 해당하는 할일 객체
     */
    @GetMapping("/{id}")
    public ResponseEntity<Task> fetchOneTask(@PathVariable Long id) {
        var result = this.taskService.getOne(id);
        return ResponseEntity.ok(result);
    }

    /**
     * 특정 상태에 해당하는 할일 목록을 반환
     *
     * @param status 할일 상태
     * @return 상태에 해당하는 할일 목록
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Task>> getByStatus(@PathVariable TaskStatus status) {
        var result = this.taskService.getByStatus(status);
        return ResponseEntity.ok(result);
    }
}
