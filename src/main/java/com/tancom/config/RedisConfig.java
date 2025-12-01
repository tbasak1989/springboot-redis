package com.tancom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
public class RedisConfig {

        @Bean
        public RedisCacheConfiguration redisCacheConfiguration() {

            GenericJackson2JsonRedisSerializer jsonSerializer = new GenericJackson2JsonRedisSerializer();

            return RedisCacheConfiguration.defaultCacheConfig()
                    // Serialize keys as strings
                    .serializeKeysWith(
                            RedisSerializationContext.SerializationPair
                                    .fromSerializer(new StringRedisSerializer())
                    )
                    // Serialize values as JSON
                    .serializeValuesWith(
                            RedisSerializationContext.SerializationPair
                                    .fromSerializer(jsonSerializer)
                    )
                    // Set TTL for all cache entries
                    .entryTtl(Duration.ofMinutes(2))
                    // Do not cache null values
                    .disableCachingNullValues();
        }
    }





