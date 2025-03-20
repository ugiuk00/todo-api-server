package com.todo.todo_api_server.persist.entity;

import com.todo.todo_api_server.constants.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "task") //테이블과 매핑
@DynamicUpdate //created_at, updated_at 같이 자동으로 데이터 생성하는 컬럼 관리
@DynamicInsert
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING) //enum(열거)타입이니까 그중 하나가 status에 대입되면 무슨 타입으로 설정하는가 -> String
    private TaskStatus status;

    private Date dueDate;

    @CreationTimestamp
    @Column(updatable = false, insertable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(updatable = false, insertable = false)
    private Timestamp updatedAt;
}
