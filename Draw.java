package com.mycompany.dproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Draw extends JPanel implements MouseListener, MouseMotionListener {
    // Inner class representing a shape
    private static class Shape {
        String type;// Type of shape (Cube, Rectangle, Sphere, Pyramid)
        Color color;// Color of the shape
        int x, y, width, height;// Position and dimensions of the shape
        boolean selected;// Flag indicating if the shape is selected
        int offsetX, offsetY;// Offset for dragging
        
        // Constructor for Shape
        Shape(String type, Color color, int x, int y, int width, int height) {
            this.type = type;
            this.color = color;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.selected = false;
        }
    }

    private final List<Shape> shapes = new ArrayList<>();// List to hold shapes
    private Shape selectedShape;// Currently selected shape
    private boolean resizing;// Flag indicating resizing action
    
     // Constructor
    public Draw() {
        addMouseListener(this);
        addMouseMotionListener(this);
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }
        });
    }
    // Method to add a new shape to the list
    public void addShape(String selectedShape, Color color) {
        if (selectedShape == null || selectedShape.isEmpty() || color == null) {
            return;
        }

        int size = 100;
        shapes.add(new Shape(selectedShape, color, 50, 50, size, size));

        repaint();
    }
    // Method to handle key presses
    public void handleKeyPress(KeyEvent e) {
        if (selectedShape != null) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    selectedShape.width += 10;
                    selectedShape.height += 10;
                    break;
                case KeyEvent.VK_DOWN:
                    if (selectedShape.width > 10 && selectedShape.height > 10) {
                        selectedShape.width -= 10;
                        selectedShape.height -= 10;
                    }
                    break;
                case KeyEvent.VK_DELETE:
                    shapes.remove(selectedShape);
                    selectedShape = null;
                    break;
            }
            repaint();
        }
    }
    // Method to paint the shapes on the panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
         // Draw each shape in the list
        for (Shape shape : shapes) {
            g2d.setColor(shape.color);
            if (shape.selected) {
                g2d.setStroke(new BasicStroke(2));
                g2d.setColor(Color.RED);
                g2d.drawRect(shape.x - 5, shape.y - 5, shape.width + 10, shape.height + 10);
                g2d.setColor(shape.color);
            }
            switch (shape.type) {
                case "Cube":
                    draw3DCube(g2d, shape.color, shape.x, shape.y, shape.width, shape.height, 45);
                    break;
                case "Rectangle":
                    draw3DRectangle(g2d, shape.color, shape.x, shape.y, shape.width, shape.height, 45);
                    break;
                case "Sphere":
                    draw3DSphere(g2d, shape.color, shape.x, shape.y, shape.width, shape.height, 45);
                    break;
                case "Pyramid":
                    draw3DPyramid(g2d, shape.color, shape.x, shape.y, shape.width, shape.height, 45);
                    break;
            }
        }
    }
    // Method to draw a 3D cube
    private void draw3DCube(Graphics2D g2d, Color color, int x, int y, int width, int height, double angle) {
        int depth = 20; // Depth for the 3D effect
        g2d.setColor(color);
        Polygon poly = new Polygon();
        poly.addPoint(x, y);
        poly.addPoint(x + width, y);
        poly.addPoint(x + width, y + height);
        poly.addPoint(x, y + height);
        g2d.fillPolygon(poly);
        g2d.setColor(color.darker());
        poly = new Polygon();
        poly.addPoint(x, y);
        poly.addPoint(x + depth, y - depth);
        poly.addPoint(x + width + depth, y - depth);
        poly.addPoint(x + width, y);
        g2d.fillPolygon(poly);
        g2d.setColor(color.darker().darker());
        poly = new Polygon();
        poly.addPoint(x + width, y);
        poly.addPoint(x + width + depth, y - depth);
        poly.addPoint(x + width + depth, y + height - depth);
        poly.addPoint(x + width, y + height);
        g2d.fillPolygon(poly);
    }
     // Method to draw a 3D rectangle
    private void draw3DRectangle(Graphics2D g2d, Color color, int x, int y, int width, int height, double angle) {
        int depth = 20; // Depth for the 3D effect
        g2d.setColor(color);
        Polygon poly = new Polygon();
        poly.addPoint(x, y);
        poly.addPoint(x + width, y);
        poly.addPoint(x + width, y + height);
        poly.addPoint(x, y + height);
        g2d.fillPolygon(poly);
        g2d.setColor(color.darker());
        poly = new Polygon();
        poly.addPoint(x, y);
        poly.addPoint(x + depth, y - depth);
        poly.addPoint(x + width + depth, y - depth);
        poly.addPoint(x + width, y);
        g2d.fillPolygon(poly);
        g2d.setColor(color.darker().darker());
        poly = new Polygon();
        poly.addPoint(x + width, y);
        poly.addPoint(x + width + depth, y - depth);
        poly.addPoint(x + width + depth, y + height - depth);
        poly.addPoint(x + width, y + height);
        g2d.fillPolygon(poly);
    }

    private void draw3DSphere(Graphics2D g2d, Color color, int x, int y, int width, int height, double angle) {
        g2d.setColor(color);
        g2d.fillOval(x, y, width, height);
        g2d.setColor(color.darker());
        g2d.fillOval(x + 5, y - 5, width, height);
    }
    
    // Method to draw a 3D pyramid
    private void draw3DPyramid(Graphics2D g2d, Color color, int x, int y, int width, int height, double angle) {
        int depth = 20; // Depth for the 3D effect
        g2d.setColor(color);
        Polygon poly = new Polygon();
        poly.addPoint(x, y + height);
        poly.addPoint(x + width / 2, y);
        poly.addPoint(x + width, y + height);
        g2d.fillPolygon(poly);
        g2d.setColor(color.darker());
        poly = new Polygon();
        poly.addPoint(x, y + height);
        poly.addPoint(x + width / 2, y);
        poly.addPoint(x + width / 2 + depth, y - depth);
        poly.addPoint(x + depth, y + height - depth);
        g2d.fillPolygon(poly);
        g2d.setColor(color.darker().darker());
        poly = new Polygon();
        poly.addPoint(x + width / 2, y);
        poly.addPoint(x + width, y + height);
        poly.addPoint(x + width + depth, y + height - depth);
        poly.addPoint(x + width / 2 + depth, y - depth);
        g2d.fillPolygon(poly);
    }
    
    
     // Method called when the mouse button is pressed
    @Override
    public void mousePressed(MouseEvent e) {
        // Loop through all shapes
        for (Shape shape : shapes) {
            // Check if the mouse is within the boundaries of a shape
            if (e.getX() >= shape.x && e.getX() <= shape.x + shape.width && e.getY() >= shape.y && e.getY() <= shape.y + shape.height) {
                 // If the mouse is within the shape, select it
                shape.selected = true;
                // Calculate offset for dragging
                shape.offsetX = e.getX() - shape.x;
                shape.offsetY = e.getY() - shape.y;
                // Set the selected shape
                selectedShape = shape;
                // Check if the mouse is on the resize handle
                resizing = e.getX() > shape.x + shape.width - 10 && e.getY() > shape.y + shape.height - 10;
            } else {
                // If the mouse is not within the shape, deselect it
                shape.selected = false;
            }
        }
        // Repaint the panel
        repaint();
    }
    // Method called when the mouse button is released

    @Override
    public void mouseReleased(MouseEvent e) {
         // Reset selected shape and resizing flag
        selectedShape = null;
        resizing = false;
    }
// Method called when the mouse is dragged
    @Override
public void mouseDragged(MouseEvent e) {
    if (selectedShape != null) {
        if (resizing) {
            // If resizing, update width and height based on mouse position
            int newWidth = e.getX() - selectedShape.x;
            int newHeight = e.getY() - selectedShape.y;
            //  minimum width and height
            
            selectedShape.width = Math.max(newWidth, 10);
            selectedShape.height = Math.max(newHeight, 10);
        } else {
            // If not resizing, move the shape based on mouse drag
            selectedShape.x = e.getX() - selectedShape.offsetX;
            selectedShape.y = e.getY() - selectedShape.offsetY;
        }
        repaint();
    }
}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}
}
