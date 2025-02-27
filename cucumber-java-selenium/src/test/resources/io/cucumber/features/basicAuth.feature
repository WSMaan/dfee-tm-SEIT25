Feature: Basic Auth Login
  This feature covers (some) Example pages on 'the-internet.herokuapp.com'

#  @TEST_TI_0001
#  Scenario: Homepage has a list of links to Expected examples
#    Given the page under test is 'https://the-internet.herokuapp.com'
#    And an example Home Page step
#    And add other steps

  @TEST_TI_0002 @regression @login
  Scenario: Basic Auth allows validated access
    Given the page under test is 'https://admin:admin@the-internet.herokuapp.com/basic_auth'
    Then the user should see a success message




#  @TEST_TI_0003
#  Scenario: Sortable Data Tables - Example 1 displays the expected 4 results
#    Given the page under test is 'Sortable Data Tables'
#    And add other steps