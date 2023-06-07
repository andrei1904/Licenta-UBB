from sklearn import linear_model
from sklearn.metrics import mean_squared_error
import numpy as np
import csv


def loadData(fileName, inputVariabName1, inputVariabName2, outputVariabName):
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
    selectedVariable = dataNames.index(inputVariabName1)
    inputs1 = [float(data[i][selectedVariable]) for i in range(len(data))]
    selectedVariable = dataNames.index(inputVariabName2)
    inputs2 = [float(data[i][selectedVariable]) for i in range(len(data))]
    selectedOutput = dataNames.index(outputVariabName)
    outputs = [float(data[i][selectedOutput]) for i in range(len(data))]

    return inputs1, inputs2, outputs


def split(inputs1, inputs2, outputs):
    np.random.seed(5)
    indexes = [i for i in range(len(inputs1))]
    trainSample = np.random.choice(indexes, int(0.8 * len(inputs1)), replace=False)
    validationSample = [i for i in indexes if not i in trainSample]

    trainInputs1 = [inputs1[i] for i in trainSample]
    trainInputs2 = [inputs2[i] for i in trainSample]
    trainOutputs = [outputs[i] for i in trainSample]

    validationInputs1 = [inputs1[i] for i in validationSample]
    validationInputs2 = [inputs2[i] for i in validationSample]
    validationOutputs = [outputs[i] for i in validationSample]

    return trainInputs1, trainInputs2, trainOutputs, validationInputs1, validationInputs2, validationOutputs


def run(trainInputs1, transInputs2, trainOutputs, validationInputs1, validationInputs2, validationOutputs):
    xx = []
    for i in range(len(trainInputs1)):
        xx.append([trainInputs1[i], transInputs2[i]])

    regressor = linear_model.LinearRegression()
    regressor.fit(xx, trainOutputs)

    w0, w1, w2 = regressor.intercept_, regressor.coef_[0], regressor.coef_[1]
    print('the learnt model: f(x) = ', w0, ' + ', w1, ' * x1 + ', w2, ' * x2')

    validationInputs = []
    for i in range(len(validationInputs1)):
        validationInputs.append([validationInputs1[i], validationInputs2[i]])

    computedValidationOutputs = regressor.predict(validationInputs)
    error = mean_squared_error(validationOutputs, computedValidationOutputs)
    print('Prediction error: ', error)


if __name__ == '__main__':
    input1, input2, output = loadData('data.csv', 'Economy..GDP.per.Capita.',
                                      'Freedom', 'Happiness.Score')
    trainInput1, trainInput2, trainOutput, validationInput1, validationInput2, validationOutput = \
        split(input1, input2, output)

    run(trainInput1, trainInput2, trainOutput, validationInput1, validationInput2, validationOutput)
