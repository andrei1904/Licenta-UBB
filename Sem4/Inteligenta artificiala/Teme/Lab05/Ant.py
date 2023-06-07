from random import randint


class Ant:
    def __init__(self, params=None):
        self.__path = [randint(1, params['no_nodes'])]
        self.__distance = 0

    @property
    def path(self):
        return self.__path

    @property
    def distance(self):
        return self.__distance

    @path.setter
    def path(self, l=None):
        if l is None:
            l = []
        self.__path = l

    @distance.setter
    def distance(self, distance):
        self.__distance = distance
