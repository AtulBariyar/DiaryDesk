package com.diaryDesk.cache;

import com.diaryDesk.entity.ConfigDiaryDeskEntity;
import com.diaryDesk.repository.ConfigDiaryDeskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum keys{
        WEATHER_API;
    }

    @Autowired
    private ConfigDiaryDeskRepository configDiaryDeskRepository;

    public Map<String, String> appCache;

    @PostConstruct
    public void init(){
        appCache = new HashMap<>();
        List<ConfigDiaryDeskEntity> all = configDiaryDeskRepository.findAll();
        for (ConfigDiaryDeskEntity configDiaryDeskEntity : all) {
            appCache.put(configDiaryDeskEntity.getKey(), configDiaryDeskEntity.getValue());
        }
    }

}
