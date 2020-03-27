# Sudoku

This is a Sudoku app

## How to start game

- Please note that button New game is not working because Step 1 must be implemented

- Main class:
[com.kaleyra.academy.sudoku.Sudoku.java](https://github.com/ltozi/academy/blob/55898181fe33cb7dfaa1c4f1ed7d29eef2446f49/src/main/java/com/kaleyra/academy/sudoku/Sudoku.java)

## Todo

- ### Step 1
    Sudoku board is loaded from a file but the logic must be implemented inside file:
    
    [com.kaleyra.academy.sudoku.model.ModelDAO](https://github.com/ltozi/academy/blob/55898181fe33cb7dfaa1c4f1ed7d29eef2446f49/src/main/java/com/kaleyra/academy/sudoku/model/ModelDAO.java#L74)

    Your code should be implemented at line 94 (see //TODO...) 
    
    This is the file containing a model for Sudoku board:
    
    [game1.sudoku](https://github.com/ltozi/academy/blob/55898181fe33cb7dfaa1c4f1ed7d29eef2446f49/src/main/resources/game1.sudoku)
    
    Please note that the first two lines inside files should be ignored. Here how to read the record:
    
    P;0;2;8 <----- means P(redefined cell ) with value 8 placed inside cell with row 0 and column 2  
    
    Each predefined cell must be loaded inside the matrix at line 76:
    
    ```java
    int[][] predefinedCells =
                        new int[GameModel.ROWS][GameModel.COLS];
    ```
    
             

- ### Step 2
    Junit test [DefaultValidationStrategyTest.java](https://github.com/ltozi/academy/blob/55898181fe33cb7dfaa1c4f1ed7d29eef2446f49/src/test/java/com/kaleyra/academy/sudoku/model/DefaultValidationStrategyTest.java) is broken and we have to implement logic in class
    com.kaleyra.academy.sudoku.model.strategy.validation.impl.DefaultValidationStrategy to let it pass.
    
- ### Step 3 (bonus)
    Find a way to switch game generation strategy with a config file 
    
- ### Step 4 (bonus)
    A new requirement exists: Changing the game difficulty for the random generated model. This
    is now set by default set to MEDIUM in the class below.
    
    [SimpleGenerationStrategy.java](https://github.com/ltozi/academy/blob/55898181fe33cb7dfaa1c4f1ed7d29eef2446f49/src/main/java/com/kaleyra/academy/sudoku/model/strategy/generation/impl/SimpleGenerationStrategy.java#L18)
    
    Note: For the sake of simplicity, game difficulty is supposed to change with the number of 
    hidden cells inside sudoku board. 
    For example, a game is considered easy when the number of predefined cells are is high.  
    
    Try to refactor this code to switch game difficulty from a property set inside file that
    can have three values: EASY, MEDIUM, HARD

## Documentation

- Uml class diagram: 
[docs/sudoku-class-diagram.pdf](https://github.com/ltozi/academy/blob/55898181fe33cb7dfaa1c4f1ed7d29eef2446f49/docs/sudoku-class-diagram.pdf)

