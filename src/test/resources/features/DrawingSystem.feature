Feature: Drawing System

  Scenario: Should Create empty canvas
    Given no canvas
    When a drawing command C 20 4 is issued
    Then canvas will look like
      | ____________________ |
      | ____________________ |
      | ____________________ |
      | ____________________ |

  Scenario: Should draw line
    Given empty canvas 20x4
    When a drawing command L 1 2 6 2 is issued
    Then canvas will look like
      | ____________________ |
      | xxxxxx______________ |
      | ____________________ |
      | ____________________ |

