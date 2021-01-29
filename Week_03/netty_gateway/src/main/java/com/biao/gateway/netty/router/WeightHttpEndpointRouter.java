package com.biao.gateway.netty.router;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @desc:
 * @author: biao
 * @create: 2021-01-29 23:07
 **/
public class WeightHttpEndpointRouter implements HttpEndpointRouter {

    private static Map<String, Integer> initWeightMap = new HashMap<>();

    public WeightHttpEndpointRouter(){
        initWeightMap.put("http://localhost:8801", 2);
        initWeightMap.put("http://localhost:8802", 3);
        initWeightMap.put("http://localhost:8803", 5);
    }

    @Override
    public String route(List<String> endpoints) {
        Map<String, Integer> map = new HashMap<>();
        int sum = 0;
        for (Map.Entry<String, Integer> entry : initWeightMap.entrySet()) {
            if(endpoints.contains(entry.getKey())){
                sum += entry.getValue();
                map.put(entry.getKey(), sum);
            }
        }
        Random random = new Random(System.currentTimeMillis());
        int hit = random.nextInt(sum);
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            if(entry.getValue() > hit){
                return entry.getKey();
            }
        }
        return null;
    }
}
