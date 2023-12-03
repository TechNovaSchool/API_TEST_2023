Feature: Airtable API Tests

  @api
  Scenario: Verify status code
    When a user calls a GET endpoint
    Then user will receive status "200"

  @verifyFirstName
  Scenario: Verify status code for a get single record endpoint
    When a user calls a GET endpoint for a single record
    Then user will receive status 200
    And user verifies the first name

  @crudOp
  Scenario: Verify status code for a new record
    When a user calls a POST endpoint for a new record
    Then user will receive status 200
    And user extracts and saves the recordID from the response
    When a user calls a PATCH endpoint to update the email field
    Then user will receive status 200
    When a user calls a DELETE endpoint to delete the record
    Then user will receive status 200

  @negativeScenario
  Scenario: Create a request with wrong request body
    When user creates a record with incorrect payload
    Then user will receive status 422

  @outline
  Scenario Outline: Verify POST method with custom variables
    Given user has "<firstName>" "<lastName>" "<email>" <age>
    When a user calls a POST endpoint with custom data
    Then user will receive status 200

    Examples:
      | firstName | lastName | email        | age |
      | Tim       | Rogan    | joe@mail.com | 99  |
      | Andrew    | Test     | Test         | 99  |
      | Bruce     | Lee      | Test         | 99  |



  ##
#**Task**:
#1. **Create a Record**:
#- Use the POST method to create a new record.
#- Validate that the server returns a status code of `200 OK`.
#- Extract and save the `recordID` from the response.
#
#2. **Update the Record**:
#- Use the PUT method to update the email field of the created record, identified by `recordID`.
#- Validate that the server returns a status code of `200 OK`.
#
#3. **Delete the Record**:
#- Use the DELETE method to remove the record identified by `recordID`.
#- Validate that the server returns a status code of `200 OK`.
#
##### Instructions
#- Implement the above scenario using Java and Rest Assured.
#- Write Cucumber feature files with appropriate Gherkin syntax for each step.
#- Ensure your tests handle response validation

#

  Task
#Feature: API response validation
#  Scenario: Validate response from API
    When I request data from the GET API
    Then the response status should be 200
    And the response should match the expected schema
