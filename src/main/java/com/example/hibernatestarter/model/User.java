package com.example.hibernatestarter.model;

import com.example.hibernatestarter.converter.BirthdayConverter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;

import java.time.Instant;

/**
 * @author Zhurenkov Pavel 01.10.2023
 */
//решение проблемы N+1
@NamedEntityGraph(
        name = "withCompany", attributeNodes = {
                @NamedAttributeNode("company")
}
)
@NamedQuery(name = "findUserByNameAndCompany", query = """
                select u from User u
                left join u.company c
                where u.firstname = :firstname
                and c.name = :company
                """)

@FetchProfile(name = "withCompany", fetchOverrides = {
        @FetchProfile.FetchOverride(entity = User.class, association = "company", mode = FetchMode.JOIN)
})

@Data
@ToString(exclude = {"company", "profile"})
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User implements BaseEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String firstname;

    private String lastname;

    @Convert(converter = BirthdayConverter.class)
    @Column(name = "birth_day")
    private Birthday birthDate;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @BatchSize(size = 5)
    private Profile profile;


}
