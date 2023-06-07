import csv
import numpy as np
import MySGDRegression
from sklearn import linear_model
from sklearn.preprocessing import StandardScaler


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
    selectedVariable = dataNames.index(inputVariabName)
    inputs = [float(data[i][selectedVariable]) for i in range(len(data))]
    selectedOutput = dataNames.index(outputVariabName)
    outputs = [float(data[i][selectedOutput]) for i in range(len(data))]

    return inputs, outputs


def loadDataMoreInputs(fileName, inputVariabNames, outputVariabName):
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
    selectedVariable1 = dataNames.index(inputVariabNames[0])
    selectedVariable2 = dataNames.index(inputVariabNames[1])
    inputs = [[float(data[i][selectedVariable1]), float(data[i][selectedVariable2])] for i in range(len(data))]
    selectedOutput = dataNames.index(outputVariabName)
    outputs = [float(data[i][selectedOutput]) for i in range(len(data))]

    return inputs, outputs


def normalisationTool(trainData, testData):
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

def normalisation(feature1, feature2):
    feature1scaled=scale01(feature1)
    feature2scaled=scale01(feature2)
    feature1centered=zeroCentralisation(feature1scaled)
    feature2centered=zeroCentralisation(feature2scaled)
    return  feature1centered, feature2centered

# map the feature's values to [0,1]
def scale01(features):
    minFeat = min(features)
    maxFeat = max(features)
    scaledFeatures = [(feat - minFeat) / (maxFeat - minFeat) for feat in features] #min-max
    return scaledFeatures


# data centralisation
def zeroCentralisation(features):
    meanValue = sum(features) / len(features)
    centeredFeatures = [feat - meanValue for feat in features]
    return centeredFeatures

# statistical normalisation (centered around meand and standardisation)
def statisticalNormalisation(features):
    meanValue = sum(features) / len(features)
    stdDevValue = (1 / len(features) * sum([ (feat - meanValue) ** 2 for feat in features])) ** 0.5
    normalisedFeatures = [(feat - meanValue) / stdDevValue for feat in features]
    return normalisedFeatures

def univariateRegressionTool():

    inputs, outputs = loadData('data/world-happiness-report-2017.csv', 'Economy..GDP.per.Capita.', 'Happiness.Score')

    # split data into training data (80%) and testing data (20%)
    np.random.seed(5)
    indexes = [i for i in range(len(inputs))]
    trainSample = np.random.choice(indexes, int(0.8 * len(inputs)), replace=False)
    testSample = [i for i in indexes if not i in trainSample]
    trainInputs = [inputs[i] for i in trainSample]
    trainOutputs = [outputs[i] for i in trainSample]
    testInputs = [inputs[i] for i in testSample]
    testOutputs = [outputs[i] for i in testSample]


    # training step
    xx = [[el] for el in trainInputs]
    #regressor = linear_model.SGDRegressor(max_iter =  10000)
    #regressor.fit(xx, trainOutputs)
    regressor=linear_model.SGDRegressor(max_iter=1000,tol=1e-3)
    regressor.average=True
    for i in range (1000):
        regressor.partial_fit(xx,trainOutputs)

    # makes predictions for test data (by tool)
    computedTestOutputs = regressor.predict([[x] for x in testInputs])


    from sklearn.metrics import mean_squared_error
    error = mean_squared_error(testOutputs, computedTestOutputs)
    print("prediction error (tool): ", error)

