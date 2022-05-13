Feature: Get index.html template.

  Background:
    * url baseUrl

  Scenario: Get index.html template.

    Given path '/'
    When method get
    Then status 200

