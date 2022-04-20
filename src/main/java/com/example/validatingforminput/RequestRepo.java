package com.example.validatingforminput;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

@EnableJpaRepositories
public interface RequestRepo extends CrudRepository<Request, Long> {
}
