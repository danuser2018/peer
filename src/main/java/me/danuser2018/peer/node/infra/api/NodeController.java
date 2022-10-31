package me.danuser2018.peer.node.infra.api;

import me.danuser2018.peer.node.domain.Node;
import me.danuser2018.peer.node.domain.NodeService;
import me.danuser2018.peer.node.infra.api.model.NodeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class NodeController {

    private final NodeService nodeService;

    @Autowired
    public NodeController(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    @PostMapping("/nodes")
    public ResponseEntity<List<NodeData>> createNode(@RequestBody NodeData nodeRequest) {
        List<Node> nodes = nodeService.addNode(nodeRequest.map(this::mapToNode));
        return ResponseEntity.ok(nodes.stream().map(this::mapToNodeData).collect(Collectors.toList()));
    }

    private Node mapToNode(NodeData nodeData) {
        return Node.builder()
                .id(nodeData.getId())
                .host(nodeData.getHost())
                .port(nodeData.getPort())
                .build();
    }

    private NodeData mapToNodeData(Node node) {
        return NodeData.builder()
                .id(node.getId())
                .host(node.getHost())
                .port(node.getPort())
                .build();
    }
}
