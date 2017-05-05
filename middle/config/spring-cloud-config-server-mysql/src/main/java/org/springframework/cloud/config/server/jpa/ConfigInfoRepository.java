package org.springframework.cloud.config.server.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface ConfigInfoRepository extends CrudRepository<ConfigInfo, Long> {
    List<ConfigInfo> findByApplicationAndProfile(String application, String profile);
}