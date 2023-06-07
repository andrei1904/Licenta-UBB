#include <GLFW/glfw3.h>
#include <GL/glut.h>

#define XC 200
#define YC 200

void init() {

    glClearColor(1.0, 1.0, 1.0, 0.0);
    glMatrixMode(GL_PROJECTION);
    gluOrtho2D(0.0, 400.0, 0.0, 400.0);
}

void plot_point(int x, int y) {
    glBegin(GL_POINTS);
    glVertex2i(XC + x, YC + y);
    glVertex2i(XC + x, YC - y);
    glVertex2i(XC + y, YC + x);
    glVertex2i(XC + y, YC - x);
    glVertex2i(XC - x, YC - y);
    glVertex2i(XC - y, YC - x);
    glVertex2i(XC - x, YC + y);
    glVertex2i(XC - y, YC + x);
    glEnd();
}

void bresenham_circle(int r) {
    int x = 0, y = r;
    double pk = (5.0 / 4.0) - r;

    plot_point(x, y);

    while (x < y) {
        x = x + 1;
        if (pk < 0)
            pk = pk + 2 * x + 1;
        else {
            y = y - 1;
            pk = pk + 2 * (x - y) + 1;
        }
        plot_point(x, y);
    }
    glFlush();
}

int main() {
    GLFWwindow *window;

    if (!glfwInit()) {
        return -1;
    }

    window = glfwCreateWindow(400, 400, "Bresenham’s Circle algorithm", nullptr, nullptr);
    if (!window) {
        glfwTerminate();
        return -1;
    }

    glfwMakeContextCurrent(window);

    init();

    while (!glfwWindowShouldClose(window)) {
        bresenham_circle(120);
        glfwSwapBuffers(window);
        glfwPollEvents();
    }

    glfwTerminate();
    return 0;
}
