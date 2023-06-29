package com.cbr.bpm;

import com.cbr.bpm.service.MigrationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Migration {

    @Autowired
    MigrationService migrationService;

    @Test
    public void migration() {
        // проверяем что миграция успешна и на 8-й версии не осталось экземпляров процессов
        assertTrue(migrationService.migrate());
    }
}
