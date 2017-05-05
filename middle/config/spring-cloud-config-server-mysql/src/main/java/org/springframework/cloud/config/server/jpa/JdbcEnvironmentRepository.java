package org.springframework.cloud.config.server.jpa;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.YamlProcessor;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.util.StringUtils;

import java.util.*;

@Slf4j
public class JdbcEnvironmentRepository implements EnvironmentRepository {

    private static final String LABEL = "label";
    private static final String PROFILE = "profile";
    private static final String DEFAULT = "default";
    private static final String DEFAULT_PROFILE_VALUE = "default";
    private static final String DEFAULT_LABEL = null;

    private ConfigInfoRepository configInfoRepository;

    public JdbcEnvironmentRepository(ConfigInfoRepository configInfoRepository) {
        this.configInfoRepository = configInfoRepository;
    }

    @Override
    public Environment findOne(String application, String profile, String label) {
        log.info("get env config, application:{},profile:{}", application, profile);
        String[] profilesArr = StringUtils.commaDelimitedListToStringArray(profile);
        List<String> profiles = new ArrayList<String>(Arrays.asList(profilesArr.clone()));
        if (profiles == null || profiles.size() == 0) {
            profiles.add(DEFAULT_PROFILE_VALUE); // Default configuration will have 'null' profile
        }
        profiles = sortedUnique(profiles);

        List<String> labels = Arrays.asList(label, DEFAULT_LABEL); // Default configuration will have 'null' label
        labels = sortedUnique(labels);

        Environment environment = null;
        try {
            environment = new Environment(application, profilesArr, label, null, null);
            for (String tmpProfile : profiles) {
                List<ConfigInfo> selList = configInfoRepository.findByApplicationAndProfile(application, tmpProfile);
                Map<String, Object> flatSource = Maps.newHashMap();
                String sourceName = application + "_" + tmpProfile;
                for (ConfigInfo configInfo : selList) {
                    flatSource.put(configInfo.getKey(), configInfo.getValue());
                }
                PropertySource propSource = new PropertySource(sourceName, flatSource);
                environment.add(propSource);
            }

        } catch (Exception e) {
            throw new IllegalStateException("Cannot load environment", e);
        }
        log.info("sel result:{}", JSON.toJSONString(environment));
        return environment;
    }

    private ArrayList<String> sortedUnique(List<String> values) {
        return new ArrayList<String>(new LinkedHashSet<String>(values));
    }

    private void sortSourcesByLabel(List<MongoPropertySource> sources,
                                    final List<String> labels) {
        Collections.sort(sources, new Comparator<MongoPropertySource>() {

            @Override
            public int compare(MongoPropertySource s1, MongoPropertySource s2) {
                int i1 = labels.indexOf(s1.getLabel());
                int i2 = labels.indexOf(s2.getLabel());
                return Integer.compare(i1, i2);
            }

        });
    }

    private void sortSourcesByProfile(List<MongoPropertySource> sources,
                                      final List<String> profiles) {
        Collections.sort(sources, new Comparator<MongoPropertySource>() {

            @Override
            public int compare(MongoPropertySource s1, MongoPropertySource s2) {
                int i1 = profiles.indexOf(s1.getProfile());
                int i2 = profiles.indexOf(s2.getProfile());
                return Integer.compare(i1, i2);
            }

        });
    }

    private String generateSourceName(String environmentName, MongoPropertySource source) {
        String sourceName;
        String profile = source.getProfile() != null ? source.getProfile() : DEFAULT;
        String label = source.getLabel();
        if (label != null) {
            sourceName = String.format("%s-%s-%s", environmentName, profile, label);
        } else {
            sourceName = String.format("%s-%s", environmentName, profile);
        }
        return sourceName;
    }

    public static class MongoPropertySource {

        private String profile;
        private String label;
        private LinkedHashMap<String, Object> source = new LinkedHashMap<String, Object>();

        public String getProfile() {
            return profile;
        }

        public void setProfile(String profile) {
            this.profile = profile;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public LinkedHashMap<String, Object> getSource() {
            return source;
        }

        public void setSource(LinkedHashMap<String, Object> source) {
            this.source = source;
        }

    }

    private static class MapFlattener extends YamlProcessor {

        public Map<String, Object> flatten(Map<String, Object> source) {
            return getFlattenedMap(source);
        }

    }

}
