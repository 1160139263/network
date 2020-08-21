package cn.blatter.network.service;

import cn.blatter.network.domain.Connection;

import java.util.List;

public interface ConnectionService {
    List<Connection> findAll();
}
