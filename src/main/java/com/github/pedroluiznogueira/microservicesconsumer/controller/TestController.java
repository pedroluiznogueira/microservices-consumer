package com.github.pedroluiznogueira.microservicesconsumer.controller;

import com.github.pedroluiznogueira.microservicesconsumer.model.ProductEventLog;
import com.github.pedroluiznogueira.microservicesconsumer.repository.ProductEventLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ProductEventLogRepository productEventLogRepository;

    @GetMapping
    public Iterable<ProductEventLog> getAll() {
        return productEventLogRepository.findAll();
    }
}
