Feature: Создание аккаунта

  Scenario Outline: Проверка валидации длины параметра "account_name"
    Given Созданы данные аккаунта с длинной параметра account_name "<accountNameLength>"
    When Отправлен запрос на создание аккаунта с подготовленными данными
    Then Проверка успешности создания аккаунта, result = "<result>"

    Examples:
      | accountNameLength | result |
      |         0         |    0   |
      |         2         |    0   |
      |         4         |    0   |
      |         5         |    1   |
      |         6         |    1   |
      |        10         |    1   |
      |        19         |    1   |
      |        20         |    1   |
      |        21         |    0   |
      |        30         |    0   |

  Scenario Outline: Проверка валидации длины параметра "account_password"
    Given Созданы данные аккаунта с длинной параметра account_password "<accountPasswordLength>"
    When Отправлен запрос на создание аккаунта с подготовленными данными
    Then Проверка успешности создания аккаунта, result = "<result>"

    Examples:
      | accountPasswordLength | result |
      |           7           |    0   |
      |           8           |    1   |
      |           9           |    1   |
      |          15           |    1   |

  Scenario Outline: Проверка набора валют "currency", доступных для аккаунта аккаунта
    Given Созданы данные аккаунта со значением параметра currency = "<currency>"
    When Отправлен запрос на создание аккаунта с подготовленными данными
    Then Проверка успешности создания аккаунта, result = "<result>"

    Examples:
      | currency | result |
      |    USD   |    1   |
      |    RUR   |    1   |
      |    EUR   |    1   |
      |    KZT   |    1   |
      |    NON   |    0   |
