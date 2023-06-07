from random import randint

from Ant import Ant


class Colony:
    def __init__(self, params):
        self.__params = params
        self.__population = []
        self.__pheromoneMatrix = [[0.0 for _ in range(self.__params['no_nodes'])]
                                  for _ in range(self.__params['no_nodes'])]

    @property
    def population(self):
        return self.__population

    @property
    def pheromone_matrix(self):
        return self.__pheromoneMatrix

    def initialisation(self):
        self.__population = []
        for _ in range(self.__params['size']):
            ant = Ant(self.__params)
            self.__population.append(ant)

    def run(self):
        for i in range(1, self.__params['no_nodes']):
            for ant in range(self.__params['size']):
                neighbour = self.select(ant)

                self.__population[ant].path = self.__population[ant].path + [neighbour]

                pos1 = self.population[ant].path[-1]
                pos2 = self.population[ant].path[-2]
                if i == self.__params['no_nodes'] - 1:
                    pos1 = self.population[ant].path[0]
                    pos2 = self.population[ant].path[-1]

                self.pheromone_matrix[pos1 - 1][pos2 - 1] = (1.0 - self.__params['ro']) * \
                                                            self.pheromone_matrix[pos1 - 1][pos2 - 1] + self.__params[
                                                                'ro'] * self.__params['pheromone']
                self.pheromone_matrix[pos2 - 1][pos1 - 1] = (1.0 - self.__params['ro']) * \
                                                            self.pheromone_matrix[pos2 - 1][pos1 - 1] + self.__params[
                                                                'ro'] * self.__params['pheromone']

        for i in range(self.__params['size']):
            self.population[i].distance = self.calculate_distance(self.population[i].path)

    def select(self, k):
        neighbours = self.get_unvisited_neighbours(k)
        node = self.population[k].path[-1]
        maxim = 0.0
        selected = neighbours[0]
        for i in neighbours:
            edge_pheromone = (self.pheromone_matrix[i - 1][node - 1] ** 2) * ((1.0 / self.__params['distances'][i - 1][node - 1]) ** 5)
            if edge_pheromone > maxim:
                maxim = edge_pheromone
                selected = i
        return selected

    def update_pheromone(self):
        path = self.best_solution().path
        for i in range(len(path) - 1):
            self.pheromone_matrix[path[i] - 1][path[i + 1] - 1] = (1.0 - self.__params['ro']) * self.pheromone_matrix[path[i] - 1][path[i + 1] - 1] + self.__params['ro'] * (1.0 / self.calculate_distance(self.best_solution().path))
            self.pheromone_matrix[path[i + 1] - 1][path[i] - 1] = (1.0 - self.__params['ro']) * self.pheromone_matrix[path[i + 1] - 1][path[i] - 1] + self.__params['ro'] * (1.0 / self.calculate_distance(self.best_solution().path))

        self.pheromone_matrix[path[0] - 1][path[-1] - 1] = (1.0 - self.__params['ro']) * self.pheromone_matrix[path[0] - 1][path[-1] - 1] + self.__params['ro'] * (1.0 / self.calculate_distance(self.best_solution().path))
        self.pheromone_matrix[path[-1] - 1][path[0] - 1] = (1.0 - self.__params['ro']) * self.pheromone_matrix[path[-1] - 1][path[0] - 1] + self.__params['ro'] * (1.0 / self.calculate_distance(self.best_solution().path))

    def get_unvisited_neighbours(self, k):
        node = self.population[k].path[-1]
        neighbours = []
        for i in range(self.__params['no_nodes']):
            if self.__params['distances'][node - 1][i] != 0 and i + 1 not in self.__population[k].path:
                neighbours.append(i + 1)
        return neighbours

    def best_solution(self):
        minim = self.population[0].distance
        index = 0
        for i in range(1, self.__params['size']):
            if self.population[i].distance < minim:
                minim = self.population[i].distance
                index = i
        return self.population[index]

    def calculate_distance(self, path):
        distance = 0
        for i in range(len(path) - 1):
            distance += self.__params['distances'][path[i] - 1][path[i + 1] - 1]
        distance += self.__params['distances'][path[-1] - 1][path[0] - 1]
        return distance

    def dynamic(self):
        value = randint(self.__params['min'], self.__params['max'])

        node1 = randint(0, self.__params['no_nodes'] - 1)
        node2 = randint(0, self.__params['no_nodes'] - 1)
        while node1 == node2:
            node1 = randint(0, self.__params['no_nodes'] - 1)
            node2 = randint(0, self.__params['no_nodes'] - 1)

        self.__params['distances'][node1][node2] = value
        self.__params['distances'][node2][node1] = value

        print('New cost for edge: ', node1, " , ", node2, " = ", value)
