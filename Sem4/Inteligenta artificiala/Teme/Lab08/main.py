import csv
from sklearn import linear_model
from sklearn.metrics import mean_squared_error
import numpy as np
from sklearn.preprocessing import StandardScaler
from GD import MySGDRegression


def loadData(file_name, input_var_name, output_var_name):
    data = []
    data_names = []
    with open(file_name) as csv_file:
        csv_reader = csv.reader(csv_file, delimiter=',')
        line_count = 0
        for row in csv_reader:
            if line_count == 0:
                data_names = row
            else:
                data.append(row)
            line_count += 1
    selectedVariable1 = data_names.index(input_var_name[0])
    selectedVariable2 = data_names.index(input_var_name[1])
    inputs = [[float(data[i][selectedVariable1]), float(data[i][selectedVariable2])] for i in range(len(data))]
    selectedOutput = data_names.index(output_var_name)
    outputs = [float(data[i][selectedOutput]) for i in range(len(data))]

    return inputs, outputs


def split(inputs, outputs):
    np.random.seed(5)
    indexes = [i for i in range(len(inputs))]
    train_sample = np.random.choice(indexes, int(0.8 * len(inputs)), replace=False)
    validation_sample = [i for i in indexes if not i in train_sample]

    train_inputs = [inputs[i] for i in train_sample]
    train_outputs = [outputs[i] for i in train_sample]

    validation_inputs = [inputs[i] for i in validation_sample]
    validation_outputs = [outputs[i] for i in validation_sample]

    return train_inputs, train_outputs, validation_inputs, validation_outputs


def scale01(features):
    min_feat = min(features)
    max_feat = max(features)
    return [(feat - min_feat) / (max_feat - min_feat) for feat in features]


def normalisation(feature1, feature2):
    feature1_scaled = scale01(feature1)
    feature2_scaled = scale01(feature2)
    return feature1_scaled, feature2_scaled


def normalisation_tool(train_data, validation_data):
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


def univariate_reg_tool(train_inputs, train_outputs, validation_inputs, validation_outputs):
    train_inputs = [[el] for el in train_inputs]

    regressor = linear_model.SGDRegressor(average=True)
    regressor.fit(train_inputs, train_outputs)

    computed_validation_outputs = regressor.predict([[x] for x in validation_inputs])

    error = mean_squared_error(validation_outputs, computed_validation_outputs)
    print("Prediction error - tool: ", error)


def univariate_reg(train_inputs, train_outputs, validation_inputs, validation_outputs):
    train_inputs = [[x] for x in train_inputs]
    regressor = MySGDRegression()
    regressor.fit(train_inputs, train_outputs)

    computed_validation_outputs = regressor.predict([[x] for x in validation_inputs])

    error = 0.0
    for t1, t2 in zip(computed_validation_outputs, validation_outputs):
        error += (t1 - t2) ** 2
    error /= len(validation_outputs)
    print("Prediction error: ", error)


def bivariate_reg_tool(train_inputs, train_outputs, validation_inputs, validation_outputs):
    train_inputs, validation_inputs = normalisation_tool(train_inputs, validation_inputs)
    train_outputs, validation_outputs = normalisation_tool(train_outputs, validation_outputs)

    regressor = linear_model.SGDRegressor(average=True)
    regressor.fit(train_inputs, train_outputs)

    computed_test_outputs = regressor.predict(validation_inputs)

    error = mean_squared_error(validation_outputs, computed_test_outputs)
    print('Prediction error - tool: ', error)


def bivariate_reg(train_inputs, train_outputs, validation_inputs, validation_outputs):
    train_inputs[0], train_inputs[1] = normalisation(train_inputs[0], train_inputs[1])
    validation_inputs[0], validation_inputs[1] = normalisation(validation_inputs[0], validation_inputs[1])
    train_outputs, validation_outputs = normalisation(train_outputs, validation_outputs)

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

    train_feature1 = [x[0] for x in train_in]
    validation_feature1 = [x[0] for x in validation_in]

    univariate_reg_tool(train_feature1, train_out, validation_feature1, validation_out)
    univariate_reg(train_feature1, train_out, validation_feature1, validation_out)

    bivariate_reg_tool(train_in, train_out, validation_in, validation_out)
    bivariate_reg(train_in, train_out, validation_in, validation_out)
