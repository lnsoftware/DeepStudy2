package org.springframework.cloud.config.server.jpa;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "config_info")
public class ConfigInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "`application'", nullable = false)
    private String application;
    @Column(name = "`profile'", nullable = true)
    private String profile;
    @Column(name = "`key'", nullable = false)
    private String key;
    @Column(name = "`value'", nullable = false)
    private String value;

    @Column(name = "`lable'", nullable = true)
    private String lable;

//    private String description;
//    private Integer delStatus;

}