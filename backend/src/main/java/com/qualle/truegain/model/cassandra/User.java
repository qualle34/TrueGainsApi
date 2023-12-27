package com.qualle.truegain.model.cassandra;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;


@Data
@Table("user")
public class User {

    @Column("id")
    @PrimaryKey
    private Integer id;

    @Column("name")
    private String name;

    @Column("age")
    private Integer age;
}
