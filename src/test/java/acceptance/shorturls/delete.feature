Feature: Delete shortened urls

  Background:
    * url baseUrl

  Scenario: Delete a shortened url.

    Given path '/v1/shorturls'
    And request { url: 'https://google.ch' }
    And header Accept = 'application/json'
    When method post
    Then status 201
    * def firstResponse = response
    And match firstResponse == {id: '#string', shortUrl: '#string', url: 'https://google.ch'}

    Given path '/v1/shorturls/' + firstResponse.id
    When method delete
    Then status 204
