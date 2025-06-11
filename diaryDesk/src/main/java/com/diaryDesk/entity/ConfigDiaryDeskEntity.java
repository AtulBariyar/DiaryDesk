package com.diaryDesk.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "config_DiaryDesk")
@Data
@NoArgsConstructor
public class ConfigDiaryDeskEntity {

    private String key;
    private String value;

}
