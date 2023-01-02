package com.example.exercisebatch.repository.user;


import com.example.exercisebatch.repository.BaseEntity;
import com.vladmihalcea.hibernate.type.json.JsonType;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
@Table(name = "user")
@TypeDef(name = "json", typeClass = JsonType.class)  // json 타입을 정의합니다.
public class UserEntity extends BaseEntity {

    @Id
    private String userId;

    private String userName;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    private String phone;

    // json 형태로 저장되어 있는 문자열 데이터를 Map으로 매핑합니다.
    @Type(type = "json")
    private Map<String, Object> meta;

    public String getUuid() {
        String uuid = null;
        if (meta.containsKey("uuid")) {
            uuid = String.valueOf(meta.get("uuid"));
        }
        return uuid;
    }

    @Builder
    public UserEntity(final String userName, final UserStatus status, final String phone,
                      final Map<String, Object> meta) {
        this.userName = userName;
        this.status = status;
        this.phone = phone;
        this.meta = meta;
    }
}