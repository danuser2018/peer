package me.danuser2018.peer.node.infra.node.memory.model;

import lombok.Builder;
import lombok.Value;

import java.util.function.Function;

@Value
@Builder
public class NodeEntity {
    String id;
    String host;
    int port;

    public <T> T map(Function<NodeEntity, T> mapFun) {
        return mapFun.apply(this);
    }
}
