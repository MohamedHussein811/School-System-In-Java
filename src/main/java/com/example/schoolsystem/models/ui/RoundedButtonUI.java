package com.example.schoolsystem.models.ui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class RoundedButtonUI extends BasicButtonUI {
    private static final int ARC_RADIUS = 15; // Adjust this value for different levels of border radius

    @Override
    protected void paintButtonPressed(Graphics g, AbstractButton b) {
        // Override this method to prevent the default button pressed behavior
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        AbstractButton b = (AbstractButton) c;
        ButtonModel model = b.getModel();
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int width = b.getWidth();
        int height = b.getHeight();

        // Fill background
        g2.setColor(b.getBackground());
        g2.fillRoundRect(0, 0, width, height, ARC_RADIUS, ARC_RADIUS);

        // Draw border
        g2.setColor(Color.BLACK); // Set border color to black
        g2.drawRoundRect(0, 0, width - 1, height - 1, ARC_RADIUS, ARC_RADIUS);

        // Draw text
        if (model.isEnabled()) {
            g2.setColor(b.getForeground());
        } else {
            g2.setColor(b.getForeground().darker());
        }
        FontMetrics fm = g2.getFontMetrics();
        String text = b.getText();
        int x = (width - fm.stringWidth(text)) / 2;
        int y = (height + fm.getAscent()) / 2;
        g2.drawString(text, x, y);

        g2.dispose();
    }
}
