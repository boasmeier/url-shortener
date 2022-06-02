Feature: Redirect over shortened urls

  Background:
    * url baseUrl

  Scenario: Redirect over a shortened url.

    Given path '/v1/shorturls'
    And request { url: 'https://google.ch' }
    And header Accept = 'application/json'
    When method post
    Then status 201
    * def firstResponse = response
    And match firstResponse == {id: '#string', shortUrl: '#string', url: 'https://google.ch', redirectionTimeout: '#number'}

    Given path '/r/' + firstResponse.shortUrl
    And header Accept = 'application/json'
    When method get
    Then status 200

