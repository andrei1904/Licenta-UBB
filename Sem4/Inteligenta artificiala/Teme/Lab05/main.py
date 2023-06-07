from Ant import Ant
from Colony import Colony


def read(file_name):
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


def main():
    data = read('data/medium.txt')

    params = {'size': 20, 'iterations': 100, 'no_nodes': data[0], 'distances': data[1],
              'ro': 0.1, 'pheromone': 1.0, 'min': 1, 'max': 50}

    colony = Colony(params)

    solution = Ant(params)
    solution.distance = 999999

    for i in range(params['iterations']):
        colony.initialisation()
        colony.run()
        res = colony.best_solution()

        if res.distance < solution.distance:
            solution = res
        colony.update_pheromone()
        colony.dynamic()

    print('\nBest solution: ', solution.path, ' cost: ', solution.distance)


if __name__ == '__main__':
    main()
