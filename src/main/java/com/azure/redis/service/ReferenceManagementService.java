package com.azure.redis.service;

import com.azure.redis.dto.ReferenceType;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReferenceManagementService {

    private final RedisTemplate<String, List<ReferenceType>> redisTemplate;

    public ReferenceType addPrimaryReferenceType(ReferenceType referenceType) {
        HashOperations<String, String, List<ReferenceType>> valueOps = redisTemplate.opsForHash();
//        List<ReferenceType> availablePrimaryrefs = valueOps.get("primary_reference", referenceType.getReferenceType());
//        if (CollectionUtils.isEmpty(availablePrimaryrefs))
//            availablePrimaryrefs = new ArrayList<>();
        List<ReferenceType> availablePrimaryrefs = new ArrayList<>();
        availablePrimaryrefs.add(referenceType);
        valueOps.put("primary_reference", referenceType.getReferenceType(), availablePrimaryrefs);
        return referenceType;
    }

    public List<ReferenceType> getPrimaryRefTypes() {
        HashOperations<String, String, List<ReferenceType>> valueOps = redisTemplate.opsForHash();
        var res = valueOps.values("primary_reference");
        List<ReferenceType> allRefs = res.stream().flatMap(List::stream)
                .collect(Collectors.toList());
        return allRefs;
    }
}
