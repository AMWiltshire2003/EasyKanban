/*package com.company;

import java.awt.*;
import javax.swing.*;

/**
 * Central place to manage light and dark themes
 */
/*public class ThemeManager {

    public enum Theme {
        LIGHT, DARK
    }

    private static Theme currentTheme = Theme.LIGHT;

    // Light theme colors
    private static final Color LIGHT_BG = Color.WHITE;
    private static final Color LIGHT_CARD = new Color(245, 245, 245);
    private static final Color LIGHT_TEXT = Color.BLACK;

    // Dark theme colors
    private static final Color DARK_BG = new Color(30, 30, 30);
    private static final Color DARK_CARD = new Color(45, 45, 45);
    private static final Color DARK_TEXT = Color.WHITE;

    public static void toggleTheme() {
        currentTheme = (currentTheme == Theme.LIGHT)
                ? Theme.DARK
                : Theme.LIGHT;
    }

    public static Theme getCurrentTheme() {
        return currentTheme;
    }

    public static Color background() {
        return currentTheme == Theme.DARK ? DARK_BG : LIGHT_BG;
    }

    public static Color cardBackground() {
        return currentTheme == Theme.DARK ? DARK_CARD : LIGHT_CARD;
    }

    public static Color text() {
        return currentTheme == Theme.DARK ? DARK_TEXT : LIGHT_TEXT;
    }

    /**
     * Apply theme recursively to a container

    public static void applyTheme(Container container) {
        for (Component c : container.getComponents()) {

            if (c instanceof JPanel) {
                c.setBackground(background());
            }

            if (c instanceof JLabel) {
                c.setForeground(text());
            }

            if (c instanceof JButton) {
                c.setBackground(cardBackground());
                c.setForeground(text());
            }

            if (c instanceof Container) {
                applyTheme((Container) c);
            }
        }
    }
}*/
