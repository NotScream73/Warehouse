# Тестовое CRUD-приложение для склада товаров

## Инструкция по запуску приложения
Склонируйте репозиторий:

git clone https://github.com/NotScream73/Warehouse.git

Перейдите в директорию проекта:

cd warehouse

Переключитесь на нужную ветку:

git checkout develop

Настройте базу данных PostgreSQL:

Создайте базу данных в PostgreSQL и настройте файл application.properties.

Запустите приложение, прописав команду:

./gradlew bootRun -Pprofile=prod

Swagger доступен по ссылке:

http://localhost:8080/swagger-ui/index.html