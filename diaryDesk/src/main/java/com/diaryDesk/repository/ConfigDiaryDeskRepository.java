package com.diaryDesk.repository;

import com.diaryDesk.entity.ConfigDiaryDeskEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigDiaryDeskRepository extends MongoRepository<ConfigDiaryDeskEntity, ObjectId> {

}
