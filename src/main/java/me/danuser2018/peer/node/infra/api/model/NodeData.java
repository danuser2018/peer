package me.danuser2018.peer.node.infra.api.model;

import lombok.Builder;
import lombok.Value;

import java.util.function.Function;

@Value
@Builder
public class NodeData {
    String id;
    String host;
    int port;

    public <T> T map(Function<NodeData, T> mapFun) {
        return mapFun.apply(this);
    }
}
