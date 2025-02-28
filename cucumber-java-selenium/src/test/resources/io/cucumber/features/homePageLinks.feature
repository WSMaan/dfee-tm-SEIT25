Feature: HomePage Links Verification
  This feature covers (some) Example pages on 'the-internet.herokuapp.com'

  @TEST_TI_0001 @smoke @regression
  Scenario: Homepage has a list of links to Expected examples
    Given the page under test is 'https://the-internet.herokuapp.com'
    When I extract the list of links from the homepage
    Then the list should contain the expected links
