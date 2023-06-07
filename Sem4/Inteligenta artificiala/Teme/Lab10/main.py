from PIL import Image
import numpy as np
from numpy import asarray
from sklearn import neural_network
from sklearn.metrics import accuracy_score


def generate_sepia(image_path: str) -> Image:
    img = Image.open(image_path)
    width, height = img.size

    pixels = img.load()

    for py in range(height):
        for px in range(width):
            r, g, b = img.getpixel((px, py))

            tr = int(0.393 * r + 0.769 * g + 0.189 * b)
            tg = int(0.349 * r + 0.686 * g + 0.168 * b)
            tb = int(0.272 * r + 0.534 * g + 0.131 * b)

            if tr > 255:
                tr = 255

            if tg > 255:
                tg = 255

            if tb > 255:
                tb = 255

            pixels[px, py] = (tr, tg, tb)

    return img


def generate():
    for i in range(10):
        image = generate_sepia('img/' + str(i + 1) + ".jpg")
        print('ok' + str(i))
        image.save('img/' + str(i + 1) + "sepia.jpg")


def load_data():
    inputs = []
    for i in range(10):
        image = Image.open('img/' + str(i + 1) + '.jpg')
        inputs.append(np.average(asarray(image), axis=(0, 1)))

        image = Image.open('img/' + str(i + 1) + 'sepia.jpg')
        inputs.append(np.average(asarray(image), axis=(0, 1)))

    outputs = [0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1]

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


if __name__ == '__main__':
    inputs, outputs = load_data()

    train_inputs, train_outputs, validation_inputs, validation_outputs = split(inputs, outputs)

    classifier = neural_network.MLPClassifier(hidden_layer_sizes=(5,), learning_rate_init=.1)

    classifier.fit(train_inputs, train_outputs)
    computed_outputs = classifier.predict(validation_inputs)

    print("Accuracy: ", accuracy_score(validation_outputs, computed_outputs))
