from chromosome import Chromosome, randint


class GA:
    def __init__(self, param=None, problem_parameters=None):
        self.__param = param
        self.__probl_param = problem_parameters
        self.__population = []

    @property
    def population(self):
        return self.__population

    def initialisation(self):
        for _ in range(0, self.__param['pop_size']):
            c = Chromosome(self.__probl_param)
            self.__population.append(c)

    def evaluation(self):
        for c in self.__population:
            c.fitness = self.__probl_param['function'](c, self.__probl_param['dist'])

    def best_chromosome(self):
        best = self.__population[0]
        for c in self.__population:
            if c.fitness > best.fitness:
                best = c
        return best

    def selection(self):
        index1 = randint(0, self.__param['pop_size'] - 1)
        index2 = randint(0, self.__param['pop_size'] - 1)
        if self.__population[index1].fitness < self.__population[index2].fitness:
            return index1
        else:
            return index2

    def one_generation_elitism(self):
        new_population = [self.best_chromosome()]
        for _ in range(self.__param['pop_size'] - 1):
            p1 = self.__population[self.selection()]
            p2 = self.__population[self.selection()]
            offspring = p1.crossover(p2)
            offspring.mutation()
            new_population.append(offspring)
        self.__population = new_population
        self.evaluation()

    def one_generation(self):
        newPop = []
        for _ in range(self.__param['pop_size']):
            p1 = self.__population[self.selection()]
            p2 = self.__population[self.selection()]
            off = p1.crossover(p2)
            off.mutation()
            newPop.append(off)
        self.__population = newPop
        self.evaluation()
