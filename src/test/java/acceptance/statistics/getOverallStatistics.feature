Feature: Get overall statistics of url-shortener.

  Background:
    * url baseUrl

  Scenario: Get the overall statistic.

    Given path '/v1/statistics'
    When method get
    Then status 200
    And match response == {totalNumberOfCalls: '#number', averageForwardDurationInMillis: '#number', timeOfLastCall: '#ignore'}



