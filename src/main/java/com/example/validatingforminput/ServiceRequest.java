package com.example.validatingforminput;

import javax.persistence.*;

@Entity
public class ServiceRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(value = EnumType.ORDINAL)
    private Services services;

    @ManyToOne()
    @JoinColumn(name = "request_id", referencedColumnName = "id")
    private Request request;

    public ServiceRequest(Services services, Request request) {
        this.services = services;
        this.request = request;
    }
    public ServiceRequest() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}
