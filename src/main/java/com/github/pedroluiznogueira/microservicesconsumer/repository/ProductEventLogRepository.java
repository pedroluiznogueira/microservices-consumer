package com.github.pedroluiznogueira.microservicesconsumer.repository;

import com.github.pedroluiznogueira.microservicesconsumer.model.ProductEventKey;
import com.github.pedroluiznogueira.microservicesconsumer.model.ProductEventLog;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface ProductEventLogRepository extends CrudRepository<ProductEventLog, ProductEventKey> {

    List<ProductEventLog> findAllByPk(String code);

    List<ProductEventLog> findAllByPkAndSkStartsWith(String code, String eventType);
}
