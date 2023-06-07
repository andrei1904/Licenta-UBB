import csv

import numpy as np
from sklearn.preprocessing import StandardScaler
from sklearn.linear_model import LogisticRegression
from sklearn.metrics import accuracy_score

from LR import MyLogisticRegression


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
        train_data = [[d] for d in train_data]
        validation_data = [[d] for d in validation_data]

        scaler.fit(train_data)
        normalised_train_data = scaler.transform(train_data)
        normalised_validation_data = scaler.transform(validation_data)

        normalised_train_data = [el[0] for el in normalised_train_data]
        normalised_validation_data = [el[0] for el in normalised_validation_data]
    else:
        scaler.fit(train_data)
        normalised_train_data = scaler.transform(train_data)
        normalised_validation_data = scaler.transform(validation_data)
    return normalised_train_data, normalised_validation_data


def logistic_regression_tool(train_inputs, train_outputs, validation_inputs, validation_outputs):
    regression = LogisticRegression()
    regression.fit(train_inputs, train_outputs)

    computed_outputs = regression.predict(validation_inputs)

    print("Classification accuracy - tool: ", accuracy_score(validation_outputs, computed_outputs))


def logistic_regression(train_inputs, train_outputs, validation_inputs, validation_outputs):
    regression = MyLogisticRegression()

    versicolor_out = [1 if x == 'Iris-versicolor' else 0 for x in train_outputs]
    regression.fit(train_inputs, versicolor_out)
    computed_versicolor = regression.predict(validation_inputs)

    virginica_out = [1 if x == 'Iris-virginica' else 0 for x in train_outputs]
    regression.fit(train_inputs, virginica_out)
    computed_virginica = regression.predict(validation_inputs)

    setosa_out = [1 if i == 'Iris-setosa' else 0 for i in train_outputs]
    regression.fit(train_inputs, setosa_out)
    computed_setosa = regression.predict(validation_inputs)

    computed_output = []
    for i in range(len(computed_versicolor)):
        if computed_virginica[i] > computed_setosa[i] and computed_virginica[i] > computed_versicolor[i]:
            computed_output.append('Iris-virginica')
        elif computed_setosa[i] > computed_versicolor[i] and computed_setosa[i] > computed_virginica[i]:
            computed_output.append('Iris-setosa')
        else:
            computed_output.append('Iris-versicolor')

    accuracy = 0.0
    for t1, t2 in zip(computed_output, validation_outputs):
        if t1 == t2:
            accuracy += 1
    print('Classification accuracy: ', accuracy / len(validation_outputs))


if __name__ == '__main__':
    data_in, data_out = load_data('data.csv', ['sepal lenght', 'sepal width', 'petal length', 'petal width'], 'label')

    train_in, train_out, validation_in, validation_out = split(data_in, data_out)

    train_in, validation_in = normalisation(train_in, validation_in)

    logistic_regression_tool(train_in, train_out, validation_in, validation_out)

    logistic_regression(train_in, train_out, validation_in, validation_out)
