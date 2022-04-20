package com.example.validatingforminput;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@Entity
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "client_name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "request", fetch = FetchType.EAGER)
    private List<ServiceRequest> services;

    public Request( String name, String email, LocalDateTime dateTime) {
        this.name = name;
        this.email = email;
        this.dateTime = dateTime;
    }
    public Request() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setServices(List<ServiceRequest> services) {
        this.services = services;
    }

    public List<ServiceRequest> getServices() {
        return services;
    }

    public String getServicesAsString(){
        return Arrays.toString(services.stream().map((Function<ServiceRequest, Object>) ServiceRequest::getServices).toArray());
    }
}
