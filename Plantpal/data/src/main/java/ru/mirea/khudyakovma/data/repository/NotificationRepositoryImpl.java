package ru.mirea.khudyakovma.data.repository;

import ru.mirea.khudyakovma.domain.repository.NotificationRepository;

public class NotificationRepositoryImpl implements NotificationRepository {
    @Override
    public String getNextWateringReminder() {
        return "Полейте растение завтра в 10:00";
    }
}
