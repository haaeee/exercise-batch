package com.example.exercisebatch.repository.pass;

import com.example.exercisebatch.repository.BaseEntity;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
@Table(name = "pass")
public class PassEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer passSeq;

    private Integer packageSeq;

    private String userId;

    @Enumerated(EnumType.STRING)
    private PassStatus status;

    private Integer remainingCount;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    private LocalDateTime expiredAt;

    @Builder
    public PassEntity(final Integer packageSeq, final String userId, final PassStatus status,
                      final Integer remainingCount, final LocalDateTime startedAt,
                      final LocalDateTime endedAt, final LocalDateTime expiredAt) {
        this.packageSeq = packageSeq;
        this.userId = userId;
        this.status = status;
        this.remainingCount = remainingCount;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.expiredAt = expiredAt;
    }
}