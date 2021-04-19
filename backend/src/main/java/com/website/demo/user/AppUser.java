package com.website.demo.user;

import com.website.demo.address.Address;
import com.website.demo.authorities.AppUserRole;
import com.website.demo.schedule.Schedule;
import com.website.demo.visit.Visit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "app_user")
public class AppUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String secondName;
    private String lastName;
    private String phone;
    private String password;
    private String email;
    private String imageName;
    private LocalDate birthdate;
    @OneToMany(mappedBy = "doctor")
    private Set<Visit> visitSet;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @ManyToMany
    @JoinTable(name = "doctor_schedule", joinColumns = {@JoinColumn(name = "app_user_id")}, inverseJoinColumns = {@JoinColumn(name = "schedule_id")})
    private Set<Schedule> schedules;
    @Enumerated(value = EnumType.STRING)
    private AppUserRole role;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    public AppUser(String firstName,
                   String secondName,
                   String lastName,
                   String email,
                   String password,
                   String phone,
                   Address address,
                   LocalDate birthdate,
                   AppUserRole role) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = false;
        this.birthdate = birthdate;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        Set<SimpleGrantedAuthority> roleGrantedAuthorities = role.getGrantedAuthorities();
        authorities.addAll(roleGrantedAuthorities);
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
