import csv

import numpy as np
from sklearn.preprocessing import StandardScaler
from sklearn.linear_model import LogisticRegression
from sklearn.metrics import accuracy_score

from LogisticRegression import MyLogisticRegression


def load_data(fileName, inputVariabName, outputVariabName):
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
    selectedVariable3 = dataNames.index(inputVariabName[2])
    selectedVariable4 = dataNames.index(inputVariabName[3])

    inputs = [[float(data[i][selectedVariable1]), float(data[i][selectedVariable2]),
               float(data[i][selectedVariable3]), float(data[i][selectedVariable4])]
              for i in range(len(data))]

    selectedOutput = dataNames.index(outputVariabName)
    outputs = [str(data[i][selectedOutput]) for i in range(len(data))]

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


def normalisation(train_data, validation_data):
    scaler = StandardScaler()
    if not isinstance(train_data[0], list):
        # encode each sample into a list
        trainData = [[d] for d in train_data]
        testData = [[d] for d in validation_data]

        scaler.fit(trainData)  # fit only on training data
        normalisedTrainData = scaler.transform(trainData)  # apply same transformation to train data
        normalisedTestData = scaler.transform(testData)  # apply same transformation to test data

        # decode from list to raw values
        normalisedTrainData = [el[0] for el in normalisedTrainData]
        normalisedTestData = [el[0] for el in normalisedTestData]
    else:
        scaler.fit(train_data)  # fit only on training data
        normalisedTrainData = scaler.transform(train_data)  # apply same transformation to train data
        normalisedTestData = scaler.transform(validation_data)  # apply same transformation to test data
    return normalisedTrainData, normalisedTestData


def logistic_regresion_tool(train_inputs, train_outputs, validation_inputs, validation_outputs):
    regression = LogisticRegression()
    regression.fit(train_inputs, train_outputs)

    computed_outputs = list(regression.predict(validation_inputs))

    error = 1 - accuracy_score(validation_outputs, computed_outputs)
    print("Classification error - tool: ", error)


def logistic_regresion(train_inputs, train_outputs, validation_inputs, validation_outputs):
    regression = MyLogisticRegression()
    regression.fit(train_inputs, train_outputs)

    computed = regression.predict(validation_inputs)

    error = 0.0
    for t1, t2 in zip(computed, validation_outputs):
        if t1 != t2:
            error += 1
    error /= len(validation_outputs)
    print('Classification error - manual:', error)


if __name__ == '__main__':
    data_in, data_out = load_data('data.csv', ['sepal lenght', 'sepal width', 'petal length', 'petal width'], 'label')

    train_in, train_out, validation_in, validation_out = split(data_in, data_out)

    train_in, validation_in = normalisation(train_in, validation_in)

    logistic_regresion_tool(train_in, train_out, validation_in, validation_out)
    logistic_regresion(train_in, train_out, validation_in, validation_out)
