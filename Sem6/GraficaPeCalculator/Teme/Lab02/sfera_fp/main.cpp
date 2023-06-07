#include <GLFW/glfw3.h>
#include <GL/glut.h>
#include <unistd.h>
#include <cmath>

void renderSphere(const float fRadius, const int iStacks, const int iSlices) {
    const auto PI = (float) M_PI;
    const auto PIx2 = (float) (M_PI * 2.0);

    GLfloat drho = PI / (GLfloat) iStacks;
    GLfloat dtheta = PIx2 / (GLfloat) iSlices;
    GLfloat ds = 1.0f / (GLfloat) iSlices;
    GLfloat dt = 1.0f / (GLfloat) iStacks;
    GLfloat t = 1.0f;
    GLfloat s;

    for (int i = 0; i < iStacks; i++) {
        const GLfloat rho = (GLfloat) i * drho;
        const auto srho = (GLfloat) (sinf(rho));
        const auto crho = (GLfloat) (cosf(rho));
        const auto srhodrho = (GLfloat) (sinf(rho + drho));
        const auto crhodrho = (GLfloat) (cosf(rho + drho));

        glBegin(GL_TRIANGLE_STRIP);
        s = 0.0f;
        for (int j = 0; j <= iSlices; j++) {
            const GLfloat theta = (j == iSlices) ? 0.0f : j * dtheta;
            const auto stheta = (GLfloat) (sinf(theta));
            const auto ctheta = (GLfloat) (cosf(theta));

            GLfloat x = stheta * srho;
            GLfloat y = ctheta * srho;
            GLfloat z = crho;

            glTexCoord2f(s, t);
            glNormal3f(x, y, z);
            glVertex3f(x * fRadius, y * fRadius, z * fRadius);

            x = stheta * srhodrho;
            y = ctheta * srhodrho;
            z = crhodrho;
            glTexCoord2f(s, t - dt);
            s += ds;
            glNormal3f(x, y, z);
            glVertex3f(x * fRadius, y * fRadius, z * fRadius);
        }
        glEnd();

        t -= dt;
    }
}

int display[2] = {800, 800};

void init() {

    // openGL va ascunde suprafetele ce vor fi specificate
    glEnable(GL_CULL_FACE);
    // specificam sa se ascunda pe cele din spate
    glCullFace(GL_BACK);
    // precizam ordinea in care sunt desenate (invers acelor de ceasornic e default) --
    glFrontFace(GL_CW);

    // precizam ca aspura carei matrici vom stabili parametrii (in acest caz asupra matricii de proiectie)
    glMatrixMode(GL_PROJECTION);

    // stabilim perspectiva
    gluPerspective(45, (display[0] / display[1]), 0.0, 50.0);
    // culoarea fundalului
    glClearColor(0.0, 0.0, 0.0, 0.0);
    // mutam camera in spate cu 10 unitati ca sa vedem mai bine cubul
    glTranslatef(1.5, -1.5, -10);
    glRotatef(60, 0, 1, 1);
}

int main() {
    float angle = 0.0f;
    GLFWwindow *window;
    /* Initializam libraria */
    if (!glfwInit())
        return -1;

    /* Cream o fereastra si ii atasam un context OpenGL */
    window = glfwCreateWindow(display[0], display[1], "Sphere fixed pipeline!", nullptr, nullptr);
    if (!window) {
        glfwTerminate();
        return -1;
    }

    /* Facem fereastra curenta contextul curent */
    glfwMakeContextCurrent(window);

    /* se initializeaza conditiile initiale, projection mode, etc. */
    init();

    /* Loop pana cand se inchide fereastra */
    while (!glfwWindowShouldClose(window)) {

        /* Aici se desenează */
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
        renderSphere(1, 50, 50);

        glMatrixMode(GL_MODELVIEW);
        glRotatef(0, 0, 0, 1);
        glTranslatef(cos(angle) / 5, sin(angle) / 5, 0);
        angle += 0.1f;

        /* Se inverseaza bufferele */
        glfwSwapBuffers(window);

        /* intarziem putin ca sa putem sa vedem rotatia */
        usleep(100000);

        /* Procesam evenimentelele */
        glfwPollEvents();
    }

    glfwTerminate();
    return 0;
}
