package com.app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Transient
    private String confirmPassword;

    @Column(name = "active")
    private int active;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="user_role", joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns=@JoinColumn(name="role_id"))
    private Set<Role> roles;


    @Getter
    @Setter
    @OneToMany(mappedBy = "user")
    private List<Comments> comments;


    @java.beans.ConstructorProperties({"id", "email", "firstname", "lastname", "username", "password", "confirmPassword", "active", "roles"})
    public User(Long id, String email, String firstname, String lastname, String username, String password, String confirmPassword, int active, Set<Role> roles) {
        this.id = id;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.active = active;
        this.roles = roles;
    }

    public User() {
    }

    public Long getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public int getActive() {
        return this.active;
    }

    public Set<Role> getRoles() {
        return this.roles;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String toString() {
        return "User(id=" + this.getId() + ", email=" + this.getEmail() + ", firstname=" + this.getFirstname() + ", lastname=" + this.getLastname() + ", username=" +
                this.getUsername() + ", active=" + this.getActive() +  ")";
    }
}
