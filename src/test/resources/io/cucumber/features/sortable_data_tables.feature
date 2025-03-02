Feature: Sortable Data Tables
  This feature covers (some) Example pages on 'the-internet.herokuapp.com'



  @TEST_TI_0003 @regression @data_table
  Scenario: Verify Example 1 Table Displays Expected Entries
    Given the page under test is 'https://the-internet.herokuapp.com'
    When the user clicks on the "Sortable Data Tables" link
    And the user extracts data from the "Example 1" table
    Then the extracted table data should match the expected entries:
      | Last Name | First Name | Email                   | Due     | Web Site                      |
      | Smith     | John       | jsmith@gmail.com        | $50.00  | http://www.jsmith.com         |
      | Bach      | Frank      | fbach@yahoo.com         | $51.00  | http://www.frank.com          |
      | Doe       | Jason      | jdoe@hotmail.com        | $100.00 | http://www.jdoe.com           |
      | Conway    | Tim        | tconway@earthlink.net   | $50.00  | http://www.timconway.com      |



