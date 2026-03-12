package com.example.securecoding.model;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class User {

    private Long id;

    @NotBlank(message = "Name is required")
    @Size(max = 50, message = "Name too long")
    private String name;

    @Email(message = "Invalid email format")
    private String email;

    @Size(min = 8, max = 20)
    private String password;

    public User() {}

    public User(Long id,String name,String email,String password){
        this.id=id;
        this.name=name;
        this.email=email;
        this.password=password;
    }

    public Long getId(){ return id; }
    public void setId(Long id){ this.id=id; }

    public String getName(){ return name; }
    public void setName(String name){ this.name=name; }

    public String getEmail(){ return email; }
    public void setEmail(String email){ this.email=email; }

    public String getPassword(){ return password; }
    public void setPassword(String password){ this.password=password; }
}
