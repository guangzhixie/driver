Feature: Find driver
  Tests find driver

  Scenario: when request with missing lat or long, return 400
    When find driver
      | latitude |
      | 10       |
    Then response status code should be 400

    When find driver
      | longitude |
      | 10        |
    Then response status code should be 400

  Scenario: when request with valid location info, return 200
    Given update location for driver 1
      | latitude | longitude | accuracy |
      | 10       | 20        | 0.7      |
    When find driver
      | latitude | longitude |
      | 10       | 20        |
    Then response status code should be 200

