package me.danuser2018.peer.node.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class NodeService {

    private final NodePort nodePort;

    @Autowired
    public NodeService(NodePort nodePort) {
        this.nodePort = nodePort;
    }

    public List<Node> addNode(Node node) {
        Predicate<Node> nodeMatcher = n -> node.getId().equals(n.getId());

        List<Node> currentNodes = nodePort.getAll();
        if (currentNodes.stream().noneMatch(nodeMatcher)) {
            nodePort.addNode(node);
        } else {
            currentNodes = currentNodes.stream()
                    .filter(nodeMatcher.negate())
                    .collect(Collectors.toList());
        }

        currentNodes.add(nodePort.self());
        return currentNodes;
    }
}
