Feature: Search on Booking.com

  Scenario: Search by city criteria
    Given User is looking for 'Minsk' city
    When User does search
    Then Hotel 'Crowne Plaza Minsk Hotel' should be on the first page
    And Rating of the hotel 'Crowne Plaza Minsk Hotel' is '7,8'

  Scenario: Search by city criteria
    Given User is looking for 'Брест' city
    When User does search
    Then Hotel 'Отель ЭРМИТАЖ' should be on the first page
    And Rating of the hotel 'Отель ЭРМИТАЖ' is '9,3'