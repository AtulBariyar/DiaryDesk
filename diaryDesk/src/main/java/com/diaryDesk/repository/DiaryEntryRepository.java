package com.diaryDesk.repository;

import com.diaryDesk.entity.DiaryEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DiaryEntryRepository extends MongoRepository<DiaryEntry, ObjectId> {

}
