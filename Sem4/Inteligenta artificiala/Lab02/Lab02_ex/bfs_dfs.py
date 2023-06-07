def bfs(tree, startNode):
    visited = []
    queue = [startNode]

    while queue:
        nodCurent = queue.pop(0)
        if not visited.__contains__(nodCurent):
            visited.append(nodCurent)
            for el in tree[nodCurent]:
                queue.append(el)
    return visited


def dfs(graph, node):
    stack = [node]
    visited = []

    while stack:
        nodCurent = stack.pop(len(stack)-1)
        if not visited.__contains__(nodCurent):
            visited.append(nodCurent)
            for el in tree[nodCurent]:
                stack.append(el)
    return visited


# call the bfs method
tree = {'A': ['B', 'C'],
        'B': ['A', 'D'],
        'C': ['A', 'D', 'E'],
        'D': ['B', 'C', 'E'],
        'E': ['D', 'C', 'F'],
        'F': ['E']}
print(bfs(tree, 'A'))
print(dfs(tree, 'A'))
