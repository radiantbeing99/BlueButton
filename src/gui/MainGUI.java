package gui;

public class MainGUI {
    // Create BaseFrame
    static BaseFrame bFrame = new BaseFrame();
    // Create Windows
    static RoomViewWindow roomViewWindow = new RoomViewWindow();
    static LogInWindow logInWindow = new LogInWindow();
    static AdminLoginWindow adminLoginWindow = new AdminLoginWindow();
    
    void run() {
        bFrame.createAndShowGUI();
        bFrame.centerPanel.add(roomViewWindow);
    }

    static void changeWindow(Template window) {
        bFrame.centerPanel.removeAll();
        bFrame.centerPanel.revalidate();
        bFrame.centerPanel.repaint();
        bFrame.centerPanel.add(window);
    }

    public static void main(String[] args) {
        MainGUI main = new MainGUI();
        main.run();
    }

}
