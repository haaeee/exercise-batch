package com.example.exercisebatch.repository.booking;

import com.example.exercisebatch.repository.BaseEntity;
import com.example.exercisebatch.repository.pass.PassEntity;
import com.example.exercisebatch.repository.user.UserEntity;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "booking")
public class BookingEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingSeq;

    private Integer passSeq;

    private String userId;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    private boolean usedPass;

    private boolean attended;

    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private LocalDateTime cancelledAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passSeq", insertable = false, updatable = false)
    private PassEntity passEntity;

    @Builder
    public BookingEntity(final Integer passSeq, final String userId, final BookingStatus status, final boolean usedPass,
                         final boolean attended,
                         final LocalDateTime startedAt, final LocalDateTime endedAt, final LocalDateTime cancelledAt,
                         final UserEntity userEntity,
                         final PassEntity passEntity) {
        this.passSeq = passSeq;
        this.userId = userId;
        this.status = status;
        this.usedPass = usedPass;
        this.attended = attended;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.cancelledAt = cancelledAt;
        this.userEntity = userEntity;
        this.passEntity = passEntity;
    }

    // endedAt 기준, yyyy-MM-HH 00:00:00
    public LocalDateTime getStatisticsAt() {
        return this.endedAt.withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

}
