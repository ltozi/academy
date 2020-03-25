/**
 * HistoryViewFrame
 */
package com.kaleyra.academy.sudoku.ui;

import com.kaleyra.academy.sudoku.controller.commands.AbstractCommand;
import com.kaleyra.academy.sudoku.controller.commands.InsertValueCommand;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//TODO
public class HistoryViewFrame extends JFrame {

    JList list;
    MyListModel model;
    List data = new ArrayList();

    public HistoryViewFrame() {
        super("Mosse svolte");

        setResizable(false);
        getContentPane().setLayout(new BorderLayout());
        model = new MyListModel();
        list = new JList(model);
        getContentPane().add(new JScrollPane(list),
                BorderLayout.CENTER);

        setSize(100, 250);
        setLocation(420, 0);
    }

    public void update(Iterator iter) {
        data.clear();
        while (iter.hasNext()) {
            AbstractCommand command = (AbstractCommand) iter.next();
            if (command instanceof InsertValueCommand) {
                InsertValueCommand ic = (InsertValueCommand) command;
                data.add(ic.getDescription());
            }
        }
        model.fireDataChanged();
    }

    class MyListModel extends AbstractListModel {

        public MyListModel() {
        }

        public int getSize() {
            return data.size();
        }

        public Object getElementAt(int index) {
            return data.get(index);
        }

        public void fireDataChanged() {
            fireContentsChanged(list, 0, data.size());
        }
    }
}
