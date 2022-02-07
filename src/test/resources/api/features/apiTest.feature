@casino
@api
@Enabled
Feature: casino-api-test

  Scenario: авто тесты с Rest Assured
    When Получить токен гостя username: front_2d6b0a8391742f5d789d7d915755e09e, password: admin1
    Then Гость авторизован
    When Зарегистрировать игрока
    Then Игрок зарегистрирован
    When Авторизоваться под созданным игроком
    Then Игрок авторизован
    When Запросить данные профиля игрока
    Then Найден игрок
    When Запросить данные другого игрока
    Then Игрок не найден
