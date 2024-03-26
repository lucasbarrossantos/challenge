package com.code.challenge.model;

import com.code.challenge.codegen.types.UpdateUserInput;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Builder
@Table(name = "app_user")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Setter
    private String name;
    @Setter
    private String email;

    public User (String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void update(UpdateUserInput input) {
        this.name = input.getName();
        this.email = input.getEmail();
    }
}
