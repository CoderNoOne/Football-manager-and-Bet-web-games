package com.app.web_app.model.user;

import com.app.web_app.model.bet_game.ScoreEntity;
import com.app.web_app.model.manager_game.Game;
import com.app.web_app.model.manager_game.Team;
import com.app.web_app.model.user.enums.Authority;
import com.app.web_app.model.user.enums.Gender;
import com.app.web_app.validators.hibernate_validators.annotations.AdultUser;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    private String username;

    @Pattern(regexp = "[A-Z][a-z]+", message = "first name should start with a capital letter Abc")
    @Size(min = 2, max = 15, message = "first name should contain 2-15 letters")
    private String firstName;

    @Pattern(regexp = "[A-Z][a-z]+", message = "last name should start with a capital letter Abc")
    @Size(min = 2, max = 15, message = "last name should contain 2-15 letters")
    private String lastName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @AdultUser
    private LocalDate birthDate;

    @Email(message = "not valid email address")
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String password;

    private String photoUrl;

    @Column(columnDefinition = "tinyInt")
    private Boolean enabled;

    @ElementCollection(/*fetch = FetchType.LAZY*/)
    @CollectionTable(name = "authorities", joinColumns = @JoinColumn(name = "username"))
    @Column(name = "authority")
    @Enumerated(EnumType.STRING)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Authority> authorities;


    @ManyToMany
    @JoinTable(
            name = "users_games",
            joinColumns = @JoinColumn(name = "user_username", referencedColumnName = "username"),
            inverseJoinColumns = @JoinColumn(name = "game_id", referencedColumnName = "id")
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Game> games;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Team> teams;


    @OneToMany(mappedBy = "user")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<ScoreEntity> scoreEntities;


//    @OneToOne(mappedBy = "user")
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private VerificationToken confirmationToken;
}
