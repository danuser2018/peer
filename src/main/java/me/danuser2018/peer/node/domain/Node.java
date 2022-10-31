package me.danuser2018.peer.node.domain;

import lombok.Builder;
import lombok.Value;

import java.util.function.Function;

@Value
@Builder
public class Node {
    String id;
    String host;
    int port;

    public <T> T map(Function<Node, T> mapFun) {
        return mapFun.apply(this);
    }
}
