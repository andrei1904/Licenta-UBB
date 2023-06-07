from tsp import TSP
import sys


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


def list_to_string(data):
    res = ""
    for x in data:
        res = res + str(x) + ","
    return res[0:-1]


def write(file_name, data_out):
    if data_out == -1:
        print("There is no road.")
        return

    file = open(file_name, "w")

    file.write(str(data_out[0]) + "\n" + list_to_string(data_out[1]) + "\n" +
               str(data_out[2]) + "\n" + str(data_out[3]) + "\n" +
               list_to_string(data_out[4]) + "\n" + str(data_out[5]))
    file.close()


if __name__ == "__main__":
    if len(sys.argv) != 3:
        print("Error")

    else:
        tsp = TSP(read(sys.argv[1]))
        write(sys.argv[2], tsp.run())
