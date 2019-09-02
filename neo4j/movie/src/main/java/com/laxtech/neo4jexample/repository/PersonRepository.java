package com.laxtech.neo4jexample.repository;


import com.laxtech.neo4jexample.domain.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;


public interface PersonRepository extends Neo4jRepository<Person, Long> {

    Person findByName(String name);

}