Feature: Drawing System

  Scenario: Should Create empty canvas
    Given no canvas
    When a drawing command C 5 4 is issued
    Then canvas will look like
      | _ _ _ _ _ _ |
      | _ _ _ _ _ _ |
      | _ _ _ _ _ _ |
      | _ _ _ _ _ _ |

