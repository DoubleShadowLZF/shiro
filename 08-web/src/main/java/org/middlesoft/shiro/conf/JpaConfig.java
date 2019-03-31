package org.middlesoft.shiro.conf;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class JpaConfig {
    @Bean
    public JPAQueryFactory jpaQuery(EntityManager entityManager){
        return new JPAQueryFactory(entityManager);
    }
}
