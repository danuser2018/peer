package me.danuser2018.peer.node.infra.node.memory;

import me.danuser2018.peer.node.domain.Node;
import me.danuser2018.peer.node.domain.NodePort;
import me.danuser2018.peer.node.infra.node.memory.model.NodeEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MemoryNodeAdapter implements NodePort {

    private final NodeEntity selfNode;
    private final List<NodeEntity> nodes = new ArrayList<>();

    public MemoryNodeAdapter(
            @Value("${self.id}") String id,
            @Value("${self.host}") String host,
            @Value("${self.port}") int port) {
        selfNode = NodeEntity.builder()
                .id(id)
                .host(host)
                .port(port)
                .build();
    }

    @Override
    public void addNode(Node node) {
        nodes.add(node.map(this::mapToNodeEntity));
    }

    @Override
    public List<Node> getAll() {
        return nodes.stream()
                .map(this::mapToNode)
                .collect(Collectors.toList());
    }

    @Override
    public Node self() {
        return selfNode.map(this::mapToNode);
    }

    private Node mapToNode(NodeEntity nodeEntity) {
        return Node.builder()
                .id(nodeEntity.getId())
                .host(nodeEntity.getHost())
                .port(nodeEntity.getPort())
                .build();
    }

    private NodeEntity mapToNodeEntity(Node node) {
        return NodeEntity.builder()
                .id(node.getId())
                .host(node.getHost())
                .port(node.getPort())
                .build();
    }
}
