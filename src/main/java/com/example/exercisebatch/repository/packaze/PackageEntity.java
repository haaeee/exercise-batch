package com.example.exercisebatch.repository.packaze;

import com.example.exercisebatch.repository.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "package")
public class PackageEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer packageSeq;

    private String packageName;

    private Integer count;

    private Integer period;

    @Builder
    private PackageEntity(final String packageName, final Integer count, final Integer period) {
        this.packageName = packageName;
        this.count = count;
        this.period = period;
    }
}
