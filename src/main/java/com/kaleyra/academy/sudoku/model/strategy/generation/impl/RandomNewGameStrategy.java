/**
 * RandomNewGameStrategy
 */
package com.kaleyra.academy.sudoku.model.strategy.generation.impl;

import com.kaleyra.academy.sudoku.model.GameModel;
import com.kaleyra.academy.sudoku.model.strategy.generation.NewGameStrategy;
import com.kaleyra.academy.sudoku.model.strategy.validation.SudokuValidationStrategy;
import com.kaleyra.academy.sudoku.model.strategy.validation.impl.DefaultValidationStrategy;
import com.kaleyra.academy.sudoku.utils.SudokuException;

import java.util.*;

/**
 * Crea un gioco utilizzando numeri casuali
 */
public abstract class RandomNewGameStrategy implements NewGameStrategy {

    private SudokuValidationStrategy validationStrategy = new DefaultValidationStrategy();
    private DefaultValidationStrategy validationStrategy2 = new DefaultValidationStrategy();

    /**
     * Template method
     *
     * @return un gioco generato casualmente
     */

    public void printSudokuFullTable2(){

        int[][] matrix = new int[9][9];

        int[] matrixIndices = new int[2];
        CellValueFinder2 cvf= new CellValueFinder2(matrix);

        boolean sense = true;
        int k = 0;
        do{
            if (k < 0 )
                k = 0;
            matrixIndices = validationStrategy2.linerToMatrixIndices(k, GameModel.COLS);
            if (cvf.setCellValue(matrixIndices[0], matrixIndices[1],   sense  )) { // returns true if a valid value was found and set in the table successfully. False otherwise
                //System.out.println("- matrix after elemnt "+k+" is set-");
                //cvf.print();
                k++;
                //System.out.println();
                if(sense == false)
                    sense = true;
            } else {
                //System.out.println("- matrix after element "+k+" is set-");
                //System.out.println("Impossible to find value for element " + k +". Going back one cell ...\n");
                k--;
                sense = false;
            }

        }while (k < GameModel.ROWS * GameModel.COLS);

        cvf.print();

    }



    public GameModel createModel() throws SudokuException { //non toccare camilo
        GameModel gameModel = new GameModel();

        int[][] matrix = gameModel.getData();

        /*
        for (int row = 0; row < matrix.length; row++) {
            Set<Integer> alreadyUsedValues = new HashSet<>(); //Empty
            for (Integer col = 0; col < matrix[row].length; col++) {
                CellValueFinder valueFinder = new CellValueFinder(matrix, row, col, alreadyUsedValues).build();
                row = valueFinder.row;
                col = valueFinder.col;
                matrix[row][col] = valueFinder.random;
            }
        }
        */
        int[] matrixIndices = new int[2];
        CellValueFinder2 cvf= new CellValueFinder2(matrix);
        boolean sense = true;
        int k = 0;
        do{
            if (k < 0 )
                k = 0;
            matrixIndices = validationStrategy2.linerToMatrixIndices(k, GameModel.COLS);
            if (cvf.setCellValue(matrixIndices[0], matrixIndices[1],   sense  )) { // returns true if a valid value was found and set in the table successfully. False otherwise
                //System.out.println("- matrix after elemnt "+k+" is set-");
                //cvf.print();
                k++;
                //System.out.println();
                if(sense == false)
                    sense = true;
            } else {
                //System.out.println("- matrix after element "+k+" is set-");
                //System.out.println("Impossible to find value for element " + k +". Going back one cell ...\n");
                k--;
                sense = false;
            }

        }while (k < GameModel.ROWS * GameModel.COLS);

        //cvf.print();
        matrix = cvf.getMatrix();


        if (!validationStrategy.isValidModel(matrix)) {
            throw new SudokuException("Wrong random model generation: " + "\n" + gameModel.toString());
        }

        return setGameDifficulty(gameModel);
        //return gameModel;



    }

    /**
     * Subclass must define a way to hide cells handling game difficulty
     *
     * @return
     */
    public abstract GameModel setGameDifficulty(final GameModel model);

    /**
     * @return a Set with all the number available
     */

    // do camlilo
    // queli da cui estrarreil random value
    public Set<Integer> allowedValues() {
        return new HashSet<>(); //TODO logic
    }

    /**
     * @param allowedNumbers is decreased in size on each extraction
     * @return a Set with all the number available
     */

    // do camilo
    public Integer generateRandomValue(Set<Integer> allowedNumbers) { //argument from Set<Integer> allowedValues()
        return 0;
    }

