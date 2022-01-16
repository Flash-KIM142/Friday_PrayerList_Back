package com.example.demo.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import javax.persistence.*;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="notice")
public class Notice {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd_HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd_HH:mm:ss", timezone = "Asia/Seoul")
    @CreationTimestamp
    private LocalDateTime createdTime;

    public Notice(String name, String content, LocalDateTime createdTime){
        this.name = name;
        this.content = content;
        this.createdTime = createdTime;
    }

    public Notice(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public void update(String editedName, String editedContent){
        this.name = editedName;
        this.content = editedContent;
    }

}