from utils import generate_new_value, randint, generate_random_permutation


class Chromosome:
    def __init__(self, problem_parameters=None):
        self.__probl_param = problem_parameters
        self.__repres = generate_random_permutation(self.__probl_param['no_nodes'])
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
        pos1 = randint(-1, self.__probl_param['no_nodes'] - 1)
        pos2 = randint(-1, self.__probl_param['no_nodes'] - 1)
        if pos2 < pos1:
            pos1, pos2 = pos2, pos1
        k = 0
        newrepres = self.__repres[pos1: pos2]
        for el in c.__repres[pos2:] + c.__repres[:pos2]:
            if el not in newrepres:
                if len(newrepres) < self.__probl_param['no_nodes'] - pos1:
                    newrepres.append(el)
                else:
                    newrepres.insert(k, el)
                    k += 1

        offspring = Chromosome(self.__probl_param)
        offspring.repres = newrepres
        return offspring

    def mutation(self):
        pos1 = randint(0, self.__probl_param['no_nodes'] - 1)
        pos2 = randint(0, self.__probl_param['no_nodes'] - 1)
        if pos2 < pos1:
            pos1, pos2 = pos2, pos1
        el = self.__repres[pos2]
        del self.__repres[pos2]
        self.__repres.insert(pos1 + 1, el)

    def __str__(self):
        return '\nChromo: ' + str(self.__repres) + ' has fit: ' + str(self.__fitness)

    def __repr__(self):
        return self.__str__()

    def __eq__(self, c):
        return self.__repres == c.__repres and self.__fitness == c.__fitness
