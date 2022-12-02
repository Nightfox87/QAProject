# Курсовой проект по модулю «Автоматизация тестирования» для профессии «Инженер по тестированию»
В курсовом проекте проводится автоматизация тестирования комплексного сервиса, взаимодействующего с СУБД и API банка.

## Запуск автотестов
1. открыть страницу [проекта на GitHub] (https://github.com/Nightfox87/QAProject).
2. сделать форк репозитория.
3. скопировать проект из GitHub в локальный репозиторий.
4. открыть проект в Intellij IDEA.
5. запустить контейнер с базой данных MySQL при помощи команды *docker-compose up*
6. запустить приложение сервиса покупки тура командой *java -jar aqa-shop.jar*
7. запустить тесты командой *./gradlew clean test*
8. для получения отчета о тестировании набрать команду *./gradlew allureServe*, после чего отчет откроется в браузере. Также отчет можно найти через путь ./build/reports/tests/test/index.html

## Необходимые инструменты
Для успешной работы сервиса и автотестов должны быть установлены следующие программы:
- браузер (в проекте использовался Google Chrome)
- среда разработки Intellij IDEA
- Docker
- Git
- Java: OpenJDK 11

## Тестовые сценарии
Всего в проекте было разработано 17 автотестов (1 позитивный и 16 негативных) для проверки работы сервиса по покупке тура с помощью банковской карты.
План тестирования можно найти в файле Plan.md через путь ./docs/Plan.md
По результатам проведения тестирования: успешных тестов - 58.82%, неуспешных - 41.18%.
Для неуспешных тестов были заведены [баг-репорты] (https://github.com/Nightfox87/QAProject/issues).

