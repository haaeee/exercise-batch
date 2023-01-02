package com.example.exercisebatch.repository.packaze;


import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@DisplayName("JPA 연결 테스트")
@DisplayNameGeneration(ReplaceUnderscores.class)
//@Import(TestJapConfig.class)
@SpringBootTest
class PackageRepositoryTest {

    private final PackageRepository packageRepository;

    public PackageRepositoryTest(@Autowired final PackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    @Test
    void insert() {
        // Given
        PackageEntity packageEntity = PackageEntity.builder()
                .packageName("바디 챌린지 PT 12주")
                .period(84)
                .build();

        // When
        packageRepository.saveAndFlush(packageEntity);

        // Then
        assertThat(packageEntity.getPackageSeq()).isNotNull();
    }
    
    @Test
    void CREATED_AT_기준_이후_엔티티_조회() {
        // Given
        LocalDateTime dateTime = LocalDateTime.now().minusMinutes(1);

        PackageEntity packageEntityA = PackageEntity.builder()
                .packageName("학생 전용 3개월")
                .period(90)
                .build();
        packageRepository.save(packageEntityA);

        PackageEntity packageEntityB = PackageEntity.builder()
                .packageName("학생 전용 6개월")
                .period(180)
                .build();
        packageRepository.save(packageEntityB);

        // When
        final List<PackageEntity> packageEntities = packageRepository.findByCreatedAtAfter(dateTime,
                PageRequest.of(0, 1, Sort.by("packageSeq").descending()));

        // Then
        assertThat(packageEntities.size()).isEqualTo(1);
        assertThat(packageEntityB.getPackageSeq()).isEqualTo(packageEntities.get(0).getPackageSeq());
    }
    
    @Test
    void count_와_Period__업데이트() {
        // Given

        // When
        
        
        // Then
    }

//    @EnableJpaAuditing
//    @TestConfiguration
//    static class TestJapConfig {}
}