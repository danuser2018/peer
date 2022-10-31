package me.danuser2018.peer.node

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PeerNodeApplication

fun main(args: Array<String>) {
	runApplication<PeerNodeApplication>(*args)
}
