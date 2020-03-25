/**
 * IconToolbar
 */
package com.kaleyra.academy.sudoku.ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.RescaleOp;

public class IconToolbar extends JToolBar {
    protected Color buttonLine = new Color(185, 185, 185);
    protected Color buttonBackground = new Color(219, 219, 219);

    public IconToolbar() {
        setFloatable(false);
        setBackground(Color.WHITE);
        setBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, buttonLine));
        addSeparator(new Dimension(7, 10));
    }

    public JButton createButton(String text, String iconName) {
        JButton button = new JButton(" " + text + " ");
        ImageIcon icon = getIcon(iconName);

        button.setIcon(icon);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setVerticalTextPosition(AbstractButton.BOTTOM);
        button.setHorizontalTextPosition(AbstractButton.CENTER);
        button.setBackground(buttonBackground);
        button.setIconTextGap(0);

        Border border1 =
                BorderFactory.createMatteBorder(0, 1, 0, 1, buttonLine);
        Border border2 =
                BorderFactory.createMatteBorder(1, 0, 1, 0, buttonBackground);
        Border border3 =
                BorderFactory.createEmptyBorder(5, 0, 2, 0);
        Border compoundBorder1 =
                BorderFactory.createCompoundBorder(border1, border2);
        Border border =
                BorderFactory.createCompoundBorder(compoundBorder1, border3);
        button.setBorder(border);

        Image img = icon.getImage();
        ImageIcon pressedIcon = new ImageIcon(
                createSelectedIcon(img));
        button.setPressedIcon(pressedIcon);

        add(button);

        return button;
    }

    private Image createSelectedIcon(Image image) {
        ImageObserver observer = new ImageObserver() {
            public boolean imageUpdate(Image image, int infoflags, int x, int y, int width, int height) {
                return (infoflags & ImageObserver.ALLBITS) == 0;
            }
        };
        int width = image.getWidth(observer);
        int height = image.getHeight(observer);

//		BufferedImage img1 = (BufferedImage)createImage(width, height);
//		Graphics g = img1.getGraphics();
//		g.drawImage( image, width, height, observer );

        BufferedImage img1 = new BufferedImage(image.getWidth(null),
                image.getHeight(null),
                BufferedImage.TYPE_INT_RGB);
        Graphics g = img1.getGraphics();
        g.drawImage(image, 0, 0, null);

        BufferedImage img2 = new BufferedImage(
                width, height,
                BufferedImage.TYPE_INT_RGB);

        RescaleOp rop = new RescaleOp(0.8f, -1.0f, null);
        rop.filter(img1, img2);

        return img2;
    }

    private ImageIcon getIcon(String name) {
        return new ImageIcon(getClass().getResource(name));
    }

}
