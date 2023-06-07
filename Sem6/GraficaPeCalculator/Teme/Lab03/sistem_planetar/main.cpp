#include <GL/glew.h>
#include <GL/freeglut.h>

#define STB_IMAGE_IMPLEMENTATION

#include "stb_image.h"


using namespace std;

class Planet {
public:
    float radius, distance, orbit, orbitSpeed, axisTilt, axisAni;

    Planet(float _radius, float _distance, float _orbit, float _orbitSpeed, float _axisTilt, float _axisAni) {
        radius = _radius;
        distance = _distance;
        orbit = _orbit;
        orbitSpeed = _orbitSpeed;
        axisTilt = _axisTilt;
        axisAni = _axisAni;
    }

    void drawSmallOrbit() const {
        glPushMatrix();
        glColor3ub(255, 255, 255);
        glRotatef(90.0, 1.0, 0.0, 0.0);
        glutWireTorus(0.001, distance, 100.0, 100.0);
        glPopMatrix();
    }

    void drawMoon() const {
        GLUquadricObj *quadric;
        quadric = gluNewQuadric();
        glPushMatrix();
        glColor3ub(255, 255, 255);
        glRotatef(orbit, 0.0, 1.0, 0.0);
        glTranslatef(distance, 0.0, 0.0);
        gluSphere(quadric, radius, 20.0, 20.0);
        glPopMatrix();
    }

};

int isAnimate = 0;

Planet sun(5.0, 0, 0, 0, 0, 0);
Planet earth(2.0, 16, 0, 2.98, 23.44, 0);
Planet moon(.40, 3, 0, 5.40, 0, 0);

GLuint sunTexture, earthTexture;

void setup() {
    glClearColor(0.0, 0.0, 0.0, 0.0);
    glEnable(GL_DEPTH_TEST);

    glEnable(GL_NORMALIZE);
    glEnable(GL_COLOR_MATERIAL);

    int width = 0, height = 0, chanels = 0;
    stbi_uc *sunImage = stbi_load("sun.bmp", &width, &height, &chanels, 3);

    if (sunImage != nullptr) {
        glGenTextures(1, &sunTexture);
        glBindTexture(GL_TEXTURE_2D, sunTexture);

        glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGB, GL_UNSIGNED_BYTE, sunImage);
        glPixelStorei(GL_UNPACK_ALIGNMENT, 4);

        stbi_image_free(sunImage);
    }

    stbi_uc *earthImage = stbi_load("earth.bmp", &width, &height, &chanels, 3);

    if (earthImage != nullptr) {
        glGenTextures(1, &earthTexture);
        glBindTexture(GL_TEXTURE_2D, earthTexture);

        glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGB, GL_UNSIGNED_BYTE, earthImage);
        glPixelStorei(GL_UNPACK_ALIGNMENT, 4);

        stbi_image_free(earthImage);
    }

}

void orbitalTrails() {
    glPushMatrix();
    glColor3ub(255, 255, 255);
    glTranslatef(0.0, 0.0, 0.0);
    glRotatef(90.0, 1.0, 0.0, 0.0);
    glutWireTorus(0.001, earth.distance, 100.0, 100.0);
    glPopMatrix();
}

void drawScene() {
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    glLoadIdentity();

    gluLookAt(0.0, 30, 0.00001, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);

    orbitalTrails();

    GLUquadric *quadric;
    quadric = gluNewQuadric();

    //Sun
    glPushMatrix();
    glRotatef(sun.orbit, 0.0, 1.0, 0.0);
    glTranslatef(sun.distance, 0.0, 0.0);

    glPushMatrix();
    glRotatef(sun.axisTilt, 1.0, 0.0, 0.0);
    glRotatef(sun.axisAni, 0.0, 1.0, 0.0);
    glRotatef(90.0, 1.0, 0.0, 0.0);
    glEnable(GL_TEXTURE_2D);
    glBindTexture(GL_TEXTURE_2D, sunTexture);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
    gluQuadricTexture(quadric, 1);
    gluSphere(quadric, sun.radius, 20.0, 20.0);
    glDisable(GL_TEXTURE_2D);
    glPopMatrix();
    glPopMatrix();

    // earth and moon
    glPushMatrix();
    glRotatef(earth.orbit, 0.0, 1.0, 0.0);
    glTranslatef(earth.distance, 0.0, 0.0);

    glPushMatrix();
    glRotatef(earth.axisTilt, 1.0, 0.0, 0.0);
    glRotatef(earth.axisAni, 0.0, 1.0, 0.0);
    glRotatef(90.0, 1.0, 0.0, 0.0);
    glEnable(GL_TEXTURE_2D);
    glBindTexture(GL_TEXTURE_2D, earthTexture);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
    gluQuadricTexture(quadric, 1);
    gluSphere(quadric, earth.radius, 20.0, 20.0);
    glDisable(GL_TEXTURE_2D);
    glPopMatrix();

    moon.drawSmallOrbit();
    moon.drawMoon();

    glPopMatrix();

    glutSwapBuffers();
}

void resize(int w, int h) {
    glViewport(0, 0, w, h);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    glFrustum(-5.0, 5.0, -5.0, 5.0, 5.0, 200.0);
    glMatrixMode(GL_MODELVIEW);
}

void animate(int n) {
    if (isAnimate) {
        earth.orbit += earth.orbitSpeed;
        moon.orbit += moon.orbitSpeed;

        if (earth.orbit > 360.0) {
            earth.orbit -= 360.0;
        }

        if (moon.orbit > 360.0) {
            moon.orbit -= 360.0;
        }

        earth.axisAni += 10.0;

        if (earth.axisAni > 360.0) {
            earth.axisAni -= 360.0;
        }
        glutPostRedisplay();
        glutTimerFunc(30, animate, 1);
    }
}

void keyInput(unsigned char key, int x, int y) {
    if (key == ' ') {
        if (isAnimate) {
            isAnimate = 0;
        } else {
            isAnimate = 1;
            animate(1);
        }
    }
}

int main(int argc, char **argv) {

    glutInit(&argc, argv);

    glutInitContextVersion(4, 2);
    glutInitContextProfile(GLUT_COMPATIBILITY_PROFILE);

    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGBA | GLUT_DEPTH);
    glutInitWindowSize(500, 500);
    glutCreateWindow("Solar System");

    glutDisplayFunc(drawScene);
    glutReshapeFunc(resize);
    glutKeyboardFunc(keyInput);

    glewExperimental = GL_TRUE;
    glewInit();

    setup();
    glutMainLoop();
}

