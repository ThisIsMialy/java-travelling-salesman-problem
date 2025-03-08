# java-travelling-salesman-problem
The Travelling Salesman Problem solved by Heuristic Nearest Neighbour and Minimum Spanning Tree (Prim's Algorithm) in JAVA


## Algorithm 1: Heuristic Nearest Neighbour
It follows this pseudocode from Kindervater, Lenstra and Shmoys (1989)
1. Start at a given node/city
2. Find the nearest city to the current city to the tour, provided this vertex has not yet been
visited
3. Repeat step 2 until all cities have been visited
4. Return to starting city

**Time Complexity**: 𝑂(𝑁<sup>2</sup>) where N is number of cities, which is fast but the result is far from being the best

## Algorithm 2: Minimum Spanning Tree (Prim’s Algorithm)
Prim’s Algorithm based on Sedgewick and Wayne (2014):
1. Start with a city and add to tree
2. Add all cities connected to starting city to possible cities that can be added to tree
3. Add the minimum-cost city from the possible cities to the tree, provided it has not been
visited yet
4. Add the cities connected to the minimum-cost city to possible cities that can be added to
tree
5. Repeat step 3-4 until all cities are visited and connected to the tree
6. Return a list of cities in the MST

**Time Complexity**: 𝑂(𝐸 lg 𝑉) where E is the number of edges in a set. This time is achieved after looping through all vertices as starting vertex.


## Reference list
Cormen, T.H. et al. (2009) Introduction to algorithms. 3rd edn. MIT Press.
Gutin, G., Yeo, A. and Zverovich, A. (2002) ‘Traveling salesman should not be greedy: domination
analysis of greedy-type heuristics for the TSP’, Discrete Applied Mathematics, 117(1-3), pp. 81–
86. Available at: https://doi.org/10.1016/s0166-218x(01)00195-0.
Kindervater, G.A.P., Lenstra, J.K. and Shmoys, D.B. (1989) ‘The parallel complexity of TSP
heuristics’, Journal of Algorithms, 10(2), pp. 249–270. Available at:
https://doi.org/10.1016/0196-6774(89)90015-1.
Martel, C. (2002) ‘The expected complexity of Prim’s minimum spanning tree algorithm’,
Information Processing Letters, 81(4), pp. 197–201. Available at:
https://doi.org/10.1016/s0020-0190(01)00220-4.
Nedialkov, N. (2012) Traveling Salesman Problem Parallel Distributed Tree Search. Available at:
https://www.cas.mcmaster.ca/~nedialk/COURSES/4f03/tsp/tsp.pdf (Accessed: 17 November
2023).
Sedgewick, R. and Wayne, K. (2014) Algorithms. 4th edn. Addison-Wesley Professional.
