@casino
Feature: casino-ui-test

  Scenario Outline: войти в систему с валидными учетными данными
    Given открыть главную страницу
    When ввести login admin1 и password [9k<k8^z!+$$GkuP
    Then пользователь admin1 успешно авторизован
    When нажать на выпадающее меню Users, перейти в Players
    Then успешно открылась страница PLAYER MANAGEMENT
    When выбрать "<столбец>"
    Then таблица верно отсортирована по выбранному столбцу

    Examples:
      | столбец           |
      | Username          |
      | External ID       |
      | Name              |
      | Last name         |
      | E-mail            |
      | Phone             |
      | Hall              |
      | Registration date |
      | Last visit        |
      | Verified player   |
      | Status            |



