Feature: Airtable API Tests

  @api
  Scenario: Verify status code
    When a user calls a GET endpoint
    Then user will receive status "200"