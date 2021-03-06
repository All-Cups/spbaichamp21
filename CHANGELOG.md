# Changelog

## v1.0.7

- Исправлено смещение отображения тумана войны

## v1.0.6

- Исправлено падение встроенной QuickStart стратегии при включенном тумане войны

## v1.0.5

- Разрешены специальности в пресетах `FinalA`, `FinalB`

## v1.0.4

- Отображение тумана войны
- Информация о летящих группах при наведении
- Транзитные группы теперь отображаются кружащими вокруг планеты в момент определения следующей планеты в пути

## v1.0.3

- Исправлено: теперь роботы со специальностью логистика всегда летят напрямую на целевую планету, если возможно
- Если не выбрать специальность в первый тик, сервер выберет автоматически в **первый тик сразу всем**

## v1.0.2

- Исправлен баг с уроном по пролетающим роботам
- Исправлен баг с постройкой зданий без роботов на планете

## v1.0.1

- Исправлен баг в игровом сервере
- Вернули json при наведении на планету

## v1.0.0

- Исправлено игнорирование вражеских пролетающих групп
- Детализация счета
- Улучшения визуализации
- Убран рандом из встроенного QuickStart игрока

## v0.4.0

- Добавлена командная механика
- Добавлена механика специализаций
- Добавлена механика тумана войны
- Добавлена механика улучшения зданий
- **Изменен порядок действий** — битвы теперь проходят до перемещения
- Добавлено поле `Building::last_tick_tasks_done`
- Добавлены комментарии к классам и полям в клиентах
- Исправлено минимальное расстояние между планетами
- Добавлена сборка приложения под Linux ARMv7
- Улучшена производительность игрового сервера
