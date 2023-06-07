import networkx as nx
import numpy as np
import matplotlib.pyplot as plt
import warnings
from ga import GA
from utils import components


def read_data(file_name):
    graph = nx.read_gml(file_name, label='id')
    network = {}

    if file_name == 'real/karate/karate.gml':
        mat = [[0 for _ in range(len(graph) + 1)] for _ in range(len(graph) + 1)]
    else:
        mat = [[0 for _ in range(len(graph))] for _ in range(len(graph))]

    degrees = []

    for i in graph.adj:
        adjacency = []
        for j in graph.adj[i]:
            adjacency.append(j)
            mat[i][j] = 1
            mat[j][i] = 1
        degrees.append(len(adjacency))

    network['mat'] = mat
    network['no_nodes'] = len(graph)
    network['no_edges'] = len(graph.edges)
    network['degrees'] = degrees

    return network


def modularity(communities, param):
    noNodes = param['no_nodes']
    mat = param['mat']
    degrees = param['degrees']
    noEdges = param['no_edges']
    M = 2 * noEdges
    Q = 0.0
    for i in range(0, noNodes):
        for j in range(0, noNodes):
            if communities[i] == communities[j]:
               Q += (mat[i][j] - degrees[i] * degrees[j] / M)
    return Q * 1 / M
