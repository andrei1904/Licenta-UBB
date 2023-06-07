from math import exp
from numpy.linalg import inv
import numpy as np


def sigmoid(x):
    return 1 / (1 + exp(-x))


class MyLogisticRegression:
    def __init__(self):
        self.intercept_ = 0.0
        self.coef_ = []

    def standardisation(self, data):
        avg = sum(data) / len(data)
        std_dev_value = (1 / len(data) * sum([(feat - avg) ** 2 for feat in data])) ** 0.5

        return [(feat - avg) / std_dev_value for feat in data]

    def normalisation(self, features):
        sepal_length = [feature[0] for feature in features]
        sepal_width = [feature[1] for feature in features]
        petal_length = [feature[2] for feature in features]
        petal_width = [feature[3] for feature in features]

        sepal_length = self.standardisation(sepal_length)
        sepal_width = self.standardisation(sepal_width)
        petal_length = self.standardisation(petal_length)
        petal_width = self.standardisation(petal_width)

        return [[feature1, feature2, feature3, feature4] for
                feature1, feature2, feature3, feature4 in
                zip(sepal_length, sepal_width, petal_length, petal_width)]

    # use the gradient descent method
    # simple stochastic GD
    def fit(self, x, y, learningRate=0.001, noEpochs=1000):
        self.coef_ = [0.0 for _ in range(1 + len(x[0]))]  # beta or w coefficients y = w0 + w1 * x1 + w2 * x2 + ...
        # self.coef_ = [random.random() for _ in range(len(x[0]) + 1)]    #beta or w coefficients
        for epoch in range(noEpochs):
            # TBA: shuffle the trainind examples in order to prevent cycles
            for i in range(len(x)):  # for each sample from the training data
                ycomputed = sigmoid(self.eval(x[i], self.coef_))  # estimate the output
                crtError = ycomputed - y[i]  # compute the error for the current sample
                for j in range(0, len(x[0])):  # update the coefficients
                    self.coef_[j + 1] = self.coef_[j + 1] - learningRate * crtError * x[i][j]
                self.coef_[0] = self.coef_[0] - learningRate * crtError * 1

        self.intercept_ = self.coef_[0]
        self.coef_ = self.coef_[1:]

    def eval(self, xi, coef):
        yi = coef[0]
        for j in range(len(xi)):
            yi += coef[j + 1] * xi[j]
        return yi

    def predictOneSample(self, sampleFeatures):
        threshold = 0.5
        coefficients = [self.intercept_] + [c for c in self.coef_]
        computedFloatValue = self.eval(sampleFeatures, coefficients)
        computed01Value = sigmoid(computedFloatValue)
        computedLabel = 0 if computed01Value < threshold else 1
        return computedLabel

    def predict(self, inTest):
        computedLabels = [self.predictOneSample(sample) for sample in inTest]
        return computedLabels
