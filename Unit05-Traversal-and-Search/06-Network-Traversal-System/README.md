# Lab 06: Network Traversal & Exploration System

## 1. Problem Statement / Aim
To build a practical Network Traversal & Exploration System in Java that applies BFS for minimum-hop routing between infrastructure servers and DFS for identifying disconnected subnet components and isolated nodes in an enterprise network.

---

## 2. System Architecture & Algorithms
1. **Shortest Hop Routing (BFS):**
   - Dispatches a level-by-level breadth-first search from source router to target node.
   - Traces predecessor pointers to extract the minimal-latency hop path.
2. **Subnet & Island Discovery (DFS):**
   - Iterates through all network devices.
   - For every unvisited device, initiates depth-first component exploration to group and report mutually reachable subnets.

---

## 3. Java Implementation
Refer to [`NetworkTraversalSystem.java`](NetworkTraversalSystem.java) for the complete implementation.

---

## 4. Sample Input
- 7 Network Nodes: Gateway-Router, Core-Switch-A, Core-Switch-B, App-Server-1, Database-Server, Backup-Storage, Isolated-Test-Node
- Source: Gateway-Router, Destination: Backup-Storage

---

## 5. Sample Output
```text
==========================================================================
                NETWORK TRAVERSAL & EXPLORATION SYSTEM                    
==========================================================================
Optimal Routing Path from Gateway-Router to Backup-Storage:
Gateway-Router -> Core-Switch-A -> App-Server-1 -> Backup-Storage
Total Network Hops: 3
--------------------------------------------------------------------------
Network Connected Components Analysis:
  Subnet Component 1: [ Gateway-Router Core-Switch-A App-Server-1 Backup-Storage Database-Server Core-Switch-B ]
  Subnet Component 2: [ Isolated-Test-Node ]
Total Independent Subnets: 2
==========================================================================
```

---

## 6. Output Screenshot
*(Screenshot will be uploaded upon test execution)*
![Network Traversal System Output](output/output.png)

---

## 7. Learning Outcomes
- Applied graph traversal algorithms to solve core networking tasks (packet routing and reachability analysis).
- Combined BFS shortest-hop pathing with DFS connected-component decomposition in a unified framework.
