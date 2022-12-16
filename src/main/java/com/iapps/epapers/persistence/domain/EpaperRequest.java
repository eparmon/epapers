package com.iapps.epapers.persistence.domain;

import com.querydsl.core.annotations.QueryEntity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@QueryEntity
@Table(name = "epaper_requests")
public class EpaperRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "newspaper_name")
    private String newspaperName;

    @Column(name = "screen_width")
    private Integer screenWidth;

    @Column(name = "screen_height")
    private Integer screenHeight;

    @Column(name = "screen_dpi")
    private Integer screenDpi;

    @Column(name = "file_name")
    private String fileName;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

}
