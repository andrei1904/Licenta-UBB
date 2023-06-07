import random


class MySGDRegression:
    def __init__(self):
        self.intercept_ = 0.0
        self.coef_ = []

    def fit(self, x, y, learningRate=0.001, noEpochs=1000):
        self.coef_ = [0.0 for _ in range(len(x[0]) + 1)]

        for epoch in range(noEpochs):
            avg_error = 0.0
            for i in range(len(x)):
                ycomputed = self.eval(x[i])
                avg_error += ycomputed - y[i]
            avg_error /= len(x)

            for j in range(0, len(x[0])):
                self.coef_[j] = self.coef_[j] - learningRate * avg_error * x[i][j]
            self.coef_[len(x[0])] = self.coef_[len(x[0])] - learningRate * avg_error * 1

        self.intercept_ = self.coef_[-1]
        self.coef_ = self.coef_[:-1]

    def eval(self, xi):
        yi = self.coef_[-1]
        for j in range(len(xi)):
            yi += self.coef_[j] * xi[j]
        return yi

    def predict(self, x):
        yComputed = [self.eval(xi) for xi in x]
        return yComputed
