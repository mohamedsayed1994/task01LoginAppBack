package com.test.task01LoginAppBack.sec;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "SEC_USERS")
@Getter
@Setter
public class SecUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String username;
    @JsonIgnore
    private String password;
    private boolean active;
    private String roles; // "ADMIN_ROLE" , "USER_ROLE"

    public SecUser() {
    }

    public SecUser(@NonNull String username, String password, boolean active, String roles) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.roles = roles;
    }
}
