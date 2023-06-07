from utils import generate_new_value, randint


class Chromosome:
    def __init__(self, problem_parameters=None):
        self.__probl_param = problem_parameters
        self.__repres = [randint(0, problem_parameters['no_dim'] - 1)
                         for _ in range(problem_parameters['no_dim'])]
        self.__fitness = 0.0

    @property
    def repres(self):
        return self.__repres

    @property
    def fitness(self):
        return self.__fitness

    @repres.setter
    def repres(self, rep=None):
        if rep is None:
            rep = []
        self.__repres = rep

    @fitness.setter
    def fitness(self, fit=0.0):
        self.__fitness = fit

    def crossover(self, c):
        index1 = randint(0, len(self.__repres) - 1)
        index2 = randint(index1, len(self.__repres) - 1)

        new_representation = []
        for i in range(index1):
            new_representation.append(self.__repres[i])
        for i in range(index1, index2):
            new_representation.append(c.__repres[i])
        for i in range(index2, len(self.__repres)):
            new_representation.append(self.__repres[i])

        offspring = Chromosome(c.__probl_param)
        offspring.repres = new_representation

        return offspring

    def mutation(self):
        index = randint(0, len(self.__repres) - 1)
        a = generate_new_value(self.__probl_param['min'], self.__probl_param['max'])
        b = generate_new_value(self.__probl_param['min'], self.__probl_param['max'])

        if a > b:
            aux = a
            a = b
            b = aux
        self.__repres[index] = generate_new_value(a, b)

    def __str__(self):
        return '\nChromo: ' + str(self.__repres) + ' has fit: ' + str(self.__fitness)

    def __repr__(self):
        return self.__str__()

    def __eq__(self, c):
        return self.__repres == c.__repres and self.__fitness == c.__fitness
