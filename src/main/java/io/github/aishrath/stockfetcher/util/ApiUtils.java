package io.github.aishrath.stockfetcher.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.vavr.Tuple2;
import io.vavr.collection.Stream;
import lombok.extern.slf4j.Slf4j;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.Map;

@Slf4j
public class ApiUtils {
    public static Map<String, Object> getBeanProperties(Object bean) {
        try {
            return Stream.of(Introspector.getBeanInfo(bean.getClass(), Object.class)
                    .getPropertyDescriptors())
                    .filter(pd -> pd.getReadMethod() != null)
                    .toJavaMap(pd -> {
                        try {
                            return new Tuple2<>(
                                    pd.getName(),
                                    pd.getReadMethod().invoke(bean));
                        } catch (Exception e) {
                            throw new IllegalStateException();
                        }
                    });
        } catch (IntrospectionException e) {
            throw new IllegalStateException();
        }
    }

    public static JsonNode generateBadRequestJsonNode() {
        log.info("Invalid ticker invocation");
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode root = objectMapper.createObjectNode();
        root.put("message", "Invalid ticker provided!");
        return root;
    }
}
