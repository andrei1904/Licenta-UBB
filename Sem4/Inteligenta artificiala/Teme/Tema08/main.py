import csv
from sklearn import linear_model
from sklearn.metrics import mean_squared_error
import numpy as np
from sklearn.preprocessing import StandardScaler

from GD import MySGDRegression


def loadData(fileName, inputVariabName, outputVariabName):
    data = []
    dataNames = []
    with open(fileName) as csv_file:
        csv_reader = csv.reader(csv_file, delimiter=',')
        line_count = 0
        for row in csv_reader:
            if line_count == 0:
                dataNames = row
            else:
                data.append(row)
            line_count += 1
    selectedVariable1 = dataNames.index(inputVariabName[0])
    selectedVariable2 = dataNames.index(inputVariabName[1])
    inputs = [[float(data[i][selectedVariable1]), float(data[i][selectedVariable2])] for i in range(len(data))]
    selectedOutput = dataNames.index(outputVariabName)
    outputs = [float(data[i][selectedOutput]) for i in range(len(data))]

    return inputs, outputs


def split(inputs, outputs):
    np.random.seed(5)
    indexes = [i for i in range(len(inputs))]
    trainSample = np.random.choice(indexes, int(0.8 * len(inputs)), replace=False)
    validationSample = [i for i in indexes if not i in trainSample]

    trainInputs = [inputs[i] for i in trainSample]
    trainOutputs = [outputs[i] for i in trainSample]

    validationInputs = [inputs[i] for i in validationSample]
    validationOutputs = [outputs[i] for i in validationSample]

    return trainInputs, trainOutputs, validationInputs, validationOutputs


def scale01(features):
    min_feat = min(features)
    max_feat = max(features)
    return [(feat - min_feat) / (max_feat - min_feat) for feat in features]


def zero_centralisation(features):
    meanValue = sum(features) / len(features)
    centeredFeatures = [feat - meanValue for feat in features]
    return centeredFeatures


def statistical_normalisation(features):
    mean_value = sum(features) / len(features)
    std_dev_value = (1 / len(features) * sum([(feat - mean_value) ** 2 for feat in features])) ** 0.5
    return [(feat - mean_value) / std_dev_value for feat in features]


def normalisation(feature1, feature2):
    feature1_scaled = scale01(feature1)
    feature2_scaled = scale01(feature2)

    return zero_centralisation(feature1_scaled), zero_centralisation(feature2_scaled)


def normalisation_tool(trainData, testData):
    scaler = StandardScaler()
    if not isinstance(trainData[0], list):
        # encode each sample into a list
        trainData = [[d] for d in trainData]
        testData = [[d] for d in testData]

        scaler.fit(trainData)  # fit only on training data
        normalisedTrainData = scaler.transform(trainData)  # apply same transformation to train data
        normalisedTestData = scaler.transform(testData)  # apply same transformation to test data

        # decode from list to raw values
        normalisedTrainData = [el[0] for el in normalisedTrainData]
        normalisedTestData = [el[0] for el in normalisedTestData]
    else:
        scaler.fit(trainData)  # fit only on training data
        normalisedTrainData = scaler.transform(trainData)  # apply same transformation to train data
        normalisedTestData = scaler.transform(testData)  # apply same transformation to test data
    return normalisedTrainData, normalisedTestData


def univariate_reg_tool(train_inputs, train_outputs, validation_inputs, validation_outputs):
    xx = [[el] for el in train_inputs]
    regressor = linear_model.SGDRegressor(max_iter=1000, tol=1e-3)
    regressor.average = True
    for i in range(1000):
        regressor.partial_fit(xx, train_outputs)

    computed_validation_outputs = regressor.predict([[x] for x in validation_inputs])

    error = mean_squared_error(validation_outputs, computed_validation_outputs)
    print("Prediction error - tool: ", error)


def univariate_reg(train_inputs, train_outputs, validation_inputs, validation_outputs):
    xx = [[el] for el in train_inputs]
    regressor = MySGDRegression()
    regressor.fit(xx, train_outputs)
    w0, w1 = regressor.intercept_, regressor.coef_[0]

    computed_validation_outputs = [w0 + w1 * el for el in validation_inputs]

    error = 0.0
    for t1, t2 in zip(computed_validation_outputs, validation_outputs):
        error += (t1 - t2) ** 2
    error /= len(validation_outputs)
    print("Prediction error: ", error)


def bivariate_reg_tool(train_inputs, train_outputs, validation_inputs, validation_outputs):
    train_inputs, validation_inputs = normalisation_tool(train_inputs, validation_inputs)
    train_outputs, validation_outputs = normalisation_tool(train_outputs, validation_outputs)

    regressor = linear_model.SGDRegressor(max_iter=1000, tol=1e-3)
    regressor.average = True
    for i in range(1000):
        regressor.partial_fit(train_inputs, train_outputs)

    computed_test_outputs = regressor.predict(validation_inputs)

    error = mean_squared_error(validation_outputs, computed_test_outputs)
    print('Prediction error - tool: ', error)


def bivariate_reg(train_inputs, train_outputs, validation_inputs, validation_outputs):
    train_inputs[0], train_inputs[1] = normalisation(train_inputs[0], train_inputs[1])
    validation_inputs[0], validation_inputs[1] = normalisation(validation_inputs[0], validation_inputs[1])
    train_outputs, validation_outputs = normalisation_tool(train_outputs, validation_outputs)

    regressor = MySGDRegression()
    regressor.fit(train_inputs, train_outputs)

    computed_validation_outputs = regressor.predict(validation_inputs)

    error = 0.0
    for t1, t2 in zip(computed_validation_outputs, validation_outputs):
        error += (t1 - t2) ** 2
    error /= len(validation_outputs)
    print('Prediction error: ', error)


if __name__ == '__main__':
    data_in, data_out = loadData('data.csv', ['Economy..GDP.per.Capita.', 'Freedom'], 'Happiness.Score')

    train_in, train_out, validation_in, validation_out = split(data_in, data_out)

    train_feature1 = [ex[0] for ex in train_in]
    train_feature2 = [ex[1] for ex in train_in]

    validation_feature1 = [ex[0] for ex in validation_in]
    validation_feature2 = [ex[1] for ex in validation_in]

    univariate_reg_tool(train_feature1, train_out, validation_feature1, validation_out)
    univariate_reg(train_feature1, train_out, validation_feature1, validation_out)

    bivariate_reg_tool(train_in, train_out, validation_in, validation_out)
    bivariate_reg(train_in, train_out, validation_in, validation_out)
