import matplotlib.pyplot as plt


def regresion():
    realOutputs1 =     [3, 9.5,   4, 5, 6, 7.2, 2, 1.1]
    computedOutputs1 = [4,   9, 4.3, 6, 1,  10, 4, 1.2]

    realOutputs2 =     [5, 4.5, 6.2, 5.1, 10, 5, 2, 0.5]
    computedOutputs2 = [2,   7, 4.5,   5,  7, 6, 3, 0.8]

    error1 = sum(abs(r - c) for r, c in zip(realOutputs1, computedOutputs1)) / len(realOutputs1)
    error2 = sum(abs(r - c) for r, c in zip(realOutputs2, computedOutputs2)) / len(realOutputs2)

    show_plot(realOutputs1, computedOutputs1)
    show_plot(realOutputs2, computedOutputs2)

    print('Regression')
    print('Error1 MAE =', error1, '\nError2 MAE =', error2)
    print('Error MAE = ', (error1 + error2) / 2)


def show_plot(real, computed):
    indexes = [i for i in range(len(real))]
    real, = plt.plot(indexes, real, 'ro', label='real')
    computed, = plt.plot(indexes, computed, 'bo', label='computed')
    plt.xlim(0, 8)
    plt.ylim(0, 10)
    plt.legend([real, (real, computed)], ["Real", "Computed"])
    plt.show()
