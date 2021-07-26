
# Socket Programming
<img alt="Java" src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white"/>

## Description
The Program utilises to TCP Sockets to communicate between a Client, and a Server. 

## Working
The client sends the server 4 values - 
- **X**, which is the adjacency matrix of a directed graph.
- **n** which is the length of the path
- Starting vertex of the path, say **B**.
- Ending vertex of the path, say **C**.

The server sends back 2 responses - 
- A response Y or N, depending on the existence of a path of length n between B and C in graph X.
- The Image of the directed graph X.

The image of the graph is generated using Java Swing, and an image file of the graph is saved as **graph.png** in the same folder as the client and server. 

## Example
### Input (Server)
>  **X**:
> |   |   |   |   |   |  
> ---- | ---- | ---- | ---- | ---- | 
> | 0 | 1 | 0 | 1 | 0 |
> | 1 | 0 | 0 | 1 | 1 |
> | 0 | 0 | 0 | 1 | 0 |
> | 0 | 0 | 0 | 0 | 0 |
> | 0 | 0 | 0 | 0 | 0 |

> **n**: 2

> **Source**: A

> **Destination**: E

### Output (Client)

```bash
  Path of length 2 exists from A to E!
  Saved Image Successfully!
```
#### graph.png
<img src="https://raw.githubusercontent.com/kirtiksingh/WebSocket-Programming/master/graph.png" width="500px"/>




