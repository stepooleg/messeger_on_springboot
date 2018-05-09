package com.exemple.msg.repositories;

import com.exemple.msg.models.MsgModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MsgRepo extends JpaRepository<MsgModel,Long> {
    List<MsgModel>findByTag(String tag);
}
