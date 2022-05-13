Feature: Get statistics of shortened urls

  Background:
    * url baseUrl

  Scenario: Get the statistics of a shortened url.

    Given path '/v1/shorturls'
    And request { url: 'https://google.ch' }
    And header Accept = 'application/json'
    When method post
    Then status 201
    * def firstResponse = response
    And match firstResponse == {id: '#string', shortUrl: '#string', url: 'https://google.ch'}

    Given path '/v1/shorturls/' + firstResponse.id + '/statistics'
    When method get
    Then status 200
    And match response == { totalNumberOfCalls: 0, averageForwardDurationInMillis: 0, timeOfLastCall: null }
