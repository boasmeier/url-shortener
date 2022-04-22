Feature: Create shortened urls

  Background:
    * url baseUrl

  Scenario: Create a shortened url. For same urls should always the same shortened url be returned.

    Given path '/create'
    And request { url: 'https://google.ch' }
    And header Accept = 'application/json'
    When method post
    Then status 201
    * def firstResponse = response
    And match firstResponse == {url: 'https://google.ch', shortenedUrl: '#string'}

    Given path '/create'
    And request { url: 'https://google.ch' }
    And header Accept = 'application/json'
    When method post
    Then status 201
    And match response == firstResponse