    public class hashSet{ //make anonymous class instead ??
        public HashSet<Integer> hs = new HashSet<Integer>();
//        public hashSet(){
//            hs = new HashSet<Integer>();
//            hs.add(0);
//        }

    }

    public class CellValueFinder2 {
        private int[][] matrix;

        private HashSet<Integer> ALLOWED_VALUES = new HashSet<>();


        //81 hash sets corresponding to each cell of the sudoku table
        private hashSet[] forbiddenElementsPerCell;// = new hashSet[GameModel.ROWS * GameModel.COLS];

        CellValueFinder2(int[][] matrix) {
            this.matrix = matrix;
            ALLOWED_VALUES.addAll(SudokuValidationStrategy.ALLOWED_VALUES);

            forbiddenElementsPerCell = new hashSet[GameModel.ROWS * GameModel.COLS];
            for (int i =0; i< forbiddenElementsPerCell.length; i++)
                forbiddenElementsPerCell[i] = new hashSet();
        }

        // sense is the "sense" in which we arrived to the cell
        public boolean setCellValue(int i, int j, boolean sense){  //true -> 1, false-> -1
            int elementLinearIndex = validationStrategy2.matrixToLinearIndex(i,j,matrix[0].length);
            if(sense == false){ //arrived in negative sense, i.e. coming back to this cell
                forbiddenElementsPerCell[elementLinearIndex].hs.add(matrix[i][j]);
            }else{
                //forbiddenElementsPerCell[elementLinearIndex].hs.add(0); //before calling clear it must contain some element
                forbiddenElementsPerCell[elementLinearIndex].hs.clear();
                //forbiddenElementsPerCell[elementLinearIndex].hs.add(0); //before calling clear it must contain some element

            }

            HashSet<Integer> rowOrColummnElementsExclusive  = validationStrategy2.getElementsInRowOrColumnExclusive(matrix, i, j);
            HashSet<Integer> quadrantElementsExclusive = validationStrategy2.getElementsInQuadrantOfElementExclusive(matrix, i, j);
            HashSet<Integer> allNotAllowedValues = validationStrategy2.getUnion(rowOrColummnElementsExclusive, quadrantElementsExclusive);



            HashSet<Integer> possibleValues = validationStrategy2.getRelativeComplement(ALLOWED_VALUES, allNotAllowedValues );
            possibleValues.remove(0); // we don't want to set the cell with 0
            possibleValues.removeAll(forbiddenElementsPerCell[elementLinearIndex].hs);


            List<Integer> possibleValuesList = new ArrayList<>(possibleValues);
            Collections.shuffle(possibleValuesList);

            //System.out.println("possible values are: " + possibleValuesList.toString());

            if (possibleValuesList.size() > 0 ){
                matrix[i][j] = possibleValuesList.get(0);
                return true;
            }

            return false;
        }

        public void print(){
            int i,j;
            for ( i = 0 ; i < 9; i++){
                for ( j = 0; j < 9; j++)
                    System.out.print(matrix[i][j] + "  ");
                System.out.println();
            }
        }

        public int[][] getMatrix(){
            return matrix;
        }


    }






    /**
     * A helper class that encapsulate logic to find a dynamic value for each cell
     */
    public class CellValueFinder {
        private int[][] matrix;
        private Integer row;
        private Set<Integer> allowedNumbers = allowedValues();
        private Set<Integer> alreadyUsedValues;
        private Integer col;
        private Integer random;

        CellValueFinder(int[][] matrix, Integer row, Integer col, Set<Integer> alreadyUsedValues) {
            this.matrix = matrix;
            this.row = row;
            this.alreadyUsedValues = alreadyUsedValues;
            this.col = col;
        }

        CellValueFinder build() {
            do {
                random = generateRandomValue(allowedNumbers);

                if (random == null) {//This happens because elements are escaped
                    allowedNumbers = allowedValues();

                    goBackOneCell();

                    alreadyUsedValues.add(matrix[row][col]);
                    allowedNumbers.removeAll(alreadyUsedValues); //Exclude already used numbers
                    matrix[row][col] = 0;//
                    random = generateRandomValue(allowedNumbers); //Generate

                    if (random == null) {
                        allowedNumbers = allowedValues();
                        alreadyUsedValues.removeAll(allowedNumbers); //Try again every number
                        random = generateRandomValue(allowedNumbers);
                    }
                }
            }
            while (!validationStrategy.isValidMove(matrix, row, col, random));

            return this;
        }

        private void goBackOneCell() {
            if (col > 0)
                col--;
            else {
                row--;
                col = matrix.length; //Backtracking till correct solutions exists
            }
        }
    }




}
