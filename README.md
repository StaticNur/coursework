# Курсовая работа 2023 - 2024 гг. (3 курс)

# Тема: Сервлет (Java) 
(пока четкой формулировки темы не известна)

## Задача №3 (infiniteScrollingOfLists)

Реализовать функционал бесконечной ленты.

### Технологии
- Java 17
- Jakarta EE
- Tomcat 10
- База данных MongoDB (по умолчанию содержит 10 элементов)
- Docker
- maven

### Процесс сборки и запуск проекта
1. Загружаем проект к себе
2. Заходим в терминал и вводим команду ``` docker-compose up -d``` для поднятия контейнера с бд
3. Собрать приложение: ``` mvn clean package ```
4. Запустить через контейнер сервлетов Tomcat не ниже 10 версии

---

### Эндпоинты

GET ``` http://localhost:8080/infinite-scroll?page=<Какая-Страница>&size=<Количество-Страниц> ```

Возвращает новую партию страничек.
``` 
[
    {
        "fileName": "videoNum0",
        "fileSizeFromByte": 1.0,
        "videoBytes": "c3ty0tht53404jfd923jd2e0"
    },
    {
        "fileName": "videoNum1",
        "fileSizeFromByte": 2.0,
        "videoBytes": "c3ty10101010tht534101010104jfd923jd2e10101010"
    }
]
``` 

Возможные ошибки в запросе:
``` 
{
    "message": "Parameters must not be empty."
}
``` 
``` 
{
    "message": "Parameters must be numbers."
}
``` 
``` 
{
    "message": "Parameters must not be negative."
}
``` 



