@apis
Feature: Get todo apis

  As a consumer I want to Access the todo APIs
  So that I get all to todos

  @todos
  Scenario Outline: API_ToDo_01 # Verify API response codes for valid and invalid resources.
    Given as a user I want to access todo Apis
    When I make GET request to the <Resource>
    Then I should get the response code <ResponseCode>

  Examples:
    |Resource   |ResponseCode |
    |todos      |200          |
    |ttodos     |404          |
    |todoss     |404          |

  Scenario Outline: API_ToDo_03 # Verify Todo GET request returns 200 todos.
    Given as a user I want to access todo Apis
    When I make GET request to the <Resource>
    Then I should get the response code <ResponseCode>
    And I should get 200 todos

    Examples:
      |Resource   |ResponseCode | AddToDOJsonBody |
      |todos      |200          | PostTodo1.json  |


  @todos
  Scenario Outline: API_ToDo_04 # Post a todo and verify it's sucessfully created.
    Given as a user I want to add a todo
    When I make POST request to resource <Resource> with Body <AddToDOJsonBody>
    Then I should get the response code <ResponseCode>
    When I make GET request to the <Resource>
    Then I should get the above todo created

    Examples:
      |Resource   |ResponseCode | AddToDOJsonBody |
      |todos      |201          | PostTodo1.json  |

  @todos
  Scenario Outline: API_ToDo_05 # Update/PUT a Doto verify it's updated sucessfully.
    Given as a user I want to update a todo
    When I make PUT request to resource <Resource> with Body <UpdateToDOJsonBody>
    Then I should get the response code <ResponseCode>
    When I make GET request to the <Resource>
    Then I should get the todo updated json body <UpdateToDOJsonBody>

    Examples:
      |Resource      |ResponseCode | UpdateToDOJsonBody |
      |todos/25      |201          | PutTodo25.json  |

  @todos
  Scenario Outline: API_ToDo_06 # Verify user can get a todo.
    Given as a user I want to access todo Apis
    When I make GET request to the <Resource>
    Then I should get the response code <ResponseCode>
    And I should get the todo updated json body <UpdateToDOJsonBody>

    Examples:
      |Resource      |ResponseCode | UpdateToDOJsonBody |
      |todos/25      |200          | GetTodo25.json  |

  Scenario Outline: API_ToDo_07 # Verify user can Delete a todo.
    Given as a user I want to access todo Apis
    When I make DELETE request to the <Resource>
    Then I should get the response code <ResponseCode>
    When I make GET request to the <Resource>
    Then I should get the response code <AfterResponseCode>

    Examples:
      |Resource      |ResponseCode | AfterResponseCode |
      |todos/25      |200          | 404               |