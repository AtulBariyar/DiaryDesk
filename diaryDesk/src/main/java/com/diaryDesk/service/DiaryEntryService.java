package com.diaryDesk.service;

import com.diaryDesk.repository.DiaryEntryRepository;
import lombok.extern.slf4j.Slf4j;
import com.diaryDesk.entity.DiaryEntry;
import com.diaryDesk.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DiaryEntryService {

    @Autowired
    private DiaryEntryRepository diaryEntryRepository;

    @Autowired
    private UserService userService;



    @Transactional
    public void saveEntry(DiaryEntry diaryEntry, String userName) {
        try {
            User user = userService.findByUserName(userName);
            diaryEntry.setDate(LocalDateTime.now());
            DiaryEntry saved = diaryEntryRepository.save(diaryEntry);
            user.getJournalEntries().add(saved);
            userService.saveUser(user);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while saving the entry.", e);
        }
    }

    public void saveEntry(DiaryEntry diaryEntry) {
        diaryEntryRepository.save(diaryEntry);
    }

    public List<DiaryEntry> getAll() {
        return diaryEntryRepository.findAll();
    }

    public Optional<DiaryEntry> findById(ObjectId id) {
        return diaryEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName) {
        boolean removed = false;
        try {
            User user = userService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userService.saveUser(user);
                diaryEntryRepository.deleteById(id);
            }
        } catch (Exception e) {
            log.error("Error ",e);
            throw new RuntimeException("An error occurred while deleting the entry.", e);
        }
        return removed;
    }

}