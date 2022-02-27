package com.github.pedroluiznogueira.microservicesconsumer.config.local;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;
import com.github.pedroluiznogueira.microservicesconsumer.repository.ProductEventLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Profile("local")
@EnableDynamoDBRepositories(basePackageClasses = ProductEventLogRepository.class)
public class DynamoDbConfigLocalStack {

    private static final Logger LOG = LoggerFactory.getLogger(
            DynamoDbConfigLocalStack.class);

    final private AmazonDynamoDB amazonDynamoDB;

    public DynamoDbConfigLocalStack() {
        this.amazonDynamoDB = AmazonDynamoDBClient.builder()
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration("http://localhost:4566",
                                Regions.US_EAST_1.getName()))
                .build();

//        DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);
//
//        List<AttributeDefinition> attributeDefinitions= new ArrayList<AttributeDefinition>();
//        attributeDefinitions.add(new AttributeDefinition().withAttributeName("pk").withAttributeType(ScalarAttributeType.S));
//        attributeDefinitions.add(new AttributeDefinition().withAttributeName("sk").withAttributeType(ScalarAttributeType.S));
//
//        List<KeySchemaElement> keySchema = new ArrayList<KeySchemaElement>();
//        keySchema.add(new KeySchemaElement().withAttributeName("pk").withKeyType(KeyType.HASH));
//        keySchema.add(new KeySchemaElement().withAttributeName("sk").withKeyType(KeyType.RANGE));
//
//        CreateTableRequest request = new CreateTableRequest()
//                .withTableName("product-events")
//                .withKeySchema(keySchema)
//                .withAttributeDefinitions(attributeDefinitions)
//                .withBillingMode(BillingMode.PAY_PER_REQUEST);
//
//        Table table = dynamoDB.createTable(request);
//
//        try {
//            table.waitForActive();
//        } catch (InterruptedException e) {
//            LOG.error(e.getMessage());
//        }
    }

    @Bean
    @Primary
    public DynamoDBMapperConfig dynamoDBMapperConfig() {
        return DynamoDBMapperConfig.DEFAULT;
    }

    @Bean
    @Primary
    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB,
                                         DynamoDBMapperConfig config) {
        return new DynamoDBMapper(amazonDynamoDB, config);
    }

    @Bean
    @Primary
    public AmazonDynamoDB amazonDynamoDB() {
        return this.amazonDynamoDB;
    }

}
