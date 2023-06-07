import warnings
from ga import GA


def read_data(file_name):
    file = open(file_name, "r")

    number_of_cities = int(file.readline())
    data_in = [number_of_cities]

    distances = []
    for i in range(number_of_cities):
        distances.append(file.readline().split(","))
        distances[i] = [int(x) for x in distances[i]]
    data_in.append(distances)

    source = int(file.readline())
    data_in.append(source)

    destination = int(file.readline())
    data_in.append(destination)

    file.close()
    return data_in


# def list_to_string(data):
#     res = ""
#     for x in data:
#         res = res + str(x) + ","
#     return res[0:-1]
#
#
# def write(file_name, data_out):
#     if data_out == -1:
#         print("There is no road.")
#         return
#
#     file = open(file_name, "w")
#
#     file.write(str(data_out[0]) + "\n" + list_to_string(data_out[1]) + "\n" +
#                str(data_out[2]) + "\n" + str(data_out[3]) + "\n" +
#                list_to_string(data_out[4]) + "\n" + str(data_out[5]))
#     file.close()


def distance(repres, distances):
    total = 0

    for index in range(len(repres)):
        if index == len(repres) - 1:
            total += distances[repres[index]][repres[0]]
        else:
            total += distances[repres[index]][repres[index + 1]]

    return total


def fitness(cromosome, distances):
    if cromosome.repres[0] != 0:
        return 0.0
    return 1 / float(distance(cromosome.repres, distances))


if __name__ == '__main__':
    data = read_data('data/hard.txt')

    ga_param = {'pop_size': 50, 'no_generations': 100}
    probl_param = {'min': 0, 'max': data[0], 'function': fitness, 'no_nodes': data[0], 'dist': data[1]}

    ga = GA(ga_param, probl_param)
    ga.initialisation()
    ga.evaluation()

    maxim = -1000000
    res = []
    chromosome = None

    for g in range(ga_param['no_generations']):
        ga.one_generation_elitism()

        best_chromosome = ga.best_chromosome()

        print('Best chromosome in generation ', g, ' is: ', best_chromosome.repres,
              '\n fitness = ', best_chromosome.fitness)

        if best_chromosome.fitness > maxim:
            maxim = best_chromosome.fitness
            res = best_chromosome.repres
            chromosome = best_chromosome

    dist = distance(chromosome.repres, data[1])
    for i in range(len(res)):
        res[i] += 1

    print('Best chromosome: ')
    print(res)
    print('Distance: ')
    print(dist)
