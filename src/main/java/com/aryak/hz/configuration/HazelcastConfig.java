package com.aryak.hz.configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.ManagementCenterConfig;
import com.hazelcast.config.MapConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author aryak
 *
 * Hazelcast cluster and management UI configuration
 */
@Configuration
public class HazelcastConfig {

    @Value("${instance.name:nameless-instance}")
    String instanceName;

    // renamed to avoid conflict with a pre-existing spring managed bean
    @Bean(name = "customHazelcastConfig")
    public Config hazelcastConfig() {

        Config config = new Config();
        // TODO : need to make this dynamic
        config.setInstanceName(instanceName);

        // Network Configuration
        JoinConfig joinConfig = config.getNetworkConfig().getJoin();
        // TODO : need to experiment these, i think multicast is better but chatGPT saying TCP/IP is better
        joinConfig.getMulticastConfig().setEnabled(false);
        joinConfig.getTcpIpConfig()
                .setEnabled(true)
                // TODO : need to make this dynamic
                .setMembers(List.of("127.0.0.1:5701", "127.0.0.1:5702"));

        // Map Configuration
        MapConfig mapConfig = new MapConfig();
        mapConfig.setName("clientMetadata")
                .setBackupCount(1) // Default replication factor
                .setTimeToLiveSeconds(0); // No TTL

        config.addMapConfig(mapConfig);

        // Enable Management Center - cluster will automatically connect to management UI??
        ManagementCenterConfig managementCenterConfig = new ManagementCenterConfig();
        managementCenterConfig.setConsoleEnabled(true);
        config.setManagementCenterConfig(managementCenterConfig);
        return config;
    }
}
