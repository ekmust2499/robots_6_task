package gui;

import java.awt.*;

public class Bug {

    private volatile double m_robotPositionX;
    private volatile double m_robotPositionY;
    private volatile double m_robotDirection;
    private final Color color;

    public Bug(double x, double y, double direction, Color color) {
        m_robotPositionX = x;
        m_robotPositionY = y;
        m_robotDirection = direction;
        this.color = color;
    }

    public double getM_bugPositionX() {
        return m_robotPositionX;
    }

    public void setM_bugPositionX(double m_robotPositionX) {
        this.m_robotPositionX = m_robotPositionX;
    }

    public double getM_bugPositionY() {
        return m_robotPositionY;
    }

    public void setM_bugPositionY(double m_robotPositionY) {
        this.m_robotPositionY = m_robotPositionY;
    }

    public double getM_bugDirection() {
        return m_robotDirection;
    }

    public void setM_bugDirection(double m_robotDirection) {
        this.m_robotDirection = m_robotDirection;
    }

    public Color getColor() {
        return color;
    }
}
