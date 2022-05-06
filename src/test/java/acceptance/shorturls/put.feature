Feature: Put shortened urls

  Background:
    * url baseUrl

  Scenario: Put a shortened url.

    Given path '/v1/shorturls'
    And request { url: 'https://google.ch' }
    And header Accept = 'application/json'
    When method post
    Then status 201
    * def firstResponse = response
    And match firstResponse == {id: '#string', shortUrl: '#string', url: 'https://google.ch'}

    Given path '/v1/shorturls/' + firstResponse.id
    And request { id: '#(firstResponse.id)', shortUrl: '#(firstResponse.shortUrl)', url: 'https://www.google.ch' }
    And header Accept = 'application/json'
    When method put
    Then status 200
    And match response == { id: '#(firstResponse.id)', shortUrl: '#(firstResponse.shortUrl)', url: 'https://www.google.ch' }
