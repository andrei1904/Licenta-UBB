from random import randint
import collections


def generate_new_value(lim1, lim2):
    return randint(lim1, lim2)


def bin_to_int(x):
    val = 0
    for bit in x:
        val = val * 2 + bit
    return val


def dfs(node, communities, count, visited, neighbour):
    communities[node - 1] = count
    visited.add(node)
    for n in neighbour[node]:
        if n not in visited:
            dfs(n, communities, count, visited, neighbour)


def components(l):
    edges = []
    for i in range(0, len(l)):
        edges.append([i + 1, l[i]])

    visited = set()
    count = 0
    communities = [0 for _ in range(0, len(l))]
    neighbour = collections.defaultdict(list)

    for e in edges:
        u, v = e[0], e[1]
        neighbour[u].append(v)
        neighbour[v].append(u)

    for i in range(1, len(l) + 1):
        if i not in visited:
            count += 1
            dfs(i, communities, count, visited, neighbour)
    return communities
