package com.cbr.bpm.service;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.migration.MigrationPlan;
import org.springframework.stereotype.Service;

@Service
public class MigrationServiceImpl implements MigrationService {

    private final RuntimeService runtimeService;

    public MigrationServiceImpl(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    @Override
    public Boolean migrate() {
        String sourceDefinitionId = "partymaker:8:1d549ff7-166f-11ee-80b4-3afb087e6da0";
        String targetDefinitionId = "partymaker:13:f96cf278-1677-11ee-a974-3afb087e6da0";

        MigrationPlan plan = runtimeService.createMigrationPlan(sourceDefinitionId, targetDefinitionId
                )
                .mapEqualActivities() // мигрируем активности с совпадающими именами
                .build(); // Здесь можно сопоставить имена активностей, если они поменялись (mapActivities).

        runtimeService
                .newMigration(plan) // план миграции
                .processInstanceQuery(runtimeService.createProcessInstanceQuery().processDefinitionId(sourceDefinitionId))
                .execute(); // executeAsync() - если значительное число экземпляров процессов (тысячи, сотни)

        /* Здесь мы можем вызвать RuntimeService и произвести необходимые манипуляции с контекстом процесса:
        * - передвинуть токен runtimeService.createModification()
        * - установить переменные процесса runtimeService.setVariable();
        * */
        // Убедиться, что не осталось экземпляров процесса на исходной версии
        return runtimeService.createProcessInstanceQuery().processDefinitionId(sourceDefinitionId).list().isEmpty();
    }
}
