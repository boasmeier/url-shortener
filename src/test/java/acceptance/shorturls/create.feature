Feature: Create shortened urls

  Background:
    * url baseUrl

  Scenario: Create a shortened url. For same urls should always the same shortened url be returned.

    Given path '/v1/shorturls'
    And request { url: 'https://google.ch' }
    And header Accept = 'application/json'
    When method post
    Then status 201
    * def firstResponse = response
    And match firstResponse == {id: '#string', shortUrl: '#string', url: 'https://google.ch', redirectionTimeout: '#number'}

    Given path '/v1/shorturls'
    And header Accept = 'application/json'
    When method get
    Then status 200
    And match response[0] == firstResponse

    Given path '/v1/shorturls'
    And request { url: 'https://google.ch' }
    And header Accept = 'application/json'
    When method post
    Then status 201
    And match response == firstResponse