def univariateRegression():
    inputs, outputs = loadData('data/world-happiness-report-2017.csv', 'Economy..GDP.per.Capita.', 'Happiness.Score')

    # split data into training data (80%) and testing data (20%)
    np.random.seed(5)
    indexes = [i for i in range(len(inputs))]
    trainSample = np.random.choice(indexes, int(0.8 * len(inputs)), replace=False)
    testSample = [i for i in indexes if not i in trainSample]
    trainInputs = [inputs[i] for i in trainSample]
    trainOutputs = [outputs[i] for i in trainSample]
    testInputs = [inputs[i] for i in testSample]
    testOutputs = [outputs[i] for i in testSample]

    # training step
    xx = [[el] for el in trainInputs]
    regressor = MySGDRegression.MySGDRegression()
    regressor.fit(xx, trainOutputs)
    w0, w1 = regressor.intercept_, regressor.coef_[0]

    # makes predictions for test data
    computedTestOutputs = [w0 + w1 * el for el in testInputs]

    # compute the differences between the predictions and real outputs
    error = 0.0
    for t1, t2 in zip(computedTestOutputs, testOutputs):
        error += (t1 - t2) ** 2
    error = error / len(testOutputs)
    print("prediction error (manual): ", error)

def bivariateRegressionTool():
    inputs, outputs = loadDataMoreInputs('data/world-happiness-report-2017.csv', ['Economy..GDP.per.Capita.', 'Freedom'], 'Happiness.Score')

    feature1 = [ex[0] for ex in inputs]
    feature2 = [ex[1] for ex in inputs]

    # split data into training data (80%) and testing data (20%)
    np.random.seed(5)
    indexes = [i for i in range(len(inputs))]
    trainSample = np.random.choice(indexes, int(0.8 * len(inputs)), replace=False)
    testSample = [i for i in indexes if not i in trainSample]

    trainInputs = [inputs[i] for i in trainSample]
    trainOutputs = [outputs[i] for i in trainSample]
    testInputs = [inputs[i] for i in testSample]
    testOutputs = [outputs[i] for i in testSample]

    #normalisation
    trainInputs, testInputs = normalisationTool(trainInputs, testInputs)
    trainOutputs, testOutputs = normalisationTool(trainOutputs, testOutputs)

    # regressor = linear_model.SGDRegressor()
    # regressor.fit(trainInputs, trainOutputs)
    regressor = linear_model.SGDRegressor(max_iter=1000, tol=1e-3)
    regressor.average = True
    for i in range(1000):
        regressor.partial_fit(trainInputs, trainOutputs)

    # makes predictions for test data (by tool)
    computedTestOutputs = regressor.predict(testInputs)

    from sklearn.metrics import mean_squared_error

    error = mean_squared_error(testOutputs, computedTestOutputs)
    print('prediction error (tool):   ', error)

def bivariateRegression():
    inputs, outputs = loadDataMoreInputs('data/world-happiness-report-2017.csv', ['Economy..GDP.per.Capita.', 'Freedom'], 'Happiness.Score')

    # split data into training data (80%) and testing data (20%)
    np.random.seed(5)
    indexes = [i for i in range(len(inputs))]
    trainSample = np.random.choice(indexes, int(0.8 * len(inputs)), replace=False)
    testSample = [i for i in indexes if not i in trainSample]

    trainInputs = [inputs[i] for i in trainSample]
    trainOutputs = [outputs[i] for i in trainSample]
    testInputs = [inputs[i] for i in testSample]
    testOutputs = [outputs[i] for i in testSample]

    #normalisation
    trainInputs[0], trainInputs[1]=normalisation(trainInputs[0], trainInputs[1])
    testInputs[0], testInputs[1] = normalisation(testInputs[0], testInputs[1])
    #trainInputs, testInputs = normalisation(trainInputs, testInputs)
    trainOutputs, testOutputs = normalisation(trainOutputs, testOutputs)

    regressor = MySGDRegression.MySGDRegression()
    regressor.fit(trainInputs, trainOutputs)

    # makes predictions for test data (by tool)
    computedTestOutputs = regressor.predict(testInputs)

    # compute the differences between the predictions and real outputs
    error = 0.0
    for t1, t2 in zip(computedTestOutputs, testOutputs):
        error += (t1 - t2) ** 2
    error = error / len(testOutputs)
    print('prediction error (manual): ', error)




if __name__ == '__main__':
    print('Univariate:')
    univariateRegressionTool()
    univariateRegression()
    print('Bivariate:')
    bivariateRegressionTool()
    bivariateRegression()


