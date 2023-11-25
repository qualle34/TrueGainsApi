package com.qualle.truegain;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.images.builder.Transferable;

import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

public class QuickTest {
    @Test
    public void test() throws SQLException {

        try (PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:14-alpine")
                .withCopyToContainer(Transferable.of(initsql), "/docker-entrypoint-initdb.d/init.sql")) {
            postgreSQLContainer.start();
            Connection connection = DriverManager.getConnection(postgreSQLContainer.getJdbcUrl(), postgreSQLContainer.getUsername(), postgreSQLContainer.getPassword());
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM guides");
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            assertThat(resultSet.getInt(1)).isEqualTo(6);
        }
    }

    @Test
    public void test2(){
        for (int i = 122; i < 122 * 2; i++) {
            System.out.println(i + ", ");
        }
    }

    private static final String initsql =
            "create table guides\n" +
                    "(\n" +
                    "    id         bigserial     not null,\n" +
                    "    title      varchar(1023)  not null,\n" +
                    "    url        varchar(1023) not null,\n" +
                    "    primary key (id)\n" +
                    ");\n" +
                    "\n" +
                    "insert into guides(title, url)\n" +
                    "values ('Getting started with Testcontainers', 'https://testcontainers.com/getting-started/'),\n" +
                    "       ('Getting started with Testcontainers for Java', 'https://testcontainers.com/guides/getting-started-with-testcontainers-for-java/'),\n" +
                    "       ('Getting started with Testcontainers for .NET', 'https://testcontainers.com/guides/getting-started-with-testcontainers-for-dotnet/'),\n" +
                    "       ('Getting started with Testcontainers for Node.js', 'https://testcontainers.com/guides/getting-started-with-testcontainers-for-nodejs/'),\n" +
                    "       ('Getting started with Testcontainers for Go', 'https://testcontainers.com/guides/getting-started-with-testcontainers-for-go/'),\n" +
                    "       ('Testcontainers container lifecycle management using JUnit 5', 'https://testcontainers.com/guides/testcontainers-container-lifecycle/')\n" +
                    ";";
}
