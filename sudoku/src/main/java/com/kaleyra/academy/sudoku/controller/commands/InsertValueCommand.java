/**
 * InsertValue
 */
package com.kaleyra.academy.sudoku.controller.commands;

import com.kaleyra.academy.sudoku.controller.GameController;
import com.kaleyra.academy.sudoku.model.GameModel;

/**
 * Inserisce il valore in una cella
 *
 * @pattern Command
 */
public class InsertValueCommand extends AbstractCommand {

    /**
     * riga in cui inserire il valore
     */
    private int row;

    /**
     * colonna in cui inserire il valore
     */
    private int col;

    /**
     * valore da inserire
     */
    private Integer value;

    /**
     * precedente valore della cella
     */
    private Integer oldValue;

    /**
     * @param row
     * @param col
     * @param value
     */
    public InsertValueCommand(int row, int col, Integer value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }

    /**
     * Inserisce il valore nella cella
     */
    public boolean doCommand() {
        GameModel model = GameController.getInstance().getModel();

        //salva il vecchio valore della cella per l'undo
        oldValue = model.getCellValue(row, col);

        //assegna il nuovo valore
        model.setCellValue(row, col, value);

        //rinfresca la vista
        super.doCommand();

        return true;
    }

    /**
     * Ripristina il vecchio valore della cella
     */
    public boolean undoCommand() {
        GameModel model = GameController.getInstance().getModel();
        model.setCellValue(row, col, oldValue);

        //rinfresca la vista
        super.doCommand();
        return true;
    }

    /**
     * @return colonna
     */
    public int getCol() {
        return col;
    }

    /**
     * @return riga
     */
    public int getRow() {
        return row;
    }

    /**
     * @return valore
     */
    public Integer getValue() {
        return value;
    }

    /**
     * @return una rappresentazione testuale dell'oggetto
     */
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(getClass().getName());
        sb.append("=[");
        sb.append("col=");
        sb.append(col);
        sb.append(", row=");
        sb.append(row);
        sb.append(", value=");
        sb.append(value);
        sb.append(", oldValue=");
        sb.append(oldValue);
        sb.append("]");
        return sb.toString();
    }

    /**
     * @return descrizione per l'utente di questo comando
     */
    public Object getDescription() {
        StringBuffer sb = new StringBuffer();
        sb.append("(");
        sb.append(row);
        sb.append(", ");
        sb.append(col);
        sb.append(") <- ");
        sb.append(value);
        return sb.toString();
    }

}
