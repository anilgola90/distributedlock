package org.anil.distributedlock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.jdbc.lock.DefaultLockRepository;
import org.springframework.integration.jdbc.lock.JdbcLockRegistry;
import org.springframework.integration.jdbc.lock.LockRepository;

import javax.sql.DataSource;

@SpringBootApplication
public class DistributedlockApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistributedlockApplication.class, args);
    }


    @Bean
    public DefaultLockRepository DefaultLockRepository(DataSource dataSource){
        return new DefaultLockRepository(dataSource);
    }

    @Bean
    public JdbcLockRegistry jdbcLockRegistry(LockRepository lockRepository){
        return new JdbcLockRegistry(lockRepository);
    }


}
