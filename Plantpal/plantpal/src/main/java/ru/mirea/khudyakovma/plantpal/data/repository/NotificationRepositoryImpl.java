package ru.mirea.khudyakovma.plantpal.data.repository;

import ru.mirea.khudyakovma.plantpal.domain.repository.NotificationRepository;

public class NotificationRepositoryImpl implements NotificationRepository {
    @Override
    public String getNextWateringReminder() {
        return "Полейте растение завтра в 10:00";
    }
}
