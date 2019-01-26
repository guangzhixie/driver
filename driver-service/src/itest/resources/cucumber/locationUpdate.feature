Feature: Location update
  Tests driver location update

  Scenario: update valid location, return 200
    When update location for driver 1
      | latitude | longitude | accuracy |
      | 10       | 20        | 0.7      |
    Then response status code should be 200

  Scenario: update location with invalid driver id, return 404
    When update location for driver 10000000
      | latitude | longitude | accuracy |
      | 10       | 20        | 0.7      |
    Then response status code should be 404

  Scenario: update location with invalid accuracy, return 422
    When update location for driver 1
      | latitude | longitude |
      | 10       | 20        |
    Then response status code should be 422

  Scenario: update location with invalid lat or long, return 422
    When update location for driver 1
      | latitude | longitude | accuracy |
      | 100      | 20        | 0.7      |
    Then response status code should be 422

    When update location for driver 1
      | latitude | longitude | accuracy |
      | 10       | 200       | 0.7      |
    Then response status code should be 422