package com.mycompany.dproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame implements ActionListener {
    JPanel navPanel = new JPanel();
    JComboBox<String> shapescb;
    Draw drawPanel = new Draw();
    JLabel shapelbl = new JLabel();

    Window() {
        setTitle("3D Object Project");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(100, 100, 100));

        navPanel.setBounds(0, 0, 1200, 50);
        navPanel.setBackground(new Color(30, 50, 56));
        navPanel.setLayout(null);

        String[] shapes = {"", "Cube", "Pyramid", "Sphere", "Rectangle"};
        shapescb = new JComboBox<>(shapes);
        shapescb.setBounds(500, 10, 100, 30);
        shapescb.addActionListener(this);

        shapelbl.setText("SHAPES");
        shapelbl.setBounds(450, 10, 100, 30);
        shapelbl.setForeground(Color.WHITE);

        navPanel.add(shapescb);
        navPanel.add(shapelbl);

        add(navPanel);

        drawPanel.setBounds(0, 50, 1200, 750);
        drawPanel.setBackground(new Color(69, 69, 69));
        add(drawPanel);

        setVisible(true);

        // Add key listener to the frame
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                drawPanel.handleKeyPress(e);
            }
        });

        // Set the focusable window state
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }
    
    // Method called when an action is performed ( button click)
    @Override
    public void actionPerformed(ActionEvent e) {
         // Check if the action is from the shapescb JComboBox
        if (e.getSource() == shapescb) {
            // Get the selected shape from the JComboBox
            String selectedShape = (String) shapescb.getSelectedItem();
            if (selectedShape != null && !selectedShape.isEmpty()) {
                // Open a color chooser dialog to select the shape color
                Color color = JColorChooser.showDialog(this, "Choose Shape Color", Color.BLUE);
                // If a color is selected
                if (color != null) {
                    // Add the selected shape with the chosen color to the drawPanel
                    drawPanel.addShape(selectedShape, color);
                }
            }
        }
    }
}