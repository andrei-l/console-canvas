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

  Scenario: Should draw another line
    Given empty canvas 20x4
    When a drawing command L 1 2 6 2 is issued
    And a drawing command L 6 3 6 4 is issued
    Then canvas will look like
      | ____________________ |
      | xxxxxx______________ |
      | _____x______________ |
      | _____x______________ |

  Scenario: Should draw rectangle
    Given empty canvas 20x4
    When a drawing command L 1 2 6 2 is issued
    And a drawing command L 6 3 6 4 is issued
    And a drawing command R 16 1 20 3 is issued
    Then canvas will look like
      | _______________xxxxx |
      | xxxxxx_________x___x |
      | _____x_________xxxxx |
      | _____x______________ |

  Scenario: Should fill area not covered by any element
    Given empty canvas 20x4
    When a drawing command L 1 2 6 2 is issued
    And a drawing command L 6 3 6 4 is issued
    And a drawing command R 16 1 20 3 is issued
    And a drawing command B 10 3 o is issued
    Then canvas will look like
      | oooooooooooooooxxxxx |
      | xxxxxxooooooooox___x |
      | _____xoooooooooxxxxx |
      | _____xoooooooooooooo |

  Scenario: Should fill area covered by lines
    Given empty canvas 20x4
    When a drawing command L 1 2 6 2 is issued
    And a drawing command L 6 3 6 4 is issued
    And a drawing command R 16 1 20 3 is issued
    And a drawing command B 6 3 f is issued
    Then canvas will look like
      | _______________xxxxx |
      | ffffff_________x___x |
      | _____f_________xxxxx |
      | _____f______________ |
