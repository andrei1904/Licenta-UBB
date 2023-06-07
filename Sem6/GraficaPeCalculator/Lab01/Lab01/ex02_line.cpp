#include <GLFW/glfw3.h>
#include <GL/glut.h>

void init() {
    glClearColor(1.0, 1.0, 1.0, 0.0);
    glMatrixMode(GL_PROJECTION);
    gluOrtho2D (0.0 ,400.0 ,0.0 ,400.0);
}

void setPixel(GLint x, GLint y) {
    glBegin(GL_POINTS);
    glVertex2d(x, y);
    glEnd();
}

void line() {
    int x0 = 50, y0=50, xn = 300, yn = 150, x, y;
    int dx, dy, pk;

    glClear(GL_COLOR_BUFFER_BIT);
    glColor3f( 1  ,0, 0);
    setPixel(x0, y0);

    dx = xn - x0;
    dy = yn - y0;
    pk = 2 * dy - dx;
    x = x0;
    y = y0;

    for(int k = 0; k < dx - 1; ++k) {
        if (pk < 0) {
            pk = pk + 2 * dy;
        } else {
            pk = pk + 2 * dy - 2 * dx;
            ++y;
        }
        ++x;
        setPixel(x, y);
    }

    glFlush();
}
int main() {
    GLFWwindow* window;

    if (!glfwInit()) {
        return -1;
    }

    window = glfwCreateWindow (400, 400, "Bresenham ’s Line  algorithm , works  only for |m| < 1", nullptr, nullptr);
    if (!window) {
        glfwTerminate();
        return -1;
    }

    glfwMakeContextCurrent(window);

    init();

    while(! glfwWindowShouldClose(window)) {

        line();
        glfwSwapBuffers(window);
        glfwPollEvents();
    }

    glfwTerminate();
    return 0;
}
