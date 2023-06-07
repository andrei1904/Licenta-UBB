import sys


class TSP:
    def __init__(self, input_data):
        self.number_of_cities = input_data[0]
        self.distances = input_data[1]
        self.source = input_data[2] - 1
        self.destination = input_data[3] - 1

    def tsp(self, distances, source, destination):
        visited = [False] * self.number_of_cities
        path = [source]
        total_distance = 0
        vertex = source
        nr = 0

        while vertex != destination and nr < self.number_of_cities - 1:
            visited[vertex] = True
            minimum = sys.maxsize
            next_vertex = -1

            for index in range(self.number_of_cities):
                if not visited[index] and 0 < distances[vertex][index] < minimum:
                    minimum = distances[vertex][index]
                    next_vertex = index

            if next_vertex == -1:
                return -1, -1

            vertex = next_vertex
            path.append(vertex)
            total_distance += minimum
            nr += 1

        if destination == -1:
            total_distance += distances[path[-1]][source]

        path = [vertex + 1 for vertex in path]
        return path, total_distance

    def run(self):
        res = [self.number_of_cities]

        res_path, res_distance = self.tsp(self.distances, 0, -1)
        if res_path == -1 and res_distance == -1:
            return 0
        res.append(res_path)
        res.append(res_distance)

        res_path, res_distance = self.tsp(self.distances, self.source, self.destination)
        if res_path == -1 and res_distance == -1:
            return 0
        res.append(len(res_path))
        res.append(res_path)
        res.append(res_distance)

        return res
