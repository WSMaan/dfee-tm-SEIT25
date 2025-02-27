Feature: The Internet
  This feature covers (some) Example pages on 'the-internet.herokuapp.com'

  @TEST_TI_0001 @links_verification
  Scenario: Homepage has a list of links to Expected examples
    Given the page under test is 'https://the-internet.herokuapp.com'
    When I extract the list of links from the homepage
    Then the list should contain the expected links

#  @TEST_TI_0001
#  Scenario: Basic Auth allows validated access
#    Given the page under test is 'https://the-internet.herokuapp.com'
#    When the 'Basic Auth' example is opened
#    And valid credentials are supplied
#    Then Congratulations should be displayed
#
#  @TEST_TI_0001
#  Scenario: Sortable Data Tables - Example 1 displays the expected 4 results
#    Given the page under test is 'Sortable Data Tables'
#    And add other steps