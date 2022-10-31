package me.danuser2018.peer.node.domain;

import java.util.List;

public interface NodePort {
    void addNode(Node node);
    List<Node> getAll();
    Node self();
}
